package com.mebigfatguy.nurbs.render.impl;

import java.util.Iterator;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mebigfatguy.nurbs.model.GridPoints;
import com.mebigfatguy.nurbs.model.NurbsObject;
import com.mebigfatguy.nurbs.render.Mesh;

public class SimpleMeshIterator implements Iterator<Mesh> {

	static int DEFAULT_STEPS = 10;
	
    private NurbsObject object;
    private int steps;
    
    private int curU;
    private int curV;
    private int curStepW;
    private int curStepH;

    public SimpleMeshIterator(NurbsObject object, int steps) {
    	this.object = object;
    	this.steps = steps;
    	
		GridPoints grid = object.getGridPoints();

    	curU = -1;
    	curV = grid.getHeight() - object.getVOrder();
    	curStepW = steps;
    	curStepH = steps;
    }

	@Override
	public boolean hasNext() {
		if (curStepH < steps) {
			return true;
		};
		if (curStepW < steps) {
			return true;
		};
		
		GridPoints grid = object.getGridPoints();
		
		if (curV < grid.getHeight() - object.getVOrder()) {
			return true;
		}

		if (curU < grid.getWidth() - object.getUOrder()) {
			return true;
		}
		
		return false;
	}

	@Override
	public Mesh next() {
		toNext();
		return null;
	}
	
	private void toNext() {
		if (curStepH < steps) {
			curStepH++;
			return;
		}
		
		if (curStepW < steps) {
			curStepW++;
			curStepH = 0;
			return;
		}
		
		GridPoints grid = object.getGridPoints();
		
		if (curV < grid.getHeight() - object.getVOrder()) {
			curV++;
			curStepH = 0; 
			curStepW = 0;
			return;
		}

		if (curU < grid.getWidth() - object.getUOrder()) {
			curU++;
			curV = 0;
			curStepH = 0;
			curStepW = 0;
			return;
		}
		
		throw new IllegalStateException("curU: " + curU + " curV: " + curV + " curStepW: " + curStepW + " curStepH: " + curStepH);
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
