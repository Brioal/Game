package com.brioal.utool;

/**
 * Created by null on 15-11-6.
 * 封装的棋盘中的点的属性
 * 包含三个
 * 在19*19棋盘中的x，y坐标
 * 标示己方对方的棋子颜色
 */
public class Point {
    public static int STATE_WHITE = 1000;
    public static int STATE_BLACK = 1001;
    private int x ;
    private int y ;
    private int  state ;

    public Point() {
        state = STATE_WHITE;
    }

    public Point(int x, int y, int state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return x + ":" + y + ":" + state;
    }
}
