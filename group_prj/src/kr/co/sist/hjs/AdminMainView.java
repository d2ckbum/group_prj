package kr.co.sist.hjs;

import javax.swing.*;

import kr.co.sist.kji.MemberVO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		setResizable(false);

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
		welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		jbtnLogout = new JButton("로그아웃");
		jbtnLogout.setFont(new Font("맑은 고딕", Font.BOLD, 15));

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
		gbc.weighty = 1;

		// 버튼 폰트 설정 
		Font buttonFont = new Font("맑은 고딕", Font.BOLD, 20);

		// 회원 현황 버튼
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jbtnMember = new JButton("회원 현황");
		jbtnMember.setFont(buttonFont);
		centerPanel.add(jbtnMember, gbc);

		// 상품 관리 버튼
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jbtnItemManage = new JButton("상품 관리");
		jbtnItemManage.setFont(buttonFont);
		centerPanel.add(jbtnItemManage, gbc);

		// 정비 관리 버튼
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jbtnFixManage = new JButton("정비 관리");
		jbtnFixManage.setFont(buttonFont);
		centerPanel.add(jbtnFixManage, gbc);

		// 매출 현황 버튼
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		jbtnSales = new JButton("매출 현황");
		jbtnSales.setFont(buttonFont);
		centerPanel.add(jbtnSales, gbc);

		// 문의 관리 버튼
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1; // 나머지 공간을 사용
		jbtnInquiry = new JButton("문의 관리");
		jbtnInquiry.setFont(buttonFont);
		centerPanel.add(jbtnInquiry, gbc);

		// 이벤트 등록
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