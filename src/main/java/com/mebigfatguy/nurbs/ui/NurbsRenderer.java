package com.mebigfatguy.nurbs.ui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.mebigfatguy.nurbs.model.NurbsModel;

public class NurbsRenderer {

	public void render(Graphics2D g2d, NurbsModel model, double centerX, double centerY, double zoom) {
		
		AffineTransform savedTransform = setUpTransform(g2d, model, centerX, centerY, zoom);
		try {
			
		} finally {
			clearTransform(g2d, savedTransform);
		}
	}


	private AffineTransform setUpTransform(Graphics2D g2d, NurbsModel model, double centerX, double centerY, double zoom) {
		AffineTransform savedTransform = g2d.getTransform();
		AffineTransform at = new AffineTransform();
		at.translate(centerX, centerY);
		at.scale(zoom, -zoom);
		g2d.setTransform(at);
		
		return savedTransform;
	}
	
	private void clearTransform(Graphics2D g2d, AffineTransform at) {
		g2d.setTransform(at);
	}
}
