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

import com.mebigfatguy.nurbs.ui.NurbsWindowSystem;

public class NewAction implements ActionListener {

    private static final NewAction NEW_ACTION = new NewAction();

    private NewAction() {
    }

    public static NewAction get() {
        return NEW_ACTION;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NurbsWindowSystem.get().newWindow();
    }
}
