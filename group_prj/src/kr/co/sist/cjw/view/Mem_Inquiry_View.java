package kr.co.sist.cjw.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.cjw.event.Mem_Inquiry_Event;
import kr.co.sist.cjw.service.Mem_Inquiry_Service;
import kr.co.sist.cjw.vo.FAQ_VO;
import kr.co.sist.cjw.vo.Inquiry_VO;


public class Mem_Inquiry_View extends JFrame {
	//button
		private String id;
		
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
		
		//JFrame
		private JFrame mem_Inquiry_Main_View;
		private JFrame mem_Inquiry_Write_View;
		private JFrame mem_Inquiry_Confirm_View;
		private JFrame mem_Inquiry_Edit_View;
		


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	public Mem_Inquiry_View(String id) throws HeadlessException {
		this.id=id;
	}



	public void mem_Inquiry_Main_View() {
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
	    faqTable.setDefaultEditor(Object.class, null);
	    faqTable.setShowGrid(false);
	    faqTable.setIntercellSpacing(new Dimension(0, 0));
	    JScrollPane faqScrollPane = new JScrollPane(faqTable);
	    faqScrollPane.setBounds(50, 90, 1100, 260); 
	    faqScrollPane.setBorder(new LineBorder(Color.lightGray));
	    mem_Inquiry_Main_View.add(faqScrollPane);

	    //문의 목록 테이블
	    inqTable = new JTable(); 
	    inqTable.setDefaultEditor(Object.class, null);
	    inqTable.setShowGrid(false);
	    inqTable.setIntercellSpacing(new Dimension(0, 0));
	    JScrollPane inqScrollPane = new JScrollPane(inqTable);
	    inqScrollPane.setBounds(50, 380, 1100, 410); 
	    inqScrollPane.setBorder(new LineBorder(Color.lightGray));
	    mem_Inquiry_Main_View.add(inqScrollPane);
	    
	    
	    Mem_Inquiry_Service Mem_Inq_Service = new Mem_Inquiry_Service();
	    List<FAQ_VO> faqList = Mem_Inq_Service.searchFAQ();

	    DefaultTableModel faqModel = new DefaultTableModel(new String[]{"", "", ""}, 0);
	    faqTable.setModel(faqModel);
        for (FAQ_VO faq : faqList) {
            faqModel.addRow(new Object[]{
                faq.getFaq_title(), 
                "⇒ " + faq.getFaq_contents(), 
                faq.getFaq_reg_date() 
            });
        }
        
        
        faqTable.getColumnModel().getColumn(0).setPreferredWidth(400); 
        faqTable.getColumnModel().getColumn(1).setPreferredWidth(600); 
        faqTable.getColumnModel().getColumn(2).setPreferredWidth(100); 
        faqTable.setModel(faqModel);
        
	    List<Inquiry_VO> inqList = Mem_Inq_Service.searchINQ();

	    DefaultTableModel inqModel = new DefaultTableModel(new String[]{"문의번호", "날짜", "제목","상태"}, 0);
	    inqTable.setModel(inqModel);
        for (Inquiry_VO inq : inqList) {
        	inqModel.addRow(new Object[]{
                inq.getInq_Id(), 
                inq.getInq_Reg_Date(), 
                inq.getInq_Title(), 
                inq.getInq_Status() 
            });
        }
        
        //가운데 정렬 설정
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // 문의번호, 날짜, 상태 열에 가운데 정렬 적용
        inqTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // 문의번호
        inqTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // 날짜
        inqTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // 상태
        
        inqTable.getColumnModel().getColumn(0).setPreferredWidth(100); 
        inqTable.getColumnModel().getColumn(1).setPreferredWidth(100); 
        inqTable.getColumnModel().getColumn(2).setPreferredWidth(900);
        inqTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        inqTable.setModel(inqModel);
        
        Mem_Inquiry_Event eventHandler = new Mem_Inquiry_Event(this);
        inqTable.addMouseListener(eventHandler);
        addJbtn.addActionListener(eventHandler);
        cnlMainJbtn.addActionListener(eventHandler);
	    


	    mem_Inquiry_Main_View.setVisible(true); 
	}//mem_Inquiry_Main_View
	
	
	
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
	    logoutWriteBtn = new JButton("로그아웃");
	    logoutWriteBtn.setBounds(1030,70,100,30);
	    mem_Inquiry_Write_View.add(logoutWriteBtn);
	    
	    saveBtn = new JButton("저장");
	    saveBtn.setBounds(920, 800, 100, 40); 
	    mem_Inquiry_Write_View.add(saveBtn);

	    cnlBtn = new JButton("취소");
	    cnlBtn.setBounds(1030, 800, 100, 40); 
	    mem_Inquiry_Write_View.add(cnlBtn);
	    
	    //이벤트 추가
	    Mem_Inquiry_Event eventHandler = new Mem_Inquiry_Event(this);
	    
	    
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



	public JButton getLogoutWriteBtn() {
		return logoutWriteBtn;
	}



	public void setLogoutWriteBtn(JButton logoutWriteBtn) {
		this.logoutWriteBtn = logoutWriteBtn;
	}



	public JButton getLogoutConfirmBtn() {
		return logoutConfirmBtn;
	}



	public void setLogoutConfirmBtn(JButton logoutConfirmBtn) {
		this.logoutConfirmBtn = logoutConfirmBtn;
	}



	public JButton getLogoutEditBtn() {
		return logoutEditBtn;
	}



	public void setLogoutEditBtn(JButton logoutEditBtn) {
		this.logoutEditBtn = logoutEditBtn;
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



}//class
