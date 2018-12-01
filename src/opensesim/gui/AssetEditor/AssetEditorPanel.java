/*
 * Copyright (c) 2018, 7u83 <7u83@mail.ru>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package opensesim.gui.AssetEditor;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import opensesim.AbstractAsset;
import opensesim.gui.Globals;
import opensesim.gui.util.JTextFieldLimit;
import opensesim.gui.util.Json.Export;
import opensesim.gui.util.Json.Import;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class AssetEditorPanel extends javax.swing.JPanel {


 

    
    ArrayList<Class<AbstractAsset>> asset_types;

    /**
     * Creates new form AssetEditor
     */
    public AssetEditorPanel() {
        super();
        asset_types = Globals.getAvailableAssetsTypes();
        asset_types.sort(new Comparator<Class<AbstractAsset>>() {
            @Override
            public int compare(Class<AbstractAsset> o1, Class<AbstractAsset> o2) {
                AbstractAsset a1, a2;
                try {
                    a1 = o1.newInstance();
                    try {
                        try {
                            a1 = o1.getConstructor().newInstance(null);
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(AssetEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(AssetEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(AssetEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SecurityException ex) {
                        Logger.getLogger(AssetEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    a2 = o2.newInstance();
                } catch (InstantiationException | IllegalAccessException ex) {
                    Logger.getLogger(AssetEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
                    return 0;
                }

                String t1, t2;
                t1 = a1.getTypeName();
                t2 = a2.getTypeName();

                return t1.compareToIgnoreCase(t2);
            }

        });

        initComponents();
        symField.setLimit(Globals.MAX.SYMLEN);
        nameField.setLimit(Globals.MAX.NAMELEN);
    }

    void initFields(AbstractAsset asset) {
        if (asset == null) {
            return;
        }

        symField.setText(asset.getSymbol());
        nameField.setText(asset.getName());
        decimalsField.getModel().setValue(asset.getDecimals());

    }

  
    public String getNameField() {
        return nameField.getText();
    }


    public String getSymField() {
        return symField.getText();
    }
    
    @Export
    public String hallo = "hello";
    
    @Import("type")
    public void putType(String type){
        System.out.printf("Here we have a type: %s\n", type);
    }
    
    public JDialog dialog;


    ComboBoxModel getComboBoxModel() {
        ArrayList vector = new ArrayList();

        // in case asset types are not initialized return a demo 
        // combo box, so it will be displaeyd in NetBens designer
        if (asset_types == null) {
            vector.add(0, "Currency");
            vector.add(1, "Stock");

            return new DefaultComboBoxModel(vector.toArray());
        }

        int i;

        for (i = 0; i < asset_types.size(); i++) {
            AbstractAsset ait;
            Class<AbstractAsset> asset_type = asset_types.get(i);
            try {

                ait = asset_type.newInstance();
                vector.add(i, ait.getTypeName());
                //     assetTypesComboBox.addItem(ait.getTypeName());

            } catch (InstantiationException | IllegalAccessException | ClassCastException ex) {
                Logger.getLogger(AssetEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return new DefaultComboBoxModel(vector.toArray());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        assetTypesComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        symField = new opensesim.gui.util.JTextFieldLimit();
        jLabel3 = new javax.swing.JLabel();
        nameField = new opensesim.gui.util.JTextFieldLimit();
        jLabel4 = new javax.swing.JLabel();
        decimalsField = new javax.swing.JSpinner();
        guiPanel = new javax.swing.JPanel();
        defaultGuiPanel = new javax.swing.JPanel();
        label = new javax.swing.JLabel();

        jLabel1.setText("Symbol:");

        assetTypesComboBox.setModel(getComboBoxModel());
        assetTypesComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assetTypesComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Type:");

        symField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                symFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Name:");

        jLabel4.setText("Decimals:");

        decimalsField.setModel(new javax.swing.SpinnerNumberModel(0, 0, 8, 1));

        guiPanel.setLayout(new java.awt.BorderLayout());

        defaultGuiPanel.setMinimumSize(new java.awt.Dimension(360, 25));

        label.setText("no config");

        javax.swing.GroupLayout defaultGuiPanelLayout = new javax.swing.GroupLayout(defaultGuiPanel);
        defaultGuiPanel.setLayout(defaultGuiPanelLayout);
        defaultGuiPanelLayout.setHorizontalGroup(
            defaultGuiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
            .addGroup(defaultGuiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(defaultGuiPanelLayout.createSequentialGroup()
                    .addGap(0, 148, Short.MAX_VALUE)
                    .addComponent(label)
                    .addGap(0, 149, Short.MAX_VALUE)))
        );
        defaultGuiPanelLayout.setVerticalGroup(
            defaultGuiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
            .addGroup(defaultGuiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(defaultGuiPanelLayout.createSequentialGroup()
                    .addGap(0, 5, Short.MAX_VALUE)
                    .addComponent(label)
                    .addGap(0, 5, Short.MAX_VALUE)))
        );

        guiPanel.add(defaultGuiPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(symField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(assetTypesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(nameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(decimalsField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(assetTypesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(symField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(decimalsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guiPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void assetTypesComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assetTypesComboBoxActionPerformed

        int i = this.assetTypesComboBox.getSelectedIndex();
        AbstractAsset a;
        try {
            a = (AbstractAsset) asset_types.get(i).newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(AssetEditorPanel.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        JPanel gui = a.getEditGui();

        guiPanel.removeAll();
        if (gui != null) {

            guiPanel.add(gui, java.awt.BorderLayout.CENTER);
            gui.setVisible(true);

        } else {
            guiPanel.add(defaultGuiPanel, java.awt.BorderLayout.CENTER);
        }

        dialog.pack();
        dialog.revalidate();


    }//GEN-LAST:event_assetTypesComboBoxActionPerformed

    private void symFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_symFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_symFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JComboBox<String> assetTypesComboBox;
    public javax.swing.JSpinner decimalsField;
    private javax.swing.JPanel defaultGuiPanel;
    private javax.swing.JPanel guiPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel label;
    @Import("name")
    @Export("name")
    public opensesim.gui.util.JTextFieldLimit nameField;
    @Export("symbol")
    @Import("symbol")
    public opensesim.gui.util.JTextFieldLimit symField;
    // End of variables declaration//GEN-END:variables
}
