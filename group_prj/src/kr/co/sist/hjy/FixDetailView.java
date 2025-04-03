package kr.co.sist.hjy;


import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class FixDetailView extends JDialog{
	private FixPanel fp;
	
	private JLabel jlblItemNameData;
	private JLabel jlblItemPriceData;
	private JLabel jlblFixNumData;
	private JLabel jlblFixRegDateData;
	private JButton jbtnClose;
	
	private JTextArea jtaFixMemo;
	

	private char dStatus='1';//'1','2','3'

	
	private final int X=50,Y=20;
	
	public FixDetailView(FixPanel fp) {
//		super(fp,"정비 상세",true);
		
		this.fp=fp;
		
		makeFixDetailView();
	}//FixDetailView
	
	public void makeFixDetailView() {//전체화면
		
		setLayout(null);
		
		
		/*기본 폰트*/
		Font titleFont=new Font("맑은 고딕",Font.BOLD,18);
		Font plainFont=new Font("맑은 고딕",Font.PLAIN,15);
		
		/*테두리 설정*/
		LineBorder lb=new LineBorder(Color.black, 1, false);
		
		
		/*Label Background Color*/
		Color labelColor=new Color(243,243,243);

		
		
		defaultView(titleFont, plainFont,lb,labelColor);
		
		/*이벤트발생*/
		FixDetailEvt fde=new FixDetailEvt(this);
		addWindowListener(fde);
		jbtnClose.addActionListener(fde);
		
		
		
		getContentPane().setBackground(Color.WHITE);//다이얼로그 백그라운드 색 지정
		setBounds(fp.getX()+20,fp.getY()+20,770,750);
		setVisible(true);
	}//makeFixDetailView
	
	private void defaultView(Font titleFont, Font plainFont, LineBorder lb, Color labelColor) {
		//map '1': 접수완료, '2':정비중, '3':정비완료
		Map<Character,String> statusMap=new HashMap<Character, String>();
		statusMap.put('1', "접수완료");
		statusMap.put('2', "정비중");
		statusMap.put('3', "정비완료");
		
		firstView(titleFont,plainFont,lb,labelColor);//접수번호, 접수 일시, 상품명 테이블 처럼 생긴거
		secondView(titleFont,plainFont,lb,labelColor);//회원 정보관련 View
		
		//여기서 statusFlag를 보고 1,2,3 어떤 view를 띄울지 선택하도록 if문을 주자아아아아아
		if(dStatus=='1' || dStatus=='2') {
			ingView(plainFont,lb,labelColor,"저장");
		}
		else if(dStatus=='3') {
			modifyMemoView(plainFont,lb,labelColor,"수정");	
		}else {
			modifyMemoView(plainFont,lb,labelColor,"수정");
		}//end if~else
		
		
		thirdView(titleFont,plainFont,lb,labelColor);//정비 메모 관련 View
		
		
		
		
	}//defaultView
	
	
	private void firstView(Font titleFont, Font plainFont, LineBorder lb,Color labelColor) {

		JLabel jlblFixNum = new JLabel("접수번호");
		jlblFixNumData = new JLabel("20240101131114");
		
		JLabel jlblFixRegDate=new JLabel("접수일시");
		jlblFixRegDateData=new JLabel("2025-03-19 19:16:18");
		
		JLabel jlblItemName=new JLabel("상품명");
		jlblItemNameData=new JLabel("불스원샷 엔진오일 1L");
		
		JLabel jlblItemPrice=new JLabel("결제금액");
		jlblItemPriceData=new JLabel("51,600");
		
		
		JLabel jlblWork=new JLabel("작업내역");
		JLabel jlblWorkData=new JLabel("엔진오일 교체");
		
		JLabel jlblFixStatus=new JLabel("처리상태");
		
		/*상품명 ToolTip 설정*/
		jlblItemNameData.setToolTipText(jlblItemNameData.getText());

		
		/*font 설정*/
		jlblFixNum.setFont(titleFont);
		jlblFixNumData.setFont(plainFont);
		
		jlblFixRegDate.setFont(titleFont);
		jlblFixRegDateData.setFont(plainFont);
		
		jlblItemName.setFont(titleFont);
		jlblItemNameData.setFont(plainFont);
		
		jlblItemPrice.setFont(titleFont);
		jlblItemPriceData.setFont(plainFont);
		
		jlblWork.setFont(titleFont);
		jlblWorkData.setFont(plainFont);
		
		jlblFixStatus.setFont(titleFont);
		
		/*테두리 설정*/
		jlblItemName.setBorder(lb);
		jlblItemNameData.setBorder(lb);
		
		jlblItemPrice.setBorder(lb);
		jlblItemPriceData.setBorder(lb);
		
		jlblWork.setBorder(lb);
		jlblWorkData.setBorder(lb);
		
		jlblFixStatus.setBorder(lb);
		
		
		/*label 배경색 설정*/
		jlblItemName.setOpaque(true);//JLabel 투명하지 않게 설정
		jlblItemName.setBackground(labelColor);
		
		jlblItemPrice.setOpaque(true);
		jlblItemPrice.setBackground(labelColor);
		
		jlblWork.setOpaque(true);
		jlblWork.setBackground(labelColor);
		
		jlblFixStatus.setOpaque(true);
		jlblFixStatus.setBackground(labelColor);
		
		/*라벨 안의 이름 위치 설정*/
		jlblItemName.setHorizontalAlignment(SwingConstants.CENTER);
		jlblItemNameData.setHorizontalAlignment(SwingConstants.CENTER);
		
		jlblItemPrice.setHorizontalAlignment(SwingConstants.CENTER);
		jlblItemPriceData.setHorizontalAlignment(SwingConstants.CENTER);
		
		jlblWork.setHorizontalAlignment(SwingConstants.CENTER);
		jlblWorkData.setHorizontalAlignment(SwingConstants.CENTER);
		
		jlblFixStatus.setHorizontalAlignment(SwingConstants.CENTER);
		
		/*Dialog에서의 위치 설정*/
		
		jlblFixNum.setBounds(X,Y,100,30);
		jlblFixNumData.setBounds(X+100,Y,170,30);
	
		jlblFixRegDate.setBounds(X+330,Y,100,30);
		jlblFixRegDateData.setBounds(X+430,Y,240,30);
		
		jlblItemName.setBounds(X,Y+40,200,30); //상품명
		jlblItemNameData.setBounds(X, Y+69, 200, 30);
		
		jlblItemPrice.setBounds(X+199,Y+40,150,30); //결제금액
		jlblItemPriceData.setBounds(X+199,Y+69,150,30);
		
		jlblWork.setBounds(X+348,Y+40,150,30);//작업 내역
		jlblWorkData.setBounds(X+348,Y+69,150,30);
		
		
		jlblFixStatus.setBounds(X+497, Y+40, 150, 30);
		
		/*화면에 추가*/
		add(jlblFixNum);
		add(jlblFixNumData);
		add(jlblFixRegDate);
		add(jlblFixRegDateData);
		add(jlblItemName);
		add(jlblItemNameData);
		add(jlblItemPrice);
		add(jlblItemPriceData);
		add(jlblWork);
		add(jlblWorkData);
		add(jlblFixStatus);
		
		
		
	}//firstView
	
	
	private void secondView(Font titleFont, Font plainFont, LineBorder lb,Color labelColor) {
		JLabel memInfo = new JLabel("회원 정보");
		
		JLabel memNum=new JLabel("회원번호");
		JLabel memNumData=new JLabel("20230213132521");
		
		JLabel memName=new JLabel("접수자");
		JLabel memNameData=new JLabel("홍길동");
		
		JLabel memTell=new JLabel("전화번호");
		JLabel memTellData=new JLabel("010-1234-5678");
		
		/*Font 설정*/
		memInfo.setFont(titleFont);
		
		memNum.setFont(titleFont);
		memNumData.setFont(plainFont);
		
		memName.setFont(titleFont);
		memNameData.setFont(plainFont);
		
		memTell.setFont(titleFont);
		memTellData.setFont(plainFont);
		
		/*테두리 설정*/
		memNum.setBorder(lb);
		memNumData.setBorder(lb);
		
		memName.setBorder(lb);
		memNameData.setBorder(lb);
		
		memTell.setBorder(lb);
		memTellData.setBorder(lb);
		
		
		/*text 위치 설정*/
		memNum.setHorizontalAlignment(SwingConstants.CENTER);
		memNumData.setHorizontalAlignment(SwingConstants.CENTER);
		
		memName.setHorizontalAlignment(SwingConstants.CENTER);
		memNameData.setHorizontalAlignment(SwingConstants.CENTER);
		
		memTell.setHorizontalAlignment(SwingConstants.CENTER);
		memTellData.setHorizontalAlignment(SwingConstants.CENTER);
		
		/*Label Background 설정*/
		memNum.setOpaque(true);
		memNum.setBackground(labelColor);
		
		memName.setOpaque(true);
		memName.setBackground(labelColor);
		
		memTell.setOpaque(true);
		memTell.setBackground(labelColor);
		
		
		/*위치 설정*/
		memInfo.setBounds(X, Y+130, 100, 30);
		
		memNum.setBounds(X,Y+170,200,30);
		memNumData.setBounds(X,Y+199,200,30);
		
		memName.setBounds(X+199,Y+170,200,30);
		memNameData.setBounds(X+199,Y+199,200,30);
		
		memTell.setBounds(X+398,Y+170,200,30);
		memTellData.setBounds(X+398,Y+199,200,30);
		
		/*화면에 추가*/
		add(memInfo);
		add(memNum);
		add(memNumData);
		add(memName);
		add(memNameData);
		add(memTell);
		add(memTellData);

	}//secondView

	
	private void thirdView(Font titleFont,Font plainFont,LineBorder lb,Color labelColor) {
		JLabel fixMemo=new JLabel("정비 메모");
		JLabel fixMemoExplain=new JLabel("(300자 이내)");
		jbtnClose=new JButton("닫기");
		
		jtaFixMemo=new JTextArea();
		
		JScrollPane jspMemo=new JScrollPane(jtaFixMemo);
		
		
		fixMemo.setFont(titleFont);
		fixMemoExplain.setFont(new Font("맑은 고딕",Font.PLAIN,10));
		jtaFixMemo.setFont(new Font("맑은 고딕",Font.PLAIN,15));
		
		jtaFixMemo.setBorder(lb);
		jbtnClose.setBorder(lb);
		
		jbtnClose.setBackground(labelColor);
		jbtnClose.setFont(plainFont);
		
		fixMemo.setBounds(X,Y+260,100,30);
		fixMemoExplain.setBounds(X+93,Y+263,100,30);
		jspMemo.setBounds(X, Y+300, 650, 300);
		jbtnClose.setBounds(X+530,Y+620,120,50);
		
		add(fixMemo);
		add(fixMemoExplain);
		add(jspMemo);
		add(jbtnClose);
		
	}//thirdView

	
	private void ingView(Font plainFont,LineBorder lb,Color labelColor,String jbtnText) {
		DefaultComboBoxModel<String> dcbm=new DefaultComboBoxModel<String>();
		JComboBox<String> jtbStatus=new JComboBox<String>(dcbm);
		
		JButton jbtnSave=new JButton(jbtnText);
		
		dcbm.addElement("접수완료");
		dcbm.addElement("정비중");
		dcbm.addElement("정비완료");
		
		jtbStatus.setBorder(lb);
		jtbStatus.setBackground(Color.WHITE);
		jtbStatus.setFont(plainFont);
		
		jbtnSave.setBackground(labelColor);
		jbtnSave.setBorder(lb);
		jbtnSave.setFont(plainFont);
		
		
		jtbStatus.setBounds(X+497, Y+69, 150,30);
		jbtnSave.setBounds(X+365,Y+620,120,50);
		
		
		add(jtbStatus);
		add(jbtnSave);
		
	}//ingView
	
	private void modifyMemoView(Font plainFont,LineBorder lb,Color labelColor,String jbtnText) {
		JLabel jlblStatus=new JLabel("접수중");
		JButton jbtnModifySave=new JButton(jbtnText);
		
		jlblStatus.setOpaque(true);
		jlblStatus.setBackground(Color.WHITE);
		jlblStatus.setBorder(lb);
		jlblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		jlblStatus.setFont(plainFont);
		
		jbtnModifySave.setBorder(lb);
		jbtnModifySave.setBackground(labelColor);
		jbtnModifySave.setFont(plainFont);
		
		jlblStatus.setBounds(X+497, Y+69, 150,30);
		jbtnModifySave.setBounds(X+365,Y+620,120,50);
		
		
		 add(jlblStatus);
		 add(jbtnModifySave);
		
		
	}//modifyMemoView

	public JButton getJbtnClose() {
		return jbtnClose;
	}//getJbtnClose
	
	
	
	
	
}//class
