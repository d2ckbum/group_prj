package kr.co.sist.hjs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLoginView extends JFrame implements ActionListener {
	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JButton jbtnLogin;

	public AdminLoginView() {
		setTitle("관리자 로그인");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(4, 1));

		// 최상단 라벨
		JLabel titleLabel = new JLabel("관리자 로그인", JLabel.CENTER);
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		add(titleLabel);

		// 아이디 입력
		JPanel idPanel = new JPanel();
		JLabel idLabel = new JLabel("아이디 : ");
		jtfId = new JTextField(30);
		idPanel.add(idLabel);
		idPanel.add(jtfId);
		add(idPanel);

		// 비밀번호 입력
		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("비밀번호 : ");
		jpfPass = new JPasswordField(30);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(jpfPass);
		add(passwordPanel);

		// 로그인 버튼
		JPanel buttonPanel = new JPanel();
		jbtnLogin = new JButton("로그인");
		buttonPanel.add(jbtnLogin);
		add(buttonPanel);

		// 로그인 버튼 이벤트 처리
		jbtnLogin.addActionListener(new AdminLoginEvt(this)); // 여기서 AdminLoginEvt와 연결

		setLocationRelativeTo(null); // 화면 중앙에 배치

		setVisible(true);// 가시화
	}

	public JTextField getJtfId() {
		return jtfId;
	}

	public void setJtfId(JTextField jtfId) {
		this.jtfId = jtfId;
	}

	public JPasswordField getJpfPass() {
		return jpfPass;
	}

	public void setJpfPass(JPasswordField jpfPass) {
		this.jpfPass = jpfPass;
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
