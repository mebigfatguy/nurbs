/*
 * nurbs - a playground for messing around with nurbs
 * Copyright 2019 MeBigFatGuy.com
 * Copyright 2019 Dave Brosius
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations
 * under the License.
 */
package com.mebigfatguy.nurbs.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.mebigfatguy.nurbs.model.KnotVector;
import com.mebigfatguy.nurbs.model.NurbsMesh;
import com.mebigfatguy.nurbs.model.NurbsModel;
import com.mebigfatguy.nurbs.model.UVIndex;

public class NurbsFileReader {

    private static final String schemaFile = "/com/mebigfatguy/nurbs/io/nurbs.xsd";

    private static final Pattern point3DPattern = Pattern.compile("\\((-?[0-9]+(?:\\.[0-9]*)?),(-?[0-9]+(?:\\.[0-9]*)?),(-?[0-9]+(?:\\.[0-9]*)?)\\)");
    private static final Pattern point4DPattern = Pattern
            .compile("\\((-?[0-9]+(?:\\.[0-9]*)?),\\s*(-?[0-9]+(?:\\.[0-9]*)?),\\s*(-?[0-9]+(?:\\.[0-9]*)?),\\s*(-?[0-9]+(?:\\.[0-9]*)?)\\)");
    private Path nurbsPath;

    public NurbsFileReader(Path path) {
        nurbsPath = path;
    }

    public NurbsModel readModel() throws IOException {
        try (Reader r = Files.newBufferedReader(nurbsPath)) {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(NurbsFileReader.class.getResource(schemaFile));
            spf.setSchema(schema);

            SAXParser parser = spf.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            NurbsModel model = new NurbsModel();
            reader.setContentHandler(new NurbsHandler(model));

            reader.parse(new InputSource(r));

            return model;
        } catch (SAXException | ParserConfigurationException e) {
            throw new IOException("Failed parsing xml document: " + nurbsPath, e);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    class NurbsHandler extends DefaultHandler {

        private NurbsModel nurbsModel;
        private List<String> visitedNodes;
        private StringBuilder textContent;
        private int uOrder, vOrder;
        private int uSize, vSize;
        Map<UVIndex, double[]> activeGrid;
        List<KnotVector> activeUKnots;
        List<KnotVector> activeVKnots;

        public NurbsHandler(NurbsModel model) {
            nurbsModel = model;
            visitedNodes = new ArrayList<>();
            textContent = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            visitedNodes.add(localName);
            textContent.setLength(0);

            switch (localName) {
                case "mesh":
                    uOrder = Integer.parseInt(attributes.getValue("uOrder"));
                    vOrder = Integer.parseInt(attributes.getValue("vOrder"));
                    break;

                case "grid":
                    uSize = Integer.parseInt(attributes.getValue("uSize"));
                    vSize = Integer.parseInt(attributes.getValue("vSize"));
                    break;

                case "knots":
                    activeUKnots = new ArrayList<>();
                    activeVKnots = new ArrayList<>();
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (localName) {
                case "lookat":
                    nurbsModel.setLookAt(parse3DPoint(textContent.toString()));
                    break;

                case "lookfrom":
                    nurbsModel.setLookFrom(parse3DPoint(textContent.toString()));
                    break;

                case "mesh":
                    nurbsModel.addObject(new NurbsMesh(uOrder, vOrder, activeGrid, activeUKnots, activeVKnots));
                    activeGrid = null;
                    activeUKnots = null;
                    activeVKnots = null;
                    break;

                case "grid":
                    activeGrid = parseGrid(textContent.toString());
                    break;

                case "uvector":
                    activeUKnots.add(parseKnotVector(uOrder, textContent.toString()));
                    break;

                case "vvector":
                    activeVKnots.add(parseKnotVector(vOrder, textContent.toString()));

                    break;
            }
            visitedNodes.remove(visitedNodes.size() - 1);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            textContent.append(ch, start, length);
        }

        private double[] parse3DPoint(String pt) {
            Matcher m = point3DPattern.matcher(pt);
            if (!m.matches()) {
                throw new IllegalArgumentException(pt);
            }

            double[] pt3d = new double[3];
            for (int i = 0; i < 3; i++) {
                pt3d[i] = Double.parseDouble(m.group(i + 1));
            }
            return pt3d;
        }

        private Map<UVIndex, double[]> parseGrid(String grid) {
            Map<UVIndex, double[]> gridPoints = new HashMap<>(uSize * vSize);
            Matcher m = point4DPattern.matcher(grid);

            for (int u = 0; u < uSize; u++) {
                for (int v = 0; v < vSize; v++) {
                    if (m.find()) {
                        double[] pt = new double[4];
                        pt[0] = Double.parseDouble(m.group(1));
                        pt[1] = Double.parseDouble(m.group(2));
                        pt[2] = Double.parseDouble(m.group(3));
                        pt[3] = Double.parseDouble(m.group(4));
                        gridPoints.put(new UVIndex(u, v), pt);
                    }
                }
            }
            return gridPoints;
        }

        private KnotVector parseKnotVector(int order, String nv) {
            String[] values = nv.split("\\s*,\\s*");
            double[] knots = new double[values.length];

            int i = 0;
            for (String v : values) {
                knots[i++] = Double.parseDouble(v);
            }

            return new KnotVector(order, knots);
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
        }
    }

}
