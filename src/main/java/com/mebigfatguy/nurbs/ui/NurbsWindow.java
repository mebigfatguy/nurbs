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

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

public class NurbsWindow extends JFrame {

	private JMenu fileMenu;
	private JMenu editMenu;

	public NurbsWindow() {
		setupMenus();

		JScrollPane scroller = new JScrollPane(new NurbsPanel());
		setContentPane(scroller);
	}

	private void setupMenus() {

		JMenuBar bar = new JMenuBar();
		fileMenu = new JMenu("File");
		bar.add(fileMenu);

		editMenu = new JMenu("Edit");
		bar.add(editMenu);

		setJMenuBar(bar);
	}

}
