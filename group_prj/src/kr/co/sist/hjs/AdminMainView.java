package kr.co.sist.hjs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainView extends JFrame {
    private JButton jbtnMember;
    private JButton jbtnItemManage;
    private JButton jbtnFixManage;
    private JButton jbtnSales;
    private JButton jbtnInquiry;
    private JLabel welcomeLabel;
    private JButton jbtnLogout;
    private AdminMainViewEvt amve; // 이벤트 처리 클래스

    public AdminMainView() {
        setTitle("관리자 페이지");
        setSize(1200, 900); // 창 크기 설정
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 배치

        // 창 크기 고정
        setResizable(false); // 창 크기 조정 불가

        // 이벤트 처리 객체 생성
        amve = new AdminMainViewEvt(this);

        // 전체 레이아웃을 BorderLayout으로 설정
        setLayout(new BorderLayout());

        // 상단 패널
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("관리자 페이지", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        welcomeLabel = new JLabel("관리자님 어서오세요");
        welcomeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16)); 
        
        jbtnLogout = new JButton("로그아웃");
        jbtnLogout.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        
        rightPanel.add(welcomeLabel);
        rightPanel.add(jbtnLogout);
        topPanel.add(rightPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        // 중앙 버튼 패널 (GridBagLayout 사용)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // 내부 여백 설정
        add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // 버튼 간 간격 설정
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1; // 세로 방향으로도 공간을 채우도록 설정

        // 버튼 폰트 설정 
        Font buttonFont = new Font("맑은 고딕", Font.BOLD, 20);

        // 첫 번째 행 (3개 버튼)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // 각 버튼이 1개의 셀을 차지
        jbtnMember = new JButton("회원현황");
        jbtnMember.setFont(buttonFont);
        centerPanel.add(jbtnMember, gbc);

        gbc.gridx = 1;
        jbtnItemManage = new JButton("상품관리");
        jbtnItemManage.setFont(buttonFont);
        centerPanel.add(jbtnItemManage, gbc);

        gbc.gridx = 2;
        jbtnFixManage = new JButton("정비관리");
        jbtnFixManage.setFont(buttonFont);
        centerPanel.add(jbtnFixManage, gbc);

        // 두 번째 행 
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // 각 버튼이 1개의 셀을 차지
        gbc.anchor = GridBagConstraints.CENTER; // 가운데 정렬

        jbtnSales = new JButton("매출현황");
        jbtnSales.setFont(buttonFont);
        jbtnSales.setPreferredSize(jbtnMember.getPreferredSize()); // 첫 번째 행 버튼과 동일한 크기 설정
        centerPanel.add(jbtnSales, gbc);

        gbc.gridx = 2; // 문의관리 버튼을 세 번째 셀에 배치
        jbtnInquiry = new JButton("문의관리");
        jbtnInquiry.setFont(buttonFont);
        jbtnInquiry.setPreferredSize(jbtnMember.getPreferredSize()); // 첫 번째 행 버튼과 동일한 크기 설정
        centerPanel.add(jbtnInquiry, gbc);

        // 두 버튼을 가운데로 오게 하기 위해 빈 셀을 가운데에 배치
        gbc.gridx = 1;
        gbc.gridwidth = 1; // 이 위치는 한 셀만 차지
        centerPanel.add(new JLabel(), gbc); // 빈 공간 추가
        
        //버튼 이벤트 등록
        jbtnMember.addActionListener(amve); 
        jbtnItemManage.addActionListener(amve); 
        jbtnFixManage.addActionListener(amve); 
        jbtnSales.addActionListener(amve); 
        jbtnInquiry.addActionListener(amve); 

        // 로그아웃 버튼 기능 추가
        jbtnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
            dispose();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
         new AdminMainView();
    }

    public JButton getJbtnMember() {
        return jbtnMember;
    }

    public JButton getJbtnItemManage() {
        return jbtnItemManage;
    }

    public JButton getJbtnFixManage() {
        return jbtnFixManage;
    }

    public JButton getJbtnSales() {
        return jbtnSales;
    }

    public JButton getJbtnInquiry() {
        return jbtnInquiry;
    }
    // AdminMainView를 닫는 메소드 (AdminMainViewEvt에서 호출)
    public void closeAdminMainView() {
        dispose();
    }
}