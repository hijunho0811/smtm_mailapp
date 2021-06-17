package com.example.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.EmailAttachment;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.io.File;
import java.util.List;

public class EmailGUI extends JFrame {

    private JPanel contentPane;
    private File file_append;


    public void email_guicall(Property property) {

        Property p = property;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 576, 490);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setVisible(true);

        JTextField tf_mail = new JTextField();
        tf_mail.setBounds(103, 12, 353, 21);
        contentPane.add(tf_mail);
        tf_mail.setColumns(10);

        JLabel lblNewLabel = new JLabel("받는 사람");
        lblNewLabel.setBounds(25, 10, 78, 24);
        contentPane.add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "제목", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(19, 43, 437, 46);
        contentPane.add(panel);
        panel.setLayout(null);

        JTextField tf_title = new JTextField();
        tf_title.setBounds(6, 17, 416, 21);
        panel.add(tf_title);
        tf_title.setColumns(10);

        JScrollPane sp_mailtext = new JScrollPane();
        sp_mailtext.setBounds(25, 93, 523, 236);
        contentPane.add(sp_mailtext);

        JScrollPane dragndrop = new JScrollPane();
        dragndrop.setBounds(25, 340, 523, 100);
        contentPane.add(dragndrop);
        JTextField file_name = new JTextField();
//        file_name.setText("여기에 파일을 드랍해주세요.");
        file_name.setEditable(false);
        file_name.setText("여기에 파일을 드랍하세요.");
        dragndrop.setViewportView(file_name);

        file_name.setDropTarget(new DropTarget() {
            public void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles = (List)
                            evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        //process files
                        file_append = file;
                        if(file_append != null)
                            file_name.setText(file_append.getName());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        });

        //메일 본문 작성
        JTextArea mailtext = new JTextArea();
        sp_mailtext.setViewportView(mailtext);

        JButton btnNewButton = new JButton("전송");
        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //이메일 전송 메소드

                if (file_append != null){
                    multiPartEmail(p, tf_mail.getText(), tf_title.getText(), mailtext.getText());
                }
                else{
                    simpleEmail(p, tf_mail.getText(), tf_title.getText(), mailtext.getText());
                }

            }
        });
        btnNewButton.setBounds(468, 11, 80, 72);
        contentPane.add(btnNewButton);
    }

    public void simpleEmail(Property p, String reciever, String title, String mailtext){
        // 메일 전송기능 라이브러리 준비
        // CommonsEmail
        // http://commons.apache.org/proper/commons-email
        //  ▶ commons-email-1.5.jar

        // JavaMail
        // http://javaee.github.io/javamail
        //  ▶ mail.jar

        // JAF(java activation framework)
        //  ▶ activation.jar

        // SimpleEmail 클래스: 텍스트 이메일 전송

        // MultiPartEmail 클래스: 메시지와 첨부파일을 함께 전송
        //   - EmailAttachment 클래스: 첨부파일

        // HtmlEmail 클래스: 이메일 컨텐츠를 HTML형식으로 전송

        // 이메일 서비스는 메일 전송용 프로토콜인 SMTP를 사용함.
        // SMTP 서버 호스트명(IP주소)
        // SMTP 서버 포트: 기본포트 465(SSL) 또는 587(TLS)

        // SMTP 서버 계정정보: 로그인할 아이디, 패스워드
        // 네이버 메일 - smtp.naver.com
        // 다음 메일 - smtp.daum.net
        // 구글 지메일 - smtp.google.com

        // https://support.google.com/mail/answer/7126229?hl=ko

        long beginTime = System.currentTimeMillis();

        // SimpleEmail 객체 생성
        SimpleEmail email = new SimpleEmail();
        // SMTP 서버 연결 설정
        email.setHostName(p.smtp_server);
        email.setSmtpPort(p.port);
        email.setAuthentication(p.id, p.passwd);

        // SMTP 보안 SSL, TLS 설정
        email.setSSLOnConnect(true); // SSL 사용 설정
        email.setStartTLSEnabled(true); // TLS 사용 설정

        String result = "fail";
        try {
            // 보내는 사람 설정
            email.setFrom(p.mailid, p.id ,"utf-8");
            // 받는 사람 설정
            email.addTo(reciever, reciever , "utf-8");
            // 받는사람(참조인) 설정
            //email.addCc(email, name, charset);
            // 받는사람(숨은참조인) 설정
            //email.addBcc(email, name, charset);

            // 제목 설정
            email.setSubject(title);
            // 본문 내용 준비

            // 본문 설정
            email.setMsg(mailtext);
            // 메일 전송
            result = email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        } finally {
            long execTime = System.currentTimeMillis() - beginTime;
            System.out.println("execTime : " + execTime);
            System.out.println("result : " + result);

            JOptionPane.showMessageDialog(null, "전송되었습니다.");
        }

    }
    public void multiPartEmail(Property p, String reciever, String title, String mailtext){
        // 메일 전송기능 라이브러리 준비
        // CommonsEmail
        // http://commons.apache.org/proper/commons-email
        //  ▶ commons-email-1.5.jar

        // JavaMail
        // http://javaee.github.io/javamail
        //  ▶ mail.jar

        // JAF(java activation framework)
        //  ▶ activation.jar

        // SimpleEmail 클래스: 텍스트 이메일 전송

        // MultiPartEmail 클래스: 메시지와 첨부파일을 함께 전송
        //   - EmailAttachment 클래스: 첨부파일

        // HtmlEmail 클래스: 이메일 컨텐츠를 HTML형식으로 전송

        // 이메일 서비스는 메일 전송용 프로토콜인 SMTP를 사용함.
        // SMTP 서버 호스트명(IP주소)
        // SMTP 서버 포트: 기본포트 465(SSL) 또는 587(TLS)

        // SMTP 서버 계정정보: 로그인할 아이디, 패스워드
        // 네이버 메일 - smtp.naver.com
        // 다음 메일 - smtp.daum.net
        // 구글 지메일 - smtp.google.com

        // https://support.google.com/mail/answer/7126229?hl=ko

        long beginTime = System.currentTimeMillis();

        // SimpleEmail 객체 생성
        MultiPartEmail email = new MultiPartEmail();
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(file_append.getAbsolutePath());//절대경로 저
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setName(file_append.getName()); // 파일의 이름을 지정
        // SMTP 서버 연결 설정
        email.setHostName(p.smtp_server);
        email.setSmtpPort(p.port);
        email.setAuthentication(p.id, p.passwd);

        // SMTP 보안 SSL, TLS 설정
        email.setSSLOnConnect(true); // SSL 사용 설정
        email.setStartTLSEnabled(true); // TLS 사용 설정

        String result = "fail";
        try {
            // 보내는 사람 설정
            email.setFrom(p.mailid, p.id , "utf-8");
            // 받는 사람 설정
            email.addTo(reciever, reciever, "utf-8");
            // 받는사람(참조인) 설정
            //email.addCc(email, name, charset);
            // 받는사람(숨은참조인) 설정
            //email.addBcc(email, name, charset);

            // 제목 설정
            email.setSubject(title);
            // 본문 설정
            email.setMsg(mailtext);
            //파일 첨부
            email.attach(attachment);
            // 메일 전송
            result = email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        } finally {
            long execTime = System.currentTimeMillis() - beginTime;
            System.out.println("execTime : " + execTime);
            System.out.println("result : " + result);

            JOptionPane.showMessageDialog(null, "전송되었습니다.");
        }

    }

}