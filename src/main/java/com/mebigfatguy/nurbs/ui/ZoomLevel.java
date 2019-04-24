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
package com.mebigfatguy.nurbs.ui;

import java.awt.Dimension;

public enum ZoomLevel {
    // @formatter:off
    ZOOM_TO_FIT,
    ZOOM_25,
    ZOOM_50,
    ZOOM_100,
    ZOOM_200,
    ZOOM_400;
    // @formatter:on

    public static ZoomLevel fromActionString(String action) {
        try {
            return ZoomLevel.valueOf(action.substring(0, action.length() - "_ITEM".length()));
        } catch (Exception e) {
            return ZoomLevel.ZOOM_TO_FIT;
        }
    }

    public double getZoomLevel(Dimension pageSize, Dimension windowSize) {
        switch (this) {
            case ZOOM_25:
                return 0.25;

            case ZOOM_50:
                return 0.5;

            case ZOOM_100:
                return 1.0;

            case ZOOM_200:
                return 2.0;

            case ZOOM_400:
                return 4.0;

            case ZOOM_TO_FIT:
            default:
                double hDiff = (double) windowSize.width / pageSize.width;
                double vDiff = (double) windowSize.height / pageSize.height;
                return hDiff < vDiff ? hDiff : vDiff;
        }
    }
}
