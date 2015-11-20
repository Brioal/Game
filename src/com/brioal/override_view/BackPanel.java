package com.brioal.override_view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by null on 15-11-10.
 */
public class BackPanel extends JPanel {

    public BackPanel() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ImageIcon back = new ImageIcon("drawable/background.png");
        g2d.drawImage(back.getImage(), 0, 0, 1280, 720, null);
    }


//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        BackPanel panel = new BackPanel();
//        frame.add(panel);
//        frame.setSize(1280, 720);
//        frame.setVisible(true);
//    }
}
