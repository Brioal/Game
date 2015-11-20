package com.brioal.socket;

import com.brioal.panels.PlayerPanel;
import com.brioal.utool.Point;
import com.brioal.interfaces.Call;
import com.brioal.panels.BoradPanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by null on 15-11-6.
 * 客户端程序，用于已客户端的方式接受与发送数据，
 * 接收传入的数据， host地址， 端口号， BoradPanel对象
 * 在以客户端形式启动的时候启动，其中的Get方法要以新建线程的方式来启动
 */
public class Client implements Call {
    private Socket socket = null;   //Socket套接字对象
    private BoradPanel panel;       //棋盘的引用对象，用于调用addPoint方法
    private int state_color; // 用于存放已方所选棋子颜色

    public Client(String host, int port, BoradPanel panel, int state_color) { // 新建客户端程序要传入三个数据，host地址，端口号，棋盘对象
        this.panel = panel;
        this.state_color = state_color;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean TestConnection(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    //发送数据到输入流
    public void Send(String s) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(socket.getOutputStream()); //获取输出流对象
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(s); // 数据写入到输出流
        writer.flush(); // 刷新输入流
    }

    //关闭连接的方法
    public void Close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    传出数据 ， 在BoradPanel中调用，传出x+","+y的字符串
    @Override
    public void Put(String s) {
//        System.out.println("Clent_Put" + s);
        Send(s); // 调用发送数据的方法
    }

    //获取到数据，并且调用BoradPanel的addPoint方法添加穿进来的点
    @Override
    public void Get() {
        BufferedReader reader = null; // 获取读入对象
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 获取输入流
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            String str = null;
            try {
                str = reader.readLine(); //读取内容
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (str != null) {
                String[] data = str.split(",");
                int x = Integer.valueOf(data[0]);
                int y = Integer.valueOf(data[1]);

                Point point = null;
                if (state_color == 1) {
                    point = new Point(x, y, Point.STATE_WHITE);
                } else {
                    point = new Point(x, y, Point.STATE_BLACK);
                }
               panel.addPoint(point); //添加棋子到棋盘
            }

        }

    }
}
