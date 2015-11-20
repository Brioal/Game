package com.brioal.frames;

import com.brioal.interfaces.DiyViews;
import com.brioal.override_view.ImageButton;
import com.brioal.override_view.BlankPanel;
import com.brioal.panels.BoradPanel;
import com.brioal.socket.Client;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by null on 15-11-14.
 */
public class ConnectService extends JFrame implements DiyViews, ActionListener {
    private JLabel label_background, label_host, label_port;
    private JTextField text_host , text_port ;
    private JButton btnStart ,btnCancle;
    private ImageIcon icon = new ImageIcon("drawable/loading.png");
    int mx = 0, my = 0, jfx = 0, jfy = 0;
    private BlankPanel blankPanel = null;
    private int state_color ; // 存放使用的棋子的颜色
    private int width = 550 ;
    private int height = 550;


    //构造方法
    public ConnectService(int state_color) {
        this.state_color = state_color;
        initViews();
        setViews();
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
        Font f = new Font("幼圆", Font.PLAIN, 15);
        UIManager.put("Label.font", f);
        UIManager.put("Label.foreground", Color.WHITE);
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
        //绘制背景
        label_background = new JLabel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                icon.paintIcon(this, g, 0, 0);
            }

        };
        //JLabel
        blankPanel = new BlankPanel(180);
        label_host = new JLabel("IP地址：");
        label_port = new JLabel("端口号：");
        //JTextFiled
        text_host = new JTextField(WelcomeFrame.host);
        text_port = new JTextField(WelcomeFrame.port+"");

        //JButton
        btnStart = new ImageButton("启动游戏");
        btnCancle = new ImageButton("  取消  ");
    }

    @Override
    public void setViews() {
        setUndecorated(true);
//        blankPanel.setBounds(10, 10, 280, 180);
        //jlABEL
        label_host.setBounds(140, 130, 80, 45);
        label_port.setBounds(140, 180, 80, 45);

        //JTextFiled
        text_host.setBounds(230, 130, 100, 35);
        text_port.setBounds(230, 180, 100, 35);

        //JButton
        btnStart.setBounds(120, 300, 100, 35);
        btnStart.addActionListener(this);
        btnCancle.setBounds(230,300,100,35);
        btnCancle.addActionListener(this);
    }

    @Override
    public void addViews() {
        //JLable
        add(label_host);
        add(label_port);
        //JTextFiled
        add(text_host);
        add(text_port);
        //JButton
        add(btnStart);
        add(btnCancle);
//        this.add(blankPanel);
        this.add(label_background);
        setUndecorated(true); // 不装饰
        setSize(width, height); // 设置窗口大小
        AWTUtilities.setWindowOpaque(this, false);
        setLocationRelativeTo(null);  //设置窗口居中
        setVisible(true);
    }

    public static void main(String[] args) {
        ConnectService connectService = new ConnectService(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStart) {
            String host = WelcomeFrame.host;
            if (Client.TestConnection(host, WelcomeFrame.port)) {

                if (state_color == BoradFrame.STATE_BLACK) {
                    new Thread() {
                        @Override
                        public void run() {
                            BoradPanel panel = new BoradPanel(host, WelcomeFrame.port, BoradFrame.STATE_CLIENT, BoradFrame.STATE_BLACK);
                            BoradFrame frame = new BoradFrame(panel,BoradFrame.STATE_SERVICE,state_color);
                            frame.setTitle("客户端");
                        }
                    }.start();
                } else {
                    //客户端选择白子
                    new Thread() {
                        @Override
                        public void run() {
                            BoradPanel panel = new BoradPanel(host, WelcomeFrame.port, BoradFrame.STATE_CLIENT, BoradFrame.STATE_WHITE);
                            BoradFrame frame = new BoradFrame(panel,BoradFrame.STATE_CLIENT,state_color);
                            frame.setTitle("客户端");
                        }
                    }.start();
                }
            } else {
                ConnectedError frame = new ConnectedError();
            }
        }

        if (e.getSource() == btnCancle) {
            WelcomeFrame welcomeFrame = new WelcomeFrame();
            ConnectService.this.dispose();

        }

    }

}
