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

import javax.swing.JPanel;

public class NurbsPanel extends JPanel {

    private static final int BORDER_SIZE = 20;

    private double zoomFactor;
    private Dimension pageSize;

    public NurbsPanel() {
        zoomFactor = 1.0;
        pageSize = new Dimension(1000, 1000);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics render = g.create();
        try {
            render.setColor(Color.GRAY);
            Dimension sz = getSize();
            render.fillRect(0, 0, sz.width, sz.height);

            // need to scale the page
            render.setColor(Color.WHITE);
            render.fillRect(BORDER_SIZE, BORDER_SIZE, pageSize.width, pageSize.height);
            render.setColor(Color.BLACK);
            render.drawRect(BORDER_SIZE, BORDER_SIZE, pageSize.width, pageSize.height);

        } finally {
            render.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension sz = (Dimension) pageSize.clone();
        sz.width += 2 * BORDER_SIZE;
        sz.height += 2 * BORDER_SIZE;
        sz.width *= zoomFactor;
        sz.height *= zoomFactor;
        return sz;
    }

    public void setZoomLevel(ZoomLevel level) {

    }

}
