package kr.co.sist.cjw.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.cjw.event.Admin_Inquiry_Event;

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
	private JButton cnlInqEditBtn;
	
	
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
	private JTextArea contentsInqJta;
	private JTextArea replyWriteJta;
	private JTextArea contentsEditJta;
	private JTextArea replyEditJta;
	

	//Frame
	private JFrame admin_Inquiry_Main_View;
	private JFrame admin_FAQ_Write_View;
	private JFrame admin_FAQ_Edit_View;
	private JFrame admin_Inquiry_Write_View;
	private JFrame admin_Inquiry_Edit_View;
	
	//DefaultTablemodel
	private DefaultTableModel faqModel;
	private DefaultTableModel inqModel;
	
	//String
	private JLabel inqSub;
	private JLabel memName;
	private JLabel inqDate;
	private JLabel inqStatus;
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public void admin_Inquiry_Main_View() {
		//프레임
	    admin_Inquiry_Main_View = new JFrame("문의게시판");
	    admin_Inquiry_Main_View.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    admin_Inquiry_Main_View.setLayout(null);
	    admin_Inquiry_Main_View.setResizable(false);
	    admin_Inquiry_Main_View.setBounds(800, 600, 1200, 900); 
	    admin_Inquiry_Main_View.setLocationRelativeTo(null);

	    //라벨
	    JLabel adminJlbl = new JLabel("관리자 페이지");
	    adminJlbl.setBounds(550, 20, 100, 25); 
	    admin_Inquiry_Main_View.add(adminJlbl);
	    JLabel faqJlbl = new JLabel("FAQ");
	    faqJlbl.setBounds(70, 65, 100, 25); 
	    admin_Inquiry_Main_View.add(faqJlbl);
	    
	    //버튼 추가
	    addJbtn = new JButton("등록");
	    addJbtn.setBounds(1060, 63, 70, 25); 
	    addJbtn.setBackground(new Color(217, 217, 217));
	    admin_Inquiry_Main_View.add(addJbtn);
	    
	    cnlJbtn = new JButton("취소");
	    cnlJbtn.setBounds(550, 800, 90, 40); 
	    cnlJbtn.setBackground(new Color(217, 217, 217));
	    admin_Inquiry_Main_View.add(cnlJbtn);
	    
	    //구분선 추가
	    JPanel separator1 = new JPanel();
	    separator1.setBackground(Color.lightGray);
	    separator1.setBounds(50, 60, 1100, 2);
	    admin_Inquiry_Main_View.add(separator1);


	    //FAQ 테이블
	    faqTable = new JTable(); 
	    faqTable.setDefaultEditor(Object.class, null);
	    faqTable.setShowGrid(false);
	    faqTable.setIntercellSpacing(new Dimension(0, 0));
	    JScrollPane faqScrollPane = new JScrollPane(faqTable);
	    faqScrollPane.setBounds(50, 90, 1100, 260); 
	    faqScrollPane.setBorder(new LineBorder(Color.lightGray));
	    admin_Inquiry_Main_View.add(faqScrollPane);
	    
	    faqModel = new DefaultTableModel(new String[]{"", "", ""}, 0);
        

	    //문의 목록 테이블
	    inqTable = new JTable(); 
	    inqTable.setDefaultEditor(Object.class, null);
	    inqTable.setShowGrid(false);
	    inqTable.setIntercellSpacing(new Dimension(0, 0));
	    JScrollPane inqScrollPane = new JScrollPane(inqTable);
	    inqScrollPane.setBounds(50, 380, 1100, 410); 
	    inqScrollPane.setBorder(new LineBorder(Color.lightGray));
	    admin_Inquiry_Main_View.add(inqScrollPane);
	    inqModel = new DefaultTableModel(new String[]{"문의번호","고객명", "날짜", "제목","상태"}, 0);
        
	    
	    
	    //이벤트 추가
	    Admin_Inquiry_Event eventHandler = new Admin_Inquiry_Event(this);
	    faqTable.addMouseListener(eventHandler);
	    inqTable.addMouseListener(eventHandler);
	    addJbtn.addActionListener(eventHandler);
	    cnlJbtn.addActionListener(eventHandler);
	    admin_Inquiry_Main_View.addWindowListener(eventHandler);
	    
	    

	    admin_Inquiry_Main_View.setVisible(true); 
		
	}//admin_Inquiry_Main_View
	
	
	
	public JFrame getAdmin_Inquiry_Main_View() {
		return admin_Inquiry_Main_View;
	}



	public void admin_FAQ_Write_View() {
		admin_FAQ_Write_View = new JFrame("FAQ 등록");
		admin_FAQ_Write_View.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		admin_FAQ_Write_View.setLayout(null);
		admin_FAQ_Write_View.setResizable(false);
		admin_FAQ_Write_View.setBounds(800, 600, 1200, 900); 
		admin_FAQ_Write_View.setLocationRelativeTo(null); 
		
		
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
	    saveFAQBtn.setBackground(new Color(217, 217, 217));
	    admin_FAQ_Write_View.add(saveFAQBtn);

	    cnlFAQBtn = new JButton("취소");
	    cnlFAQBtn.setBounds(1030, 800, 100, 40); 
	    cnlFAQBtn.setBackground(new Color(217, 217, 217));
	    admin_FAQ_Write_View.add(cnlFAQBtn);
	    
	    Admin_Inquiry_Event eventHandler = new Admin_Inquiry_Event(this);
	    
	    subFAQJta.addKeyListener(eventHandler);
	    saveFAQBtn.addActionListener(eventHandler);
	    cnlFAQBtn.addActionListener(eventHandler);
	    
	    admin_FAQ_Write_View.setVisible(true);
		
	}//admin_FAQ_Write_View
	
	public JFrame getAdminFAQWriteView() {
        return admin_FAQ_Write_View; // admin_FAQ_Write_View 반환
    }
	
	
	
	
	
	public void admin_FAQ_Edit_View() {
		admin_FAQ_Edit_View = new JFrame("FAQ 수정/삭제");
		admin_FAQ_Edit_View.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		admin_FAQ_Edit_View.setLayout(null);
		admin_FAQ_Edit_View.setResizable(false);
		admin_FAQ_Edit_View.setBounds(800, 600, 1200, 900); 
		admin_FAQ_Edit_View.setLocationRelativeTo(null);
		
		
		//라벨
	    JLabel adminJlbl = new JLabel("관리자 페이지");
	    adminJlbl.setBounds(550, 20, 100, 25); 
	    admin_FAQ_Edit_View.add(adminJlbl);
	    JLabel faqJlbl = new JLabel("FAQ");
	    faqJlbl.setBounds(70, 65, 100, 25); 
	    admin_FAQ_Edit_View.add(faqJlbl);
	    
	    //구분선 추가
	    JPanel separator1 = new JPanel();
	    separator1.setBackground(Color.lightGray); 
	    separator1.setBounds(50, 60, 1100, 2);
	    admin_FAQ_Edit_View.add(separator1);
	    JPanel separator2 = new JPanel();
	    separator2.setBackground(Color.lightGray); 
	    separator2.setBounds(50, 90, 1100, 2);
	    admin_FAQ_Edit_View.add(separator2);
	    
	    //FAQ 제목 작성 영역
	    JLabel titleLabel = new JLabel("제목");
	    titleLabel.setBounds(70, 100, 50, 25);
	    admin_FAQ_Edit_View.add(titleLabel);
	    
	    subFAQEditJta = new JTextArea();
	    subFAQEditJta.setBounds(70, 130, 1060, 30);
	    subFAQEditJta.setLineWrap(true);
	    subFAQEditJta.setWrapStyleWord(true);
	    subFAQEditJta.setMargin(new Insets(5, 5, 5, 5));
	    subFAQEditJta.setBorder(new LineBorder(Color.lightGray));
	    admin_FAQ_Edit_View.add(subFAQEditJta);
	    
	    

	    //FAQ 내용 작성 영역
	    JLabel contentLabel = new JLabel("내용");
	    contentLabel.setBounds(70, 170, 50, 25);
	    admin_FAQ_Edit_View.add(contentLabel);
	    
	    contentsFAQEditJta = new JTextArea();
	    contentsFAQEditJta.setLineWrap(true);
	    contentsFAQEditJta.setWrapStyleWord(true); 
	    contentsFAQEditJta.setMargin(new Insets(5, 5, 5, 5)); 

	    faqEditJsp = new JScrollPane(contentsFAQEditJta);
	    faqEditJsp.setBounds(70, 200, 1060, 580);
	    faqEditJsp.setBorder(new LineBorder(Color.lightGray));
	    admin_FAQ_Edit_View.add(faqEditJsp);
	    
	    //버튼 추가
	    editFAQBtn = new JButton("수정");
	    editFAQBtn.setBounds(810, 800, 100, 40); 
	    editFAQBtn.setBackground(new Color(217, 217, 217));
	    admin_FAQ_Edit_View.add(editFAQBtn);
	    
	    delFAQBtn = new JButton("삭제");
	    delFAQBtn.setBounds(920, 800, 100, 40); 
	    delFAQBtn.setBackground(new Color(217, 217, 217));
	    admin_FAQ_Edit_View.add(delFAQBtn);

	    cnlFAQEditBtn = new JButton("취소");
	    cnlFAQEditBtn.setBounds(1030, 800, 100, 40); 
	    cnlFAQEditBtn.setBackground(new Color(217, 217, 217));
	    admin_FAQ_Edit_View.add(cnlFAQEditBtn);
	    
	    //이벤트 추가
	    Admin_Inquiry_Event eventHandler = new Admin_Inquiry_Event(this);
	    subFAQEditJta.addKeyListener(eventHandler);
	    editFAQBtn.addActionListener(eventHandler);
	    delFAQBtn.addActionListener(eventHandler);
	    cnlFAQEditBtn.addActionListener(eventHandler);
	    
	    admin_FAQ_Edit_View.setVisible(true);
		
	}//admin_FAQ_Edit_View
	
	public void admin_Inquiry_Write_View() {
		admin_Inquiry_Write_View = new JFrame("답변 등록");
		admin_Inquiry_Write_View.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		admin_Inquiry_Write_View.setLayout(null);
		admin_Inquiry_Write_View.setResizable(false);
		admin_Inquiry_Write_View.setBounds(800, 600, 1200, 900); 
		admin_Inquiry_Write_View.setLocationRelativeTo(null);
		
		
		//라벨
	    JLabel adminJlbl = new JLabel("관리자 페이지");
	    adminJlbl.setBounds(550, 20, 100, 25); 
	    admin_Inquiry_Write_View.add(adminJlbl);
	    
	    inqSub = new JLabel();
	    inqSub.setBounds(70, 65, 450, 25); 
	    admin_Inquiry_Write_View.add(inqSub);
	    memName = new JLabel();
	    memName.setBounds(670, 65, 100, 25); 
	    admin_Inquiry_Write_View.add(memName);
	    inqDate = new JLabel();
	    inqDate.setBounds(830, 65, 100, 25); 
	    admin_Inquiry_Write_View.add(inqDate);
	    inqStatus = new JLabel();
	    inqStatus.setBounds(1000, 65, 100, 25); 
	    admin_Inquiry_Write_View.add(inqStatus);
	    
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
	    
	    contentsInqJta = new JTextArea();
	    contentsInqJta.setLineWrap(true);
	    contentsInqJta.setWrapStyleWord(true);
	    contentsInqJta.setEditable(false);
	    contentsInqJta.setMargin(new Insets(5, 5, 5, 5));
	    
	    inqWriteJsp = new JScrollPane(contentsInqJta);
	    inqWriteJsp.setBounds(70, 130, 1060, 260);
	    inqWriteJsp.setBorder(new LineBorder(Color.lightGray));
	    admin_Inquiry_Write_View.add(inqWriteJsp);

	    //문의 답변 작성 영역
	    JLabel contentLabel = new JLabel("답변");
	    contentLabel.setBounds(70, 400, 50, 25);
	    admin_Inquiry_Write_View.add(contentLabel);
	    
	    replyWriteJta = new JTextArea();
	    replyWriteJta.setLineWrap(true);
	    replyWriteJta.setWrapStyleWord(true); 
	    replyWriteJta.setMargin(new Insets(5, 5, 5, 5)); 

	    inqReplyWriteJsp = new JScrollPane(replyWriteJta);
	    inqReplyWriteJsp.setBounds(70, 430, 1060, 360); 
	    inqReplyWriteJsp.setBorder(new LineBorder(Color.lightGray)); 
	    admin_Inquiry_Write_View.add(inqReplyWriteJsp);
	    
	    // 버튼 추가
	    saveInqBtn = new JButton("저장");
	    saveInqBtn.setBounds(920, 800, 100, 40); 
	    saveInqBtn.setBackground(new Color(217, 217, 217));
	    admin_Inquiry_Write_View.add(saveInqBtn);

	    cnlInqBtn = new JButton("취소");
	    cnlInqBtn.setBounds(1030, 800, 100, 40); 
	    cnlInqBtn.setBackground(new Color(217, 217, 217));
	    admin_Inquiry_Write_View.add(cnlInqBtn);
	    
	    Admin_Inquiry_Event eventHandler = new Admin_Inquiry_Event(this);
	    saveInqBtn.addActionListener(eventHandler);
	    cnlInqBtn.addActionListener(eventHandler);
	    
	    
	    
	    admin_Inquiry_Write_View.setVisible(true);
		
	}//admin_Inquiry_Write_View
	
	public void admin_Inquiry_Edit_View() {
		admin_Inquiry_Edit_View = new JFrame("답변 수정");
		admin_Inquiry_Edit_View.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		admin_Inquiry_Edit_View.setLayout(null);
		admin_Inquiry_Edit_View.setResizable(false);
		admin_Inquiry_Edit_View.setBounds(800, 600, 1200, 900); 
		admin_Inquiry_Edit_View.setLocationRelativeTo(null); 
		
		//라벨
	    JLabel adminJlbl = new JLabel("관리자 페이지");
	    adminJlbl.setBounds(550, 20, 100, 25); 
	    admin_Inquiry_Edit_View.add(adminJlbl);
	    
	    
	    inqSub = new JLabel();
	    inqSub.setBounds(70, 65, 450, 25); 
	    admin_Inquiry_Edit_View.add(inqSub);
	    memName = new JLabel();
	    memName.setBounds(670, 65, 100, 25); 
	    admin_Inquiry_Edit_View.add(memName);
	    inqDate = new JLabel();
	    inqDate.setBounds(830, 65, 100, 25); 
	    admin_Inquiry_Edit_View.add(inqDate);
	    inqStatus = new JLabel();
	    inqStatus.setBounds(1000, 65, 100, 25); 
	    admin_Inquiry_Edit_View.add(inqStatus);
	    
	    //구분선 추가
	    JPanel separator1 = new JPanel();
	    separator1.setBackground(Color.lightGray); 
	    separator1.setBounds(50, 60, 1100, 2);
	    admin_Inquiry_Edit_View.add(separator1);
	    JPanel separator2 = new JPanel();
	    separator2.setBackground(Color.lightGray); 
	    separator2.setBounds(50, 90, 1100, 2);
	    admin_Inquiry_Edit_View.add(separator2);
	    
	    
	    //문의 내용 영역
	    JLabel titleLabel = new JLabel("문의내용");
	    titleLabel.setBounds(70, 100, 50, 25);
	    admin_Inquiry_Edit_View.add(titleLabel);
	    
	    contentsEditJta = new JTextArea();
	    contentsEditJta.setLineWrap(true);
	    contentsEditJta.setWrapStyleWord(true);
	    contentsEditJta.setEditable(false);
	    contentsEditJta.setMargin(new Insets(5, 5, 5, 5));
	    
	    inqEditJsp = new JScrollPane(contentsEditJta);
	    inqEditJsp.setBounds(70, 130, 1060, 260);
	    inqEditJsp.setBorder(new LineBorder(Color.lightGray));
	    admin_Inquiry_Edit_View.add(inqEditJsp);

	    //문의 답변 작성 영역
	    JLabel contentLabel = new JLabel("답변");
	    contentLabel.setBounds(70, 400, 50, 25);
	    admin_Inquiry_Edit_View.add(contentLabel);
	    
	    replyEditJta = new JTextArea();
	    replyEditJta.setLineWrap(true);
	    replyEditJta.setWrapStyleWord(true); 
	    replyEditJta.setMargin(new Insets(5, 5, 5, 5)); 

	    inqReplyEditJsp = new JScrollPane(replyEditJta);
	    inqReplyEditJsp.setBounds(70, 430, 1060, 360); 
	    inqReplyEditJsp.setBorder(new LineBorder(Color.lightGray)); 
	    admin_Inquiry_Edit_View.add(inqReplyEditJsp);
	    
	    // 버튼 추가
	    editInqBtn = new JButton("수정");
	    editInqBtn.setBounds(920, 800, 100, 40); 
	    editInqBtn.setBackground(new Color(217, 217, 217));
	    admin_Inquiry_Edit_View.add(editInqBtn);

	    cnlInqEditBtn = new JButton("취소");
	    cnlInqEditBtn.setBounds(1030, 800, 100, 40); 
	    cnlInqEditBtn.setBackground(new Color(217, 217, 217));
	    admin_Inquiry_Edit_View.add(cnlInqEditBtn);
	    
	    
	    //이벤트 추가
	    Admin_Inquiry_Event eventHandler = new Admin_Inquiry_Event(this);
	    editInqBtn.addActionListener(eventHandler);
	    cnlInqEditBtn.addActionListener(eventHandler);
	    
	    
	    admin_Inquiry_Edit_View.setVisible(true);
		
	}//admin_Inquiry_Confirm_View



	public JButton getAddJbtn() {
		return addJbtn;
	}



	public void setAddJbtn(JButton addJbtn) {
		this.addJbtn = addJbtn;
	}



	public JButton getCnlJbtn() {
		return cnlJbtn;
	}



	public void setCnlJbtn(JButton cnlJbtn) {
		this.cnlJbtn = cnlJbtn;
	}



	public JButton getSaveFAQBtn() {
		return saveFAQBtn;
	}



	public void setSaveFAQBtn(JButton saveFAQBtn) {
		this.saveFAQBtn = saveFAQBtn;
	}



	public JButton getCnlFAQBtn() {
		return cnlFAQBtn;
	}



	public void setCnlFAQBtn(JButton cnlFAQBtn) {
		this.cnlFAQBtn = cnlFAQBtn;
	}



	public JButton getEditFAQBtn() {
		return editFAQBtn;
	}



	public void setEditFAQBtn(JButton editFAQBtn) {
		this.editFAQBtn = editFAQBtn;
	}



	public JButton getDelFAQBtn() {
		return delFAQBtn;
	}



	public void setDelFAQBtn(JButton delFAQBtn) {
		this.delFAQBtn = delFAQBtn;
	}



	public JButton getCnlFAQEditBtn() {
		return cnlFAQEditBtn;
	}



	public void setCnlFAQEditBtn(JButton cnlFAQEditBtn) {
		this.cnlFAQEditBtn = cnlFAQEditBtn;
	}



	public JButton getSaveInqBtn() {
		return saveInqBtn;
	}



	public void setSaveInqBtn(JButton saveInqBtn) {
		this.saveInqBtn = saveInqBtn;
	}



	public JButton getCnlInqBtn() {
		return cnlInqBtn;
	}



	public void setCnlInqBtn(JButton cnlInqBtn) {
		this.cnlInqBtn = cnlInqBtn;
	}



	public JButton getEditInqBtn() {
		return editInqBtn;
	}



	public void setEditInqBtn(JButton editInqBtn) {
		this.editInqBtn = editInqBtn;
	}



	public JButton getCnlInqEditBtn() {
		return cnlInqEditBtn;
	}



	public void setCnlInqEditBtn(JButton cnlInqConfirmBtn) {
		this.cnlInqEditBtn = cnlInqConfirmBtn;
	}



	public JTable getFaqTable() {
		return faqTable;
	}



	public void setFaqTable(JTable faqTable) {
		this.faqTable = faqTable;
	}



	public JTable getInqTable() {
		return inqTable;
	}



	public void setInqTable(JTable inqTable) {
		this.inqTable = inqTable;
	}



	public JScrollPane getFaqWriteJsp() {
		return faqWriteJsp;
	}



	public void setFaqWriteJsp(JScrollPane faqWriteJsp) {
		this.faqWriteJsp = faqWriteJsp;
	}



	public JScrollPane getFaqEditJsp() {
		return faqEditJsp;
	}



	public void setFaqEditJsp(JScrollPane faqEditJsp) {
		this.faqEditJsp = faqEditJsp;
	}



	public JScrollPane getInqWriteJsp() {
		return inqWriteJsp;
	}



	public void setInqWriteJsp(JScrollPane inqWriteJsp) {
		this.inqWriteJsp = inqWriteJsp;
	}



	public JScrollPane getInqReplyWriteJsp() {
		return inqReplyWriteJsp;
	}



	public void setInqReplyWriteJsp(JScrollPane inqReplyWriteJsp) {
		this.inqReplyWriteJsp = inqReplyWriteJsp;
	}



	public JScrollPane getInqEditJsp() {
		return inqEditJsp;
	}



	public void setInqEditJsp(JScrollPane inqEditJsp) {
		this.inqEditJsp = inqEditJsp;
	}



	public JScrollPane getInqReplyEditJsp() {
		return inqReplyEditJsp;
	}



	public void setInqReplyEditJsp(JScrollPane inqReplyEditJsp) {
		this.inqReplyEditJsp = inqReplyEditJsp;
	}



	public JTextArea getSubFAQJta() {
		return subFAQJta;
	}



	public void setSubFAQJta(JTextArea subFAQJta) {
		this.subFAQJta = subFAQJta;
	}



	public JTextArea getContentsFAQJta() {
		return contentsFAQJta;
	}



	public void setContentsFAQJta(JTextArea contentsFAQJta) {
		this.contentsFAQJta = contentsFAQJta;
	}



	public JTextArea getSubFAQEditJta() {
		return subFAQEditJta;
	}



	public void setSubFAQEditJta(JTextArea subFAQEditJta) {
		this.subFAQEditJta = subFAQEditJta;
	}



	public JTextArea getContentsFAQEditJta() {
		return contentsFAQEditJta;
	}



	public void setContentsFAQEditJta(JTextArea contentsFAQEditJta) {
		this.contentsFAQEditJta = contentsFAQEditJta;
	}



	public JTextArea getContentsInqJta() {
		return contentsInqJta;
	}



	public void setContentsInqJta(JTextArea subInqJta) {
		this.contentsInqJta = subInqJta;
	}



	public JTextArea getReplyWriteJta() {
		return replyWriteJta;
	}



	public void setReplyWriteJta(JTextArea replyInqJta) {
		this.replyWriteJta = replyInqJta;
	}



	public JTextArea getContentsEditJta() {
		return contentsEditJta;
	}



	public void setContentsEditJta(JTextArea subConfirmJta) {
		this.contentsEditJta = subConfirmJta;
	}



	public JTextArea getReplyEditJta() {
		return replyEditJta;
	}



	public void setReplyEditJta(JTextArea replyConfirmJta) {
		this.replyEditJta = replyConfirmJta;
	}



	public JFrame getAdmin_FAQ_Write_View() {
		return admin_FAQ_Write_View;
	}



	public JFrame getAdmin_FAQ_Edit_View() {
		return admin_FAQ_Edit_View;
	}



	public JFrame getAdmin_Inquiry_Write_View() {
		return admin_Inquiry_Write_View;
	}



	public JFrame getAdmin_Inquiry_Edit_View() {
		return admin_Inquiry_Edit_View;
	}



	public void setAdmin_Inquiry_Main_View(JFrame admin_Inquiry_Main_View) {
		this.admin_Inquiry_Main_View = admin_Inquiry_Main_View;
	}



	public void setAdmin_FAQ_Write_View(JFrame admin_FAQ_Write_View) {
		this.admin_FAQ_Write_View = admin_FAQ_Write_View;
	}



	public void setAdmin_FAQ_Edit_View(JFrame admin_FAQ_Confirm_View) {
		this.admin_FAQ_Edit_View = admin_FAQ_Confirm_View;
	}



	public void setAdmin_Inquiry_Write_View(JFrame admin_Inquiry_Write_View) {
		this.admin_Inquiry_Write_View = admin_Inquiry_Write_View;
	}



	public void setAdmin_Inquiry_Edit_View(JFrame admin_Inquiry_Confirm_View) {
		this.admin_Inquiry_Edit_View = admin_Inquiry_Confirm_View;
	}



	public DefaultTableModel getFaqModel() {
		return faqModel;
	}



	public void setFaqModel(DefaultTableModel faqModel) {
		this.faqModel = faqModel;
	}



	public DefaultTableModel getInqModel() {
		return inqModel;
	}



	public void setInqModel(DefaultTableModel inqModel) {
		this.inqModel = inqModel;
	}



	public JLabel getInqSub() {
		return inqSub;
	}



	public void setInqSub(JLabel inqSub) {
		this.inqSub = inqSub;
	}



	public JLabel getMemName() {
		return memName;
	}



	public void setMemName(JLabel memName) {
		this.memName = memName;
	}



	public JLabel getInqDate() {
		return inqDate;
	}



	public void setInqDate(JLabel inqDate) {
		this.inqDate = inqDate;
	}



	public JLabel getInqStatus() {
		return inqStatus;
	}



	public void setInqStatus(JLabel inqStatus) {
		this.inqStatus = inqStatus;
	}
	
	
	
}//class
