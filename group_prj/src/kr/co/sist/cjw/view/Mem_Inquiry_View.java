package kr.co.sist.cjw.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
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

import kr.co.sist.cjw.event.Mem_Inquiry_Event;


public class Mem_Inquiry_View extends JFrame {
	//button
		private String id;
		
 		private JButton addJbtn;
		private JButton cnlMainJbtn;
		private JButton saveBtn;
		private JButton cnlBtn;
		private JButton confirmBtn;
		private JButton editBtn;
		private JButton cnlEditBtn;
		private JButton confirmFAQBtn;
		
		
		//table
		private JTable faqTable;
		private JTable inqTable;
		
		//scroll
		private JScrollPane faqJsp;
		private JScrollPane inqWriteJsp;
		private JScrollPane inqConfirmJsp;
		private JScrollPane inqReplyJsp;
		private JScrollPane inqEditJsp;
		
		//TextArea
		private JTextArea subFAQJta;
		private JTextArea contentsFAQJta;
		private JTextArea subWriteJta;
		private JTextArea contentsWriteJta;
		private JTextArea subConfirmJta;
		private JTextArea contentsConfirmJta;
		private JTextArea replyConfirmJta;
		private JTextArea subEditJta;
		private JTextArea contentsEditJta;
		
		//JFrame
		private JFrame mem_Inquiry_Main_View;
		private JFrame mem_FAQ_Confirm_View;
		private JFrame mem_Inquiry_Write_View;
		private JFrame mem_Inquiry_Confirm_View;
		private JFrame mem_Inquiry_Edit_View;
		

		//DefaultTablemodel
		private DefaultTableModel faqModel;
		private DefaultTableModel inqModel;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Mem_Inquiry_View(String id) throws HeadlessException {
		this.id=id;
		mem_Inquiry_Main_View(id);
	}//Mem_Inquiry_View

	public void mem_Inquiry_Main_View(String id) {
		
		mem_Inquiry_Main_View = new JFrame("문의게시판");
		
		mem_Inquiry_Main_View.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mem_Inquiry_Main_View.setLayout(null);
		mem_Inquiry_Main_View.setResizable(false);
		mem_Inquiry_Main_View.setBounds(800, 600, 1200, 900); 
		mem_Inquiry_Main_View.setLocationRelativeTo(null); 

	    //라벨
	    JLabel logoJlbl = new JLabel("쌍용 엔진오일 샵");
	    logoJlbl.setBounds(550, 20, 100, 25); 
	    mem_Inquiry_Main_View.add(logoJlbl);
	    JLabel faqJlbl = new JLabel("FAQ");
	    faqJlbl.setBounds(70, 65, 100, 25); 
	    mem_Inquiry_Main_View.add(faqJlbl);
	    
	    //버튼 추가
	    addJbtn = new JButton("문의작성");
	    addJbtn.setBounds(930, 800, 100, 30); 
	    addJbtn.setBackground(new Color(217, 217, 217));
	    mem_Inquiry_Main_View.add(addJbtn);
	    
	    cnlMainJbtn = new JButton("취소");
	    cnlMainJbtn.setBounds(1050, 800, 100, 30); 
	    cnlMainJbtn.setBackground(new Color(217, 217, 217));
	    mem_Inquiry_Main_View.add(cnlMainJbtn);
	    
	    //구분선 추가
	    JPanel separator1 = new JPanel();
	    separator1.setBackground(Color.lightGray);
	    separator1.setBounds(50, 60, 1100, 2);
	    mem_Inquiry_Main_View.add(separator1);


	    //FAQ 테이블
	    faqTable = new JTable(); 
	    faqTable.setDefaultEditor(Object.class, null);
	    faqTable.setShowGrid(false);
	    faqTable.setIntercellSpacing(new Dimension(0, 0));
	    JScrollPane faqScrollPane = new JScrollPane(faqTable);
	    faqScrollPane.setBounds(50, 90, 1100, 260); 
	    faqScrollPane.setBorder(new LineBorder(Color.lightGray));
	    mem_Inquiry_Main_View.add(faqScrollPane);
	    faqModel = new DefaultTableModel(new String[]{"", "", ""}, 0);
	    

	    //문의 목록 테이블
	    inqTable = new JTable(); 
	    inqTable.setDefaultEditor(Object.class, null);
	    inqTable.setShowGrid(false);
	    inqTable.setIntercellSpacing(new Dimension(0, 0));
	    JScrollPane inqScrollPane = new JScrollPane(inqTable);
	    inqScrollPane.setBounds(50, 380, 1100, 410); 
	    inqScrollPane.setBorder(new LineBorder(Color.lightGray));
	    mem_Inquiry_Main_View.add(inqScrollPane);
	    
	    inqModel = new DefaultTableModel(new String[]{"문의번호", "날짜", "제목","상태"}, 0);
	    
	    

        
        Mem_Inquiry_Event eventHandler = new Mem_Inquiry_Event(this);
        faqTable.addMouseListener(eventHandler);
        inqTable.addMouseListener(eventHandler);
        addJbtn.addActionListener(eventHandler);
        cnlMainJbtn.addActionListener(eventHandler);
        mem_Inquiry_Main_View.addWindowListener(eventHandler);


	    mem_Inquiry_Main_View.setVisible(true); 
	}//mem_Inquiry_Main_View
	
	
	
	public void mem_FAQ_Confirm_View() {
		mem_FAQ_Confirm_View = new JFrame("FAQ 확인");
		mem_FAQ_Confirm_View.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mem_FAQ_Confirm_View.setLayout(null);
		mem_FAQ_Confirm_View.setResizable(false);
		mem_FAQ_Confirm_View.setBounds(800, 600, 1200, 900); 
		mem_FAQ_Confirm_View.setLocationRelativeTo(null); 
		
		
		//라벨
		JLabel logoJlbl = new JLabel("쌍용 엔진오일 샵");
	    logoJlbl.setBounds(550, 20, 100, 25); 
	    mem_FAQ_Confirm_View.add(logoJlbl);
	    
	    
	    //문의 제목 확인 영역
	    JLabel titleLabel = new JLabel("FAQ 제목");
	    titleLabel.setBounds(70, 100, 50, 25);
	    mem_FAQ_Confirm_View.add(titleLabel);
	    
	    subFAQJta = new JTextArea();
	    subFAQJta.setBounds(70, 130, 1060, 30);
	    subFAQJta.setLineWrap(true);
	    subFAQJta.setEditable(false);
	    subFAQJta.setWrapStyleWord(true);
	    
	    subFAQJta.setBorder(new LineBorder(Color.lightGray));
	    subFAQJta.setMargin(new Insets(5, 5, 5, 5)); 
	    mem_FAQ_Confirm_View.add(subFAQJta);

	    //문의 내용 확인 영역
	    JLabel contentLabel = new JLabel("FAQ 내용");
	    contentLabel.setBounds(70, 170, 50, 25);
	    mem_FAQ_Confirm_View.add(contentLabel);
	    
	    contentsFAQJta = new JTextArea();
	    contentsFAQJta.setLineWrap(true);
	    contentsFAQJta.setWrapStyleWord(true); 
	    contentsFAQJta.setEditable(false);
	    contentsFAQJta.setMargin(new Insets(5, 5, 5, 5)); 
	    mem_FAQ_Confirm_View.add(contentsFAQJta);

	    faqJsp = new JScrollPane(contentsFAQJta);
	    faqJsp.setBounds(70, 195, 1060, 580); 
	    faqJsp.setBorder(new LineBorder(Color.lightGray));
	    mem_FAQ_Confirm_View.add(faqJsp);
	    
	    // 버튼 추가
	    confirmFAQBtn = new JButton("확인");
	    confirmFAQBtn.setBounds(550, 800, 100, 40); 
	    confirmFAQBtn.setBackground(new Color(217, 217, 217));
	    mem_FAQ_Confirm_View.add(confirmFAQBtn);
	    
	  //이벤트 추가
	    Mem_Inquiry_Event eventHandler = new Mem_Inquiry_Event(this);
	    mem_FAQ_Confirm_View.addWindowListener(eventHandler);
	    confirmFAQBtn.addActionListener(eventHandler);
	    
	    mem_FAQ_Confirm_View.setVisible(true);
		
	}//mem_FAQ_Confirm_View
	
	
	
	public void mem_Inquiry_Write_View() {
		mem_Inquiry_Write_View = new JFrame("문의 작성");
		mem_Inquiry_Write_View.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mem_Inquiry_Write_View.setLayout(null);
		mem_Inquiry_Write_View.setResizable(false);
		mem_Inquiry_Write_View.setBounds(800, 600, 1200, 900); 
		mem_Inquiry_Write_View.setLocationRelativeTo(null); 
		
		
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
	    saveBtn = new JButton("저장");
	    saveBtn.setBounds(920, 800, 100, 40); 
	    saveBtn.setBackground(new Color(217, 217, 217));
	    mem_Inquiry_Write_View.add(saveBtn);

	    cnlBtn = new JButton("취소");
	    cnlBtn.setBounds(1030, 800, 100, 40); 
	    cnlBtn.setBackground(new Color(217, 217, 217));
	    mem_Inquiry_Write_View.add(cnlBtn);
	    
	    //이벤트 추가
	    Mem_Inquiry_Event eventHandler = new Mem_Inquiry_Event(this);
	    subWriteJta.addKeyListener(eventHandler);
	    saveBtn.addActionListener(eventHandler);
	    cnlBtn.addActionListener(eventHandler);
	    
	    
	    mem_Inquiry_Write_View.setVisible(true);
		
	}//mem_Inquiry_Write_View
	
	public void mem_Inquiry_Confirm_View() {
		mem_Inquiry_Confirm_View = new JFrame("문의 답변");
		mem_Inquiry_Confirm_View.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mem_Inquiry_Confirm_View.setLayout(null);
		mem_Inquiry_Confirm_View.setResizable(false);
		mem_Inquiry_Confirm_View.setBounds(800, 600, 1200, 900); 
		mem_Inquiry_Confirm_View.setLocationRelativeTo(null); 
		
		
		//라벨
		JLabel logoJlbl = new JLabel("쌍용 엔진오일 샵");
	    logoJlbl.setBounds(550, 20, 100, 25); 
	    mem_Inquiry_Confirm_View.add(logoJlbl);
	    
	    
	    //문의 제목 확인 영역
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
	    confirmBtn = new JButton("확인");
	    confirmBtn.setBounds(550, 800, 100, 40); 
	    confirmBtn.setBackground(new Color(217, 217, 217));
	    mem_Inquiry_Confirm_View.add(confirmBtn);
	    
	  //이벤트 추가
	    Mem_Inquiry_Event eventHandler = new Mem_Inquiry_Event(this);
	    mem_Inquiry_Confirm_View.addWindowListener(eventHandler);
	    confirmBtn.addActionListener(eventHandler);

	    
	    mem_Inquiry_Confirm_View.setVisible(true);
		
	}//mem_Inquiry_Confirm_View
	
	public void mem_Inquiry_Edit_View() {
		mem_Inquiry_Edit_View = new JFrame("문의 수정");
		mem_Inquiry_Edit_View.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mem_Inquiry_Edit_View.setLayout(null);
		mem_Inquiry_Edit_View.setResizable(false);
		mem_Inquiry_Edit_View.setBounds(800, 600, 1200, 900); 
		mem_Inquiry_Edit_View.setLocationRelativeTo(null); 
		
		
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
	    editBtn = new JButton("수정");
	    editBtn.setBounds(920, 800, 100, 40); 
	    editBtn.setBackground(new Color(217, 217, 217));
	    mem_Inquiry_Edit_View.add(editBtn);

	    cnlEditBtn = new JButton("취소");
	    cnlEditBtn.setBounds(1030, 800, 100, 40); 
	    cnlEditBtn.setBackground(new Color(217, 217, 217));
	    mem_Inquiry_Edit_View.add(cnlEditBtn);
	    
	    Mem_Inquiry_Event eventHandler = new Mem_Inquiry_Event(this);
	    subEditJta.addKeyListener(eventHandler);
	    editBtn.addActionListener(eventHandler);
	    cnlEditBtn.addActionListener(eventHandler);
	    
	    mem_Inquiry_Edit_View.setVisible(true);
	}//mem_Inquiry_Edit_View



	public JButton getAddJbtn() {
		return addJbtn;
	}



	public void setAddJbtn(JButton addJbtn) {
		this.addJbtn = addJbtn;
	}



	public JButton getCnlMainJbtn() {
		return cnlMainJbtn;
	}



	public void setCnlMainJbtn(JButton cnlMainJbtn) {
		this.cnlMainJbtn = cnlMainJbtn;
	}



	public JButton getSaveBtn() {
		return saveBtn;
	}



	public void setSaveBtn(JButton saveBtn) {
		this.saveBtn = saveBtn;
	}



	public JButton getCnlBtn() {
		return cnlBtn;
	}



	public void setCnlBtn(JButton cnlBtn) {
		this.cnlBtn = cnlBtn;
	}


	public JButton getConfirmBtn() {
		return confirmBtn;
	}



	public void setConfirmBtn(JButton confirmBtn) {
		this.confirmBtn = confirmBtn;
	}



	public JButton getEditBtn() {
		return editBtn;
	}



	public void setEditBtn(JButton editBtn) {
		this.editBtn = editBtn;
	}



	public JButton getCnlEditBtn() {
		return cnlEditBtn;
	}



	public void setCnlEditBtn(JButton cnlEditBtn) {
		this.cnlEditBtn = cnlEditBtn;
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



	public JScrollPane getInqWriteJsp() {
		return inqWriteJsp;
	}



	public void setInqWriteJsp(JScrollPane inqWriteJsp) {
		this.inqWriteJsp = inqWriteJsp;
	}



	public JScrollPane getInqConfirmJsp() {
		return inqConfirmJsp;
	}



	public void setInqConfirmJsp(JScrollPane inqConfirmJsp) {
		this.inqConfirmJsp = inqConfirmJsp;
	}



	public JScrollPane getInqReplyJsp() {
		return inqReplyJsp;
	}



	public void setInqReplyJsp(JScrollPane inqReplyJsp) {
		this.inqReplyJsp = inqReplyJsp;
	}



	public JScrollPane getInqEditJsp() {
		return inqEditJsp;
	}



	public void setInqEditJsp(JScrollPane inqEditJsp) {
		this.inqEditJsp = inqEditJsp;
	}



	public JTextArea getSubWriteJta() {
		return subWriteJta;
	}



	public void setSubWriteJta(JTextArea subWriteJta) {
		this.subWriteJta = subWriteJta;
	}



	public JTextArea getContentsWriteJta() {
		return contentsWriteJta;
	}



	public void setContentsWriteJta(JTextArea contentsWriteJta) {
		this.contentsWriteJta = contentsWriteJta;
	}



	public JTextArea getSubConfirmJta() {
		return subConfirmJta;
	}



	public void setSubConfirmJta(JTextArea subConfirmJta) {
		this.subConfirmJta = subConfirmJta;
	}



	public JTextArea getContentsConfirmJta() {
		return contentsConfirmJta;
	}



	public void setContentsConfirmJta(JTextArea contentsConfirmJta) {
		this.contentsConfirmJta = contentsConfirmJta;
	}



	public JTextArea getReplyConfirmJta() {
		return replyConfirmJta;
	}



	public void setReplyConfirmJta(JTextArea replyConfirmJta) {
		this.replyConfirmJta = replyConfirmJta;
	}



	public JTextArea getSubEditJta() {
		return subEditJta;
	}



	public void setSubEditJta(JTextArea subEditJta) {
		this.subEditJta = subEditJta;
	}



	public JTextArea getContentsEditJta() {
		return contentsEditJta;
	}



	public void setContentsEditJta(JTextArea contentsEditJta) {
		this.contentsEditJta = contentsEditJta;
	}
	
	public JFrame getMem_Inquiry_Main_View() {
		return mem_Inquiry_Main_View;
	}



	public void setMem_Inquiry_Main_View(JFrame mem_Inquiry_Main_View) {
		this.mem_Inquiry_Main_View = mem_Inquiry_Main_View;
	}
	

	public JFrame getMem_Inquiry_Write_View() {
		return mem_Inquiry_Write_View;
	}



	public void setMem_Inquiry_Write_View(JFrame mem_Inquiry_Write_View) {
		this.mem_Inquiry_Write_View = mem_Inquiry_Write_View;
	}



	public JFrame getMem_Inquiry_Confirm_View() {
		return mem_Inquiry_Confirm_View;
	}



	public void setMem_Inquiry_Confirm_View(JFrame mem_Inquiry_Confirm_View) {
		this.mem_Inquiry_Confirm_View = mem_Inquiry_Confirm_View;
	}



	public JFrame getMem_Inquiry_Edit_View() {
		return mem_Inquiry_Edit_View;
	}



	public void setMem_Inquiry_Edit_View(JFrame mem_Inquiry_Edit_View) {
		this.mem_Inquiry_Edit_View = mem_Inquiry_Edit_View;
	}



	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
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

	public JButton getConfirmFAQBtn() {
		return confirmFAQBtn;
	}

	public void setConfirmFAQBtn(JButton confirmFAQBtn) {
		this.confirmFAQBtn = confirmFAQBtn;
	}

	public JScrollPane getFaqJsp() {
		return faqJsp;
	}

	public void setFaqJsp(JScrollPane faqJsp) {
		this.faqJsp = faqJsp;
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

	public JFrame getMem_FAQ_Confirm_View() {
		return mem_FAQ_Confirm_View;
	}

	public void setMem_FAQ_Confirm_View(JFrame mem_FAQ_Confirm_View) {
		this.mem_FAQ_Confirm_View = mem_FAQ_Confirm_View;
	}
	
	
	


}//class
