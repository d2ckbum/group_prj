package kr.co.sist.kji;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.util.List;

public class MyInfoView extends JPanel {

	private String id;
	private MemberVO mVO;

	private JLabel pwMatch;

	private JTextField myNameTF;
	private JPasswordField myPassTF;
	private JPasswordField myPassConfirmTF;
	private JTextField myEmailTF;
	private JTextField myTellTF;
	private JTextField myZipcodeTF;
	private JTextField myAddr1TF;
	private JTextField myAddr2TF;

	private JComboBox<String> myMfgCB;
	private JComboBox<String> myCarCB;

	private JButton listBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	
    public MyInfoView(String id) {
        this.id = id;
        MemberService ms = new MemberService();
        mVO = ms.searchOneMember(id);

        setLayout(new BorderLayout());

        // 메인 정보 패널 (상단 제목 포함)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(245, 245, 245));

        TitledBorder border = BorderFactory.createTitledBorder("내 정보");
        border.setTitleFont(new Font("Dialog", Font.BOLD, 22));
        border.setTitleJustification(TitledBorder.CENTER);
        mainPanel.setBorder(border);

        // 회원 기본정보
        mainPanel.add(infoPanel("회원번호", String.valueOf(mVO.getMemNum())));
        mainPanel.add(infoPanel("아이디", mVO.getMemId()));

        myNameTF = textFieldWithValue(mainPanel, "이름", mVO.getMemName());
        myPassTF = passwordFieldWithValue(mainPanel, "비밀번호", mVO.getMemPass());
        myPassConfirmTF = passwordFieldWithValue(mainPanel, "비밀번호 확인", mVO.getMemPass());

        pwMatch = new JLabel();
        pwMatch.setFont(new Font("Dialog", Font.PLAIN, 12));
        pwMatch.setForeground(Color.RED);
        JPanel pwCheckPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pwCheckPanel.setBackground(new Color(245, 245, 245));
        pwCheckPanel.add(pwMatch);
        mainPanel.add(pwCheckPanel);

        myEmailTF = textFieldWithValue(mainPanel, "이메일", mVO.getMemEmail());
        myTellTF = textFieldWithValue(mainPanel, "전화번호", mVO.getMemTell());

        // 차량 정보
        CarService cs = new CarService();
        List<CarVO> carList = cs.searchAllMember();
        String[] carTypes = carList.stream().map(CarVO::getCarType).toArray(String[]::new);

        MfgService mfgs = new MfgService();
        List<MfgVO> mfgList = mfgs.searchAllMember();
        String[] mfgNames = mfgList.stream().map(MfgVO::getMfgName).toArray(String[]::new);

        JPanel carPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        carPanel.setBackground(new Color(245, 245, 245));
        carPanel.add(new JLabel("제조사"));
        myMfgCB = new JComboBox<>(mfgNames);
        myMfgCB.setSelectedIndex(mVO.getMfgNum() - 1);
        carPanel.add(myMfgCB);

        carPanel.add(new JLabel("차종"));
        myCarCB = new JComboBox<>(carTypes);
        myCarCB.setSelectedIndex(mVO.getCarNum() - 1);
        carPanel.add(myCarCB);

        mainPanel.add(carPanel);

        myZipcodeTF = textFieldWithValue(mainPanel, "우편번호", mVO.getMemZipcode());
        myAddr1TF = textFieldWithValue(mainPanel, "주소", mVO.getMemAddr1());
        myAddr2TF = textFieldWithValue(mainPanel, "상세주소", mVO.getMemAddr2());

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        listBtn = new JButton("내 정비 목록");
        updateBtn = new JButton("수정");
        deleteBtn = new JButton("회원 탈퇴");

        styleButton(listBtn, new Color(217, 217, 217));
        styleButton(updateBtn, new Color(217, 217, 217));
        styleButton(deleteBtn, new Color(255, 127, 127));

        buttonPanel.add(listBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        // 이벤트 연결
        MyInfoEvt mie = new MyInfoEvt(this);
        updateBtn.addActionListener(mie);
        deleteBtn.addActionListener(mie);
        myPassTF.getDocument().addDocumentListener(mie);
        myPassConfirmTF.getDocument().addDocumentListener(mie);

        // 추가
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel infoPanel(String labelText, String valueText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(245, 245, 245));
        JLabel label = new JLabel(labelText + ": " + valueText);
        label.setFont(new Font("Dialog", Font.BOLD, 16));
        panel.add(label);
        return panel;
    }

    private JTextField textFieldWithValue(JPanel parent, String label, String value) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(245, 245, 245));
        JLabel jlabel = new JLabel(label);
        jlabel.setPreferredSize(new Dimension(100, 25));
        JTextField tf = new JTextField(value, 20);
        panel.add(jlabel);
        panel.add(tf);
        parent.add(panel);
        return tf;
    }

    private JPasswordField passwordFieldWithValue(JPanel parent, String label, String value) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(245, 245, 245));
        JLabel jlabel = new JLabel(label);
        jlabel.setPreferredSize(new Dimension(100, 25));
        JPasswordField pf = new JPasswordField(value, 20);
        panel.add(jlabel);
        panel.add(pf);
        parent.add(panel);
        return pf;
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        button.setFocusPainted(false);
    }

	public String getId() {
		return id;
	}

	public JButton getUpdateBtn() {
		return updateBtn;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public JButton getListBtn() {
		return listBtn;
	}

	public MemberVO getmVO() {
		return mVO;
	}

	public JTextField getMyNameTF() {
		return myNameTF;
	}

	public JPasswordField getMyPassTF() {
		return myPassTF;
	}

	public JPasswordField getMyPassConfirmTF() {
		return myPassConfirmTF;
	}

	public JTextField getMyEmailTF() {
		return myEmailTF;
	}

	public JTextField getMyTellTF() {
		return myTellTF;
	}

	public JTextField getMyZipcodeTF() {
		return myZipcodeTF;
	}

	public JTextField getMyAddr1TF() {
		return myAddr1TF;
	}

	public JTextField getMyAddr2TF() {
		return myAddr2TF;
	}

	public JComboBox<String> getMyMfgCB() {
		return myMfgCB;
	}

	public JComboBox<String> getMyCarCB() {
		return myCarCB;
	}

	public JLabel getPwMatch() {
		return pwMatch;
	}
}
