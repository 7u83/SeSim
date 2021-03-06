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
package opensesim.gui.orderbook;

import opensesim.gui.Globals;
import opensesim.gui.util.NummericCellRenderer;
import java.util.Collection;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import opensesim.world.AssetPair;
import opensesim.world.Exchange;
import opensesim.world.GodWorld;
import opensesim.world.Order;

import opensesim.world.TradingAPI;
import opensesim.util.scheduler.Event;
import opensesim.util.scheduler.EventListener;

/**
 * Displays an orderbook
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class OrderBookPanel extends javax.swing.JPanel implements EventListener {

    DefaultTableModel model;
    TableColumn trader_column = null;

    TradingAPI api = null;

    @Override
    public long receive(Event task) {

        synchronized (this) {
            if (oupdate) {
                new_oupdate = true;
                return 0;
            }
            oupdate = true;
        }

        SwingUtilities.invokeLater(() -> {
            oupdater();
        });
        return 0;
    }

    int depth = 40;

    public void setGodMode(boolean on) {
        TableColumnModel tcm = list.getColumnModel();
        if (on) {
            if (list.getColumnCount() == 3) {
                return;
            }
            tcm.addColumn(trader_column);
            tcm.moveColumn(2, 0);

        } else {
            if (list.getColumnCount() == 2) {
                return;
            }
            tcm.removeColumn(tcm.getColumn(0));
        }
    }

    GodWorld godworld;

    public void setGodWorld(GodWorld godworld) {
        // is our world already the godworld to set?
        if (this.godworld == godworld) {
            return;
        }

        this.godworld = godworld;
        Exchange ex = godworld.getDefaultExchange();
        AssetPair ap = godworld.getDefaultAssetPair();
        api = ex.getAPI(ap);
        api.addOrderBookListener(this);

    }

    public void init(GodWorld godworld, Exchange ex, AssetPair pair) {

        api = ex.getAPI(pair);
        api.addOrderBookListener(this);

        model = (DefaultTableModel) this.list.getModel();
        trader_column = list.getColumnModel().getColumn(0);
        list.getColumnModel().getColumn(1).setCellRenderer(new NummericCellRenderer(api.getAssetPair().getCurrency().getDecimals()));
        list.getColumnModel().getColumn(2).setCellRenderer(new NummericCellRenderer(api.getAssetPair().getAsset().getDecimals()));
        this.setGodMode(true);
        this.oupdater();

    }

    /**
     * Creates new form OrderBookNew
     */
    public OrderBookPanel() {

        initComponents();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // System.out.printf("Update order book\n");
                // UpdateOrderBook();
            }
        }, 1000, 1000);

    }



    long ouctr = 0;
    Order.Type type;

    private void drawBookSimple(Collection<Order> orderbook) {
        model.setRowCount(orderbook.size());
        int row = 0;
        for (Order order : orderbook) {

//            model.setValueAt(ob1.getAccount().getOwner().getName(), row, 0);

            model.setValueAt(order.getID(), row, 0);
            model.setValueAt(order.getLimit(), row, 1);
            model.setValueAt(order.getVolume(), row, 2);
            row++;
        }
    }

    private void drawBookCmplex(Collection<Order> orderbook){
                model.setRowCount(orderbook.size());

        int row = 0;

        double volume = 0.0;
        double limit = 0.0;
        Iterator<Order> it;

        for (Order order : orderbook) {

//            model.setValueAt(ob1.getAccount().getOwner().getName(), row, 0);
            model.setValueAt(order.getLimit(), row, 1);
            model.setValueAt(order.getVolume(), row, 2);
            row++;
        }

    }

    boolean oupdate = false;
    boolean new_oupdate = false;    
    void oupdater() {
        // get order book from API
        Collection<Order> orderbook = api.getOrderBook(type);

        this.drawBookSimple(orderbook);
        

        synchronized (this) {
            oupdate = new_oupdate;
            new_oupdate = false;
        }
        if (oupdate) {
            SwingUtilities.invokeLater(() -> {
                oupdater();
            });

        }

    }

//    @Override
    public void UpdateOrderBook() {

        synchronized (this) {
            if (oupdate) {
                new_oupdate = true;
                return;
            }
            oupdate = true;
        }

        SwingUtilities.invokeLater(() -> {
            oupdater();
        });

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

        list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Trader", "Price", "Volume"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Double.class
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
        jScrollPane1.setViewportView(list);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable list;
    // End of variables declaration//GEN-END:variables

}
