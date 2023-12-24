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
package com.mebigfatguy.nurbs.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NurbsModel implements Iterable<NurbsObject> {

    private double[] lookAt;
    private double[] lookFrom;
    private double[][] transform;
    private List<NurbsObject> objects = new ArrayList<>();

    public double[] getLookAt() {
        return lookAt;
    }

    public void setLookAt(double[] lookAt) {
        this.lookAt = lookAt;
        recreateTransform();
    }

    public double[] getLookFrom() {
        return lookFrom;
    }

    public void setLookFrom(double[] lookFrom) {
        this.lookFrom = lookFrom;
        recreateTransform();
    }

    public void addObject(NurbsObject object) {
        objects.add(object);
    }

    private void recreateTransform() {
    }

    public double[][] getTransform() {
        return transform;
    }

    @Override
	public Iterator<NurbsObject> iterator() {
		return objects.iterator();
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
