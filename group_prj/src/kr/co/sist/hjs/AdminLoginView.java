package kr.co.sist.hjs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginView extends JFrame implements ActionListener {
	private JTextField idField;
	private JPasswordField passwordField;
	private JButton jbtnLogin;

	public AdminLoginView() {
		setTitle("관리자 로그인");
		setSize(800, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4, 1));

		// 최상단 라벨
		JLabel titleLabel = new JLabel("관리자 로그인", JLabel.CENTER);
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		add(titleLabel);

		// 아이디 입력
		JPanel idPanel = new JPanel();
		JLabel idLabel = new JLabel("아이디 : ");
		idField = new JTextField(30);
		idPanel.add(idLabel);
		idPanel.add(idField);
		add(idPanel);

		// 비밀번호 입력
		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("비밀번호 : ");
		passwordField = new JPasswordField(30);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		add(passwordPanel);

		// 로그인 버튼
		JPanel buttonPanel = new JPanel();
		jbtnLogin = new JButton("로그인");
		buttonPanel.add(jbtnLogin);
		add(buttonPanel);

		// 로그인 버튼 이벤트 처리
		jbtnLogin.addActionListener(new AdminLoginEvt(this));  // 여기서 AdminLoginEvt와 연결

		setLocationRelativeTo(null); // 화면 중앙에 배치
	}

	public JTextField getIdField() {
		return idField;
	}

	public void setIdField(JTextField idField) {
		this.idField = idField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}


	public JButton getJbtnLogin() {
		return jbtnLogin;
	}

	public void setJbtnLogin(JButton jbtnLogin) {
		this.jbtnLogin = jbtnLogin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
