package kr.co.sist.kji;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFormEvt  implements ActionListener {

	private String id;
	private LoginpageView mpv;
	private MemberVO mVO;
	private JTextField jtfId;
	private JPasswordField jpfPass;

	public LoginFormEvt(LoginpageView mpv) {
		this.mpv = mpv;
		// 필요한 컴포넌트를 인스턴스 변수(사용할 컴포넌트)에 할당
		jtfId = mpv.getIdField();
		jpfPass = mpv.getPasswordField();

	}// LoginFormEvt


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

	private boolean passChk() {
		boolean flag = false;
		if (!idChk()) {// 아이디가 없다면
			return flag;// 호출한 곳으로 돌아가
		} // end if

		// char[]을 하나의 문자열로 만들어서 저장 => String에서 제공하는 모든 기능을 사용할 수 있다.
		String pass = new String(jpfPass.getPassword());
		/// 비밀번호가 입력되었는지 확인

		if (pass.isEmpty()) {// 비밀번호가 없음
			jpfPass.requestFocus();
			return flag;// 호출한 곳으로 돌아가
		}//end if
			flag = true;
			


		return flag;
	}// passChk
	
	private boolean loginChk() {
		boolean flag = false;
		
		String pass = new String(jpfPass.getPassword());
		// 로그인 수행
		String id = jtfId.getText();
		
		LoginVO lVO = new LoginVO(id, pass);
		
		MemberService ms = new MemberService();
		mVO = ms.login(lVO);
		if (!(mVO.getMemName()==null)) {
			
			flag = true;
		}//end if
		System.out.println(mVO.getMemFlag());
		
		return flag;
	}// passChk
	
	private boolean deleteAccount() {
	boolean flag = false;
			
			String pass = new String(jpfPass.getPassword());
			// 로그인 수행
			String id = jtfId.getText();
			
			LoginVO lVO = new LoginVO(id, pass);
			
			MemberService ms = new MemberService();
			mVO = ms.login(lVO);
			if(mVO.getMemFlag()==null) {
				return flag;
			}
			if(mVO.getMemFlag().equals("Y")) {
				flag = true;
		}//end if
		return flag;
	}//deleteAccount

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == mpv.getJbtnLogin()) {
			if(!idChk()) {
				JOptionPane.showMessageDialog(mpv, "아이디를 넣어주세요.");
				return;
			}
			
			if(!passChk()) {
				JOptionPane.showMessageDialog(mpv, "비밀번호를 넣어주세요.");
				return;
			}//end if
			if(deleteAccount()) { 
				JOptionPane.showMessageDialog(mpv, "탈퇴한 회원입니다.");
				return;
			}//end if
			
			if(loginChk()) {
				id = mpv.getIdField().getText();
				JOptionPane.showMessageDialog(mpv, mVO.getMemName()+"님 어서오세요.");
				///////////////////////////////////////////////////
				//로그인 성공 했을 때 엔진오일 창으로 넘어가게
				//일시적으로 내 정보뷰로 넘어가세 설정
				new MyInfoView(id);
				mpv.dispose();
				
				
				//////////////////////////////////////////////////
			}
			
			
			if(!loginChk()) {
				JOptionPane.showMessageDialog(mpv, "로그인 실패");
				return;
			}//end if
			
				
//			passChk();
		}//end if
		
		if(ae.getSource() == mpv.getJbtnJoin()) {
			new JoinMemberView();
			mpv.dispose();
		}
	}// actionPerformed


	public String getId() {
		return id;
	}



}// class
