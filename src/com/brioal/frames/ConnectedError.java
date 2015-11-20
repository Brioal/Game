package com.brioal.frames;

import com.brioal.interfaces.DiyViews;
import com.brioal.override_view.BlankButton;
import com.brioal.override_view.ImageButton;
import com.brioal.override_view.BlankPanel;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by null on 15-11-14.
 */
public class ConnectedError extends JFrame implements DiyViews, ActionListener {

    private JLabel label_background, label_message;
    private JButton btnClose;
    private BlankPanel blankPanel = null;
    int mx = 0, my = 0, jfx = 0, jfy = 0;

    public ConnectedError() {
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


        //JButton

        btnClose = new BlankButton("     确定     ");
        //JPanel
        blankPanel = new BlankPanel(225);

        //JLabel
        label_message = new JLabel("找不到服务器，请确认Ip和端口号正确后重试！");
    }

    @Override
    public void setViews() {
        label_message.setBounds(15, 40, 340, 40);
        btnClose.setBounds(100, 100, 150, 40);
        btnClose.addActionListener(this);
    }

    @Override
    public void addViews() {
        add(label_message);
        add(btnClose);
        add(blankPanel);
//        add(label_background);
        setUndecorated(true); // 不装饰
        setSize(350, 150); // 设置窗口大小
        AWTUtilities.setWindowOpaque(this, false);
        setLocationRelativeTo(null);  //设置窗口居中
        setVisible(true);
    }

    public static void main(String[] args) {
        ConnectedError frame = new ConnectedError();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnClose) {
            ConnectedError.this.dispose();

        }
    }
}
