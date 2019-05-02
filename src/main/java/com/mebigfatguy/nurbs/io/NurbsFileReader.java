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

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.mebigfatguy.nurbs.model.NurbsModel;

public class NurbsFileReader {

    private static final String schemaFile = "/com/mebigfatguy/nurbs/io/nurbs.xsd";
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

    class NurbsHandler extends DefaultHandler {

        public NurbsHandler(NurbsModel model) {

        }
    }

}