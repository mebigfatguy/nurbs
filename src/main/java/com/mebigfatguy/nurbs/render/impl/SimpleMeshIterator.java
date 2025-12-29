package com.mebigfatguy.nurbs.render.impl;

import java.util.Iterator;

import com.mebigfatguy.nurbs.model.NurbsObject;
import com.mebigfatguy.nurbs.render.Mesh;

public class SimpleMeshIterator implements Iterator<Mesh> {

	
    private NurbsObject object;
    private int curU;
    private int curV;

    public SimpleMeshIterator(NurbsObject object) {
    	this.object = object;
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
