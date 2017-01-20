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
package gui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import sesim.AutoTrader;
import sesim.Exchange;
import traders.*;


/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class MainWin extends javax.swing.JFrame {

    static public sesim.Exchange se;
    //static sesim.Account_old myAccount;
   
    
    /**
     * Creates new form MainWin
     */
    public MainWin() {
     
        initComponents();
        this.setLocationRelativeTo(this);
        
        
            double aid1 = se.createAccount(100, 100);
        double aid2 = se.createAccount(1000, 100);

  /*      AccountData a1 = se.getAccountData(aid1);
        AccountData a2 = se.getAccountData(aid2);
         se.createOrder(aid2, Exchange.OrderType.ASK, 20, 11.9);      
        se.createOrder(aid2, Exchange.OrderType.ASK, 20, 11);
        se.createOrder(aid2, Exchange.OrderType.ASK, 10, 10);
        se.createOrder(aid2, Exchange.OrderType.ASK, 10, 9);
        se.createOrder(aid1, Exchange.OrderType.BID, 50, 11);
*/
  
        /*
        System.out.print("Exec Orders\n");
        se.executeOrders();
        System.out.print("Executed Orders\n");

        a1 = se.getAccountData(aid1);
        a2 = se.getAccountData(aid2);
        */
        
        
     
/*        
             AutoTraderLIst at = new AutoTraderLIst();
//        RandomTraderConfig_old rcfg = new RandomTraderConfig_old();
        SwitchingTraderConfig rcfg = new SwitchingTraderConfig();
        at.add(1000, rcfg, se, 100, 0);
        at.add(1000, rcfg, se, 0, 10000);

        
        SwitchingTraderConfig scfg = new SwitchingTraderConfig();
        at.add(1, scfg, se, 1000000, 0);
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
        controlPanel2 = new gui.ControlPanel();
        orderBookPanel1 = new gui.OrderBookPanel();
        fullChart2 = new chart.FullChart();
        MainMenu = new javax.swing.JMenuBar();
        FileMenu = new javax.swing.JMenu();
        FileNew = new javax.swing.JMenuItem();
        FileRun = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        viewMenu = new javax.swing.JMenu();
        traderList = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpAbout = new javax.swing.JMenuItem();

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
        getContentPane().add(fullChart2, java.awt.BorderLayout.CENTER);

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

        viewMenu.setText("View");

        traderList.setMnemonic('t');
        traderList.setText("Traders");
        traderList.setToolTipText("");
        traderList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                traderListActionPerformed(evt);
            }
        });
        viewMenu.add(traderList);

        MainMenu.add(viewMenu);

        helpMenu.setText("Help");

        helpAbout.setMnemonic('a');
        helpAbout.setText("About");
        helpAbout.setToolTipText("About this Software");
        helpAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpAboutActionPerformed(evt);
            }
        });
        helpMenu.add(helpAbout);

        MainMenu.add(helpMenu);

        setJMenuBar(MainMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FileNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileNewActionPerformed
        

    }//GEN-LAST:event_FileNewActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void FileRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FileRunActionPerformed
  //      se.start();
    }//GEN-LAST:event_FileRunActionPerformed

    private void helpAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpAboutActionPerformed
        AboutDialog d=new AboutDialog(this,true);
        d.show();
    }//GEN-LAST:event_helpAboutActionPerformed

    private void traderListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_traderListActionPerformed
        TraderListDialog tl = new TraderListDialog(this,false);
        tl.setVisible(true);
    }//GEN-LAST:event_traderListActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        se = new Exchange();
        se.timer.start();
        
        
        //RandomTraderConfig rcfg = new RandomTraderConfig();
        //RandomTrader rt = rcfg.createTrader(se, 1000, 100);
        //rt.start();
        
 //       SwitchingTraderConfig rcfg1 = new SwitchingTraderConfig();
   //    SwitchingTraderConfig rcfg1 = new SwitchingTraderConfig();
       RandomTraderConfig rcfg1 = new RandomTraderConfig();
     //  rcfg1.sell_limit[0]=-1;
     //  rcfg1.sell_limit[1]=1;
       
        AutoTrader rt1 = rcfg1.createTrader(se, 1000000, 1000000);
        se.traders.add(rt1);
        rt1.setName("Alice");
        rt1.start();
   
   
        //AutoTrader rt2 = rcfg1.createTrader(se, 1, 100);
        //rt2.start();
        
        
      //  SwitchingTraderConfig cfg = new SwitchingTraderConfig();
       RandomTraderConfig cfg= new RandomTraderConfig();
        
        for (int i=0; i<100; i++){
            AutoTrader randt = cfg.createTrader(se, 100000, 100000);
            
            se.traders.add(randt);
            randt.setName("Bob");
            randt.start();
        }
        
        
       


        
     //   at.add(10, rcfg, se, 1000000, 0);

        
/*        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                InstantiationException | IllegalAccessException e) {
        }
     */
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
        java.awt.EventQueue.invokeLater(() -> {
            new MainWin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu FileMenu;
    private javax.swing.JMenuItem FileNew;
    private javax.swing.JMenuItem FileRun;
    private javax.swing.JMenuBar MainMenu;
    private gui.ControlPanel controlPanel2;
    private chart.FullChart fullChart2;
    private javax.swing.JMenuItem helpAbout;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private gui.OrderBookPanel orderBookPanel1;
    private javax.swing.JMenuItem traderList;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
}
