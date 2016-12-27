/*
 * Copyright (c) 2016, 7u83 <7u83@mail.ru>
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
package Gui;

import SeSim.Exchange;
import SeSim.BuyOrder;
import javax.swing.UIManager;
import javax.swing.*;
import SeSim.*;
import Traders.*;


/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class MainWin extends javax.swing.JFrame {

    static SeSim.Exchange se;
    static SeSim.Account myAccount;
    static Traders.ManTrader myTrader;
    
    /**
     * Creates new form MainWin
     */
    public MainWin() {
        initComponents();
        
      
        //myAccount.money=1500.70;
        //myAccount.shares=250;
        
       /* class x extends Account{
            public void x(){
                tube=13;
            }
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

        jButton1 = new javax.swing.JButton();
        jMenuItem1 = new javax.swing.JMenuItem();
        controlPanel2 = new Gui.ControlPanel();
        orderBookPanel1 = new Gui.OrderBookPanel();
        chart2 = new Gui.Chart();
        MainMenu = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        FileNew = new javax.swing.JMenuItem();
        FileRun = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 480));
        getContentPane().add(controlPanel2, java.awt.BorderLayout.LINE_END);
        getContentPane().add(orderBookPanel1, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout chart2Layout = new javax.swing.GroupLayout(chart2);
        chart2.setLayout(chart2Layout);
        chart2Layout.setHorizontalGroup(
            chart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );
        chart2Layout.setVerticalGroup(
            chart2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE)
        );

        getContentPane().add(chart2, java.awt.BorderLayout.CENTER);

        FileMenu.setBackground(new java.awt.Color(254, 203, 1));
        FileMenu.setText("File");

        FileNew.setText("New");
        FileNew.setBorder(null);
        FileNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileNewActionPerformed(evt);
            }
        });
        FileMenu.add(FileNew);

        FileRun.setText("Run");
        FileRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FileRunActionPerformed(evt);
            }
        });
        FileMenu.add(FileRun);

        MainMenu.add(FileMenu);

        jMenu2.setText("Edit");
        MainMenu.add(jMenu2);

        setJMenuBar(MainMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FileNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileNewActionPerformed
        System.out.print("Menu 0 called\n");

    }//GEN-LAST:event_FileNewActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void FileRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileRunActionPerformed
        se.start();
    }//GEN-LAST:event_FileRunActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        se = new Exchange();
        myAccount = new Account(se,1000,100000000.0);
        myTrader = new Traders.ManTrader(myAccount);
        
        
        
        SeSim.SellOrder so = new SeSim.SellOrder();
        so.limit = 20.0;
        so.volume = 12;
        so.timestamp = 12;
        se.SendOrder(so);
        
        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e) {
            System.out.print("Alles muell\n");
        }
        // handle exception
        // handle exception
        // handle exception


        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 /*     try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Motif".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         */
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuItem FileNew;
    private javax.swing.JMenuItem FileRun;
    private javax.swing.JMenuBar MainMenu;
    private Gui.Chart chart2;
    private Gui.ControlPanel controlPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private Gui.OrderBookPanel orderBookPanel1;
    // End of variables declaration//GEN-END:variables
}
