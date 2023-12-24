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
package com.mebigfatguy.nurbs.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JViewport;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mebigfatguy.nurbs.model.NurbsModel;

public class NurbsPanel extends JPanel {

    private static final int DEFAULT_SIZE = 1000;

    private double zoomFactor;
    private Dimension pageSize;
    private NurbsModel nurbsModel;
    private NurbsImager renderer;

    public NurbsPanel(NurbsModel model) {
        zoomFactor = 1.0;
        pageSize = new Dimension(DEFAULT_SIZE, DEFAULT_SIZE);
        nurbsModel = model;
        renderer = new NurbsImager();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D render = (Graphics2D) g.create();
        try {
            render.setColor(Color.GRAY);
            JViewport viewPort = (JViewport) getParent();
            Dimension viewSize = viewPort.getSize();
            render.fillRect(0, 0, viewSize.width, viewSize.height);

            Dimension scaledPageSize = (Dimension) pageSize.clone();
            scaledPageSize.width *= zoomFactor;
            scaledPageSize.height *= zoomFactor;

            // need to scale the page
            render.setColor(Color.WHITE);

            int xOffset = (viewSize.width - scaledPageSize.width) / 2;
            int yOffset = (viewSize.height - scaledPageSize.height) / 2;
            render.fillRect(xOffset, yOffset, scaledPageSize.width, scaledPageSize.height);
            render.setColor(Color.BLACK);
            render.drawRect(xOffset, yOffset, scaledPageSize.width, scaledPageSize.height);
            
            renderer.render(render, nurbsModel, xOffset + scaledPageSize.width / 2.0, yOffset + scaledPageSize.height/2.0, zoomFactor);

        } finally {
            render.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension sz = (Dimension) pageSize.clone();
        sz.width *= zoomFactor;
        sz.height *= zoomFactor;
        return sz;
    }

    public void setZoomLevel(ZoomLevel level) {
        invalidate();
        JViewport viewPort = (JViewport) getParent();
        if (viewPort == null) {
            return;
        }

        Dimension viewSize = viewPort.getSize();
        if (viewSize.width == 0 || viewSize.height == 0) {
            viewSize.width = DEFAULT_SIZE;
            viewSize.height = DEFAULT_SIZE;
        }
        zoomFactor = level.getZoomLevel(pageSize, viewSize);
        revalidate();
        repaint();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
