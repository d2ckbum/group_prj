package kr.co.sist.hjy;
import java.awt.Font;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*이거 나중에 extends JPanel로 바꿔야 함.*/
@SuppressWarnings("serial")
public class FixPanel extends JPanel {
	private JTable jtbSummary;
	
	public FixPanel() {
		makeFixView();
	}//FixView
	
	
	private void makeFixView() {
		
		setLayout(null);

		FixEvt fixEvt=new FixEvt(this);
		String[] columnNames= {"접수번호","접수일","상품명","차량정보","접수자","결제금액","처리상태"};
		
		DefaultTableModel dtm=new DefaultTableModel(fixEvt.tableRecord(columnNames),columnNames);
		DefaultTableCellRenderer dtcr=new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		jtbSummary=new JTable(dtm);
		
		JScrollPane jsp =new JScrollPane(jtbSummary);
		JTableHeader tableHeader=jtbSummary.getTableHeader();
		tableHeader.setFont(new Font("맑은 고딕",Font.BOLD,15));
		tableHeader.setReorderingAllowed(false);
		jtbSummary.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//셀 가운데 정렬
		for(int i=0; i<columnNames.length;i++) {
			jtbSummary.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}//end for
		
		jtbSummary.setRowHeight(30);
		jtbSummary.setFont(new Font("굴림",Font.PLAIN,12));
		jtbSummary.setDragEnabled(true);
		jtbSummary.setFocusable(false);
		jsp.setBounds(100, 0, 1000, 600);
		
		
		add(jsp);

		
		
//		setTitle("정비");
		
//		addWindowListener(fixEvt);
		jtbSummary.addMouseListener(fixEvt);
		
		setBounds(965, 100, 1200, 700);
		setVisible(true);
		
//		setResizable(false);

	}//make FixView
	

	
	public static void main(String[] args) {
		new FixPanel();
	}

	///////////get method///////////////////////////////////////////////////

	public JTable getJtbSummary() {
		return jtbSummary;
	}
	
	
	
	
}//class

