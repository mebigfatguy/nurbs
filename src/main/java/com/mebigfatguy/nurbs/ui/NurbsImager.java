package com.mebigfatguy.nurbs.ui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.mebigfatguy.nurbs.model.NurbsModel;
import com.mebigfatguy.nurbs.render.Renderer;

public class NurbsImager {

	public void render(Graphics2D g2d, NurbsModel model, double centerX, double centerY, double zoom) {
		
		setUpTransform(g2d, model, centerX, centerY, zoom);
		Renderer renderer = new Renderer();
		renderer.render(model);
	}


	private void setUpTransform(Graphics2D g2d, NurbsModel model, double centerX, double centerY, double zoom) {
		AffineTransform at = new AffineTransform();
		at.translate(centerX, centerY);
		at.scale(zoom, -zoom);
		g2d.setTransform(at);
	}
}
