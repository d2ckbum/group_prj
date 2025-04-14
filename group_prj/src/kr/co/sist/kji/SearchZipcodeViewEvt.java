package kr.co.sist.kji;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 
 */
public class SearchZipcodeViewEvt extends MouseAdapter implements ActionListener {

	private SearchZipCodeView szv;
	
	public SearchZipcodeViewEvt(SearchZipCodeView szv) {
		this.szv= szv;
	}//SearchZipcodeViewEvt
	@Override	
	public void mouseClicked(MouseEvent e) {
		sendZipcode();
	}//mouseClicked
	
	public void sendZipcode() {
		String addr;
		String zipcode;
		int selectedBtn=JOptionPane.showConfirmDialog(szv, "우편번호를 사용하시겠습니까?");
		switch(selectedBtn) {
		case JOptionPane.OK_OPTION:
			
			JTable table = szv.getTable();
//			//JTable에 선택된 행의 값을 얻기
//			System.out.println("선택한 행의번호 : " + table.getSelectedRow() + ", 열의 번호: " + table.getSelectedColumn());
			int selectedRow = table.getSelectedRow();
//			MemberView mv = szv.getMv();
			
			zipcode = table.getValueAt(selectedRow, 0).toString();
			addr = table.getValueAt(selectedRow, 1).toString();
//			mv.getJtfZipcode().setText(table.getValueAt(selectedRow, 0).toString());
//			mv.getJtfAddress().setText(table.getValueAt(selectedRow, 1).toString());
//			mv.getJtfDetails().requestFocus();
			szv.setAddr(addr);
			szv.setZipcode(zipcode);
			szv.dispose();//자식창 닫기
//			int columncnt = table.getComponentCount();
//			int selectedCol = table.getSelectedColumn();
//			System.out.println(table.getSelectedColumnCount());
//			System.out.println(table.getValueAt(selectedBtn, selectedBtn));
//			for(int col = 0; col<=columncnt; col++) {
//				System.out.println(table.getValueAt(selectedRow, col));
//			}//end for
		}//sendZipcode
		
	}//sendZipcode

	@Override
	public void actionPerformed(ActionEvent e) {
		searchZipcode();
		
	}//actionPerformed
	
	public void searchZipcode() {
		String dong = szv.getJtfDongName().getText().trim();
		if(dong.isEmpty()) {
			JOptionPane.showMessageDialog(szv, "동이름은 필수 입력");
			return;
		}//end if
		
		SearchZipcodeService szs = new SearchZipcodeService();
		List<ZipcodeVO> list =szs.searchZipcode(dong);
		
		//조회결과를 JTable에 추가
		// 1. 조회결과로 배열을 만들고
		String[] data = null;
		Iterator<ZipcodeVO> ita = list.iterator();
		
		ZipcodeVO zVO =null;
		StringBuilder addr = new StringBuilder();
		
		DefaultTableModel dtm = szv.getDtm();//모델을 받는다.
		
		if(list.isEmpty()) {
			JOptionPane.showMessageDialog(szv, dong+"은(는) 존재하지 않습니다");
			szv.getJtfDongName().setText("");
			return;
		}//end if
		
		//기존에 행수가 하나 이상이라면 
		if(dtm.getRowCount() > 0 ) {
			//행수를 초기화한다
			dtm.setRowCount(0);			
		}
		//새로운 데이터를 채운다
		while(ita.hasNext()) {
			zVO=ita.next();
			addr.delete(0, addr.length());
			addr.append(zVO.getSido()).append(" ")
			.append(zVO.getGugun()).append(" ")
			.append(zVO.getDong()).append(" ")
			.append(zVO.getBunji()).append(" ")
			;
			
			
			data = new String[2];//우편번호, 주소
			data[0]=zVO.getZipcode();
			data[1]=addr.toString();
			
			
		// 2. DefaultTableModel에 추가(addRow)
			dtm.addRow(data);
		}//end while
		
		szv.getJtfDongName().setText("");
		
	}//searchZipcode
	
	
	
}//class
