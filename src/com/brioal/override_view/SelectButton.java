package com.brioal.override_view;

import com.brioal.interfaces.DiyViews;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by null on 15-11-13.
 */
public class SelectButton extends JButton implements DiyViews {
    private String state = "normal";
    private String content = null;
    public SelectButton(String s) {
        content = s;
        setMargin(new Insets(0,0,0,0));//设置边距
        setContentAreaFilled(false);//不绘制按钮区域
        setBorderPainted(false);//不绘制边框
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                state = "slectied";
                repaint();
            }

        });
        initViews();
        setViews();
        addViews();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString(content,20, 20);
    }

    public void setState(String state) {
        this.state = state;
        repaint();
    }

    public String getState() {
        return state;
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (state == "normal") {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(3));
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.WHITE);
            g2d.setColor(new Color(79, 196, 122));
            g2d.setStroke(new BasicStroke(3));
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        }

    }

    @Override
    public void initViews() {

    }

    @Override
    public void setViews() {

    }

    @Override
    public void addViews() {

    }
}
