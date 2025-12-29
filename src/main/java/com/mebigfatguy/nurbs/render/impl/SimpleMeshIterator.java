package com.mebigfatguy.nurbs.render.impl;

import java.util.Iterator;

import com.mebigfatguy.nurbs.model.NurbsObject;
import com.mebigfatguy.nurbs.render.Mesh;

public class SimpleMeshIterator implements Iterator<Mesh> {

	static int DEFAULT_STEPS = 10;
	
    private NurbsObject object;
    private int curU;
    private int curV;
    private int steps;

    public SimpleMeshIterator(NurbsObject object, int steps) {
    	this.object = object;
    	this.steps = steps;
    	curU = 0;
    	curV = 0;
    }

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public Mesh next() {
		return null;
	}
    
    
}
