package kr.co.sist.kji;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyInfoEvt implements ActionListener, DocumentListener {

	private MyInfoView miv;
	private String id;

	private JTextField nameField, emailField, phoneField, zipField, addrField, detailAddrField;
	private JPasswordField pwField, pwConfirmField;
	private JComboBox<String> makerBox, modelBox;
	private MemberVO mVO = new MemberVO();

	public MyInfoEvt(MyInfoView miv) {
		this.miv = miv;
		this.id = miv.getId();

		// 컴포넌트 연결
		nameField = miv.getMyNameTF();
		emailField = miv.getMyEmailTF();
		phoneField = miv.getMyTellTF();
		zipField = miv.getMyZipcodeTF();
		addrField = miv.getMyAddr1TF();
		detailAddrField = miv.getMyAddr2TF();
		pwField = miv.getMyPassTF();
		pwConfirmField = miv.getMyPassConfirmTF(); // 🔧 수정된 부분
		makerBox = miv.getMyMfgCB();
		modelBox = miv.getMyCarCB();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		MemberService ms = new MemberService();

		if (obj == miv.getListBtn()) {
			 Window window = SwingUtilities.getWindowAncestor(miv);
			 if (window instanceof Frame) {
	                new MyFixInfoView((Frame) window, miv.getId()); // 모달 다이얼로그 호출
	            }
			return;
		}

		if (obj == miv.getUpdateBtn()) {
			String pass = new String(pwField.getPassword());
			String passConf = new String(pwConfirmField.getPassword());

			if (!checkFields()) return;

			if (!pass.equals(passConf)) {
				JOptionPane.showMessageDialog(miv, "비밀번호가 일치하지 않습니다.");
				return;
			}

			int confirm = JOptionPane.showConfirmDialog(miv, "수정하시겠습니까?");
			if (confirm == JOptionPane.YES_OPTION) {
				updateMember();
				if (!EmailValidator.isValidFormat(mVO.getMemEmail().trim())) {
		            JOptionPane.showMessageDialog(miv, "이메일 형식이 올바르지 않습니다.");
		        } else if (!EmailValidator.isDomainExists(mVO.getMemEmail().trim())) {
		        	JOptionPane.showMessageDialog(miv, "이메일 도메인이 존재하지 않습니다.");
		        } else {
				ms.modifyMember(mVO);
				JOptionPane.showMessageDialog(miv, "수정되었습니다.");
		        }
			}
		}

		if (obj == miv.getDeleteBtn()) {
			int confirm = JOptionPane.showConfirmDialog(miv, "정말 탈퇴하시겠습니까?");
			if (confirm == JOptionPane.YES_OPTION) {
				ms.removeMember(id);
				JOptionPane.showMessageDialog(miv, "탈퇴되었습니다.");
				new LoginpageView();
			}
		}
	}

	private void checkMatch() {
		String pass = new String(pwField.getPassword());
		String passConf = new String(pwConfirmField.getPassword());
		JLabel pwMatch = miv.getPwMatch();
		pwMatch.setVisible(true);

		if (pass.isEmpty() && passConf.isEmpty()) {
			pwMatch.setText(" ");
		} else if (pass.equals(passConf)) {
			pwMatch.setText("비밀번호 일치");
			pwMatch.setForeground(Color.BLUE);
		} else {
			pwMatch.setText("비밀번호 불일치");
			pwMatch.setForeground(Color.RED);
		}
	}

	private boolean checkFields() {
		if (isEmpty(nameField, "이름")) return false;
		if (isEmpty(pwField, "비밀번호")) return false;
		if (isEmpty(pwConfirmField, "비밀번호 확인")) return false;
		if (isEmpty(emailField, "이메일")) return false;
		if (isEmpty(phoneField, "전화번호")) return false;
		if (isEmpty(zipField, "우편번호")) return false;
		if (isEmpty(addrField, "주소")) return false;
		if (isEmpty(detailAddrField, "상세주소")) return false;
		return true;
	}

	private boolean isEmpty(JTextField tf, String fieldName) {
		if (tf.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(miv, fieldName + "을(를) 입력해주세요.");
			tf.requestFocus();
			return true;
		}
		return false;
	}

	private boolean isEmpty(JPasswordField pf, String fieldName) {
		if (new String(pf.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(miv, fieldName + "을(를) 입력해주세요.");
			pf.requestFocus();
			return true;
		}
		return false;
	}

	private void updateMember() {
		mVO.setMemId(id);
		mVO.setMemPass(new String(pwField.getPassword()));
		mVO.setMemName(nameField.getText());
		mVO.setMemEmail(emailField.getText());
		mVO.setMemTell(phoneField.getText());
		mVO.setMemZipcode(zipField.getText());
		mVO.setMemAddr1(addrField.getText());
		mVO.setMemAddr2(detailAddrField.getText());
		mVO.setMfgNum(makerBox.getSelectedIndex() + 1);
		mVO.setCarNum(modelBox.getSelectedIndex() + 1);
	}

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
}
