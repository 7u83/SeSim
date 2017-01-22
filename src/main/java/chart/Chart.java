/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chart;

import sesim.OHLCDataItem;
import sesim.OHLCData;
import java.awt.*;
import sesim.Exchange.*;
import sesim.Quote;
import gui.MainWin;
import java.awt.geom.Rectangle2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author 7u83 <7u83@mail.ru>
 */
public class Chart extends javax.swing.JPanel implements QuoteReceiver {

    /**
     * Creates new form Chart
     */
    public Chart() {
        initComponents();
        if (MainWin.se == null) {
            return;
        }

        MainWin.se.addQuoteReceiver(this);

        //Graphics g = this.getGraphics();
        //g.drawString("Hello world", 0, 0);
    }

    int item_width = 10;
    int items = 350;
    long ntime = 0;

    OHLCData data = new OHLCData(2000);

    OHLCDataItem current = null;

    //  int min;
    // int max;
    int getY(float Y) {

        return 0;
    }

    void drawCandle(Graphics2D g, OHLCData d, int x, int y) {

    }

    class XLegendDef {

        double unit_width = 1;
        int big_tick = 10;
        long start;

        XLegendDef() {

        }

        String getAt(int unit) {
            Date date = new Date(sesim.Scheduler.timeStart + unit * 5000);
//            DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String dateFormatted = formatter.format(date);
            return dateFormatted;
        }

    }

    void drawOHLC(Graphics2D g, int x, OHLCDataItem di) {

    }

    void drawXLegend(Graphics2D g, XLegendDef xld) {

        //XLegendDef xld = new XLegendDef();
        g = (Graphics2D) g.create();

        int xl_height = 30;
        Dimension dim = this.getSize();

        int em_height = g.getFontMetrics().getHeight();
        int em_width = g.getFontMetrics().stringWidth("M");

        int y = dim.height - em_height * 3;

        g.drawLine(0, y, dim.width, y);

        int n;
        double x;

        for (n = 0, x = 0; x < dim.width; x += em_width * xld.unit_width) {

            if (n % xld.big_tick == 0) {
                g.drawLine((int) x, y, (int) x, y + em_height);
            } else {
                g.drawLine((int) x, y, (int) x, y + em_height / 2);
            }

            if (n % xld.big_tick == 0) {
                String text = "Hello";

                text = xld.getAt(n);
                int swidth = g.getFontMetrics().stringWidth(text);

                g.drawString(text, (int) x - swidth / 2, y + em_height * 2);
            }

            OHLCDataItem d;
            try {
                d = data.data.get(n);
            } catch (Exception e) {
                d = null;
            }

            n++;

        }
    }

    private void getData() {

    }

    class RenderCtx {

        Rectangle rect;
        float scaling;
        float min;
        Graphics2D g;
        float iwidth;

        float getY(float y) {
            return rect.height - ((y - min) * scaling);
        }
    }

    float getY(float y, float min, float s, Rectangle r) {

        return r.height - ((y - min) * s);
    }

    private void old_drawItem(Graphics2D g, Rectangle r, int prevx, int x, OHLCDataItem prev, OHLCDataItem item, float s, float min) {

        if (prev == null) {
            prev = item;
        }

        g.drawLine(prevx, (int) getY(prev.close, min, s, r), x, (int) getY(item.close, min, s, r));
        g.drawLine(r.x, r.height + r.y, r.x + r.width, r.height + r.y);
    }

    private void drawItem_l(RenderCtx ctx, int prevx, int x, OHLCDataItem prev, OHLCDataItem item) {

        if (prev == null) {
            prev = item;
        }
        Graphics2D g = ctx.g;

        Rectangle r = ctx.rect;
        g.drawLine(prevx, (int) ctx.getY(prev.close), x, (int) ctx.getY(item.close));
        g.drawLine(r.x, r.height + r.y, r.x + r.width, r.height + r.y);
    }

    private void drawItem(RenderCtx ctx, int prevx, int x, OHLCDataItem prev, OHLCDataItem i) {

        if (prev == null) {
            prev = i;
        }
        Graphics2D g = ctx.g;

        Rectangle r = ctx.rect;
//        g.drawLine(prevx, (int) ctx.getY(prev.close), x, (int) ctx.getY(item.close));

//        g.drawLine(x,(int)ctx.getY(i.high),x,(int)ctx.getY(i.low));
        if (i.open < i.close) {

            g.setColor(Color.BLACK);
            g.drawLine(x, (int) ctx.getY(i.close), x, (int) ctx.getY(i.high));
            g.drawLine(x, (int) ctx.getY(i.low), x, (int) ctx.getY(i.open));

            float w = ctx.iwidth;
            float h = (int) (ctx.getY(i.open) - ctx.getY(i.close));

         //   System.out.printf("CLO: %f %f \n", w, h);
            g.setColor(Color.GREEN);
            g.fillRect((int) (x - w / 2), (int) ctx.getY(i.close), (int) w, (int) h);
            g.setColor(Color.BLACK);
            g.drawRect((int) (x - w / 2), (int) ctx.getY(i.close), (int) w, (int) h);

        } else {

            g.setColor(Color.RED);
            g.drawLine(x, (int) ctx.getY(i.high), x, (int) ctx.getY(i.close));
            g.drawLine(x, (int) ctx.getY(i.open), x, (int) ctx.getY(i.low));

            float w = ctx.iwidth;
            float h = (int) (ctx.getY(i.close) - ctx.getY(i.open));

            g.fillRect((int) (x - w / 2), (int) ctx.getY(i.open), (int) w, (int) h);
            g.setColor(Color.BLACK);
            g.drawRect((int) (x - w / 2), (int) ctx.getY(i.open), (int) w, (int) h);

        }

        g.drawLine(r.x, r.height + r.y, r.x + r.width, r.height + r.y);
    }

    private void draw(Graphics2D g) {
        
        if (data == null )
            return;
        if (data.size()==0)
            return;
            
        OHLCDataItem di0 = data.get(0);
        XLegendDef xld = new XLegendDef();
        this.drawXLegend(g, xld);

        int em_height = g.getFontMetrics().getHeight();
        int em_width = g.getFontMetrics().stringWidth("M");

        this.getSize();

        int pwidth = em_width * items;
        int phight = 400;

        this.setPreferredSize(new Dimension(pwidth, phight));

        Dimension dim = this.getSize();

        Iterator<OHLCDataItem> it = data.iterator();
        OHLCDataItem prev = null;
        int myi = 0;

        RenderCtx ctx = new RenderCtx();

        Rectangle r = new Rectangle(0, 2 * em_width, pwidth, dim.height - 6 *em_width );
        ctx.rect = r;
        ctx.scaling = (float) r.height / (data.getMax() - data.getMin());
        ctx.min = data.getMin();
        ctx.g = g;
        ctx.iwidth = em_width - em_width / 5f;

        //System.out.printf("Scaling: %f  %f %f %f %f\n",diff,(float)r.height,data.getMin(),data.getMax(),yscaling);
        while (it.hasNext()) {
            OHLCDataItem di = it.next();

            int x = myi * em_width;
            this.drawItem(ctx, x - em_width, x, prev, di); //, ctx.scaling, data.getMin());

            myi++;
            prev = di;

        }

    }

    private void draw_old(Graphics2D g) {

        OHLCDataItem di0 = data.data.get(0);
        XLegendDef xld = new XLegendDef();
        this.drawXLegend(g, xld);

        this.getSize();

        int pwidth = item_width * items;
        int phight = 40;

        this.setPreferredSize(new Dimension(pwidth, phight));

        Dimension dim = this.getSize();
        //    System.out.print("Diemension "+dim.width+" "+dim.height+"\n");

        g.setColor(Color.RED);

        ArrayList<OHLCDataItem> od = data.data;

        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(3));

        Iterator<OHLCDataItem> it = od.iterator();
        int myi = 0;

        int lastx = 0;
        int lasty = 0;

        while (it.hasNext()) {
            OHLCDataItem di = it.next();

            float y = di.close;
            float max = data.getMax();
            float min = data.getMin();

            max = max / 10.0f + max;
            min = min - min / 10.0f;

            if (min == max) {
                min = y / 2;
                max = y * 2;

            }

            y -= min;
            y = dim.height - (dim.height * y / (max - min));

            int x = myi * this.item_width;
            myi++;

            g.drawLine(lastx, lasty, x, (int) y);

            lastx = x;
            lasty = (int) y;

        }

    }

    @Override
    public void paintComponent(Graphics go) {
        super.paintComponent(go);

        Graphics2D g = (Graphics2D) go;

        g.setColor(Color.GRAY);

        g.setBackground(Color.BLACK);
        //   g.get

        Rectangle bounds = g.getDeviceConfiguration().getBounds();
        // System.out.print(bounds.width + "\n");

        //g.fillRect(0, 0, 100, 100);
        Dimension d = this.getSize();

        //g.drawString("Hello world", 810, 10);
        //g.drawLine(0, 0, d.width, d.height);
        //this.setPreferredSize(new Dimension(2000,4000));
        draw(g);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(java.awt.Color.white);
        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setPreferredSize(new java.awt.Dimension(300, 300));
        setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void UpdateQuote(Quote q) {
        //    System.out.print("Quote Received\n");
//        this.realTimeAdd(q.time, (float) q.price, (float)q.volume);

        data.realTimeAdd(q.time, (float) q.price, (float) q.volume);
        //    this.invalidate();
        this.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
