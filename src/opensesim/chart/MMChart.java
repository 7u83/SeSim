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
package opensesim.chart;

import opensesim.chart.painter.ChartCrossPainter;
import opensesim.chart.painter.ChartPainter;
import opensesim.chart.painter.OHLCChartPainter;
import opensesim.chart.painter.XLegendDetail;
import opensesim.chart.painter.XLegendPainter;
import opensesim.chart.painter.YLegendPainter;
import opensesim.gui.Globals;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import opensesim.old_sesim.ChartDef;
import opensesim.old_sesim.ChartPanel;
import opensesim.old_sesim.OHLCData;
import opensesim.old_sesim.Stock;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class MMChart extends JPanel {

    Stock stock;

    /**
     * Creates new form MMChart
     */
    public MMChart() {
        stock = Globals.se.getDefaultStock();
        initComponents();
        this.em_width = 10;
        setupLayout();

    }

    ChartPanel xLegend;
    ChartPanel yLegend;
    ChartPanel mainChart;
    
    private int compression=60000;
    
    
    

    private void setupYLegend() {
        yLegend = new ChartPanel();
        Border redborder = javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0));
        yLegend.setBorder(redborder);
        yLegend.setPreferredSize(new Dimension(this.em_width * 10, 110));
        yLegend.setMinimumSize(new Dimension(em_width * 10, 110));

        GridBagConstraints gbConstraints;
        gbConstraints = new java.awt.GridBagConstraints();
        gbConstraints.gridx = 1;
        gbConstraints.gridy = 0;
        gbConstraints.fill = GridBagConstraints.BOTH;
        gbConstraints.weightx = 0.0;
        gbConstraints.weighty = 1.0;

        add(yLegend, gbConstraints);
        this.addMouseMotionListener(yLegend);
        
        OHLCChartPainter ylp = new YLegendPainter(/*null*/);
        OHLCData mydata = stock.getOHLCdata(compression);

        ylp.setOHLCData(mydata);
        yLegend.setChartDef(chartDef);
        yLegend.addChartPainter(ylp);
        
    }

    private void setupXLegend() {
        xLegend = new ChartPanel();
        //     xLegend.setBackground(Color.blue);

        xLegend.setPreferredSize(new Dimension(em_width * 2, em_width * 3));
        xLegend.setMinimumSize(new Dimension(em_width * 2, em_width * 3));

        xLegend.setChartDef(chartDef);

        GridBagConstraints gbConstraints;
        gbConstraints = new java.awt.GridBagConstraints();
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 1;
        gbConstraints.fill = GridBagConstraints.BOTH;
        gbConstraints.weightx = 1.0;
        gbConstraints.weighty = 0.0;

        add(xLegend, gbConstraints);

        OHLCChartPainter p;
        OHLCData mydata = stock.getOHLCdata(compression);

        // this.xScrollBar.setMaximum(0);
        p = new XLegendPainter();
        p.setOHLCData(mydata);
        xLegend.addChartPainter(p);

        p = new XLegendDetail();
        p.setOHLCData(mydata);
        xLegend.addChartPainter(p);

        ChartPainter p0;
        p0 = new ChartCrossPainter();
        xLegend.addChartPainter(p0);
        xLegend.setChartDef(chartDef);

    }

    private void addMouseMotionListener(JPanel panel) {
        panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
    }

    private void setupMainChart() {
        mainChart = new ChartPanel();
        mainChart.setDoubleBuffered(true);
        mainChart.setBackground(Color.green);

        GridBagConstraints gbConstraints;
        gbConstraints = new java.awt.GridBagConstraints();
        gbConstraints.gridx = 0;
        gbConstraints.gridy = 0;
        gbConstraints.fill = GridBagConstraints.BOTH;
        gbConstraints.weightx = 1.0;
        gbConstraints.weighty = 1.0;

        add(mainChart, gbConstraints);

        ChartPainter p0;
        p0 = new ChartCrossPainter();
        mainChart.addChartPainter(p0);
        
        this.addMouseMotionListener(mainChart);
        
        
        mainChart.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

    }

    ChartDef chartDef;

    private void setupLayout() {
        removeAll();

        Border redborder = javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0));
        Border blueborder = javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255));

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        chartDef = new ChartDef();
        chartDef.x_unit_width = 3.0;

        setupMainChart();
        chartDef.mainChart = mainChart;
        setupYLegend();
        setupXLegend();

        java.awt.GridBagConstraints gbConstraints;

        mainChart = new ChartPanel();
        mainChart.setPreferredSize(new Dimension(100, 40));
        mainChart.setBackground(Color.blue);
    }

    int em_width;

    @Override
    public void paint(Graphics g) {
        em_width = g.getFontMetrics().stringWidth("M");
        // this.removeAll();

        // repaint();
        //  setupLayout();
        xLegend.setPreferredSize(new Dimension(em_width * 2, em_width * 3));
        xLegend.setMinimumSize(new Dimension(em_width * 2, em_width * 3));

        revalidate();
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        System.out.printf("Mouse Moved\n");
        // mainChart.repaint();
  //      xLegend.revalidate();
        xLegend.repaint();
    }//GEN-LAST:event_formMouseMoved

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.printf("The mouse was clicked\n");
    }//GEN-LAST:event_formMouseClicked

    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
        System.out.printf("Wheel!!!\n");
    }//GEN-LAST:event_formMouseWheelMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu popupMenu;
    // End of variables declaration//GEN-END:variables
}
