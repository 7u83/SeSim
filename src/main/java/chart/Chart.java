/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chart;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class Chart extends javax.swing.JPanel {

    /**
     * Creates new form Chart
     */
    public Chart() {
        initComponents();

        //Graphics g = this.getGraphics();
        //g.drawString("Hello world", 0, 0);
    }

    @Override
    public void paintComponent(Graphics go) {
        super.paintComponent(go);
        Graphics2D g=(Graphics2D)go;
        
        g.setColor(Color.BLUE);
        
        g.setBackground(Color.BLACK);
     //   g.get
       
        Rectangle bounds = g.getDeviceConfiguration().getBounds();
        System.out.print(bounds.width+"\n");
        
        //g.fillRect(0, 0, 100, 100);
        g.drawString("Hello world", 810, 10);
        g.drawLine(0,0, 800, 100);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
