package kr.co.sist.kji;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinMemberView extends JFrame {

	private JTextField idField, nameField, emailField, phoneField, zipField, addrField, detailAddrField;
	private JPasswordField pwField, pwConfirmField;
	private JButton checkBtn,signupBtn,cancelBtn;
	private JComboBox<String> makerBox, modelBox;
	JLabel pwMatch, idUnavailable,idAvailable;
	public JoinMemberView() {

		setTitle("회원가입");
		setSize(800, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X 버튼 눌러도 종료 안됨
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);

		Font labelFont = new Font("맑은 고딕", Font.PLAIN, 14);

		JLabel title = new JLabel("회원가입");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
		title.setBounds(340, 20, 200, 30);
		panel.add(title);

		// 아이디
		JLabel idLabel = new JLabel("아이디");
		idLabel.setBounds(150, 80, 60, 25);
		panel.add(idLabel);

		idField = new JTextField(20);
		idField.setBounds(210, 80, 200, 25);
		panel.add(idField);

		checkBtn = new JButton("중복확인");
		checkBtn.setBounds(420, 80, 100, 25);
		panel.add(checkBtn);

		idAvailable = new JLabel("사용 가능한 ID");
		idAvailable.setForeground(Color.BLUE);
		idAvailable.setBounds(550, 80, 100, 25);
		idAvailable.setVisible(false);
		panel.add(idAvailable);

		idUnavailable = new JLabel("중복된 ID");
		idUnavailable.setForeground(Color.RED);
		idUnavailable.setBounds(550, 80, 100, 25);
		idUnavailable.setVisible(false);
		panel.add(idUnavailable);

		// 이름
		JLabel nameLabel = new JLabel("이름");
		nameLabel.setBounds(150, 120, 60, 25);
		panel.add(nameLabel);

		nameField = new JTextField();
		nameField.setBounds(210, 120, 360, 25);
		panel.add(nameField);

		// 비밀번호
		JLabel pwLabel = new JLabel("비밀번호");
		pwLabel.setBounds(150, 160, 60, 25);
		panel.add(pwLabel);

		pwField = new JPasswordField();
		pwField.setBounds(210, 160, 360, 25);
		panel.add(pwField);

		// 비밀번호 확인
		JLabel pwConfirmLabel = new JLabel("비밀번호 확인");
		pwConfirmLabel.setBounds(130, 200, 100, 25);
		panel.add(pwConfirmLabel);

		pwConfirmField = new JPasswordField();
		pwConfirmField.setBounds(210, 200, 360, 25);
		panel.add(pwConfirmField);

		pwMatch = new JLabel();
		pwMatch.setBounds(580, 200, 100, 25);
		panel.add(pwMatch);


		// 이메일
		JLabel emailLabel = new JLabel("이메일");
		emailLabel.setBounds(150, 240, 60, 25);
		panel.add(emailLabel);

		emailField = new JTextField();
		emailField.setBounds(210, 240, 360, 25);
		panel.add(emailField);

		// 전화번호
		JLabel phoneLabel = new JLabel("전화번호");
		phoneLabel.setBounds(140, 280, 70, 25);
		panel.add(phoneLabel);

		phoneField = new JTextField();
		phoneField.setBounds(210, 280, 360, 25);
		panel.add(phoneField);

		// 차량등록
		JLabel carLabel = new JLabel("차량등록");
		carLabel.setBounds(140, 320, 70, 25);
		panel.add(carLabel);

		JLabel makerLabel = new JLabel("제조사");
		makerLabel.setBounds(220, 320, 50, 25);
		panel.add(makerLabel);
		
		MfgService mfgs = new MfgService();
		List<MfgVO> mfgVO = new ArrayList<MfgVO>();
		mfgVO = mfgs.searchAllMember();
		String[] mfgName = new String[mfgVO.size()];
		for(int i =0; i< mfgVO.size(); i++) {
			mfgName[i] = mfgVO.get(i).getMfgName();
		}//end for
		
		
		makerBox = new JComboBox<>(mfgName);
		makerBox.setBounds(270, 320, 80, 25);
		panel.add(makerBox);

		JLabel modelLabel = new JLabel("차종");
		modelLabel.setBounds(360, 320, 40, 25);
		panel.add(modelLabel);

		CarService cs = new CarService();
		List<CarVO> cVO = new ArrayList<CarVO>();
		cVO = cs.searchAllMember();
		String[] carType = new String[cVO.size()];
		
		for(int i =0; i< cVO.size(); i++) {
			carType[i] = cVO.get(i).getCarType();
		}//end for
		
		modelBox = new JComboBox<>(carType);
		modelBox.setBounds(400, 320, 120, 25);
		panel.add(modelBox);

		// 우편번호
		JLabel zipLabel = new JLabel("우편번호");
		zipLabel.setBounds(140, 360, 70, 25);
		panel.add(zipLabel);

		zipField = new JTextField();
		zipField.setBounds(210, 360, 360, 25);
		panel.add(zipField);

		// 주소
		JLabel addrLabel = new JLabel("주소");
		addrLabel.setBounds(160, 400, 50, 25);
		panel.add(addrLabel);

		addrField = new JTextField();
		addrField.setBounds(210, 400, 360, 25);
		panel.add(addrField);

		// 상세주소
		JLabel detailAddrLabel = new JLabel("상세주소");
		detailAddrLabel.setBounds(140, 440, 70, 25);
		panel.add(detailAddrLabel);

		detailAddrField = new JTextField();
		detailAddrField.setBounds(210, 440, 360, 25);
		panel.add(detailAddrField);

		// 버튼
		signupBtn = new JButton("가입");
		signupBtn.setBounds(280, 500, 80, 30);
		panel.add(signupBtn);

		cancelBtn = new JButton("돌아가기");
		cancelBtn.setBounds(380, 500, 100, 30);
		panel.add(cancelBtn);

		JoinMemberEvt jme = new JoinMemberEvt(this);
		signupBtn.addActionListener(jme);
		checkBtn.addActionListener(jme);
		cancelBtn.addActionListener(jme);
		pwField.getDocument().addDocumentListener(jme);
		pwConfirmField.getDocument().addDocumentListener(jme);
		
		
		add(panel);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public JTextField getIdField() {
		return idField;
	}
	public JTextField getNameField() {
		return nameField;
	}
	public JTextField getEmailField() {
		return emailField;
	}
	public JTextField getPhoneField() {
		return phoneField;
	}
	public JTextField getZipField() {
		return zipField;
	}
	public JTextField getAddrField() {
		return addrField;
	}
	public JTextField getDetailAddrField() {
		return detailAddrField;
	}
	public JPasswordField getPwField() {
		return pwField;
	}
	public JPasswordField getPwConfirmField() {
		return pwConfirmField;
	}
	public JButton getCheckBtn() {
		return checkBtn;
	}
	public JButton getSignupBtn() {
		return signupBtn;
	}
	public JButton getCancelBtn() {
		return cancelBtn;
	}
	public JComboBox<String> getMakerBox() {
		return makerBox;
	}
	public JComboBox<String> getModelBox() {
		return modelBox;
	}

	public JLabel getPwMatch() {
		return pwMatch;
	}
	public JLabel getIdUnavailable() {
		return idUnavailable;
	}
	public JLabel getIdAvailable() {
		return idAvailable;
	}
	public static void main(String[] args) {
		new JoinMemberView();
	}//main
	
	
}//class
