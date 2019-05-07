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

import org.junit.Assert;
import org.junit.Test;

public class KnotVectorTest {

    private double epsilon = 0.000001;

    @Test
    public void testOpenBasis() {

        KnotVector kv = new KnotVector(3, new double[] { 0, 1, 2, 3, 4, 5, 6 });

        Assert.assertEquals(0.0, kv.basis(0, 0), epsilon);
        Assert.assertEquals(0.125, kv.basis(0, 0.5), epsilon);
        Assert.assertEquals(0.5, kv.basis(0, 0.999999999999), epsilon);

        Assert.assertEquals(0.0, kv.basis(1, 1), epsilon);
        Assert.assertEquals(0.5, kv.basis(1, 1.999999999999), epsilon);

        Assert.assertEquals(0.0, kv.basis(2, 2), epsilon);
        Assert.assertEquals(0.5, kv.basis(2, 2.999999999999), epsilon);

        Assert.assertEquals(0.0, kv.basis(3, 3), epsilon);
        Assert.assertEquals(0.5, kv.basis(3, 3.999999999999), epsilon);
    }

    @Test
    public void testNonUniformBasis() {
        KnotVector kv = new KnotVector(3, new double[] { 0, 0, 0, 1, 1, 3, 3, 3 });

        Assert.assertEquals(0.0, kv.basis(2, 0), epsilon);
        Assert.assertEquals(0.25, kv.basis(2, 0.5), epsilon);

        Assert.assertEquals(0.0, kv.basis(3, 1), epsilon);

        Assert.assertEquals(0.0, kv.basis(4, 1), epsilon);
        Assert.assertEquals(0.0625, kv.basis(4, 1.5), epsilon);
        Assert.assertEquals(0.25, kv.basis(4, 2.0), epsilon);
        Assert.assertEquals(0.5625, kv.basis(4, 2.5), epsilon);
        Assert.assertEquals(1.0, kv.basis(4, 2.999999999999), epsilon);

    }

}
