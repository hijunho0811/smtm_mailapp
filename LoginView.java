package com.example.email;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.*;



public class LoginView extends JFrame{

    private JButton btnLogin;
    private JButton btnInit;
    private JPasswordField passText;
    private JTextField userText;
    private boolean bLoginCheck;
    private JCheckBox chckbxNewCheckBox;
    private JCheckBox chckbxNewCheckBox_1;



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

        chckbxNewCheckBox = new JCheckBox("Naver");
        chckbxNewCheckBox.setBounds(50, 5, 80, 25);
        panel.add(chckbxNewCheckBox);

        chckbxNewCheckBox_1 = new JCheckBox("Gmail");
        chckbxNewCheckBox_1.setBounds(150, 5, 80, 25);
        panel.add(chckbxNewCheckBox_1);

        ButtonGroup bg = new ButtonGroup(); // 체크박스 여러개 중 하나만 선택되도록 설정
        bg.add(chckbxNewCheckBox); bg.add(chckbxNewCheckBox_1);

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

        passText = new JPasswordField(20);
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
                emailg.email_guicall();
            }
        });
    }

    //임시 아이디:test 임시 비밀번호:1234
//    public void isLoginCheck(){
//        if(userText.getText().equals("test") && new String(passText.getPassword()).equals("1234")){
//            JOptionPane.showMessageDialog(null, "환영합니다.");
//            bLoginCheck = true;
//
//            // 로그인 성공이라면 매니져창 뛰우기
//            if(isLogin()){
//                main.showFrameTest(); // 메인창 메소드를 이용해 창뛰우기
//            }
//        }else{
//            JOptionPane.showMessageDialog(null, "Faild");
//        }
//    }


}