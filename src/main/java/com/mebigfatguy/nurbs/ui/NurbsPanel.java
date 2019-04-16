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
import java.awt.Point;

import javax.swing.JPanel;

public class NurbsPanel extends JPanel {

	private Point pageSize;

	public NurbsPanel() {
		pageSize = new Point(1000, 1000);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics render = g.create();
		try {
			render.setColor(Color.GRAY);
			Dimension sz = getSize();
			render.fillRect(0, 0, sz.width, sz.height);
		} finally {
			render.dispose();
		}
	}
}
