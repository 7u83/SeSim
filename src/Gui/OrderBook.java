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

//import SeSim.*;
import javax.swing.AbstractListModel;
import java.util.SortedSet;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class OrderBook extends javax.swing.JPanel {

    private class SListModel extends AbstractListModel {

        SortedSet model;

        public SListModel(SortedSet set) {
            model = set;
        }

        @Override
        public Object getElementAt(int index) {

            SeSim.Order o = (SeSim.Order) model.toArray()[index];

            return "<html>"
                    + "<div style=\"border:1px solid black;\">"
                    +o.limit
                    +"</div>"
                    +"<div style=\"align:right; border:1px solid blue; \">"
                    +o.volume
                    +"</div>"
                    +"</html>"; 
            


        }

        @Override
        public int getSize() {
            return model.size();
        }

    }

    SeSim.Exchange se;

    SListModel bid;
    SListModel ask;

    public OrderBook() {
        this.se = MainWin.se;

        initComponents();

        if (this.se == null) {
            return;
        }
System.out.print("Order boo init\n");

        MainWin.myAccount.Sell(100, 20.0, MainWin.se);
        MainWin.myAccount.Sell(100, 10.0, MainWin.se);   

        bid = new SListModel(se.bid);
        BidList.setModel(bid);

        MainWin.myAccount.Buy(100, 2.0, MainWin.se);
        MainWin.myAccount.Buy(100, 1.0, MainWin.se);        
        
        ask = new SListModel(se.ask);
        //AskList.setModel(ask);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        BidList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        AskList = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(500, 262));
        setLayout(new java.awt.GridBagLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bid");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        add(jLabel1, gridBagConstraints);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ask");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(jLabel3, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(0, 226));

        BidList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        BidList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BidList.setMinimumSize(new java.awt.Dimension(52, 200));
        BidList.setName(""); // NOI18N
        BidList.setPreferredSize(new java.awt.Dimension(100, 392));
        jScrollPane1.setViewportView(BidList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(jScrollPane1, gridBagConstraints);

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("20.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        add(jLabel2, gridBagConstraints);

        jScrollPane4.setMinimumSize(new java.awt.Dimension(300, 150));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(100, 150));

        AskList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"#0010", "50.00", "100"},
                {"#0071", "120.25", "30"},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Price", "Volume"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AskList.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        AskList.setOpaque(false);
        AskList.setPreferredSize(new java.awt.Dimension(100, 72));
        AskList.setRowSelectionAllowed(false);
        jScrollPane4.setViewportView(AskList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        add(jScrollPane4, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AskList;
    private javax.swing.JList<String> BidList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
