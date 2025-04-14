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

public class JoinMemberEvt implements ActionListener, DocumentListener{
	private JoinMemberView jmv;
	private JTextField idField, nameField, emailField, phoneField, zipField, addrField, detailAddrField;
	private JPasswordField pwField, pwConfirmField;
	private JComboBox<String> makerBox, modelBox;
	private MemberVO mVO= new MemberVO();
	private boolean idChkFlag = false;
	private boolean emptyField = false;	
	
	
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
		makerBox = jmv.getMakerBox();
		modelBox = jmv.getModelBox();
	}//JoinMemberEvt
	
	
	
	 private void checkMatchId() {
		jmv.getIdUnavailable().setVisible(false);
		jmv.getIdAvailable().setVisible(false);
		idChkFlag=false;
        
    }//checkMatch
	 private void checkMatch() {
		String pass = new String(pwField.getPassword());
		String passConf = new String(pwConfirmField.getPassword());
     	jmv.getPwMatch().setVisible(true);
         if (pass.isEmpty() && passConf.isEmpty()) {
             jmv.getPwMatch().setText(" ");
         } else if (pass.equals(passConf)) {
         	jmv.getPwMatch().setText("비밀번호 일치");
         	jmv.getPwMatch().setForeground(Color.BLUE);
         } else {
         	jmv.getPwMatch().setText(" 비밀번호 불일치");
         	jmv.getPwMatch().setForeground(Color.RED);
         }
     }//checkMatch
	private boolean jtfEmptyChk(JTextField jtf) {
		boolean flag = false;
		
		flag = jtf.getText().trim().isEmpty(); // 아이디가 비어있지 않니?
		if (flag) {// 텍스트필드에 값이 없는경우
			jtf.requestFocus();// 커서를 입력 컴포넌트로 이동
		}//end if 

		return flag;
	}
	
	private boolean jpfEmptyChk(JPasswordField jpf) {
		boolean flag = false;
		String pass = new String(jpf.getPassword());
		flag = pass.isEmpty(); // 비밀번호가 비어있지 않니?
		if (flag) {// 비밀번호에 값이 없는경우
			jpf.requestFocus();// 커서를 패스워드 입력 컴포넌트로 이동
		}//end if 

		return flag;
	}
	
	private void emptyChk() {
		if(jtfEmptyChk(idField)) {JOptionPane.showMessageDialog(jmv, "아이디가 비었습니다");return;}
		if(jtfEmptyChk(nameField)) {JOptionPane.showMessageDialog(jmv, "이름이 비었습니다");return;}
		if(jpfEmptyChk(pwField)) {JOptionPane.showMessageDialog(jmv, "비밀번호가 비었습니다");return;}
		if(jpfEmptyChk(pwConfirmField)) {JOptionPane.showMessageDialog(jmv, "비밀번호확인이 비었습니다");return;}
		if(jtfEmptyChk(emailField)) {JOptionPane.showMessageDialog(jmv, "이메일이 비었습니다");return;}
		if(jtfEmptyChk(phoneField)) {JOptionPane.showMessageDialog(jmv, "전화번호가 비었습니다");return;}
		if(jtfEmptyChk(zipField)) {JOptionPane.showMessageDialog(jmv, "우편번호가 비었습니다");return;}
		if(jtfEmptyChk(addrField)) {JOptionPane.showMessageDialog(jmv, "주소가 비었습니다");return;}
		if(jtfEmptyChk(detailAddrField)) {JOptionPane.showMessageDialog(jmv, "상세주소가 비었습니다");return;}
		emptyField = true;
	}//emptyChk
	
	private void insertMember() {
		mVO.setMemId(idField.getText().toString());
		mVO.setMemPass( pwField.getText().toString());
		mVO.setMemName(nameField.getText().toString());
		mVO.setMemEmail(emailField.getText().toString());
		mVO.setMemTell(phoneField.getText().toString());
		mVO.setMemZipcode(zipField.getText().toString());
		mVO.setMemAddr1(addrField.getText().toString());
		mVO.setMemAddr2(detailAddrField.getText().toString());
		mVO.setCarNum(modelBox.getSelectedIndex()+1);
		mVO.setMfgNum(makerBox.getSelectedIndex()+1);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		MemberService ms = new MemberService();
		if(e.getSource() == jmv.getSignupBtn()) {
			int realJoinNum = -1;
			String pass = new String(pwField.getPassword());
			String passConf = new String(pwConfirmField.getPassword());
			
			if(!idChkFlag) {
				JOptionPane.showMessageDialog(jmv, "중복확인을 해주세요");
				return;
			}//end if
			emptyChk();
			if(!pass.equals(passConf)) {
				JOptionPane.showMessageDialog(jmv, "비밀번호가 다릅니다");
			}
			
			if( emptyField&&pass.equals(passConf)&&idChkFlag) {
				realJoinNum= JOptionPane.showConfirmDialog(jmv, "가입 하시겠습니까?");
			}//end if
			if(realJoinNum == 0) {
				insertMember();
				if (!EmailValidator.isValidFormat(mVO.getMemEmail().trim())) {
		            JOptionPane.showMessageDialog(jmv, "이메일 형식이 올바르지 않습니다.");
		        } else if (!EmailValidator.isDomainExists(mVO.getMemEmail().trim())) {
		        	JOptionPane.showMessageDialog(jmv, "이메일 도메인이 존재하지 않습니다.");
		        } else {
		            if(ms.addMember(mVO)) {
		            	JOptionPane.showMessageDialog(jmv, "가입 되었습니다.");
		            	new LoginpageView();
		            	jmv.dispose();
		            }
		        }
			}//end if
			
		}//end if
		if(e.getSource() == jmv.getCheckBtn()) {
			String id = idField.getText().trim();
			String idCon="";
			try {
			idCon=ms.searchOneMember(id).getMemId();
			}catch(NullPointerException npe) {
				if(jtfEmptyChk(idField)) {JOptionPane.showMessageDialog(jmv, "아이디가 비었습니다");return;}
				idChkFlag = true;
				idCon=id;
				jmv.getIdUnavailable().setVisible(false);
				jmv.getIdAvailable().setVisible(true);
				return;
			}//end catch
			
			if(!idCon.isEmpty()) {
				idChkFlag = false;
				jmv.getIdAvailable().setVisible(false);
				jmv.getIdUnavailable().setVisible(true);
				return;
			}//end if
			
		}//end if
		
		if(e.getSource()==jmv.getCancelBtn()) {
			new LoginpageView();
			jmv.dispose();
		}//end if
		
		if(e.getSource()==jmv.getSearchZip()) {
			SearchZipCodeView szcv= new SearchZipCodeView(jmv);
			jmv.getZipField().setText(szcv.getZipcode());
			jmv.getAddrField().setText(szcv.getAddr());
		}//end if
			
			
		
	}//actionPerformed

	@Override
	public void insertUpdate(DocumentEvent e) {
		if (e.getDocument() == idField.getDocument()) {
			checkMatchId();
		}
		checkMatch();	
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		checkMatch();
		if (e.getDocument() == idField.getDocument()) {
			checkMatchId();
		}
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		checkMatch();
		if (e.getDocument() == idField.getDocument()) {
			checkMatchId();
		}
	}

}//class
