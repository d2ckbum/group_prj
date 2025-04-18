package kr.co.sist.kji;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginpageView extends JFrame {
	private JTextField idField;
	private JPasswordField passwordField;
	private JButton jbtnLogin, jbtnJoin;

	public LoginpageView() {
		setTitle("유저 로그인");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4, 1));

		// 최상단 라벨
		JLabel titleLabel = new JLabel("쌍용 엔진오일 샵", JLabel.CENTER);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(titleLabel);

		// 아이디 입력
		JPanel idPanel = new JPanel();
		JLabel idLabel = new JLabel("아이디 : ");
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		idField = new JTextField(30);
		idField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		idPanel.add(idLabel);
		idPanel.add(idField);
		add(idPanel);

		// 비밀번호 입력
		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("비밀번호 : ");
		passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		passwordField = new JPasswordField(30);
		passwordField.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		add(passwordPanel);

		// 로그인 버튼
		JPanel buttonPanel = new JPanel();
		jbtnLogin = new JButton("로그인");
		jbtnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		jbtnLogin.setBackground(new Color(217, 217, 217));

		jbtnJoin = new JButton("회원 가입");
		jbtnJoin.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		jbtnJoin.setBackground(new Color(217, 217, 217));

		buttonPanel.add(jbtnLogin);
		buttonPanel.add(jbtnJoin);

		add(buttonPanel);

		// 로그인 버튼 이벤트 처리
		LoginFormEvt lfe = new LoginFormEvt(this);
		jbtnLogin.addActionListener(lfe);
		jbtnJoin.addActionListener(lfe);

		setLocationRelativeTo(null); // 화면 중앙에 배치
		setVisible(true);
	}

	public JTextField getIdField() {
		return idField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JButton getJbtnLogin() {
		return jbtnLogin;
	}

	public JButton getJbtnJoin() {
		return jbtnJoin;
	}
}
