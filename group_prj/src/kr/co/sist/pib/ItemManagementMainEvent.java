package kr.co.sist.pib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ItemManagementMainEvent implements ActionListener, ListSelectionListener {

	private ItemManagementMainView itemMV;
	
	
	public ItemManagementMainEvent(ItemManagementMainView itemMV) {
		this.itemMV = itemMV;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == itemMV.getRegisterButton()) {
			
			JFrame parentFrame = (JFrame)itemMV.getRootPane().getParent();
			new ItemRegView(parentFrame, itemMV);		
//			   JOptionPane.showMessageDialog(itemMV, "미구현 기능입니다.");
//			   String[] newProduct = {"1", "상품1", "소형", "100", "3000", "5000", "2025-04-01"};
//               itemMV.getDtm().addRow(newProduct); // 예시 데이터 추가
              
			
			
		}
		
		if(e.getSource() == itemMV.getUpdateButton()) {
			
			   int selectedRow = itemMV.getItem_management_table().getSelectedRow();
               if (selectedRow != -1) {
                   // 예시로 상품명을 수정
//                   itemMV.getDtm().setValueAt("수정된 상품명", selectedRow, 1);
            	   JFrame parentFrame = (JFrame)itemMV.getRootPane().getParent();
            	   new ItemModifyView(itemMV.getDtm(), itemMV.getItem_management_table().getSelectedRow(), parentFrame, itemMV);
               } else {
                   JOptionPane.showMessageDialog(itemMV, "수정할 상품을 선택하세요.", "경고", JOptionPane.WARNING_MESSAGE);
               }
               
			
			
			
		}
		
		
		
	}

	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		
	}
	
	
	public void moveToItemRegView() {
		
	}
	
	public void moveToItemModifyView(int item_num) {
		
	}

}
