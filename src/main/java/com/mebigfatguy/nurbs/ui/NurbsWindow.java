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
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBoxMenuItem;
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
    private Map<ZoomLevel, JMenuItem> zoomItems = new HashMap<>();

    private NurbsPanel nurbsPanel;

    public NurbsWindow() {
        setupMenus();

        addWindowListener(CLOSE_LISTENER);

        nurbsPanel = new NurbsPanel();
        setZoomLevel(ZoomLevel.ZOOM_TO_FIT);
        JScrollPane scroller = new JScrollPane(nurbsPanel);
        setContentPane(scroller);
    }

    public void setZoomLevel(ZoomLevel level) {
        JMenuItem selected = zoomItems.get(level);

        for (JMenuItem mi : zoomItems.values()) {
            mi.setSelected(mi == selected);
        }
        nurbsPanel.setZoomLevel(level);
    }

    private void setupMenus() {

        JMenuItem item;

        JMenuBar bar = new JMenuBar();
        fileMenu = new JMenu(NurbsBundle.getString(NurbsBundle.FILE_MENU));
        quitItem = new JMenuItem(NurbsBundle.getString(NurbsBundle.QUIT_ITEM));
        quitItem.addActionListener(QuitAction.get());
        fileMenu.add(quitItem);
        bar.add(fileMenu);

        editMenu = new JMenu(NurbsBundle.getString(NurbsBundle.EDIT_MENU));
        bar.add(editMenu);

        zoomMenu = new JMenu(NurbsBundle.getString(NurbsBundle.ZOOM_MENU));
        item = new JCheckBoxMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_TO_FIT_ITEM));
        item.setActionCommand(NurbsBundle.ZOOM_TO_FIT_ITEM.name());
        item.addActionListener(ZoomAction.get());
        item.setSelected(true);
        zoomMenu.add(item);
        zoomItems.put(ZoomLevel.ZOOM_TO_FIT, item);

        zoomMenu.addSeparator();

        item = new JCheckBoxMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_25_ITEM));
        item.setActionCommand(NurbsBundle.ZOOM_25_ITEM.name());
        item.addActionListener(ZoomAction.get());
        zoomMenu.add(item);
        zoomItems.put(ZoomLevel.ZOOM_25, item);

        item = new JCheckBoxMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_50_ITEM));
        item.setActionCommand(NurbsBundle.ZOOM_50_ITEM.name());
        item.addActionListener(ZoomAction.get());
        zoomMenu.add(item);
        zoomItems.put(ZoomLevel.ZOOM_50, item);

        item = new JCheckBoxMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_100_ITEM));
        item.setActionCommand(NurbsBundle.ZOOM_100_ITEM.name());
        item.addActionListener(ZoomAction.get());
        zoomMenu.add(item);
        zoomItems.put(ZoomLevel.ZOOM_100, item);

        item = new JCheckBoxMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_200_ITEM));
        item.setActionCommand(NurbsBundle.ZOOM_200_ITEM.name());
        item.addActionListener(ZoomAction.get());
        zoomMenu.add(item);
        zoomItems.put(ZoomLevel.ZOOM_200, item);

        item = new JCheckBoxMenuItem(NurbsBundle.getString(NurbsBundle.ZOOM_400_ITEM));
        item.setActionCommand(NurbsBundle.ZOOM_400_ITEM.name());
        item.addActionListener(ZoomAction.get());
        zoomMenu.add(item);
        zoomItems.put(ZoomLevel.ZOOM_400, item);

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