package kr.co.sist.kji;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinMemberEvt implements ActionListener{
	JoinMemberView jmv;
	private JTextField idField, nameField, emailField, phoneField, zipField, addrField, detailAddrField;
	private JPasswordField pwField, pwConfirmField;
	
	public JoinMemberEvt(JoinMemberView jmv) {
		this.jmv=jmv;
		idField=jmv.getIdField();
		nameField=jmv.getNameField();
		emailField=jmv.getEmailField();
		phoneField=jmv.getPhoneField();
		zipField=jmv.getZipField();
		addrField=jmv.getAddrField();
		detailAddrField=jmv.getDetailAddrField();
		pwField=jmv.getPwField();
		pwConfirmField=jmv.getPwConfirmField();
	}//JoinMemberEvt
	
	private boolean jtfEmptyChk(JTextField jtf) {
		boolean flag = false;
		
		flag = jtf.getText().trim().isEmpty(); // 아이디가 비어있지 않니?
		if (flag) {// 아이디에 값 이 있는경우
			jtf.requestFocus();// 커서를 패스워드 입력 컴포넌트로 이동
		}//end if 

		return flag;
	}
	
	private boolean jpfEmptyChk(JPasswordField jpf) {
		boolean flag = false;
		String pass = new String(jpf.getPassword());
		flag = pass.isEmpty(); // 아이디가 비어있지 않니?
		if (flag) {// 아이디에 값 이 있는경우
			jpf.requestFocus();// 커서를 패스워드 입력 컴포넌트로 이동
		}//end if 

		return flag;
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MemberService ms = new MemberService();
		if(e.getSource() == jmv.getSignupBtn()) {
			String pass = new String(pwField.getPassword());
			String passConf = new String(pwConfirmField.getPassword());
			
			
			if(jtfEmptyChk(idField)) {JOptionPane.showMessageDialog(jmv, "아이디가 비었습니다");return;}
			if(jtfEmptyChk(nameField)) {JOptionPane.showMessageDialog(jmv, "이름이 비었습니다");return;}
			if(jpfEmptyChk(pwField)) {JOptionPane.showMessageDialog(jmv, "비밀번호가 비었습니다");return;}
			if(jpfEmptyChk(pwConfirmField)) {JOptionPane.showMessageDialog(jmv, "비밀번호확인이 비었습니다");return;}
			if(jtfEmptyChk(emailField)) {JOptionPane.showMessageDialog(jmv, "이메일이 비었습니다");return;}
			if(jtfEmptyChk(phoneField)) {JOptionPane.showMessageDialog(jmv, "전화번호가 비었습니다");return;}
			if(jtfEmptyChk(zipField)) {JOptionPane.showMessageDialog(jmv, "우편번호가 비었습니다");return;}
			if(jtfEmptyChk(addrField)) {JOptionPane.showMessageDialog(jmv, "주소가 비었습니다");return;}
			if(jtfEmptyChk(detailAddrField)) {JOptionPane.showMessageDialog(jmv, "상세주소가 비었습니다");return;}
			
			if(pass.equals(passConf)) {
				System.out.println("비번 같은거 확인");
			}else {
				System.out.println("비번 틀림");
			}
			
			
		}//end if
		if(e.getSource() == jmv.getCheckBtn()) {
			String id = idField.getText().trim();
			String idCon="";
			try {
			idCon=ms.searchOneMember(id).getMemId();
			}catch(NullPointerException npe) {
				System.out.println("중복되지 않음");
				
			}//end catch
			
			if(!idCon.isEmpty()) {
				System.out.println("중복된 아이디");
			}
			
		}//end if
		
	}//actionPerformed

}//class
