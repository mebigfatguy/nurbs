package com.mebigfatguy.nurbs.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GridPoints {

	private int width;
	private int height;
	private double[][][] points;
	
	public GridPoints(int gridWidth, int gridHeight, double[][][] gridPoints) {
		width = gridWidth;
		height = gridHeight;
		points = gridPoints;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	public double[] getGridPoint(int x, int y) {
		assert(points.length > x);
		assert(points[0].length > y);
		
		return points[x][y];
	}
	
	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
