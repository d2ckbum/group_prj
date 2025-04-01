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

	public AdminMainView() {
		setTitle("관리자 페이지");
		setSize(1200, 900);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		// 상단 패널
		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel titleLabel = new JLabel("관리자 페이지", SwingConstants.CENTER);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		topPanel.add(titleLabel, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel welcomeLabel = new JLabel("관리자님 어서오세요");
		JButton jbtnLogout = new JButton("로그아웃");
		rightPanel.add(welcomeLabel);
		rightPanel.add(jbtnLogout);
		topPanel.add(rightPanel, BorderLayout.SOUTH);

		add(topPanel, BorderLayout.NORTH);

		// 레이어드 패널
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(1000, 500));

		JPanel buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBounds(0, 0, 1000, 500);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill = GridBagConstraints.BOTH;

		jbtnMember = new JButton("회원현황");
		jbtnItemManage = new JButton("상품관리");
		jbtnFixManage = new JButton("정비관리");
		jbtnSales = new JButton("매출현황");
		jbtnInquiry = new JButton("문의관리");

		Font buttonFont = new Font("맑은 고딕", Font.BOLD, 26);
		Dimension buttonSize = new Dimension(250, 100);

		JButton[] buttons = { jbtnMember, jbtnItemManage, jbtnFixManage, jbtnSales, jbtnInquiry };
		for (JButton btn : buttons) {
			btn.setFont(buttonFont);
			btn.setPreferredSize(buttonSize);
		}

		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(jbtnMember, gbc);
		gbc.gridx = 1;
		buttonPanel.add(jbtnItemManage, gbc);
		gbc.gridx = 2;
		buttonPanel.add(jbtnFixManage, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
		bottomPanel.add(jbtnSales);
		bottomPanel.add(jbtnInquiry);
		jbtnSales.setPreferredSize(buttonSize);
		jbtnInquiry.setPreferredSize(buttonSize);
		buttonPanel.add(bottomPanel, gbc);

		layeredPane.add(buttonPanel, JLayeredPane.DEFAULT_LAYER);

		// 버튼 위에 라벨 추가
		JLabel overlayLabel = new JLabel("관리 시스템", SwingConstants.CENTER);
		overlayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		overlayLabel.setForeground(new Color(255, 0, 0, 180));
		overlayLabel.setBounds(350, 150, 300, 50);
		layeredPane.add(overlayLabel, JLayeredPane.PALETTE_LAYER);

		add(layeredPane, BorderLayout.CENTER);

		// 로그아웃 버튼 기능 추가
		jbtnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
				dispose();
				// 로그인 화면으로 이동하는 코드 추가 가능
			}
		});

		setVisible(true);
	}

	public static void main(String[] args) {
		new AdminMainView();
	}
}