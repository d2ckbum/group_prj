package kr.co.sist.kji;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyInfoEvt implements ActionListener, DocumentListener{

	private MyInfoView miv;
	private String id;
	
	private JTextField  nameField, emailField, phoneField, zipField, addrField, detailAddrField;
	private JPasswordField pwField, pwConfirmField;
	private JComboBox<String> makerBox, modelBox;
	private MemberVO mVO = new MemberVO();
	boolean idChkFlag = false;
	boolean emptyField = false;
	
	
	
	public MyInfoEvt(MyInfoView miv) {
		this.miv = miv;
		id =miv.getId();
		nameField=miv.getMyNameTF();
		emailField=miv.getMyEmailTF();
		phoneField=miv.getMyTellTF();
		zipField=miv.getMyZipcodeTF();
		addrField=miv.getMyAddr1TF();
		detailAddrField=miv.getMyAddr2TF();
		pwField=miv.getMyPassTF();
		pwConfirmField=miv.getMemPassConfirmTF();
		makerBox = miv.getMyMfgCB();
		modelBox = miv.getMyCarCB();
		
	}//MyInfoEvt
	
	
	private void updateMember() {
		mVO.setMemId(id);
		mVO.setMemPass( pwField.getText().toString());
		mVO.setMemName(nameField.getText().toString());
		mVO.setMemEmail(emailField.getText().toString());
		mVO.setMemTell(phoneField.getText().toString());
		mVO.setMemZipcode(zipField.getText().toString());
		mVO.setMemAddr1(addrField.getText().toString());
		mVO.setMemAddr2(detailAddrField.getText().toString());
		mVO.setCarNum(modelBox.getSelectedIndex()+1);
		mVO.setMfgNum(makerBox.getSelectedIndex()+1);
	}//updateMember
	
	private boolean jtfEmptyChk(JTextField jtf) {
		boolean flag = false;
		
		flag = jtf.getText().trim().isEmpty(); // 아이디가 비어있지 않니?
		if (flag) {// 텍스트필드에 값이 없는경우
			jtf.requestFocus();// 커서를 입력 컴포넌트로 이동
		}//end if 

		return flag;
	}//jtfEmptyChk
	
	private boolean jpfEmptyChk(JPasswordField jpf) {
		boolean flag = false;
		String pass = new String(jpf.getPassword());
		flag = pass.isEmpty(); // 비밀번호가 비어있지 않니?
		if (flag) {// 비밀번호에 값이 없는경우
			jpf.requestFocus();// 커서를 패스워드 입력 컴포넌트로 이동
		}//end if 

		return flag;
	}//jpfEmptyChk
	
	private void emptyChk() {
		if(jtfEmptyChk(nameField)) {JOptionPane.showMessageDialog(miv, "이름이 비었습니다");return;}
		if(jpfEmptyChk(pwField)) {JOptionPane.showMessageDialog(miv, "비밀번호가 비었습니다");return;}
		if(jpfEmptyChk(pwConfirmField)) {JOptionPane.showMessageDialog(miv, "비밀번호확인이 비었습니다");return;}
		if(jtfEmptyChk(emailField)) {JOptionPane.showMessageDialog(miv, "이메일이 비었습니다");return;}
		if(jtfEmptyChk(phoneField)) {JOptionPane.showMessageDialog(miv, "전화번호가 비었습니다");return;}
		if(jtfEmptyChk(zipField)) {JOptionPane.showMessageDialog(miv, "우편번호가 비었습니다");return;}
		if(jtfEmptyChk(addrField)) {JOptionPane.showMessageDialog(miv, "주소가 비었습니다");return;}
		if(jtfEmptyChk(detailAddrField)) {JOptionPane.showMessageDialog(miv, "상세주소가 비었습니다");return;}
		emptyField = true;
	}//emptyChk
	
	
	private void checkMatch() {
		String pass = new String(pwField.getPassword());
		String passConf = new String(pwConfirmField.getPassword());
		miv.getPwMatch().setVisible(true);
         if (pass.isEmpty() && passConf.isEmpty()) {
        	 miv.getPwMatch().setText(" ");
         } else if (pass.equals(passConf)) {
        	 miv.getPwMatch().setText("비밀번호 일치");
        	 miv.getPwMatch().setForeground(Color.BLUE);
         } else {
        	 miv.getPwMatch().setText(" 비밀번호 불일치");
         	miv.getPwMatch().setForeground(Color.RED);
         }
     }//checkMatch
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MemberService ms = new MemberService();
		if(e.getSource()== miv.getListBtn()) {
			//new view 
			new MyFixInfoView(id);
			miv.dispose();
			//dispose
		}//상세정보
		
		if(e.getSource()==miv.getUpdateBtn()) {
			//필드에 있는거 update
			int realUpdateNum = -1;
			String pass = new String(pwField.getPassword());
			String passConf = new String(pwConfirmField.getPassword());
			
			emptyChk();
			
			if( emptyField&&pass.equals(passConf)) {
				realUpdateNum= JOptionPane.showConfirmDialog(miv, "수정 하시겠습니까?");
			}else {
				JOptionPane.showMessageDialog(miv, "비밀번호가 틀렸습니다");
			}
			if(realUpdateNum == 0) {
				updateMember();
				ms.modifyMember(mVO);
				JOptionPane.showMessageDialog(miv, "수정 되었습니다.");
			}//end if
			
				
			//DAO랑 Service 수정
		}//정보수정
		
		if(e.getSource()==miv.getDeleteBtn()) {
			//flag == 'Y'로 update
			//DAO랑 Service 수정
			int realDeleteNum =-1;
			
			realDeleteNum= JOptionPane.showConfirmDialog(miv, "탈퇴 하시겠습니까?");
			if(realDeleteNum == 0) {
				ms.removeMember(id);
				JOptionPane.showMessageDialog(miv, "탈퇴 되었습니다.");
				new LoginpageView();
				miv.dispose();
			}//end if
		}//탈퇴버튼
		
	}//actionPerformed






	@Override
	public void insertUpdate(DocumentEvent e) {
		checkMatch();	
	}






	@Override
	public void removeUpdate(DocumentEvent e) {
		checkMatch();	
	}






	@Override
	public void changedUpdate(DocumentEvent e) {
		checkMatch();	
	}
	
	
	
	
	
	
	
	
	
	
	
}//class
