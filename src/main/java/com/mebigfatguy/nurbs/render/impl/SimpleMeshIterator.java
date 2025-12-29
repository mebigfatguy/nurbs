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
		return buildMesh();
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
	
	private Mesh buildMesh() {
		return null;
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
