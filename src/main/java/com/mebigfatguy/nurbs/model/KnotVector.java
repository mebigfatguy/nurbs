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

public class KnotVector {

    private int order;
    private double[] knots;

    public KnotVector(int order, double[] knots) {
        this.order = order;
        this.knots = knots;
    }

    public double basis(int knot, double t) {
        return basis(knot, order, t);
    }

    private double basis(int knot, int order, double t) {
        if (order == 1) {
            return t >= knots[knot] && t < knots[knot + 1] ? 1 : 0;
        }

        double lhBasis = 0.0;
        double rhBasis = 0.0;
        double delta = t - knots[knot];
        if (delta != 0) {
            lhBasis = delta * basis(knot, order - 1, t) / (knots[knot + order - 1] - knots[knot]);
        }

        delta = knots[knot + order] - t;
        if (delta != 0) {
            rhBasis = delta * basis(knot + 1, order - 1, t) / (knots[knot + order] - knots[knot + 1]);
        }

        return lhBasis + rhBasis;
    }

}
