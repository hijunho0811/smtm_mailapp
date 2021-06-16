package com.example.email;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

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

public class EmailGUI extends JFrame implements DropTargetListener {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextArea textArea;
    private JCheckBox chckbxNewCheckBox;


    public void email_guicall() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 576, 490);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setVisible(true);

        textField = new JTextField();
        textField.setBounds(103, 12, 353, 21);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("보내는 사람");
        lblNewLabel.setBounds(25, 10, 78, 24);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("전송");
        btnNewButton.addMouseListener(new MouseAdapter() {

            @Override

            public void mouseClicked(MouseEvent e) {
                //이메일 전송 메소
            }
        });

        btnNewButton.setBounds(468, 11, 80, 72);
        contentPane.add(btnNewButton);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "제목", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(19, 43, 437, 46);
        contentPane.add(panel);
        panel.setLayout(null);

        textField_1 = new JTextField();
        textField_1.setBounds(6, 17, 356, 21);
        panel.add(textField_1);
        textField_1.setColumns(10);

        chckbxNewCheckBox = new JCheckBox("HTML");
        chckbxNewCheckBox.setBounds(365, 16, 60, 23);
        panel.add(chckbxNewCheckBox);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 93, 523, 236);
        contentPane.add(scrollPane);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(25, 340, 523, 100);
        contentPane.add(scrollPane_1);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
    }

    public void drop(DropTargetDropEvent event)
    {
        //Accept copy drops
        event.acceptDrop(DnDConstants.ACTION_COPY);

        //Get the transfer which provides dropped item data
        Transferable transferable = event.getTransferable();

        //Get data formats of the dropped item
        DataFlavor[] flavors = transferable.getTransferDataFlavors();

        for(DataFlavor flavor : flavors)
        {
            try
            {
                //If the drop items are files
                if(flavor.isFlavorJavaFileListType())
                {

                    @SuppressWarnings("unchecked")
                    List<File> files = (List<File>) transferable.getTransferData(flavor);
//                    sendFormattedEmail(files);

                    java.util.List list = (java.util.List) transferable.getTransferData(DataFlavor.javaFileListFlavor);


                    //파일명 출력
                    for(int i=0;i < list.size();i++)
                    {
                        System.out.println(list.size() + "-" + list.get(i));
                    }


                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        //Inform that the drop is complete
        event.dropComplete(true);
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        // TODO Auto-generated method stub

    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        // TODO Auto-generated method stub

    }

    public void sendFormattedEmail(){
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
        email.setHostName("Smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthentication("iscertorg", "1108yoon");
        // SMTP 보안 SSL, TLS 설정
        email.setSSLOnConnect(true); // SSL 사용 설정
        email.setStartTLSEnabled(true); // TLS 사용 설정

        String result = "fail";
        try {
            // 보내는 사람 설정
            email.setFrom("iscertorg@gmail.com", "공준호", "utf-8");
            // 받는 사람 설정
            email.addTo("hijunho0811@gmail.com", "카페사장 공준", "utf-8");
            // 받는사람(참조인) 설정
            //email.addCc(email, name, charset);
            // 받는사람(숨은참조인) 설정
            //email.addBcc(email, name, charset);

            // 제목 설정
            email.setSubject("메일 제목입니다.");
            // 본문 내용 준비
            StringBuilder sb = new StringBuilder();
            sb.append("메일 본문입니다.\n");
            sb.append("두번째 줄입니다.\n\n");
            sb.append("세번째 줄입니다.\n");
            // 본문 설정
            email.setMsg(sb.toString());
            // 메일 전송
            result = email.send();

        } catch (EmailException e) {
            e.printStackTrace();
        } finally {
            long execTime = System.currentTimeMillis() - beginTime;
            System.out.println("execTime : " + execTime);
            System.out.println("result : " + result);
        }

    }
}