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
package com.mebigfatguy.nurbs.render;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mebigfatguy.nurbs.model.UVIndex;

public class Triangle {

    private Mesh mesh;
    private UVIndex[] indices;

    private Triangle(Mesh mesh, UVIndex p1, UVIndex p2, UVIndex p3) {
        this.mesh = mesh;
        indices = new UVIndex[3];
        indices[0] = p1;
        indices[1] = p2;
        indices[2] = p3;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public UVIndex[] getIndices() {
        return indices;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
