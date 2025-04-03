package kr.co.sist.hjy;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*이거 나중에 extends JPanel로 바꿔야 함.*/
@SuppressWarnings("serial")
public class FixPanel extends JPanel {
	private JTable jtbSummary;
	
	public FixPanel() {
		makeFixView();
	}//FixView
	
	
	private void makeFixView() {
		
		setLayout(null);

		
		String[] columnNames= {"접수번호","접수일","상품명","차량정보","접수자","결제금액","처리상태"};
		
		DefaultTableModel dtm=new DefaultTableModel(tableRecord(columnNames),columnNames);
		
		jtbSummary=new JTable(dtm);
		
		JScrollPane jsp =new JScrollPane(jtbSummary);
		jtbSummary.setRowHeight(25);
		jtbSummary.setFont(new Font("굴림",Font.PLAIN,12));
		
		jsp.setBounds(100, 0, 1000, 600);
		
		
		add(jsp);

		
		
//		setTitle("정비");
		FixEvt fixEvt=new FixEvt(this);
//		addWindowListener(fixEvt);
		jtbSummary.addMouseListener(fixEvt);
		
		setBounds(965, 100, 1200, 700);
		setVisible(true);
		
//		setResizable(false);

	}//make FixView
	
	/**
	 * "접수번호","접수일","상품명","차량번호","접수자","결제금액","처리상태"
	 * @return
	 */
	private Object[][] tableRecord(String[] columnNames){
		
		
		FixService fs=new FixService();
		List<FixPanelVO> list=fs.viewAllFixList();
		
		Object[][] rowData =new Object[list.size()][columnNames.length];
		for(int row=0; row<list.size();row++) {//행
			rowData[row][0]=list.get(row).getFixNum();//접수번호
			rowData[row][1]=list.get(row).getFixRegDate();//접수일
			rowData[row][2]=list.get(row).getItemName().toString();//상품명
			rowData[row][3]=((list.get(row).getMfgName()+" "+list.get(row).getCarType().toString()));//차량번호
			rowData[row][4]=list.get(row).getMemName().toString();//접수자
					//결제금액
			rowData[row][6]=list.get(row).getFixStatus();//처리상태
			
		}//end for(row)

	
		
		return rowData;
	}//tableRecord
	
	public static void main(String[] args) {
		new FixPanel();
	}
	
}//class

