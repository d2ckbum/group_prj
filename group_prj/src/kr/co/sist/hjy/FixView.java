package kr.co.sist.hjy;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class FixView extends JFrame {
	private JTable jtbSummary;
	
	public FixView() {
		makeFixView();
	}//FixView
	
	
	private void makeFixView() {
		
		String[] columnNames= {"접수번호","접수일","상품명","차량번호","접수자","결제금액","처리상태"};
		String[][] rowData= {{"1","2","3","4","5","6","7"}};
		DefaultTableModel dtm=new DefaultTableModel(rowData,columnNames);
		
		jtbSummary=new JTable(dtm);
		
		JScrollPane jsp =new JScrollPane(jtbSummary);
		
		add("Center",jsp);
		
		setTitle("정비");
		FixEvt fixEvt=new FixEvt(this);
		addWindowListener(fixEvt);
		jtbSummary.addMouseListener(fixEvt);
		
		setBounds(100, 100, 1200, 700);
		setVisible(true);
		
		
		
	}//make FixView
	
	
	
	public static void main(String[] args) {
		new FixView();
	}
	
}//class

