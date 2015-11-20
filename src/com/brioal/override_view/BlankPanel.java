package com.brioal.override_view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by null on 15-11-9.
 */
public class BlankPanel extends JPanel {
    private int trans = 40;
    public BlankPanel(int trans) {
        this.trans = trans;
        setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;// 转化为2d
        // 抗锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
         g2d.setColor(Color.DARK_GRAY);
            g2d.setColor(new Color(255,255,255,trans));
            g2d.setStroke(new BasicStroke(3));
            g2d.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
    }



}
