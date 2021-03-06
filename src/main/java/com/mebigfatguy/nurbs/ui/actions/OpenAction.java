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
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mebigfatguy.nurbs.ui.NurbsWindowSystem;

public class OpenAction implements ActionListener {

    private static final OpenAction OPEN_ACTION = new OpenAction();
    private File lastDir = new File(System.getProperty("user.home"));

    private OpenAction() {
    }

    public static OpenAction get() {
        return OPEN_ACTION;
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(lastDir);
            chooser.setFileFilter(new FileFilter() {

                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(".nurbs");
                }

                @Override
                public String getDescription() {
                    return "Nurbs Files (*.nurbs)";

                }

            });
            if (chooser.showOpenDialog(NurbsWindowSystem.get().getTopNurbsWindow()) == JFileChooser.APPROVE_OPTION) {

                File f = chooser.getSelectedFile();
                lastDir = f.getParentFile();

                Path p = f.toPath();
                NurbsWindowSystem.get().newWindow(p);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
