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

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mebigfatguy.nurbs.io.NurbsFileReader;
import com.mebigfatguy.nurbs.model.NurbsModel;
import com.mebigfatguy.nurbs.render.Shading;
import com.mebigfatguy.nurbs.ui.actions.NewAction;
import com.mebigfatguy.nurbs.ui.actions.OpenAction;
import com.mebigfatguy.nurbs.ui.actions.QuitAction;
import com.mebigfatguy.nurbs.ui.actions.ZoomAction;

public class NurbsWindow extends JFrame {

    private static final WindowListener CLOSE_LISTENER = new CloseListener();
    private static final ComponentListener RESIZE_LISTENER = new ResizeListener();

    private JMenu fileMenu;
    private JMenuItem newItem;
    private JMenuItem openItem;
    private JMenuItem closeItem;
    private JMenuItem saveItem;
    private JMenuItem saveAsItem;
    private JMenuItem revertItem;
    private JMenuItem quitItem;

    private JMenu editMenu;
    private JMenuItem undoItem;
    private JMenuItem cutItem;
    private JMenuItem copyItem;
    private JMenuItem pasteItem;
    private JMenuItem clearItem;

    private JMenu zoomMenu;
    private Map<ZoomLevel, JMenuItem> zoomItems = new EnumMap<>(ZoomLevel.class);

    private JMenu renderMenu;
    private Map<Shading, JMenuItem> renderItems = new EnumMap<>(Shading.class);

    private NurbsPanel nurbsPanel;
    private ZoomLevel zoomLevel;
    private Shading shadingType;

    public NurbsWindow(Path p) throws IOException {

        NurbsModel model;

        if (p != null) {
            NurbsFileReader r = new NurbsFileReader(p);
            model = r.readModel();
        } else {
            model = new NurbsModel();
        }

        setupMenus();

        addWindowListener(CLOSE_LISTENER);
        addComponentListener(RESIZE_LISTENER);

        nurbsPanel = new NurbsPanel(model);
        setZoomLevel(ZoomLevel.ZOOM_TO_FIT);
        setShading(Shading.FLAT);
        JScrollPane scroller = new JScrollPane(nurbsPanel);
        setContentPane(scroller);
    }

    public void setZoomLevel(ZoomLevel level) {
        zoomLevel = level;
        JMenuItem selected = zoomItems.get(level);

        for (JMenuItem mi : zoomItems.values()) {
            mi.setSelected(mi == selected);
        }
        nurbsPanel.setZoomLevel(level);
    }

    public void setShading(Shading type) {
        shadingType = type;
        JMenuItem selected = renderItems.get(type);

        for (JMenuItem mi : zoomItems.values()) {
            mi.setSelected(mi == selected);
        }
    }

    private void setupMenus() {

        JMenuItem item;

        JMenuBar bar = new JMenuBar();
        fileMenu = new JMenu(NurbsBundle.getString(NurbsBundle.FILE_MENU));
        newItem = new JMenuItem(NurbsBundle.getString(NurbsBundle.NEW_ITEM), KeyEvent.VK_N);
        newItem.addActionListener(NewAction.get());
        newItem.setMnemonic('N');
        fileMenu.add(newItem);

        openItem = new JMenuItem(NurbsBundle.getString(NurbsBundle.OPEN_ITEM), KeyEvent.VK_O);
        openItem.addActionListener(OpenAction.get());
        openItem.setMnemonic('O');
        fileMenu.add(openItem);

        quitItem = new JMenuItem(NurbsBundle.getString(NurbsBundle.QUIT_ITEM), KeyEvent.VK_Q);
        quitItem.addActionListener(QuitAction.get());
        quitItem.setMnemonic('Q');
        quitItem.setAccelerator(KeyStroke.getKeyStroke("control Q"));
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

        renderMenu = new JMenu(NurbsBundle.getString(NurbsBundle.RENDER_MENU));

        item = new JMenuItem(NurbsBundle.getString(NurbsBundle.WIREFRAME_ITEM));
        // item.addActionListener(WireFrameAction.get());
        renderMenu.add(item);
        renderItems.put(Shading.WIRE_FRAME, item);

        item = new JMenuItem(NurbsBundle.getString(NurbsBundle.FLAT_ITEM));
        // item.addActionListener(FlatAction.get());
        renderMenu.add(item);
        renderItems.put(Shading.FLAT, item);

        item = new JMenuItem(NurbsBundle.getString(NurbsBundle.GOURAUD_ITEM));
        // item.addActionListener(GouraudAction.get());
        renderMenu.add(item);
        renderItems.put(Shading.GOURAUD, item);

        item = new JMenuItem(NurbsBundle.getString(NurbsBundle.PHONG_ITEM));
        // item.addActionListener(PhongAction.get());
        renderMenu.add(item);
        renderItems.put(Shading.PHONG, item);

        bar.add(renderMenu);

        setJMenuBar(bar);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
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

    private static class ResizeListener extends ComponentAdapter {

        @Override
        public void componentResized(ComponentEvent e) {
            NurbsWindow nw = (NurbsWindow) e.getSource();
            nw.setZoomLevel(nw.zoomLevel);
        }

    }

}
