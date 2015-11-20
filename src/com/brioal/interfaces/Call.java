package com.brioal.interfaces;

/**
 * Created by null on 15-11-6.
 * <p>
 * 接口，用于方法回调，实现棋盘界面与服务器，客户端对象之间的数据传递
 *  Put方法，用于在Client或者Service方法中发送BoradPanel传出的按钮数据
 *  Get方法，启用一个始终运行的方法，用于Client或者Service接收发来的数据，
 *      并在其中调用BroadPanle 的addPoint方法添加棋子到棋盘
 *          在服务启动的时候用新建的线程来操作
 */

public interface Call {
    public void Put(String s);

    public void Get();

}
