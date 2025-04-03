package kr.co.sist.cjw.view;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;


public class Mem_Inquiry_View extends JFrame {
	//button
		private JButton addJbtn;
		private JButton cnlMainJbtn;
		private JButton saveBtn;
		private JButton cnlBtn;
		private JButton logoutWriteBtn;
		private JButton logoutConfirmBtn;
		private JButton logoutEditBtn;
		private JButton confirmBtn;
		private JButton editBtn;
		private JButton cnlEditBtn;
		
		
		//table
		private JTable faqTable;
		private JTable inqTable;
		
		//scroll
		private JScrollPane inqWriteJsp;
		private JScrollPane inqConfirmJsp;
		private JScrollPane inqReplyJsp;
		private JScrollPane inqEditJsp;
		
		//TextArea
		private JTextArea subWriteJta;
		private JTextArea contentsWriteJta;
		private JTextArea subConfirmJta;
		private JTextArea contentsConfirmJta;
		private JTextArea replyConfirmJta;
		private JTextArea subEditJta;
		private JTextArea contentsEditJta;
	
		

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void mem_Inquiry_Main_View() {
		JFrame mem_Inquiry_Main_View = new JFrame("문의게시판");
		
		mem_Inquiry_Main_View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mem_Inquiry_Main_View.setLayout(null);
		mem_Inquiry_Main_View.setResizable(false);
		mem_Inquiry_Main_View.setBounds(800, 600, 1200, 900); 

	    //라벨
	    JLabel logoJlbl = new JLabel("쌍용 엔진오일 샵");
	    logoJlbl.setBounds(550, 20, 100, 25); 
	    mem_Inquiry_Main_View.add(logoJlbl);
	    JLabel faqJlbl = new JLabel("FAQ");
	    faqJlbl.setBounds(70, 65, 100, 25); 
	    mem_Inquiry_Main_View.add(faqJlbl);
	    JLabel inqNumJlbl = new JLabel("문의번호");
	    inqNumJlbl.setBounds(90, 350, 100, 25); 
	    mem_Inquiry_Main_View.add(inqNumJlbl);
	    JLabel inqDateJlbl = new JLabel("날짜");
	    inqDateJlbl.setBounds(300, 350, 100, 25); 
	    mem_Inquiry_Main_View.add(inqDateJlbl);
	    JLabel inqSubJlbl = new JLabel("제목");
	    inqSubJlbl.setBounds(600, 350, 200, 25); 
	    mem_Inquiry_Main_View.add(inqSubJlbl);
	    JLabel inqStatusJlbl = new JLabel("상태");
	    inqStatusJlbl.setBounds(1030, 350, 100, 25); 
	    mem_Inquiry_Main_View.add(inqStatusJlbl);
	    
	    //버튼 추가
	    addJbtn = new JButton("문의작성");
	    addJbtn.setBounds(930, 800, 100, 30); 
	    mem_Inquiry_Main_View.add(addJbtn);
	    
	    cnlMainJbtn = new JButton("취소");
	    cnlMainJbtn.setBounds(1050, 800, 100, 30); 
	    mem_Inquiry_Main_View.add(cnlMainJbtn);
	    
	    //구분선 추가
	    JPanel separator1 = new JPanel();
	    separator1.setBackground(Color.lightGray);
	    separator1.setBounds(50, 60, 1100, 2);
	    mem_Inquiry_Main_View.add(separator1);


	    //FAQ 테이블
	    faqTable = new JTable(); 
	    JScrollPane faqScrollPane = new JScrollPane(faqTable);
	    faqScrollPane.setBounds(50, 90, 1100, 260); 
	    faqScrollPane.setBorder(new LineBorder(Color.lightGray));
	    mem_Inquiry_Main_View.add(faqScrollPane);

	    //문의 목록 테이블
	    inqTable = new JTable(); 
	    JScrollPane inqScrollPane = new JScrollPane(inqTable);
	    inqScrollPane.setBounds(50, 380, 1100, 410); 
	    inqScrollPane.setBorder(new LineBorder(Color.lightGray));
	    mem_Inquiry_Main_View.add(inqScrollPane);


	    mem_Inquiry_Main_View.setVisible(true); 
	}//mem_Inquiry_Main_View
	
	
	
	public void mem_Inquiry_Write_View() {
		JFrame mem_Inquiry_Write_View = new JFrame("문의 작성");
		mem_Inquiry_Write_View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mem_Inquiry_Write_View.setLayout(null);
		mem_Inquiry_Write_View.setResizable(false);
		mem_Inquiry_Write_View.setBounds(800, 600, 1200, 900); 
		
		
		//라벨
		JLabel logoJlbl = new JLabel("쌍용 엔진오일 샵");
	    logoJlbl.setBounds(550, 20, 100, 25); 
	    mem_Inquiry_Write_View.add(logoJlbl);
	    
	    //문의 제목 작성 영역
	    JLabel titleLabel = new JLabel("문의제목");
	    titleLabel.setBounds(70, 100, 50, 25);
	    mem_Inquiry_Write_View.add(titleLabel);
	    
	    subWriteJta = new JTextArea();
	    subWriteJta.setBounds(70, 130, 1060, 30);
	    subWriteJta.setLineWrap(true);
	    subWriteJta.setWrapStyleWord(true);
	    subWriteJta.setBorder(new LineBorder(Color.lightGray));
	    subWriteJta.setMargin(new Insets(5, 5, 5, 5)); 
	    mem_Inquiry_Write_View.add(subWriteJta);

	    //문의 내용 작성 영역
	    JLabel contentLabel = new JLabel("문의내용");
	    contentLabel.setBounds(70, 170, 50, 25);
	    mem_Inquiry_Write_View.add(contentLabel);
	    
	    contentsWriteJta = new JTextArea();
	    contentsWriteJta.setLineWrap(true);
	    contentsWriteJta.setWrapStyleWord(true); 
	    contentsWriteJta.setMargin(new Insets(5, 5, 5, 5)); 
	    mem_Inquiry_Write_View.add(contentsWriteJta);

	    inqWriteJsp = new JScrollPane(contentsWriteJta);
	    inqWriteJsp.setBounds(70, 195, 1060, 580); 
	    inqWriteJsp.setBorder(new LineBorder(Color.lightGray));
	    mem_Inquiry_Write_View.add(inqWriteJsp);
	    
	    // 버튼 추가
	    logoutWriteBtn = new JButton("로그아웃");
	    logoutWriteBtn.setBounds(1030,70,100,30);
	    mem_Inquiry_Write_View.add(logoutWriteBtn);
	    
	    saveBtn = new JButton("저장");
	    saveBtn.setBounds(920, 800, 100, 40); 
	    mem_Inquiry_Write_View.add(saveBtn);

	    cnlBtn = new JButton("취소");
	    cnlBtn.setBounds(1030, 800, 100, 40); 
	    mem_Inquiry_Write_View.add(cnlBtn);
	    
	    mem_Inquiry_Write_View.setVisible(true);
		
	}//mem_Inquiry_Write_View
	
	public void mem_Inquiry_Confirm_View() {
		JFrame mem_Inquiry_Confirm_View = new JFrame("문의 답변");
		mem_Inquiry_Confirm_View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mem_Inquiry_Confirm_View.setLayout(null);
		mem_Inquiry_Confirm_View.setResizable(false);
		mem_Inquiry_Confirm_View.setBounds(800, 600, 1200, 900); 
		
		
		//라벨
		JLabel logoJlbl = new JLabel("쌍용 엔진오일 샵");
	    logoJlbl.setBounds(550, 20, 100, 25); 
	    mem_Inquiry_Confirm_View.add(logoJlbl);
	    
	    
	    //문의 제목 확인 영역
	    JLabel inqDateLabel = new JLabel("25.01.01");
	    inqDateLabel.setBounds(70, 1100, 50, 25);
	    mem_Inquiry_Confirm_View.add(inqDateLabel);
	    JLabel titleLabel = new JLabel("문의제목");
	    titleLabel.setBounds(70, 100, 50, 25);
	    mem_Inquiry_Confirm_View.add(titleLabel);
	    
	    subConfirmJta = new JTextArea();
	    subConfirmJta.setBounds(70, 130, 1060, 30);
	    subConfirmJta.setLineWrap(true);
	    subConfirmJta.setEditable(false);
	    subConfirmJta.setWrapStyleWord(true);
	    
	    subConfirmJta.setBorder(new LineBorder(Color.lightGray));
	    subConfirmJta.setMargin(new Insets(5, 5, 5, 5)); 
	    mem_Inquiry_Confirm_View.add(subConfirmJta);

	    //문의 내용 확인 영역
	    JLabel contentLabel = new JLabel("문의내용");
	    contentLabel.setBounds(70, 170, 50, 25);
	    mem_Inquiry_Confirm_View.add(contentLabel);
	    
	    contentsConfirmJta = new JTextArea();
	    contentsConfirmJta.setLineWrap(true);
	    contentsConfirmJta.setWrapStyleWord(true); 
	    contentsConfirmJta.setEditable(false);
	    contentsConfirmJta.setMargin(new Insets(5, 5, 5, 5)); 
	    mem_Inquiry_Confirm_View.add(contentsConfirmJta);

	    inqConfirmJsp = new JScrollPane(contentsConfirmJta);
	    inqConfirmJsp.setBounds(70, 195, 1060, 265); 
	    inqConfirmJsp.setBorder(new LineBorder(Color.lightGray));
	    mem_Inquiry_Confirm_View.add(inqConfirmJsp);
	    
	  //문의 내용 확인 영역
	    JLabel replyLabel = new JLabel("답변");
	    replyLabel.setBounds(70, 470, 50, 25);
	    mem_Inquiry_Confirm_View.add(replyLabel);
	    
	    replyConfirmJta = new JTextArea();
	    replyConfirmJta.setLineWrap(true);
	    replyConfirmJta.setWrapStyleWord(true); 
	    replyConfirmJta.setEditable(false);
	    replyConfirmJta.setMargin(new Insets(5, 5, 5, 5)); 
	    mem_Inquiry_Confirm_View.add(replyConfirmJta);

	    inqReplyJsp = new JScrollPane(replyConfirmJta);
	    inqReplyJsp.setBounds(70, 500, 1060, 280); 
	    inqReplyJsp.setBorder(new LineBorder(Color.lightGray));
	    mem_Inquiry_Confirm_View.add(inqReplyJsp);
	    
	    
	    // 버튼 추가
	    logoutConfirmBtn = new JButton("로그아웃");
	    logoutConfirmBtn.setBounds(1030,70,100,30);
	    mem_Inquiry_Confirm_View.add(logoutConfirmBtn);
	    
	    confirmBtn = new JButton("확인");
	    confirmBtn.setBounds(550, 800, 100, 40); 
	    mem_Inquiry_Confirm_View.add(confirmBtn);

	    
	    mem_Inquiry_Confirm_View.setVisible(true);
		
	}//mem_Inquiry_Confirm_View
	
	public void mem_Inquiry_Edit_View() {
		JFrame mem_Inquiry_Edit_View = new JFrame("문의 수정");
		mem_Inquiry_Edit_View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mem_Inquiry_Edit_View.setLayout(null);
		mem_Inquiry_Edit_View.setResizable(false);
		mem_Inquiry_Edit_View.setBounds(800, 600, 1200, 900); 
		
		
		//라벨
		JLabel logoJlbl = new JLabel("쌍용 엔진오일 샵");
	    logoJlbl.setBounds(550, 20, 100, 25); 
	    mem_Inquiry_Edit_View.add(logoJlbl);
	    
	    //문의 제목 작성 영역
	    JLabel titleLabel = new JLabel("문의제목");
	    titleLabel.setBounds(70, 100, 50, 25);
	    mem_Inquiry_Edit_View.add(titleLabel);
	    
	    subEditJta = new JTextArea();
	    subEditJta.setBounds(70, 130, 1060, 30);
	    subEditJta.setLineWrap(true);
	    subEditJta.setWrapStyleWord(true);
	    subEditJta.setBorder(new LineBorder(Color.lightGray));
	    subEditJta.setMargin(new Insets(5, 5, 5, 5)); 
	    mem_Inquiry_Edit_View.add(subEditJta);

	    //문의 내용 작성 영역
	    JLabel contentLabel = new JLabel("문의내용");
	    contentLabel.setBounds(70, 170, 50, 25);
	    mem_Inquiry_Edit_View.add(contentLabel);
	    
	    contentsEditJta = new JTextArea();
	    contentsEditJta.setLineWrap(true);
	    contentsEditJta.setWrapStyleWord(true); 
	    contentsEditJta.setMargin(new Insets(5, 5, 5, 5)); 
	    mem_Inquiry_Edit_View.add(contentsEditJta);

	    inqEditJsp = new JScrollPane(contentsEditJta);
	    inqEditJsp.setBounds(70, 195, 1060, 580); 
	    inqEditJsp.setBorder(new LineBorder(Color.lightGray));
	    mem_Inquiry_Edit_View.add(inqEditJsp);
	    
	    // 버튼 추가
	    logoutEditBtn = new JButton("로그아웃");
	    logoutEditBtn.setBounds(1030,70,100,30);
	    mem_Inquiry_Edit_View.add(logoutEditBtn);
	    
	    editBtn = new JButton("수정");
	    editBtn.setBounds(920, 800, 100, 40); 
	    mem_Inquiry_Edit_View.add(editBtn);

	    cnlEditBtn = new JButton("취소");
	    cnlEditBtn.setBounds(1030, 800, 100, 40); 
	    mem_Inquiry_Edit_View.add(cnlEditBtn);
	    
	    mem_Inquiry_Edit_View.setVisible(true);
	}//mem_Inquiry_Edit_View


}//class
