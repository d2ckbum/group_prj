package kr.co.sist.hjs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class AdminMainView extends JFrame {
    private JButton jbtnMember;
    private JButton jbtnItemManage;
    private JButton jbtnFixManage;
    private JButton jbtnSales;
    private JButton jbtnInquiry;
    private JLabel welcomeLabel;
    private JButton jbtnLogout;
    private AdminMainViewEvt amve; // 이벤트 처리 클래스
    private JLabel memberCountLabel; // 회원 수 표시 레이블
    private JLabel itemCountLabel; // 등록된 수 표시 레이블
    private JLabel fixCountLabel; // 정비 접수 완료 건수 표시 레이블
    private JLabel fixingCountLabel; // 정비 중 건수 표시 레이블
    private JLabel fixOkCountLabel; // 정비 완료 건수 표시 레이블
    private JLabel salesCountLabel;// 일 매출 현황 표시 레이블
    private JLabel inquiryCountLabel; // 문의 답변 대기 건수 표시 레이블
	private Color defaultButtonColor = new Color(217, 217, 217); // 기본 버튼 색상


    public AdminMainView() {
        setTitle("관리자 페이지");
        setSize(1200, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        amve = new AdminMainViewEvt(this);
        setLayout(new BorderLayout());

        // 상단 패널
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("관리자 페이지", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        welcomeLabel = new JLabel("관리자님 어서오세요");
        welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        jbtnLogout = new JButton("종료");
        jbtnLogout.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        jbtnLogout.setBackground(new Color(217,217,217));
        
        rightPanel.add(welcomeLabel);
        rightPanel.add(jbtnLogout);
        topPanel.add(rightPanel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);

        // 중앙 버튼 패널
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        add(centerPanel, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        Font buttonFont = new Font("맑은 고딕", Font.BOLD, 20);
        Font countFont = new Font("맑은 고딕", Font.PLAIN, 14);

        // 회원 현황 버튼
        JPanel memberPanel = new JPanel(new BorderLayout());
        jbtnMember = new JButton("회원 현황");
        jbtnMember.setFont(buttonFont);
        jbtnMember.setBackground(defaultButtonColor);
        memberPanel.add(jbtnMember, BorderLayout.CENTER);
        memberCountLabel = new JLabel("회원 0 명", SwingConstants.CENTER);
        memberCountLabel.setFont(countFont);
//        memberCountLabel.add(jbtnMember,BorderLayout.SOUTH);
        memberPanel.add(memberCountLabel, BorderLayout.SOUTH);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        centerPanel.add(memberPanel, gbc);

        // 상품 관리 버튼
        JPanel itemPanel = new JPanel(new BorderLayout());
        jbtnItemManage = new JButton("상품 관리");
        jbtnItemManage.setFont(buttonFont);
        jbtnItemManage.setBackground(defaultButtonColor);
        itemPanel.add(jbtnItemManage, BorderLayout.CENTER);
        itemCountLabel = new JLabel("등록 상품 0 개", SwingConstants.CENTER);
        itemCountLabel.setFont(countFont);
        itemPanel.add(itemCountLabel, BorderLayout.SOUTH);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        centerPanel.add(itemPanel, gbc);

        // 정비 관리 버튼
        JPanel fixPanel = new JPanel(new BorderLayout());
        jbtnFixManage = new JButton("정비 관리");
        jbtnFixManage.setFont(buttonFont);
        jbtnFixManage.setBackground(defaultButtonColor);
        fixPanel.add(jbtnFixManage, BorderLayout.CENTER);
        JPanel fixCountSubPanel = new JPanel(new GridLayout(3, 1)); // 3개의 라벨을 세로로 배치
        fixCountLabel = new JLabel("접수 완료 0 건", SwingConstants.CENTER);
        fixCountLabel.setFont(countFont);
        fixCountSubPanel.add(fixCountLabel);
        fixingCountLabel = new JLabel("정비 중 0 건", SwingConstants.CENTER);
        fixingCountLabel.setFont(countFont);
        fixCountSubPanel.add(fixingCountLabel);
        fixOkCountLabel = new JLabel("정비 완료 0 건", SwingConstants.CENTER);
        fixOkCountLabel.setFont(countFont);
        fixCountSubPanel.add(fixOkCountLabel);
        fixPanel.add(fixCountSubPanel, BorderLayout.SOUTH);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        centerPanel.add(fixPanel, gbc);

        // 매출 현황 버튼
        JPanel salesPanel = new JPanel(new BorderLayout());
        jbtnSales = new JButton("매출 현황");
        jbtnSales.setFont(buttonFont);
        jbtnSales.setBackground(defaultButtonColor);
        salesPanel.add(jbtnSales, BorderLayout.CENTER);
        salesCountLabel= new JLabel("일일 매출 총 0 원",SwingConstants.CENTER);
        salesCountLabel.setFont(countFont);
        salesPanel.add(salesCountLabel,BorderLayout.SOUTH);
        // 필요하다면 매출 관련 라벨 추가 가능
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        centerPanel.add(salesPanel, gbc);

        // 문의 관리 버튼
        JPanel inquiryPanel = new JPanel(new BorderLayout());
        jbtnInquiry = new JButton("문의 관리");
        jbtnInquiry.setFont(buttonFont);
        jbtnInquiry.setBackground(defaultButtonColor);
        inquiryPanel.add(jbtnInquiry, BorderLayout.CENTER);
        inquiryCountLabel = new JLabel("답변 대기 0 건",SwingConstants.CENTER);
        inquiryCountLabel.setFont(countFont);
        inquiryPanel.add(inquiryCountLabel,BorderLayout.SOUTH);
        // 필요하다면 문의 관련 라벨 추가 가능
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        centerPanel.add(inquiryPanel, gbc);

        // 이벤트 등록
        jbtnMember.addActionListener(amve);
        jbtnItemManage.addActionListener(amve);
        jbtnFixManage.addActionListener(amve);
        jbtnSales.addActionListener(amve);
        jbtnInquiry.addActionListener(amve);
        jbtnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "종료 되었습니다.");
            dispose();
        });

        // 윈도우가 활성화될 때마다 정보 업데이트
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                displayMemberCount();
                displayItemCount();
                displayFixCounts(); // 수정된 메소드 호출
                displaySalesCount();
                displayInquiryCount();
            }
        });

        // 초기 정보 표시 (프로그램 시작 시)
        displayMemberCount();
        displayItemCount();
        displayFixCounts(); // 수정된 메소드 호출
        displaySalesCount();
        displayInquiryCount();

        setVisible(true);
    }

    private void displayMemberCount() {
        AdminMainDAO model = new AdminMainDAO();
        try {
            int count = model.getMemberCount();
            memberCountLabel.setText("회원 " + count + " 명");
        } catch (SQLException e) {
            e.printStackTrace();
            memberCountLabel.setText("회원 수 오류");
        }
    }//displayMemberCount

    private void displayItemCount() {
        AdminMainDAO model = new AdminMainDAO();
        try {
            int count = model.getItemCount();
            itemCountLabel.setText("등록 상품 " + count + " 개");
        } catch (SQLException e) {
            e.printStackTrace();
            itemCountLabel.setText("상품 오류");
        }
    }//displayItemCount

    private void displayFixCounts() { // 메소드 이름 변경 (복수형)
        AdminMainDAO model = new AdminMainDAO();
        try {
            int[] counts = model.getFixCounts();
            fixCountLabel.setText("접수 완료 " + counts[0] + " 건");
            fixingCountLabel.setText("정비 중 " + counts[1] + " 건");
            fixOkCountLabel.setText("정비 완료 " + counts[2] + " 건");
        } catch (SQLException e) {
            e.printStackTrace();
            fixCountLabel.setText("정비 오류");
            fixingCountLabel.setText("정비 오류");
            fixOkCountLabel.setText("정비 오류");
        }
    }//displayFixCounts
    
    private void displaySalesCount() {
    	AdminMainDAO model = new AdminMainDAO();
    	try {
    		int count = model.getSalesCount();
    		inquiryCountLabel.setText("일일 매출 총 " + count + " 원");
    	} catch (SQLException e) {
    		e.printStackTrace();
    		inquiryCountLabel.setText("매출 오류");
    	}
    	
    }

    private void displayInquiryCount() {
        AdminMainDAO model = new AdminMainDAO();
        try {
            int count = model.getInquiryCount();
            inquiryCountLabel.setText("답변 대기 " + count + " 건");
        } catch (SQLException e) {
            e.printStackTrace();
            inquiryCountLabel.setText("답변 오류");
        }
    }//displayInquiryCount

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

    public void closeAdminMainView() {
        dispose();
    }
}