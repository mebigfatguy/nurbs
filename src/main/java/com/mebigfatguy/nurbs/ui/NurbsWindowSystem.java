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

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class NurbsWindowSystem {

	private static final int OFFSET = 50;

	private ToolPalette toolPalette;
	private int nextWindow = 1;

	public void begin() {
		toolPalette = createToolPalette();
		NurbsWindow nw = createNurbsWindow();
		nw.setVisible(true);
		toolPalette.setVisible(true);
	}

	public void end() {
	}

	private ToolPalette createToolPalette() {
		ToolPalette tp = new ToolPalette();
		tp.setLocation(new Point(OFFSET * 2, OFFSET * 2));
		return tp;
	}

	private NurbsWindow createNurbsWindow() {
		NurbsWindow nw = new NurbsWindow();
		nw.setTitle("Nurbs - " + nextWindow);

		NurbsWindow top = getTopNurbsWindow();
		if (top == null) {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
			Rectangle bounds = defaultScreen.getDefaultConfiguration().getBounds();
			nw.setLocation(bounds.x + OFFSET, bounds.y + OFFSET);
			nw.setSize(bounds.width - 2 * OFFSET, bounds.height - 2 * OFFSET);
		} else {
			nw.setSize(top.getSize());
			Point pt = top.getLocation();
			pt.x += OFFSET;
			pt.y += OFFSET;
			nw.setLocation(pt);
		}
		return nw;
	}

	private NurbsWindow getTopNurbsWindow() {
		for (Frame f : JFrame.getFrames()) {
			if (f.isVisible() && f instanceof NurbsWindow) {
				return (NurbsWindow) f;
			}
		}

		return null;
	}
}
