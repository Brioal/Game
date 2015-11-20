package com.brioal.panels;

import com.brioal.frames.BoradFrame;
import com.brioal.frames.WelcomeFrame;
import com.brioal.interfaces.DiyViews;
import com.brioal.override_view.BlankPanel;
import com.brioal.override_view.ImageButton;
import com.brioal.socket.Client;
import com.brioal.socket.Server;
import com.brioal.utool.Calculate;
import com.brioal.utool.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by null on 15-11-6
 * 根据Service或者Client的Get方法来添加棋子，故在创建Service或者Client对象的时候要传入BoradPanel对象
 * 发送棋子调用的是Service或者Client的Put方法
 */
public class BoradPanel extends JPanel implements DiyViews, ActionListener {
    private java.util.List<Point> points;  //用于存放棋盘中存在的点
    private Server server = null; // Service对象
    private Client client = null; // Client对象
    Calculate calculate;
    private int state_start; //保存启动方式
    private int state_color; // 保存所用的棋子颜色
    private BlankPanel beginpanel; // 开始的提示面板
    private JLabel label_messagge; // 开始的提示信息
    private JButton btnBegin;
    private int flag = 1;

    //传入参数， ip（供客户端使用）  端口号（均使用） 启动方式（启动客户端获取服务器端）  棋子颜色（白子或黑子）
    public BoradPanel(String host, int port, int state_start, int state_color) {
        this.state_start = state_start;
        this.state_color = state_color;
        points = new ArrayList<>();
        initViews();
        setViews();
        addViews();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (state_start == BoradFrame.STATE_SERVICE) {  //如果是以服务器形式来启动
            System.out.println("以服务器形式来启动");
            calculate = new Calculate(state_color);
            server = new Server(port, BoradPanel.this, state_color);    //创建SocketService
            new Thread() {
                @Override
                public void run() {
                    server.Get(); // 在客户端连接之前会阻塞，且要持续运行来接受数据
                }
            }.start();
        } else {
            System.out.println("以客户端形式来启动");
            calculate = new Calculate(state_color);
            client = new Client(host, WelcomeFrame.port, this, state_color);
            new Thread() {
                @Override
                public void run() {
                    client.Get();
                }
            }.start();
        }

    }

    //只添加而不立即得出下一步，用于第一次添加点 只有当黑子的时候才调用
    public void JustAdd() {
        calculate.addPoint(new Point(10, 10, state_color));
        System.out.println("第一步");
        server.Put("10,10");
        Point point; // //服务器第一步。根据服务器自己选的颜色来落子
        if (state_color == BoradFrame.STATE_BLACK) {
            point = new Point(10, 10, Point.STATE_BLACK);
        } else {
            point = new Point(10, 10, Point.STATE_WHITE);
        }
        points.add(point);
    }

    //接收棋子添加到棋盘 // 需要判断所选颜色
    public void addPoint(Point point) {
        System.out.println("添加棋子"+point.getX()+"----"+point.getY());
        if (flag == 1) {
            this.remove(beginpanel);
            updateUI();
            flag++;
        }
        if (calculate.JudegeWin(point)) {
            calculate.addPoint(point);
            points.add(point);  // 添加棋子到list中
            updateUI();

            Point result = calculate.getNext();

            result.setState(state_color);
            calculate.addPoint(result);
            points.add(result);  // 添加棋子到list中
            updateUI();
            System.out.println("发送" + result.getX() + "---" + result.getY());
            if (server != null) {
                server.Put(result.getX() + "," + result.getY());
            } else {
                client.Put(result.getX() + "," + result.getY());
            }


        } else {
            System.out.println("游戏结束");
            System.exit(0);
        }





//        if (result != null) {

//            if (state_color == BoradFrame.STATE_BLACK) {
//                result.setState(Point.STATE_BLACK);
//            } else {
//                result.setState(Point.STATE_WHITE);
//            }

//        } else {
//            System.out.println("游戏结束");
//            System.exit(0);
        }



    @Override
    public void initViews() {
        Font f = new Font("幼圆", Font.PLAIN, 15);
        Font f_label = new Font("幼圆", Font.PLAIN, 17);
        UIManager.put("Label.font", f);
        UIManager.put("Label.foreground", Color.black);
        UIManager.put("Button.font", f);
        UIManager.put("Menu.font", f);
        UIManager.put("MenuItem.font", f);
        UIManager.put("List.font", f);
        UIManager.put("CheckBox.font", f);
        UIManager.put("RadioButton.font", f);
        UIManager.put("ComboBox.font", f);
        UIManager.put("TextArea.font", f);
        UIManager.put("EditorPane.font", f);
        UIManager.put("ScrollPane.font", f);
        UIManager.put("ToolTip.font", f);
        UIManager.put("TextField.font", f);
        UIManager.put("TableHeader.font", f);
        UIManager.put("Table.font", f);
        //JPanel
        beginpanel = new BlankPanel(200);

        //JLabel
        label_messagge = new JLabel();

        //JButton
        btnBegin = new ImageButton("确定");

    }

    // 组件设置
    @Override
    public void setViews() {
        beginpanel.setBounds(250, 250, 240, 240);
        beginpanel.setLayout(null);

        label_messagge.setBounds(10, 100, 220, 40);
        //Jbutton
        btnBegin.setBounds(80, 180, 80, 40);
        btnBegin.addActionListener(this);
    }

    //添加组件
    @Override
    public void addViews() {
        if (state_color == BoradFrame.STATE_BLACK) {
            label_messagge.setText("轮到您先行!\n点击确定开始游戏");
        } else {
            label_messagge.setText("请等对方先行");
        }

        beginpanel.add(label_messagge);
        if (state_start == BoradFrame.STATE_SERVICE) {
            beginpanel.add(btnBegin);
        }
        this.add(beginpanel);
        setLayout(null); // 设置布局为空
    }

    //面板绘制方法
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon board = new ImageIcon("drawable/board.png");
        g.drawImage(board.getImage(), 0, 0, 740, 740, null);
//        用于测试位置
//        ImageIcon icon1 = new ImageIcon("drawable/white.png");
//        g.drawImage(icon1.getImage(), 15,  680, 36, 36, null);
//        g.drawImage(icon1.getImage(),  (14+19*35),680-19*35,35,   35, null);
//        g.drawImage(icon1.getImage(),  14+10*34,695-10*35,35,   35, null);
//        g.drawImage(icon1.getImage(),  682,15,35,   35, null);
//        g.drawImage(icon1.getImage(),  352,15,35,   35, null);

        //循环list中的所有点，根据点的颜色属性来指定不同的图片资源
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Point point : points) {
            ImageIcon icon = null;
            if (point.getState() == Point.STATE_WHITE) {
                icon = new ImageIcon("drawable/white.png"); // 指定白旗资源
            } else {
                icon = new ImageIcon("drawable/black.png"); // 指定黑棋资源
            }
            //指定位置绘制指定图片
            //此处绘制已考虑边界，使用point在19*19棋盘中的坐标即可
//            g.drawImage(icon.getImage(), point.getX() * 34 + 14, 695-point.getY() * 35 , 36, 36, null);
            g.drawImage(icon.getImage(), point.getX() * 36 - 9, 710 - point.getY() * 36 - 5, 36, 36, null);
        }
    }

    //用于刷新 （自定义的不刷新全部的刷新方法，虽然刷新全部不会影响整体性能）
    //后期改进
    public void refresh(Graphics graphics) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBegin) {
            this.remove(beginpanel);
            updateUI();
            JustAdd();
        }
    }
}
