package kr.co.sist.pib;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

public class ItemModifyEvent implements ActionListener, FocusListener {
    
    private itemModifyView view;
    private String initialProductName;
    private String initialCost;
    private String initialSellPrice;

    public ItemModifyEvent(itemModifyView view) {
        this.view = view;
        initialProductName = view.getNameField().getText();
        initialCost = view.getCostField().getText();
        initialSellPrice = view.getPriceField().getText();
    }
    
    public int getRepairCost(String type) {
    	switch(type) {
    	case "소형" : 
    		return 3000;
    	case "중형" :
    		return 6000;
    	case "대형" :
    		return 9000;
    	}
    	return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == view.getDecreaseButton()) {
            handleDecreaseStock();
        } else if (source == view.getIncreaseButton()) {
            handleIncreaseStock();
        } else if (source == view.getSaveButton()) {
        	   
            int result = JOptionPane.showConfirmDialog(view, "수정하시겠습니까?", "수정", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if(result == JOptionPane.YES_OPTION) {
        	ItemManagementVO addImVO = new ItemManagementVO();
        	String carType = view.getCarTypeComboBox().getSelectedItem().toString();
        	DecimalFormat df = new DecimalFormat("#.##");
        	addImVO.setItem_num(Integer.parseInt(view.getProductNum().getText()));
        	addImVO.setItem_name(view.getNameField().getText().strip());
        	addImVO.setItem_stock(Integer.parseInt(view.getStockField().getText().replace(",", "")));
//        	addImVO.setItem_cost(Integer.parseInt(view.getCostField().getText()));
        	addImVO.setItem_cost(Integer.parseInt(view.getCostField().getText().replace(",", "")));
//        	addImVO.setItem_price(Integer.parseInt(view.getPriceField().getText()));
        	addImVO.setItem_price(Integer.parseInt(view.getPriceField().getText().replace(",", "")));
        	addImVO.setItem_repair_cost(getRepairCost(carType));
        	addImVO.setCar_type(carType);
        	if(!new ItemManagementService().modifyImMember(addImVO)) {
        		JOptionPane.showMessageDialog(view, "수정에 실패하였습니다.");
        	}
        	System.out.println("업데이트 : " + addImVO);
        	ItemManagementMainView viewPanel = (ItemManagementMainView) view.getMainPanel();
        	viewPanel.setInitialTableData();
            
            }else {
            	
            }
            
        	
        	
//        	handleSaveChanges();
        }
        
        else if (source == view.getDeleteButton()) {
        	System.out.println("삭제 버튼 이벤트");
        	new ItemManagementService().removeImMember(Integer.parseInt(view.getProductNum().getText()));
        }
        
        else if (source == view.getCloseButton()) {
        	view.dispose();
        }
    }

    private void handleDecreaseStock() {
        try {
            int currentStock = Integer.parseInt(view.getStockField().getText());
            int quantityUnit = Integer.parseInt(view.getQuantityUnitField().getText());
            int desireStock = currentStock - quantityUnit;
            int finalStock = Math.max(desireStock, 0);
//            if (currentStock - quantityUnit >= 0) {
//                view.getStockField().setText(String.valueOf(currentStock - quantityUnit));
//            } else {
//                JOptionPane.showMessageDialog(view, "수량이 0보다 적을 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
//            }
            view.getStockField().setText(String.valueOf(finalStock));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "유효한 숫자를 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleIncreaseStock() {
        try {
            int currentStock = Integer.parseInt(view.getStockField().getText());
            int quantityUnit = Integer.parseInt(view.getQuantityUnitField().getText());
            view.getStockField().setText(String.valueOf(currentStock + quantityUnit));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "유효한 숫자를 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleSaveChanges() {
        view.getTableModel().setValueAt(view.getNameField().getText(), view.getSelectedRow(), 1);
        view.getTableModel().setValueAt(view.getCarTypeComboBox().getSelectedItem(), view.getSelectedRow(), 2);
        view.getTableModel().setValueAt(view.getCostField().getText(), view.getSelectedRow(), 4);
        view.getTableModel().setValueAt(view.getPriceField().getText(), view.getSelectedRow(), 5);
        view.getTableModel().setValueAt(view.getStockField().getText(), view.getSelectedRow(), 3);
        
        view.dispose();
    }

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource() == view.getNameField()) {
			if(view.getNameField().getText().equals(initialProductName))
				view.getNameField().setText("");
				view.getNameField().setForeground(Color.BLACK); 
			
		}
		if(e.getSource() == view.getCostField()) {
			if(view.getCostField().getText().equals(initialCost))
			view.getCostField().setText("");
			view.getCostField().setForeground(Color.BLACK); 
		}
		
		if(e.getSource() == view.getPriceField()) {
			if(view.getPriceField().getText().equals(initialSellPrice))
			view.getPriceField().setText("");
			view.getPriceField().setForeground(Color.BLACK); 
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == view.getNameField()) {
			if(view.getNameField().getText().isBlank()) {
				view.getNameField().setForeground(Color.GRAY); 
				view.getNameField().setText(initialProductName);
				
			}
		}
		if(e.getSource() == view.getCostField()) {
			if(view.getCostField().getText().isBlank()) {
				view.getCostField().setForeground(Color.GRAY);
				view.getCostField().setText(initialCost);
			}
		}
		
		if(e.getSource() == view.getPriceField()) {
			if(view.getPriceField().getText().isBlank()) {
				view.getPriceField().setForeground(Color.GRAY);
				view.getPriceField().setText(initialSellPrice);
			}
		}
	}
    
    
}
