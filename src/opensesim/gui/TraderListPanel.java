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
package opensesim.gui;

import opensesim.gui.util.NummericCellRenderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import opensesim.old_sesim.AutoTraderInterface;
import opensesim.old_sesim.Exchange;
import opensesim.old_sesim.Account;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class TraderListPanel extends javax.swing.JPanel {

    DefaultTableModel model;

    final void updateModel() {
        if (Globals.se == null) {
            return;
        }

        if (Globals.se.traders == null) {
            return;
        }

        opensesim.old_sesim.Quote q = Globals.se.getLastQuoete();
        double price = q == null ? 0 : q.price;

   
        int size = Globals.se.traders.size();
        model.setRowCount(size);
        for (int i = 0; i < size; i++) {
            AutoTraderInterface at = Globals.se.traders.get(i);
            Account a = at.getAccount();
            model.setValueAt(i, i, 0);
            model.setValueAt(at.getName(), i, 1);
            model.setValueAt(a.getMoney(), i, 2);
            model.setValueAt(a.getShares(), i, 3);

            double wealth = a.getShares() * price + a.getMoney();
            model.setValueAt(wealth, i, 4);
        }
        List l = list.getRowSorter().getSortKeys();
        if (l.size() > 0) {
            list.getRowSorter().allRowsChanged();
        } else {
            model.fireTableDataChanged();
        }

    }

    TimerTask updater;

    /**
     * Creates new form TraderListPanel2
     */
    public TraderListPanel() {

        initComponents();
        model = (DefaultTableModel) list.getModel();
//       updateModel();

        Timer timer = new Timer();
        updater = new TimerTask() {
            @Override
            public void run() {
                try {
                    updateModel();
                } catch (Exception e) {
                }

            }
        };
        list.getColumnModel().getColumn(2).setCellRenderer(new NummericCellRenderer(3));
        list.getColumnModel().getColumn(3).setCellRenderer(new NummericCellRenderer(0));
        list.getColumnModel().getColumn(4).setCellRenderer(new NummericCellRenderer(3));        
        timer.schedule(updater, 0, 1000);

    }

    class MyModel extends DefaultTableModel {

        MyModel(Object arg0[][], Object arg1[]) {
            super(arg0, arg1);
        }

        @Override
        public void fireTableDataChanged() {
            super.fireTableDataChanged();
        }

        @Override
        public void fireTableStructureChanged() {

        }

        @Override
        public void fireTableRowsUpdated(int firstRow, int lastRow) {
        }

        @Override
        public void fireTableCellUpdated(int row, int column) {

        }

    }

    void ZZtest() {

//        new javax.swing.table.DefaultTableModel
        MyModel m = new MyModel(
                new Object[][]{
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null},
                    {null, null, null, null, null}
                },
                new String[]{
                    "ID", "Name", "Money", "Shares", "Wealth"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Long.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JTable();

        list.setAutoCreateRowSorter(true);
        list.setModel(new MyModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Money", "Shares", "Wealth"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        list.setDoubleBuffered(true);
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(list);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        if (evt.getClickCount() == 2) {
            int index = list.rowAtPoint(evt.getPoint());

            index = list.getRowSorter().convertRowIndexToModel(index);
            Integer tid = (Integer) model.getValueAt(index, 0);
            // System.out.printf("Trader ID %d\n", tid);

            JDialog console = Globals.se.traders.get(tid).getGuiConsole();
            if (console == null) {
                return;
            }
            console.setVisible(true);

        }

    }//GEN-LAST:event_listMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable list;
    // End of variables declaration//GEN-END:variables
}
