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

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import com.mebigfatguy.nurbs.ui.actions.QuitAction;
import com.mebigfatguy.nurbs.ui.actions.ZoomAction;

public class NurbsWindow extends JFrame {

    private static final WindowListener CLOSE_LISTENER = new CloseListener();

    private JMenu fileMenu;
    private JMenuItem quitItem;

    private JMenu editMenu;
    private JMenuItem undoItem;
    private JMenuItem cutItem;
    private JMenuItem copyItem;
    private JMenuItem pasteItem;
    private JMenuItem clearItem;

    private JMenu zoomMenu;
    private JMenuItem zoomToFitItem;
    private JMenuItem zoom25Item;
    private JMenuItem zoom50Item;
    private JMenuItem zoom100Item;
    private JMenuItem zoom200Item;
    private JMenuItem zoom400Item;

    public NurbsWindow() {
        setupMenus();

        addWindowListener(CLOSE_LISTENER);

        JScrollPane scroller = new JScrollPane(new NurbsPanel());
        setContentPane(scroller);
    }

    private void setupMenus() {

        JMenuBar bar = new JMenuBar();
        fileMenu = new JMenu(NurbsBundle.getString(NurbsBundle.FILE_MENU));
        quitItem = new JMenuItem(NurbsBundle.getString(NurbsBundle.QUIT_ITEM));
        quitItem.addActionListener(QuitAction.get());
        fileMenu.add(quitItem);
        bar.add(fileMenu);

        editMenu = new JMenu(NurbsBundle.getString(NurbsBundle.EDIT_MENU));
        bar.add(editMenu);

        zoomMenu = new JMenu(NurbsBundle.getString(NurbsBundle.ZOOM_MENU));
        zoomToFitItem = new JMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_TO_FIT_ITEM));
        zoomToFitItem.setActionCommand(NurbsBundle.ZOOM_TO_FIT_ITEM.name());
        zoomToFitItem.addActionListener(ZoomAction.get());
        zoomMenu.add(zoomToFitItem);
        zoomMenu.addSeparator();
        zoom25Item = new JMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_25_ITEM));
        zoom25Item.setActionCommand(NurbsBundle.ZOOM_25_ITEM.name());
        zoom25Item.addActionListener(ZoomAction.get());
        zoomMenu.add(zoom25Item);
        zoom50Item = new JMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_50_ITEM));
        zoom50Item.setActionCommand(NurbsBundle.ZOOM_50_ITEM.name());
        zoom50Item.addActionListener(ZoomAction.get());
        zoomMenu.add(zoom50Item);
        zoom100Item = new JMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_100_ITEM));
        zoom100Item.setActionCommand(NurbsBundle.ZOOM_100_ITEM.name());
        zoom100Item.addActionListener(ZoomAction.get());
        zoomMenu.add(zoom100Item);
        zoom200Item = new JMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_200_ITEM));
        zoom200Item.setActionCommand(NurbsBundle.ZOOM_200_ITEM.name());
        zoom200Item.addActionListener(ZoomAction.get());
        zoomMenu.add(zoom200Item);
        zoom400Item = new JMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_400_ITEM));
        zoom400Item.setActionCommand(NurbsBundle.ZOOM_400_ITEM.name());
        zoom400Item.addActionListener(ZoomAction.get());
        zoomMenu.add(zoom400Item);

        bar.add(zoomMenu);

        setJMenuBar(bar);
    }

    private static class CloseListener extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            e.getWindow().dispose();
            if (NurbsWindowSystem.get().getTopNurbsWindow() == null) {
                System.exit(0);
            }
        }
    }

}
