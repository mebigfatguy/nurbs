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

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum NurbsBundle {

    // @formatter:off
	NURBS_TITLE,
	FILE_MENU,
	QUIT_ITEM,
	EDIT_MENU,
	ZOOM_MENU,
	ZOOM_TO_FIT_ITEM,
	ZOOM_25_ITEM,
	ZOOM_50_ITEM,
	ZOOM_100_ITEM,
	ZOOM_200_ITEM,
	ZOOM_400_ITEM;

	// @formatter:on

    private static final ResourceBundle bundle = ResourceBundle.getBundle("com.mebigfatguy.nurbs.nurbs");

    public static String getString(NurbsBundle b) {
        return bundle.getString(b.name());
    }

    public static String getParamString(NurbsBundle b, Object... params) {
        String pattern = getString(b);
        return MessageFormat.format(pattern, params);
    }
}
