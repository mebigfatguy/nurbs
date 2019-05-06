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

import java.util.List;
import java.util.Map;

public class NurbsMesh {

    private int uOrder;
    private int vOrder;

    private Map<UVIndex, double[]> gridPoints;
    private List<KnotVector> uKnots;
    private List<KnotVector> vKnots;

    public NurbsMesh(int u, int v, Map<UVIndex, double[]> grid, List<KnotVector> uk, List<KnotVector> vk) {
        uOrder = u;
        vOrder = v;
        gridPoints = grid;
        uKnots = uk;
        vKnots = vk;
    }
}
