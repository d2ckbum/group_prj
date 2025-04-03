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

public class Admin_Inquiry_View extends JFrame {
	//button
	private JButton addJbtn;
	private JButton cnlJbtn;
	private JButton saveFAQBtn;
	private JButton cnlFAQBtn;
	private JButton editFAQBtn;
	private JButton delFAQBtn;
	private JButton cnlFAQEditBtn;
	private JButton saveInqBtn;
	private JButton cnlInqBtn;
	private JButton editInqBtn;
	private JButton cnlInqConfirmBtn;
	
	
	//table
	private JTable faqTable;
	private JTable inqTable;
	
	//scroll
	private JScrollPane faqWriteJsp;
	private JScrollPane faqEditJsp;
	private JScrollPane inqWriteJsp;
	private JScrollPane inqReplyWriteJsp;
	private JScrollPane inqEditJsp;
	private JScrollPane inqReplyEditJsp;
	
	
	//TextArea
	private JTextArea subFAQJta;
	private JTextArea contentsFAQJta;
	private JTextArea subFAQEditJta;
	private JTextArea contentsFAQEditJta;
	private JTextArea subInqJta;
	private JTextArea replyInqJta;
	private JTextArea subConfirmJta;
	private JTextArea replyConfirmJta;
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void admin_Inquiry_Main_View() {
		//프레임
	    JFrame admin_Inquiry_Main_View = new JFrame("문의게시판");
	    admin_Inquiry_Main_View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    admin_Inquiry_Main_View.setLayout(null);
	    admin_Inquiry_Main_View.setResizable(false);
	    admin_Inquiry_Main_View.setBounds(800, 600, 1200, 900); 

	    //라벨
	    JLabel adminJlbl = new JLabel("관리자 페이지");
	    adminJlbl.setBounds(550, 20, 100, 25); 
	    admin_Inquiry_Main_View.add(adminJlbl);
	    JLabel faqJlbl = new JLabel("FAQ");
	    faqJlbl.setBounds(70, 65, 100, 25); 
	    admin_Inquiry_Main_View.add(faqJlbl);
	    JLabel inqNumJlbl = new JLabel("문의번호");
	    inqNumJlbl.setBounds(90, 350, 100, 25); 
	    admin_Inquiry_Main_View.add(inqNumJlbl);
	    JLabel inqNameJlbl = new JLabel("고객명");
	    inqNameJlbl.setBounds(230, 350, 100, 25); 
	    admin_Inquiry_Main_View.add(inqNameJlbl);
	    JLabel inqDateJlbl = new JLabel("날짜");
	    inqDateJlbl.setBounds(350, 350, 100, 25); 
	    admin_Inquiry_Main_View.add(inqDateJlbl);
	    JLabel inqSubJlbl = new JLabel("제목");
	    inqSubJlbl.setBounds(700, 350, 200, 25); 
	    admin_Inquiry_Main_View.add(inqSubJlbl);
	    JLabel inqStatusJlbl = new JLabel("상태");
	    inqStatusJlbl.setBounds(1030, 350, 100, 25); 
	    admin_Inquiry_Main_View.add(inqStatusJlbl);
	    
	    //버튼 추가
	    addJbtn = new JButton("등록");
	    addJbtn.setBounds(1060, 63, 70, 25); 
	    admin_Inquiry_Main_View.add(addJbtn);
	    
	    cnlJbtn = new JButton("취소");
	    cnlJbtn.setBounds(550, 800, 90, 40); 
	    admin_Inquiry_Main_View.add(cnlJbtn);
	    
	    //구분선 추가
	    JPanel separator1 = new JPanel();
	    separator1.setBackground(Color.lightGray);
	    separator1.setBounds(50, 60, 1100, 2);
	    admin_Inquiry_Main_View.add(separator1);


	    //FAQ 테이블
	    faqTable = new JTable(); 
	    JScrollPane faqScrollPane = new JScrollPane(faqTable);
	    faqScrollPane.setBounds(50, 90, 1100, 260); 
	    faqScrollPane.setBorder(new LineBorder(Color.lightGray));
	    admin_Inquiry_Main_View.add(faqScrollPane);

	    //문의 목록 테이블
	    inqTable = new JTable(); 
	    JScrollPane inqScrollPane = new JScrollPane(inqTable);
	    inqScrollPane.setBounds(50, 380, 1100, 410); 
	    inqScrollPane.setBorder(new LineBorder(Color.lightGray));
	    admin_Inquiry_Main_View.add(inqScrollPane);


	    admin_Inquiry_Main_View.setVisible(true); 
		
	}//admin_Inquiry_View
	
	
	
	public void admin_FAQ_Write_View() {
		JFrame admin_FAQ_Write_View = new JFrame("FAQ 등록");
		admin_FAQ_Write_View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin_FAQ_Write_View.setLayout(null);
		admin_FAQ_Write_View.setResizable(false);
		admin_FAQ_Write_View.setBounds(800, 600, 1200, 900); 
		
		
		//라벨
	    JLabel adminJlbl = new JLabel("관리자 페이지");
	    adminJlbl.setBounds(550, 20, 100, 25); 
	    admin_FAQ_Write_View.add(adminJlbl);
	    JLabel faqJlbl = new JLabel("FAQ 등록");
	    faqJlbl.setBounds(70, 65, 100, 25); 
	    admin_FAQ_Write_View.add(faqJlbl);
	    
	    //구분선 추가
	    JPanel separator1 = new JPanel();
	    separator1.setBackground(Color.lightGray); 
	    separator1.setBounds(50, 60, 1100, 2);
	    admin_FAQ_Write_View.add(separator1);
	    JPanel separator2 = new JPanel();
	    separator2.setBackground(Color.lightGray); 
	    separator2.setBounds(50, 90, 1100, 2);
	    admin_FAQ_Write_View.add(separator2);
	    
	    //FAQ 제목 작성 영역
	    JLabel titleLabel = new JLabel("제목");
	    titleLabel.setBounds(70, 100, 50, 25);
	    admin_FAQ_Write_View.add(titleLabel);
	    
	    subFAQJta = new JTextArea();
	    subFAQJta.setBounds(70, 130, 1060, 30);
	    subFAQJta.setLineWrap(true);
	    subFAQJta.setWrapStyleWord(true);
	    subFAQJta.setBorder(new LineBorder(Color.lightGray));
	    subFAQJta.setMargin(new Insets(5, 5, 5, 5)); 
	    admin_FAQ_Write_View.add(subFAQJta);

	    //FAQ 내용 작성 영역
	    JLabel contentLabel = new JLabel("내용");
	    contentLabel.setBounds(70, 170, 50, 25);
	    admin_FAQ_Write_View.add(contentLabel);
	    
	    contentsFAQJta = new JTextArea();
	    contentsFAQJta.setLineWrap(true);
	    contentsFAQJta.setWrapStyleWord(true); 
	    contentsFAQJta.setMargin(new Insets(5, 5, 5, 5)); 
	    admin_FAQ_Write_View.add(contentsFAQJta);

	    faqWriteJsp = new JScrollPane(contentsFAQJta);
	    faqWriteJsp.setBounds(70, 200, 1060, 580); 
	    faqWriteJsp.setBorder(new LineBorder(Color.lightGray));
	    admin_FAQ_Write_View.add(faqWriteJsp);
	    
	    // 버튼 추가
	    saveFAQBtn = new JButton("저장");
	    saveFAQBtn.setBounds(920, 800, 100, 40); 
	    admin_FAQ_Write_View.add(saveFAQBtn);

	    cnlFAQBtn = new JButton("취소");
	    cnlFAQBtn.setBounds(1030, 800, 100, 40); 
	    admin_FAQ_Write_View.add(cnlFAQBtn);
	    
	    admin_FAQ_Write_View.setVisible(true);
		
	}//admin_FAQ_Write_View
	
	public void admin_FAQ_Confirm_View() {
		JFrame admin_FAQ_Confirm_View = new JFrame("FAQ 수정/삭제");
		admin_FAQ_Confirm_View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin_FAQ_Confirm_View.setLayout(null);
		admin_FAQ_Confirm_View.setResizable(false);
		admin_FAQ_Confirm_View.setBounds(800, 600, 1200, 900); 
		
		
		//라벨
	    JLabel adminJlbl = new JLabel("관리자 페이지");
	    adminJlbl.setBounds(550, 20, 100, 25); 
	    admin_FAQ_Confirm_View.add(adminJlbl);
	    JLabel faqJlbl = new JLabel("FAQ");
	    faqJlbl.setBounds(70, 65, 100, 25); 
	    admin_FAQ_Confirm_View.add(faqJlbl);
	    
	    //구분선 추가
	    JPanel separator1 = new JPanel();
	    separator1.setBackground(Color.lightGray); 
	    separator1.setBounds(50, 60, 1100, 2);
	    admin_FAQ_Confirm_View.add(separator1);
	    JPanel separator2 = new JPanel();
	    separator2.setBackground(Color.lightGray); 
	    separator2.setBounds(50, 90, 1100, 2);
	    admin_FAQ_Confirm_View.add(separator2);
	    
	    //FAQ 제목 작성 영역
	    JLabel titleLabel = new JLabel("제목");
	    titleLabel.setBounds(70, 100, 50, 25);
	    admin_FAQ_Confirm_View.add(titleLabel);
	    
	    subFAQEditJta = new JTextArea();
	    subFAQEditJta.setBounds(70, 130, 1060, 30);
	    subFAQEditJta.setLineWrap(true);
	    subFAQEditJta.setWrapStyleWord(true);
	    subFAQEditJta.setMargin(new Insets(5, 5, 5, 5));
	    subFAQEditJta.setBorder(new LineBorder(Color.lightGray));
	    admin_FAQ_Confirm_View.add(subFAQEditJta);
	    
	    

	    //FAQ 내용 작성 영역
	    JLabel contentLabel = new JLabel("내용");
	    contentLabel.setBounds(70, 170, 50, 25);
	    admin_FAQ_Confirm_View.add(contentLabel);
	    
	    contentsFAQEditJta = new JTextArea();
	    contentsFAQEditJta.setLineWrap(true);
	    contentsFAQEditJta.setWrapStyleWord(true); 
	    contentsFAQEditJta.setMargin(new Insets(5, 5, 5, 5)); 

	    faqEditJsp = new JScrollPane(contentsFAQEditJta);
	    faqEditJsp.setBounds(70, 200, 1060, 580);
	    faqEditJsp.setBorder(new LineBorder(Color.lightGray));
	    admin_FAQ_Confirm_View.add(faqEditJsp);
	    
	    //버튼 추가
	    editFAQBtn = new JButton("수정");
	    editFAQBtn.setBounds(810, 800, 100, 40); 
	    admin_FAQ_Confirm_View.add(editFAQBtn);
	    
	    delFAQBtn = new JButton("삭제");
	    delFAQBtn.setBounds(920, 800, 100, 40); 
	    admin_FAQ_Confirm_View.add(delFAQBtn);

	    cnlFAQEditBtn = new JButton("취소");
	    cnlFAQEditBtn.setBounds(1030, 800, 100, 40); 
	    admin_FAQ_Confirm_View.add(cnlFAQEditBtn);
	    
	    admin_FAQ_Confirm_View.setVisible(true);
		
	}//admin_FAQ_Confirm_View
	
	public void admin_Inquiry_Write_View() {
		JFrame admin_Inquiry_Write_View = new JFrame("답변 등록");
		admin_Inquiry_Write_View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin_Inquiry_Write_View.setLayout(null);
		admin_Inquiry_Write_View.setResizable(false);
		admin_Inquiry_Write_View.setBounds(800, 600, 1200, 900); 
		
		
		//라벨
	    JLabel adminJlbl = new JLabel("관리자 페이지");
	    adminJlbl.setBounds(550, 20, 100, 25); 
	    admin_Inquiry_Write_View.add(adminJlbl);
	    
	    JLabel inqSubJlbl = new JLabel("문의 제목: ");
	    inqSubJlbl.setBounds(70, 65, 80, 25); 
	    admin_Inquiry_Write_View.add(inqSubJlbl);
	    JLabel memNameJlbl = new JLabel("고객명: ");
	    memNameJlbl.setBounds(670, 65, 50, 25); 
	    admin_Inquiry_Write_View.add(memNameJlbl);
	    JLabel inqDateJlbl = new JLabel("날 짜: ");
	    inqDateJlbl.setBounds(830, 65, 50, 25); 
	    admin_Inquiry_Write_View.add(inqDateJlbl);
	    JLabel inqStatusJlbl = new JLabel("상 태: ");
	    inqStatusJlbl.setBounds(1000, 65, 50, 25); 
	    admin_Inquiry_Write_View.add(inqStatusJlbl);
	    
	    //구분선 추가
	    JPanel separator1 = new JPanel();
	    separator1.setBackground(Color.lightGray); 
	    separator1.setBounds(50, 60, 1100, 2);
	    admin_Inquiry_Write_View.add(separator1);
	    JPanel separator2 = new JPanel();
	    separator2.setBackground(Color.lightGray); 
	    separator2.setBounds(50, 90, 1100, 2);
	    admin_Inquiry_Write_View.add(separator2);
	    
	    
	    //문의 내용 영역
	    JLabel titleLabel = new JLabel("문의내용");
	    titleLabel.setBounds(70, 100, 50, 25);
	    admin_Inquiry_Write_View.add(titleLabel);
	    
	    subInqJta = new JTextArea();
	    subInqJta.setLineWrap(true);
	    subInqJta.setWrapStyleWord(true);
	    subInqJta.setEditable(false);
	    subInqJta.setMargin(new Insets(5, 5, 5, 5));
	    
	    inqWriteJsp = new JScrollPane(subInqJta);
	    inqWriteJsp.setBounds(70, 130, 1060, 260);
	    inqWriteJsp.setBorder(new LineBorder(Color.lightGray));
	    admin_Inquiry_Write_View.add(inqWriteJsp);

	    //문의 답변 작성 영역
	    JLabel contentLabel = new JLabel("답변");
	    contentLabel.setBounds(70, 400, 50, 25);
	    admin_Inquiry_Write_View.add(contentLabel);
	    
	    replyInqJta = new JTextArea();
	    replyInqJta.setLineWrap(true);
	    replyInqJta.setWrapStyleWord(true); 
	    replyInqJta.setMargin(new Insets(5, 5, 5, 5)); 

	    inqReplyWriteJsp = new JScrollPane(replyInqJta);
	    inqReplyWriteJsp.setBounds(70, 430, 1060, 360); 
	    inqReplyWriteJsp.setBorder(new LineBorder(Color.lightGray)); 
	    admin_Inquiry_Write_View.add(inqReplyWriteJsp);
	    
	    // 버튼 추가
	    saveInqBtn = new JButton("저장");
	    saveInqBtn.setBounds(920, 800, 100, 40); 
	    admin_Inquiry_Write_View.add(saveInqBtn);

	    cnlInqBtn = new JButton("취소");
	    cnlInqBtn.setBounds(1030, 800, 100, 40); 
	    admin_Inquiry_Write_View.add(cnlInqBtn);
	    
	    admin_Inquiry_Write_View.setVisible(true);
		
	}//admin_Inquiry_Write_View
	
	public void admin_Inquiry_Confirm_View() {
		JFrame admin_Inquiry_Confirm_View = new JFrame("답변 수정");
		admin_Inquiry_Confirm_View.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		admin_Inquiry_Confirm_View.setLayout(null);
		admin_Inquiry_Confirm_View.setResizable(false);
		admin_Inquiry_Confirm_View.setBounds(800, 600, 1200, 900); 
		
		//라벨
	    JLabel adminJlbl = new JLabel("관리자 페이지");
	    adminJlbl.setBounds(550, 20, 100, 25); 
	    admin_Inquiry_Confirm_View.add(adminJlbl);
	    
	    JLabel inqSubJlbl = new JLabel("문의 제목: ");
	    inqSubJlbl.setBounds(70, 65, 80, 25); 
	    admin_Inquiry_Confirm_View.add(inqSubJlbl);
	    JLabel memNameJlbl = new JLabel("고객명: ");
	    memNameJlbl.setBounds(670, 65, 50, 25); 
	    admin_Inquiry_Confirm_View.add(memNameJlbl);
	    JLabel inqDateJlbl = new JLabel("날 짜: ");
	    inqDateJlbl.setBounds(830, 65, 50, 25); 
	    admin_Inquiry_Confirm_View.add(inqDateJlbl);
	    JLabel inqStatusJlbl = new JLabel("상 태: ");
	    inqStatusJlbl.setBounds(1000, 65, 50, 25); 
	    admin_Inquiry_Confirm_View.add(inqStatusJlbl);
	    
	    //구분선 추가
	    JPanel separator1 = new JPanel();
	    separator1.setBackground(Color.lightGray); 
	    separator1.setBounds(50, 60, 1100, 2);
	    admin_Inquiry_Confirm_View.add(separator1);
	    JPanel separator2 = new JPanel();
	    separator2.setBackground(Color.lightGray); 
	    separator2.setBounds(50, 90, 1100, 2);
	    admin_Inquiry_Confirm_View.add(separator2);
	    
	    
	    //문의 내용 영역
	    JLabel titleLabel = new JLabel("문의내용");
	    titleLabel.setBounds(70, 100, 50, 25);
	    admin_Inquiry_Confirm_View.add(titleLabel);
	    
	    subConfirmJta = new JTextArea();
	    subConfirmJta.setLineWrap(true);
	    subConfirmJta.setWrapStyleWord(true);
	    subConfirmJta.setEditable(false);
	    subConfirmJta.setMargin(new Insets(5, 5, 5, 5));
	    
	    inqEditJsp = new JScrollPane(subConfirmJta);
	    inqEditJsp.setBounds(70, 130, 1060, 260);
	    inqEditJsp.setBorder(new LineBorder(Color.lightGray));
	    admin_Inquiry_Confirm_View.add(inqEditJsp);

	    //문의 답변 작성 영역
	    JLabel contentLabel = new JLabel("답변");
	    contentLabel.setBounds(70, 400, 50, 25);
	    admin_Inquiry_Confirm_View.add(contentLabel);
	    
	    replyConfirmJta = new JTextArea();
	    replyConfirmJta.setLineWrap(true);
	    replyConfirmJta.setWrapStyleWord(true); 
	    replyConfirmJta.setMargin(new Insets(5, 5, 5, 5)); 

	    inqReplyEditJsp = new JScrollPane(replyConfirmJta);
	    inqReplyEditJsp.setBounds(70, 430, 1060, 360); 
	    inqReplyEditJsp.setBorder(new LineBorder(Color.lightGray)); 
	    admin_Inquiry_Confirm_View.add(inqReplyEditJsp);
	    
	    // 버튼 추가
	    editInqBtn = new JButton("수정");
	    editInqBtn.setBounds(920, 800, 100, 40); 
	    admin_Inquiry_Confirm_View.add(editInqBtn);

	    cnlInqConfirmBtn = new JButton("취소");
	    cnlInqConfirmBtn.setBounds(1030, 800, 100, 40); 
	    admin_Inquiry_Confirm_View.add(cnlInqConfirmBtn);
	    
	    admin_Inquiry_Confirm_View.setVisible(true);
		
	}//admin_Inquiry_Confirm_View
	
}//class
