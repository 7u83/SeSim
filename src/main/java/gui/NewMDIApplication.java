/*
 * Copyright (c) 2017, 7u83 <7u83@mail.ru>
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
package gui;

import java.awt.Dialog;
import java.awt.Frame;
import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.JSONArray;
import org.json.JSONObject;
import sesim.AutoTrader;
import sesim.AutoTraderConfig;
import sesim.Exchange;
import traders.RandomTraderConfig;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class NewMDIApplication extends javax.swing.JFrame {

    /**
     * Creates new form NewMDIApplication
     */
    public NewMDIApplication() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.setTitle("SeSim - Stock Exchange Simmulator");
    }

    public void startTraders() {

        JSONArray tlist = Globals.getTraders();
        
        Double moneyTotal=0.0;
        Double sharesTotal=0.0;
        
        for (int i=0; i<tlist.length();i++){
            JSONObject t=tlist.getJSONObject(i);
            String strategy_name = t.getString("Strategy");
            JSONObject strategy = Globals.getStrategy(strategy_name);
            String base = strategy.getString("base");
            AutoTraderConfig ac = Globals.tloader.getStrategyBase(base);
            
            System.out.printf("Load Strat: %s\n",strategy_name);
            System.out.printf("Base %s\n", base);
            Integer count =  t.getInt("Count");
            Double shares = t.getDouble("Shares");
            Double money = t.getDouble("Money");
            
            System.out.printf("Count: %d Shares: %f Money %f\n", count,shares,money);
            
            
            
            for (int i1=0;i1<count;i1++){
                AutoTrader trader = ac.createTrader(Globals.se, strategy, money, shares);                
                Globals.se.traders.add(trader);
                trader.setName(t.getString("Name")+i1);
                
                moneyTotal+=money;
                sharesTotal+=shares;
                
    //            trader.start();
            }
            
        }
        
        Globals.se.fairValue=moneyTotal/sharesTotal;
        
        for (int i=0; i<Globals.se.traders.size(); i++){
            Globals.se.traders.get(i).start();
        }
        
      //   System.exit(0);
        
        
        /*
        AutoTraderConfig cfg1 = new RandomTraderConfig();
        AutoTrader rt1 = cfg1.createTrader(Globals.se, null, 100000, 100000);
        Globals.se.traders.add(rt1);

        rt1.setName("Alice");
        rt1.start();

        AutoTraderConfig cfg2 = new RandomTraderConfig();

        for (int i = 0; i < 700; i++) {
            AutoTrader randt = cfg2.createTrader(Globals.se, null, 100, 100);

            Globals.se.traders.add(randt);
            randt.setName("Bob");
            randt.start();
        }
        */

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        jRunButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        orderBookPanel1 = new gui.OrderBookPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chart1 = new chart.Chart();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editExchangeMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        editPreferences = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        viewClock = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Hakke");
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(800, 561));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jRunButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/run.gif"))); // NOI18N
        jRunButton.setText("Run sim!");
        jRunButton.setToolTipText("Run the simmulation");
        jRunButton.setFocusable(false);
        jRunButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRunButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRunButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(jRunButton);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/pause.gif"))); // NOI18N
        jButton2.setText("Pause");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        javax.swing.GroupLayout chart1Layout = new javax.swing.GroupLayout(chart1);
        chart1.setLayout(chart1Layout);
        chart1Layout.setHorizontalGroup(
            chart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        chart1Layout.setVerticalGroup(
            chart1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(chart1);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        editExchangeMenuItem.setMnemonic('y');
        editExchangeMenuItem.setText("Exchange ...");
        editExchangeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editExchangeMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(editExchangeMenuItem);
        editMenu.add(jSeparator1);

        pasteMenuItem.setMnemonic('s');
        pasteMenuItem.setText("Strategies ...");
        pasteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Traders ...");
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(deleteMenuItem);
        editMenu.add(jSeparator2);

        editPreferences.setMnemonic('p');
        editPreferences.setText("Preferences ...");
        editPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPreferencesActionPerformed(evt);
            }
        });
        editMenu.add(editPreferences);

        menuBar.add(editMenu);

        viewMenu.setText("View");

        jMenuItem2.setText("Traders");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        viewMenu.add(jMenuItem2);

        jMenuItem3.setText("LogWindow");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        viewMenu.add(jMenuItem3);

        viewClock.setText("Clock");
        viewClock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewClockActionPerformed(evt);
            }
        });
        viewMenu.add(viewClock);

        menuBar.add(viewMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(orderBookPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orderBookPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        Dialog d = new gui.AboutDialog(this, rootPaneCheckingEnabled);
        d.setVisible(rootPaneCheckingEnabled);

    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void jRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRunButtonActionPerformed
        this.startTraders();
        Globals.se.timer.start();
    }//GEN-LAST:event_jRunButtonActionPerformed

    private void editPreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPreferencesActionPerformed
        Globals.LOGGER.info("Edit prefs...");

        Dialog d = new gui.EditPreferencesDialog(this, rootPaneCheckingEnabled);
        d.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_editPreferencesActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        TraderListDialog d = new TraderListDialog(this, false);
        d.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Globals.se.timer.pause();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
        EditAutoTraderListDialog ed = new EditAutoTraderListDialog(this, true);
        ed.setVisible(rootPaneCheckingEnabled);


    }//GEN-LAST:event_deleteMenuItemActionPerformed

    private void pasteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMenuItemActionPerformed
        EditStrategies s = new EditStrategies(this, true);
        s.setVisible(rootPaneCheckingEnabled);
        
        
        
        
    }//GEN-LAST:event_pasteMenuItemActionPerformed

    
    private final LoggerDialog log_d = new LoggerDialog(this,false);
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        log_d.setVisible(!log_d.isShowing());
        

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
       
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        JFileChooser fc = new JFileChooser();
       
        FileNameExtensionFilter filter = new FileNameExtensionFilter("SeSim Files","sesim");
        fc.setFileFilter(filter);
        
       
        
        if (fc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION){
            return;
        }
        
        
        File f = fc.getSelectedFile();
        
        String [] e = ((FileNameExtensionFilter)fc.getFileFilter()).getExtensions();
        
        System.out.printf("Abs: %s\n", f.getAbsoluteFile());
        
        String fn=f.getAbsolutePath();
        
        
        if (!f.getAbsolutePath().endsWith(e[0])){
            f = new File(f.getAbsolutePath()+"."+e[0]);
        }
        

        Globals.saveFile(f);
        
        
        System.out.printf("Sel File: %s \n",f.getAbsolutePath());
        
        
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void editExchangeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editExchangeMenuItemActionPerformed
        EditExchangeDialog ed=new EditExchangeDialog((Frame) this.getParent(),true);
        int rc = ed.showdialog();
      //  System.out.printf("EDRET: %d\n",rc);
        
    }//GEN-LAST:event_editExchangeMenuItemActionPerformed

    private void viewClockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewClockActionPerformed
        ClockDialog cd = new ClockDialog(this,true);
        cd.setVisible(rootPaneCheckingEnabled);
        
    }//GEN-LAST:event_viewClockActionPerformed

    /**
     * @param args the command line arguments
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.InstantiationException
     */
    public static void main(String args[]) throws IllegalAccessException, InstantiationException {
        Globals.se = new Exchange();

        class Tube {

        }

        Class<?> c = sesim.Exchange.class;
        Globals.prefs = Preferences.userNodeForPackage(c);

        Globals.setLookAndFeel(Globals.prefs.get("laf", "Nimbus"));

        /*        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e) {
        }
         */
        ArrayList<Class<AutoTraderConfig>> traders;
        traders = null;

        sesim.AutoTraderLoader tl = new sesim.AutoTraderLoader();
        try {
            traders = tl.getTraders();
        } catch (Exception e) {
            System.out.print("Execption\n");
        }

        for (Class<AutoTraderConfig> at_class : traders) {
            System.out.printf("Class = %s\n",at_class.getName());
            if (Modifier.isAbstract(at_class.getModifiers())) {
                continue;
            }
            
            AutoTraderConfig cfg = at_class.newInstance();
            System.out.printf("Have a Trader with name: %s\n", cfg.getDisplayName());
        }

        //System.exit(0);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                Globals.prefs = Preferences.userNodeForPackage(this)        
                new NewMDIApplication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private chart.Chart chart1;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenuItem editExchangeMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem editPreferences;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JButton jRunButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private gui.OrderBookPanel orderBookPanel1;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem viewClock;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables

}
