package com.brioal.frames ;

import com.brioal.interfaces.DiyViews;

import javax.swing.*;
import java.awt.event.*;

public  class BoradFrame   extends JFrame implements DiyViews{
    public static int STATE_BLACK = 1 ;
    public static int STATE_WHITE = -1 ;
    public static int STATE_SERVICE = 8 ;
    public static int STATE_CLIENT = 88 ;
    JPanel panel ;
    int mx = 0, my = 0, jfx = 0, jfy = 0;
    private int state_state , state_color ;


    public BoradFrame(JPanel panel,int state_state , int state_color) {
        this.state_state = state_state;
        this.state_color = state_color;
        this.panel = panel;
        initViews();
        setViews();
        addViews();
        addViews();
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - mx + jfx, e.getYOnScreen() - my + jfy);
            }
        });
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mx = e.getXOnScreen();
                my = e.getYOnScreen();
                jfx = getX();
                jfy = getY();
            }
        });
    }

    @Override
    public void initViews() {
    }

    @Override
    public void setViews() {
        setContentPane(panel);
//        setUndecorated(true);
        setSize(740, 740);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void addViews() {

    }


}