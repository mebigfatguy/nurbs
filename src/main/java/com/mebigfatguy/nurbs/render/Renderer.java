package com.mebigfatguy.nurbs.render;

import com.mebigfatguy.nurbs.model.NurbsModel;
import com.mebigfatguy.nurbs.model.NurbsObject;

public class Renderer {

	public void render(NurbsModel model) {
		
		double[][] transform = model.getTransform();
		for (NurbsObject obj : model) {
			Meshifier meshifier = MeshifierFactory.getMeshifier(obj);
			for (Mesh mesh: meshifier) {
				Triangulator triangulator = TriangulatorFactory.getTriangulator(mesh);
				for (Triangle triangle : triangulator) {
				}
			}
		}
	}
}
