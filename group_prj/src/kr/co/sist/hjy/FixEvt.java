package kr.co.sist.hjy;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixEvt extends WindowAdapter implements MouseListener {
	private FixPanel fp;
	private Map<String,String> statusMap;
	private List<FixPanelVO> tableList;
	private int listRowNum;
	
	public FixEvt(FixPanel fp) {
		this.fp=fp;
		fixStatus();
	}//FixEvt
	
	
	
	@Override
	public void windowClosing(WindowEvent e) {
//		fp.dispose();
	}//windowClosing
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println(fp.getJtbSummary().getSelectedRow());
		
		//애초에 여기서 넘길 때, 처리상태를 같이 넘겨야, 받는 부분에서 어떤걸 띄워줄지 결정할 수 있음.
		listRowNum=fp.getJtbSummary().getSelectedRow();
		new FixDetailView(this,tableList.get(fp.getJtbSummary().getSelectedRow()).getFixStatus());
		

	}//mouseClicked

	
	/**
	 * "접수번호","접수일","상품명","차량번호","접수자","결제금액","처리상태"
	 * @return
	 */
	public Object[][] tableRecord(String[] columnNames){
		DecimalFormat df = new DecimalFormat("#,###,###");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
		FixService fs=new FixService();
		tableList=fs.viewAllFixList();
		
		Object[][] rowData =new Object[tableList.size()][columnNames.length];
		for(int row=0; row<tableList.size();row++) {//행
			rowData[row][0]=tableList.get(row).getFixNum();//접수번호
			rowData[row][1]=sdf.format(tableList.get(row).getFixRegDate());//접수일
			rowData[row][2]=tableList.get(row).getItemName();//상품명
			rowData[row][3]=((tableList.get(row).getMfgName()+" "+tableList.get(row).getCarType()));//차량번호
			rowData[row][4]=tableList.get(row).getMemName();//접수자
			rowData[row][5]=df.format(tableList.get(row).getTotal()); //결제금액
//			System.out.println(list.get(row).getFixStatus());
			rowData[row][6]=statusMap.get(tableList.get(row).getFixStatus());//처리상태
			
		}//end for(row)
			return rowData;
	}//tableRecord
	

	private void fixStatus() {
		statusMap=new HashMap<String, String>();
		statusMap.put("1", "접수완료");
		statusMap.put("2", "정비중");
		statusMap.put("3", "정비완료");
		
	}//fixStatus
	
	
	////////사용 안하는 method//////////////////////////////////////////////////////////////////////////
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}



	public List<FixPanelVO> getTableList() {
		return tableList;
	}



	public int getListRowNum() {
		return listRowNum;
	}



	public FixPanel getFp() {
		return fp;
	}



	public Map<String, String> getStatusMap() {
		return statusMap;
	}
	
	
	

}//class
