package com.example.email;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.List;

import javax.swing.*;

class Property{
    String smtp_server;
    int port;
    String id;
    String passwd;
    String mailid;
}

public class LoginView extends JFrame{

    private JButton btnLogin;
    private JButton btnInit;
    private JTextField passText;
    private JTextField userText;
    private Property p = new Property();
    private String email_type;




    public void placeLoginPanel(){

        setTitle("로그인");
        setSize(280, 170);
        setResizable(false);
        setLocation(800, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // panel

        JPanel panel = new JPanel();
        // add
        add(panel);

        // visiible
        setVisible(true);

        JCheckBox naver_check = new JCheckBox("Naver");
        naver_check.setBounds(50, 5, 80, 25);
        panel.add(naver_check);
        naver_check.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    //do something...
                    p.smtp_server = "smtp.naver.com";
                    p.port = 587;
                    email_type = "@naver.com";

                }

            }
        });

        JCheckBox gmail_check = new JCheckBox("Gmail");
        gmail_check.setBounds(150, 5, 80, 25);
        panel.add(gmail_check);
        gmail_check.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {//checkbox has been selected
                    //do something...
                    p.smtp_server = "smtp.gmail.com";
                    p.port = 465;
                    email_type = "@gmail.com";

                }

            }
        });

        ButtonGroup bg = new ButtonGroup(); // 체크박스 여러개 중 하나만 선택되도록 설정
        bg.add(naver_check);
        bg.add(gmail_check);

        panel.setLayout(null);
        JLabel userLabel = new JLabel("이메일");
        userLabel.setBounds(10, 30, 80, 25);
        panel.add(userLabel);

        JLabel passLabel = new JLabel("비밀번호");
        passLabel.setBounds(10, 60, 80, 25);
        panel.add(passLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 30, 160, 25);
        panel.add(userText);

        passText = new JTextField(20);
        passText.setBounds(100, 60, 160, 25);
        panel.add(passText);
        passText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        btnInit = new JButton("초기화");
        btnInit.setBounds(10, 100, 100, 25);
        panel.add(btnInit);
        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });

        btnLogin = new JButton("로그인");
        btnLogin.setBounds(160, 100, 100, 25);
        panel.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "환영합니다.");
                EmailGUI emailg = new EmailGUI();
                dispose();
                p.id = userText.getText();
                p.passwd = passText.getText();
                p.mailid = p.id+email_type;
                emailg.email_guicall(p);
            }
        });
    }


}