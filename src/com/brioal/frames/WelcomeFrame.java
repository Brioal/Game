package com.brioal.frames;

import com.brioal.interfaces.DiyViews;
import com.brioal.override_view.ImageButton;
import com.brioal.override_view.BlankPanel;
import com.brioal.override_view.SelectButton;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by null on 15-11-9.
 * 欢迎界面
 */
public class WelcomeFrame extends JFrame implements DiyViews, ActionListener {
    //指定全局端口号
    public static int port = 9527;
    //全局IP地址 。默认为本地主机IP
    public static String host = "127.1.0.0";
    private JButton btnStart , btnClose; // 启动按钮
    private SelectButton selectService, selectClient, selectBlack, selectWhite; // 启动方式，颜色选择
    private JLabel lable_select_state, label_select_color; // 指示选择方式，选择颜色的按钮
    private JLabel BackLabel , label_message; //背景标签
    private ImageIcon icon = new ImageIcon("drawable/welcome.png");
    private int select_state = BoradFrame.STATE_SERVICE; // 启动方式。 默认为服务器方式
    private int select_color = BoradFrame.STATE_BLACK; // 棋子颜色，默认为黑子
    int mx = 0, my = 0, jfx = 0, jfy = 0;
    private BlankPanel blankPanel ; // 透明面板
    private int Frame_width = 1000 ;
    private int Frame_height = 500 ;

    public WelcomeFrame() {
        Font f = new Font("幼圆", Font.PLAIN, 15);
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
        //绘制背景
        BackLabel = new JLabel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                icon.paintIcon(this, g, 0, 0);
            }

        };
//        标签初始化
        lable_select_state = new JLabel("启动方式: ");
        label_select_color = new JLabel("棋子颜色：");
        //JButton
        selectService = new SelectButton("服务器");
        selectClient = new SelectButton("客户端");
        selectBlack = new SelectButton("黑棋");
        selectWhite = new SelectButton("白棋");
        btnStart = new ImageButton("开始游戏");

        btnClose = new ImageButton("  关闭  ");
        //JPanel
        blankPanel = new BlankPanel(180);
        if (select_state == 1) {
            selectService.setState("slectied");
            selectClient.setState("normal");
        } else {
            selectService.setState("normal");
            selectClient.setState("slectied");
        }
        if (select_color == 1) {
            selectBlack.setState("slectied");
            selectWhite.setState("normal");
        } else {
            selectBlack.setState("normal");
            selectWhite.setState("slectied");
        }
    }

    @Override
    public void setViews() {
//        JLabel
        lable_select_state.setBounds(169, 187, 100, 35);
        label_select_color.setBounds(169, 240, 100, 35);
//       JButton
        selectService.setBounds(260, 187, 100, 35);
        selectClient.setBounds(400, 187, 100, 35);

        selectBlack.setBounds(260, 240, 100, 35);
        selectWhite.setBounds(400, 240, 100, 35);
        btnStart.setBounds(200, 320, 100, 35);
        btnClose.setBounds(360,320,100,35);
        //JPanle
        blankPanel.setBounds(5, 5, Frame_width-5, Frame_height-5);

        selectService.addActionListener(this);
        selectClient.addActionListener(this);
        selectBlack.addActionListener(this);
        selectWhite.addActionListener(this);
        btnStart.addActionListener(this);

    }

    @Override
    public void addViews() {
        this.add(lable_select_state);
        this.add(label_select_color);
        this.add(selectService);
        this.add(selectClient);
        this.add(selectBlack);
        this.add(selectWhite);
        this.add(BackLabel);
        this.add(btnStart);
        this.add(btnClose);
//        this.add(blankPanel); // 使用透明面板
        this.add(BackLabel); // 使用图像
        setUndecorated(true); // 不装饰
        setSize(Frame_width, Frame_height); // 设置窗口大小
        AWTUtilities.setWindowOpaque(this, false);
        setLocationRelativeTo(null);  //设置窗口居中
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectService) {
            selectClient.setState("normal");
            //选择以服务器方式
            select_state = BoradFrame.STATE_SERVICE;
        }
        if (e.getSource() == selectClient) {
            selectService.setState("normal");
            //选择客户端方式
            select_state = BoradFrame.STATE_CLIENT;
        }
        if (e.getSource() == selectBlack) {
            //选择白棋模式
            selectWhite.setState("normal");
            select_color = BoradFrame.STATE_BLACK;
        }

        if (e.getSource() == selectWhite) {
            selectBlack.setState("normal");
            //选择黑棋模式
            select_color = BoradFrame.STATE_WHITE;
        }

        if (e.getSource() == btnStart) {

            //启动
            if (select_state == BoradFrame.STATE_SERVICE) {
                //服务器模式
                WaitClient frame = new WaitClient(WelcomeFrame.port, select_color);
                System.out.println("服务器模式，为黑子");
                WelcomeFrame.this.dispose();
            } else {
//                客户端模式
                ConnectService clientFrame;
                if (select_color == BoradFrame.STATE_BLACK) {
                    //如果客户端选择黑棋
                    clientFrame = new ConnectService(BoradFrame.STATE_BLACK);
                } else {
                    clientFrame = new ConnectService(BoradFrame.STATE_WHITE);
                }

                WelcomeFrame.this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        WelcomeFrame frame = new WelcomeFrame();
    }

}