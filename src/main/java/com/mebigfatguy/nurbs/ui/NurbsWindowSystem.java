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

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JFrame;

public class NurbsWindowSystem {

    private static final int XOFFSET = 30;
    private static final int YOFFSET = 50;

    private static final NurbsWindowSystem WINDOW_SYSTEM = new NurbsWindowSystem();

    private ToolPalette toolPalette;
    private int nextWindow = 1;

    public static final NurbsWindowSystem get() {
        return WINDOW_SYSTEM;
    }

    private NurbsWindowSystem() {
        try {
            toolPalette = createToolPalette();
            NurbsWindow nw = createNurbsWindow(null);
            nw.setVisible(true);
            toolPalette.setVisible(true);
        } catch (IOException e) {
            // wont happen
        }
    }

    public void newWindow() {
        try {
            NurbsWindow nw = createNurbsWindow(null);
            nw.setVisible(true);
            if (toolPalette.isVisible()) {
                toolPalette.toFront();
            }
        } catch (IOException e) {
            // wont happen
        }
    }

    public void newWindow(Path file) throws IOException {
        NurbsWindow nw = createNurbsWindow(file);
        nw.setVisible(true);
        if (toolPalette.isVisible()) {
            toolPalette.toFront();
        }

    }

    public boolean closeAll() {
        for (Frame f : JFrame.getFrames()) {
            if (f.isVisible() && f instanceof NurbsWindow) {
                // check for save actions
                ((NurbsWindow) f).dispose();
            }
        }
        return true;
    }

    public NurbsWindow getTopNurbsWindow() {
        for (Frame f : JFrame.getFrames()) {
            if (f.isVisible() && f instanceof NurbsWindow && f.isFocused()) {
                return (NurbsWindow) f;
            }
        }

        return null;
    }

    private ToolPalette createToolPalette() {
        ToolPalette tp = new ToolPalette();
        tp.setLocation(new Point(XOFFSET * 2, YOFFSET * 2));
        tp.setSize(50, 50);
        return tp;
    }

    private NurbsWindow createNurbsWindow(Path p) throws IOException {
        NurbsWindow nw = new NurbsWindow(p);
        nw.setTitle(NurbsBundle.getParamString(NurbsBundle.NURBS_TITLE, nextWindow++));

        NurbsWindow top = getTopNurbsWindow();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle bounds = defaultScreen.getDefaultConfiguration().getBounds();

        if (top == null) {
            nw.setLocation(bounds.x + XOFFSET, bounds.y + YOFFSET);
            nw.setSize(bounds.width - 2 * XOFFSET, bounds.height - 2 * YOFFSET);
        } else {
            Point pt = top.getLocation();
            pt.x += XOFFSET;
            pt.y += YOFFSET;
            nw.setLocation(pt);
            Dimension size = top.getSize();
            if (size.width + pt.x > bounds.width) {
                size.width = bounds.width - pt.x;
            }
            if (size.height + pt.y > bounds.height) {
                size.height = bounds.height - pt.y;
            }

            nw.setSize(size);

        }
        return nw;
    }

}
