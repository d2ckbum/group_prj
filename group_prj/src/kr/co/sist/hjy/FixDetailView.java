package kr.co.sist.hjy;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class FixDetailView extends JDialog {
	private FixView fv;
	private JTable jtbFirst;
	private JTable jtbSecond;
	private JComboBox<String> jtbStatus;
	private JTextArea jtaMemo;
	private JButton jbtnSave;
	private JButton jbtnModifiy;
	private JButton jbtnClose;
	
	public FixDetailView(FixView fv) {
		super(fv,"정비 상세",true);
		this.fv=fv;
		
		makeFixDetailView();
	}//FixDetailView
	
	public void makeFixDetailView() {//전체화면
		
		defaultView();
		
		
	
		setBounds(fv.getX()+20,fv.getY()+20,700,500);
		setVisible(true);
	}//makeFixDetailView
	
	
	public void defaultView() {
		JLabel jlblFixNum = new JLabel("접수번호");
		JLabel jlblFixRegDate=new JLabel("접수일시");
		
		String[] columnNames= {"상품명","결제금액","작업내역","처리상태"};
		String[][] rowData= {};
		DefaultTableModel dtm = new DefaultTableModel(rowData,columnNames);
		jtbFirst = new JTable(dtm);
		JScrollPane jsp=new JScrollPane(jtbFirst);
		
		JLabel jlblPersonInfo=new JLabel("회원정보");
		String[] columnNames2= {"상품명","결제금액","작업내역","처리상태"};
		String[][] rowData2= {};
		DefaultTableModel dtm2 = new DefaultTableModel(rowData2,columnNames2);
		jtbSecond = new JTable(dtm2);
		JScrollPane jsp2=new JScrollPane(jtbSecond);
		
		setLayout(null);
		
		
		jlblFixNum.setBounds(20,20,60,60);
		add(jlblFixNum);
		
	}//defaultView
	
}//class
