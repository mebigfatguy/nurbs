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
