package kr.co.sist.hjy;
import java.awt.Font;

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
	private DefaultTableModel dtm;
	
	private FixEvt fixEvt;
	
	public FixPanel() {
//		System.out.println("FixPanel 생성자");
		
		makeFixView();
	}//FixView
	
	
	public void makeFixView() {
		
		setLayout(null);
//		System.out.println("FixPanel의 makeFixView 메소드 처음  "+fixEvt.getListRowNum());
		
		
		String[] columnNames= {"접수번호","접수일","상품명","차량정보","접수자","결제금액","처리상태"};

		fixEvt=new FixEvt(this);
		fixEvt.tableRow();
//		System.out.println("FixPanel refreshFlag 0 listRowNum=="+fixEvt.getListRowNum());
		dtm=new DefaultTableModel(columnNames,fixEvt.getTableList().size()) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		fixEvt.tableRow();
		
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
		jtbSummary.setCellEditor(null);
		jsp.setBounds(100, 0, 1000, 600);
		
		
		
		
//		System.out.println("FixPanel listRowNum  " + fixEvt.getListRowNum());
		
		jtbSummary.addMouseListener(fixEvt);
		add(jsp);
//		addWindowListener(fixEvt);		
//		setTitle("정비");
		setBounds(700, 100, 1200, 700);
		setVisible(true);
		
//		setResizable(false);
		
	}//make FixView

	
	
////////////////main/////////////////////////////////////////////////////////////////////
//	public static void main(String[] args) {
//		new FixPanel();
//	}

	///////////get method///////////////////////////////////////////////////

	public JTable getJtbSummary() {
		return jtbSummary;
	}


	public DefaultTableModel getDtm() {
		return dtm;
	}
	
	
	
	
}//class

