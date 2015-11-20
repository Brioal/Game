package com.brioal.socket;


import com.brioal.frames.BoradFrame;
import com.brioal.utool.Point;
import com.brioal.interfaces.Call;
import com.brioal.panels.BoradPanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
* 服务器程序，用于已服务器的方式接受与发送数据，
 * 接收传入的数据，  端口号， BoradPanel对象
 * 在以客户端形式启动的时候启动，其中的Get方法要以新建线程的方式来启动
*/
public class Server implements Call {
    private BoradPanel panel;  // Get方法中用于添加棋子的BoradPanel对象
    ServerSocket serverSocket; // 新建服务器对象
    private Socket socket = null;
    private int state_color ; // 用于存放已方所选棋子颜色

    public Server(int port, BoradPanel panel ,  int state_color) {
        this.panel = panel;
        this.state_color = state_color;
        try {
            serverSocket = new ServerSocket(port); //根据端口号创建对象
            socket = serverSocket.accept(); // 在没有客户端连接之前会一直阻塞，此处会在一个加载动画中来进行等待
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //向输入流中发送数据
    public void Send(String s) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(socket.getOutputStream()); //获取到输出流
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(s); // 写入数据
        writer.flush(); // 刷新输出流
    }

    //关闭连接
    public void Close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFirstPoint() {
        Put("10,10");
        panel.JustAdd();
    }
    //Put方法，用与发送数据，供BoradPanel来调用
    @Override
    public void Put(String s) {
//        System.out.println("Service Put" + s);
        Send(s);
    }

    //Get方法，此方法会阻塞线程，所以在创建服务器对象之后，要通过线程的方式来使用此方法
    //此方法会一直执行，获取传进的数据，并使用BoradPanel引用来添加点到面板
    @Override
    public void Get() {
        try {
            //获取输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String str = null;
                try {
                    str = reader.readLine(); // 读取内容
//                    System.out.println(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] data = str.split(","); //分割内容
                int x = Integer.valueOf(data[0]);
                int y = Integer.valueOf(data[1]);
                Point point = null;
                if (state_color == BoradFrame.STATE_BLACK) { // 如果我方为黑子。 则穿进来的子为白子
                    point = new Point(x, y, Point.STATE_WHITE) ;
                    System.out.println("Service get White");
                } else {
                    point = new Point(x, y, Point.STATE_BLACK);
                    System.out.println("Service get Blank");

                }
                panel.addPoint(point);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
