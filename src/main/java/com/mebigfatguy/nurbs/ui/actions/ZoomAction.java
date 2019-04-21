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
package com.mebigfatguy.nurbs.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mebigfatguy.nurbs.ui.NurbsBundle;

public class ZoomAction implements ActionListener {

    private static final ZoomAction ZOOM_ACTION = new ZoomAction();

    private ZoomAction() {
    }

    public static ZoomAction get() {
        return ZOOM_ACTION;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (NurbsBundle.valueOf(e.getActionCommand())) {

            case ZOOM_25_ITEM:
                break;

            case ZOOM_50_ITEM:
                break;

            case ZOOM_100_ITEM:
                break;

            case ZOOM_200_ITEM:
                break;

            case ZOOM_400_ITEM:
                break;

            case ZOOM_TO_FIT_ITEM:
            default:
                break;
        }
    }

}
