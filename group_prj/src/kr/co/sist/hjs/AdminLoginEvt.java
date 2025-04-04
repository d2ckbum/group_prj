package kr.co.sist.hjs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminLoginEvt extends WindowAdapter implements ActionListener {

	private AdminLoginView alf;

	private JTextField jtfId;
	private JPasswordField jpfPass;

	public AdminLoginEvt(AdminLoginView alf) {
		this.alf = alf;
		// 필요한 컴포넌트를 인스턴스 변수(사용할 컴포넌트)에 할당
		jtfId = alf.getJtfId();
		jpfPass = alf.getJpfPass();

	}// LoginFormEvt

	@Override
	public void windowClosing(WindowEvent e) {
		alf.dispose();
	}// windowClosing

	private boolean idChk() {
		boolean flag = false;
		String id = jtfId.getText().trim();// 앞뒤공백을 제거하여 아이디를 저장

		flag = !id.isEmpty(); // 아이디가 비어있지 않니?
		if (flag) {// 아이디에 값 이 있는경우
			jpfPass.requestFocus();// 커서를 패스워드 입력 컴포넌트로 이동
		} else {// 아이디가 empty("")인 경우
			jtfId.requestFocus();// 커서를 아이디 입력 컴포넌트로 이동
		} // end if

		return flag;
	}// idChk

	private void passChk() throws SQLException {
		if (!idChk()) {// 아이디가 없다면
			return;// 호출한 곳으로 돌아가
		} // end if

		// char[]을 하나의 문자열로 만들어서 저장 => String에서 제공하는 모든 기능을 사용할 수 있다.
		String pass = new String(jpfPass.getPassword());
		/// 비밀번호가 입력되었는지 확인

		if (pass.isEmpty()) {// 비밀번호가 없음
			jpfPass.requestFocus();
			return;// 호출한 곳으로 돌아가
		} // end if

		// 아이디와 비밀번호 있음
		// 로그인 수행
		String id = jtfId.getText();

		AdminLoginVO lVO = new AdminLoginVO(id, pass);

		AdminLoginService ls = new AdminLoginService();
		String adminId = null;
		try {
			adminId = ls.login(lVO);
			if (adminId != null && !adminId.isEmpty()) {
				JOptionPane.showMessageDialog(alf, adminId + "님 환영합니다.", "로그인 성공", JOptionPane.INFORMATION_MESSAGE);
				// 로그인 성공 후 관리자 메인 페이지로 이동
				AdminMainView mainPage = new AdminMainView(); // 관리자 메인 페이지 객체 생성
				mainPage.setVisible(true); // 관리자 메인 페이지를 보이게 합니다.
				alf.dispose(); // 로그인 창 닫기
			} else {
				JOptionPane.showMessageDialog(alf, "아이디 또는 비밀번호를 확인해주세요.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				jtfId.requestFocus();
				jtfId.selectAll();
				jpfPass.setText("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(alf, "데이터베이스 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		}

	}// passChk

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == alf.getJbtnLogin()) {
			try {
				passChk();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(alf, "로그인 처리 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
			}
		} // end if
	}// actionPerformed

}// class