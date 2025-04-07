package kr.co.sist.kji;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MyInfoView extends JFrame {
	
	
	private String id;
	private MemberVO mVO;
	private JLabel pwMatch;
    // 상단 레이블 및 버튼
    private JButton engineOilBtn;
    private JButton recommendBtn;
    private JButton inquiryBtn;
    private JButton myPageBtn;
    private JButton logoutBtn;

    // 회원정보 패널 내부 필드
    private JPanel myInfoPanel;
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

    // 하단 버튼
    private JButton listBtn;
    private JButton updateBtn;
    private JButton deleteBtn;
    
    
    

    
    public MyInfoView(String id) {
    	JLabel memNum;
        JLabel memId;
    	
    	MemberService ms = new MemberService();
    	
    	this.id = id;
        setTitle("쌍용 엔진오일 샵 - 회원 정보 수정");
        setLayout(null);
        setSize(1080, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     // 상단 레이블
        JLabel shopLabel = new JLabel("쌍용 엔진오일 샵", SwingConstants.CENTER);
        shopLabel.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        shopLabel.setBounds(0, 0, 1080, 60);
        add(shopLabel);

        // 버튼들은 y = 70부터 시작해서 레이블과 분리
        engineOilBtn = new JButton("엔진오일");
        engineOilBtn.setBounds(250, 100, 100, 30);
        add(engineOilBtn);

        recommendBtn = new JButton("추천 상품");
        recommendBtn.setBounds(400, 100, 100, 30);
        add(recommendBtn);

        inquiryBtn = new JButton("문의 게시판");
        inquiryBtn.setBounds(550, 100, 120, 30);
        add(inquiryBtn);

        myPageBtn = new JButton("내정보");
        myPageBtn.setBounds(720, 100, 80, 30);
        add(myPageBtn);

        logoutBtn = new JButton("로그아웃");
        logoutBtn.setBounds(950, 50, 100, 30);
        add(logoutBtn);

        // 로그아웃 이벤트
        logoutBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "로그아웃되었습니다.");
            new LoginpageView();
            dispose();
            
        });

        JLabel infoLabel = new JLabel("회원 정보", SwingConstants.CENTER);
        infoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        infoLabel.setBounds(-20, 130, 1080, 60);
        add(infoLabel);
        
       
        
        // 회원정보 패널
        myInfoPanel = new JPanel(null);
        // 회원정보 패널도 아래로 살짝 내림 (기존보다 y + 50)
        myInfoPanel.setBounds(150, 180, 780, 420);
        add(myInfoPanel);
        
      
        // 필드 위치 설정
        myInfoPanel.add(new JLabel("이름")).setBounds(30, 70, 100, 25);
        myNameTF = new JTextField();
        myNameTF.setBounds(130, 70, 500, 25);
        myInfoPanel.add(myNameTF);

        myInfoPanel.add(new JLabel("비밀번호")).setBounds(30, 110, 100, 25);
        myPassTF = new JPasswordField();
        myPassTF.setBounds(130, 110, 500, 25);
        myInfoPanel.add(myPassTF);
        
        pwMatch = new JLabel();
		pwMatch.setBounds(650, 150, 100, 25);
		myInfoPanel.add(pwMatch);

        myInfoPanel.add(new JLabel("비밀번호 확인")).setBounds(30, 150, 100, 25);
        myPassConfirmTF = new JPasswordField();
        myPassConfirmTF.setBounds(130, 150, 500, 25);
        myInfoPanel.add(myPassConfirmTF);

        myInfoPanel.add(new JLabel("이메일")).setBounds(30, 190, 100, 25);
        myEmailTF = new JTextField();
        myEmailTF.setBounds(130, 190, 500, 25);
        myInfoPanel.add(myEmailTF);

        myInfoPanel.add(new JLabel("전화번호")).setBounds(30, 230, 100, 25);
        myTellTF = new JTextField();
        myTellTF.setBounds(130, 230, 500, 25);
        myInfoPanel.add(myTellTF);

        CarService cs = new CarService();
		List<CarVO> cVO = new ArrayList<CarVO>();
		cVO = cs.searchAllMember();
		String[] carType = new String[cVO.size()];
		
		for(int i =0; i< cVO.size(); i++) {
			carType[i] = cVO.get(i).getCarType();
		}//end for
		
		MfgService mfgs = new MfgService();
		List<MfgVO> mfgVO = new ArrayList<MfgVO>();
		mfgVO = mfgs.searchAllMember();
		String[] mfgName = new String[mfgVO.size()];
		for(int i =0; i< mfgVO.size(); i++) {
			mfgName[i] = mfgVO.get(i).getMfgName();
		}//end for
		
		myInfoPanel.add(new JLabel("차량")).setBounds(30, 270, 100, 25);
        myInfoPanel.add(new JLabel("차종")).setBounds(400, 270, 100, 25);
        myCarCB = new JComboBox<>(carType);
        myCarCB.setBounds(470, 270, 150, 25);
        myInfoPanel.add(myCarCB);

        myInfoPanel.add(new JLabel("제조사")).setBounds(130, 270, 100, 25);
        myMfgCB = new JComboBox<>(mfgName);
        myMfgCB.setBounds(200, 270, 150, 25);
        myInfoPanel.add(myMfgCB);

        myInfoPanel.add(new JLabel("우편번호")).setBounds(30, 310, 100, 25);
        myZipcodeTF = new JTextField();
        myZipcodeTF.setBounds(130, 310, 500, 25);
        myInfoPanel.add(myZipcodeTF);

        myInfoPanel.add(new JLabel("주소")).setBounds(30, 350, 100, 25);
        myAddr1TF = new JTextField();
        myAddr1TF.setBounds(130, 350, 500, 25);
        myInfoPanel.add(myAddr1TF);

        myInfoPanel.add(new JLabel("상세주소")).setBounds(30, 390, 100, 25);
        myAddr2TF = new JTextField();
        myAddr2TF.setBounds(130, 390, 500, 25);
        myInfoPanel.add(myAddr2TF);

        // 하단 버튼
        listBtn = new JButton("내 정비 목록");
        listBtn.setBounds(300, 650, 120, 30);
        add(listBtn);

        updateBtn = new JButton("수정");
        updateBtn.setBounds(462, 650, 100, 30);
        add(updateBtn);

        deleteBtn = new JButton("회원 탈퇴");
        deleteBtn.setBounds(600, 650, 120, 30);
        add(deleteBtn);

        

        mVO = new MemberVO();;
        mVO = ms.searchOneMember(id);
        
        myPassTF.setText(mVO.getMemPass().toString());
        myPassConfirmTF.setText(mVO.getMemPass());
        myNameTF.setText(mVO.getMemName());
        myEmailTF.setText(mVO.getMemEmail());
        myTellTF.setText(mVO.getMemTell());
        myZipcodeTF.setText(mVO.getMemZipcode());
        myAddr1TF.setText(mVO.getMemAddr1());
        myAddr2TF.setText(mVO.getMemAddr2());
        myMfgCB.setSelectedIndex(mVO.getMfgNum()-1);
        myCarCB.setSelectedIndex(mVO.getCarNum()-1);

        
        myInfoPanel.add(new JLabel("회원번호")).setBounds(30, 0, 100, 25);
        memNum= new JLabel(Integer.toString( mVO.getMemNum()));
        memNum.setBounds(130, 0, 500, 25);
        myInfoPanel.add(memNum);

        myInfoPanel.add(new JLabel("아이디")).setBounds(30, 30, 100, 25);
        memId= new JLabel(mVO.getMemId());
        memId.setBounds(130, 30, 500, 25);
        myInfoPanel.add(memId);
        
        
        MyInfoEvt mie = new MyInfoEvt(this);
        listBtn.addActionListener(mie);
        updateBtn.addActionListener(mie); 
        deleteBtn.addActionListener(mie);
//        deleteBtn;
        myPassConfirmTF.getDocument().addDocumentListener(mie);
        myPassTF.getDocument().addDocumentListener(mie);
        
        
        setVisible(true);
    }//MyInfoView




	public JLabel getPwMatch() {
		return pwMatch;
	}




	public MemberVO getmVO() {
		return mVO;
	}




	public String getId() {
		return id;
	}


	public JButton getEngineOilBtn() {
		return engineOilBtn;
	}


	public JButton getRecommendBtn() {
		return recommendBtn;
	}


	public JButton getInquiryBtn() {
		return inquiryBtn;
	}


	public JButton getMyPageBtn() {
		return myPageBtn;
	}


	public JButton getLogoutBtn() {
		return logoutBtn;
	}


	public JPanel getMyInfoPanel() {
		return myInfoPanel;
	}


	public JTextField getMyNameTF() {
		return myNameTF;
	}


	public JPasswordField getMyPassTF() {
		return myPassTF;
	}


	public JPasswordField getMemPassConfirmTF() {
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


	public JButton getListBtn() {
		return listBtn;
	}


	public JButton getUpdateBtn() {
		return updateBtn;
	}


	public JButton getDeleteBtn() {
		return deleteBtn;
	}


}//class
