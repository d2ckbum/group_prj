package kr.co.sist.pib;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

public class itemRegViewEvent implements ActionListener, FocusListener {
    
    private itemRegView view;
    private String initialProductName;
    private String initialCost;
    private String initialSellPrice;

    public itemRegViewEvent(itemRegView view) {
        this.view = view;
        initialProductName = view.getNameField().getText();
        initialCost = view.getCostField().getText();
        initialSellPrice = view.getPriceField().getText();
        
     // 메시지 다이얼로그에 사용될 글꼴 설정
        Font dialogFont = new Font("맑은 고딕", Font.BOLD, 12);

        // UIManager를 통해 JOptionPane의 기본 UI 설정을 수정
        UIManager.put("OptionPane.messageFont", dialogFont);
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
        } 
//        else if (source == view.getSaveButton()) {
//        	   
//            int result = JOptionPane.showConfirmDialog(view, "수정하시겠습니까?", "수정", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//            
//            if(result == JOptionPane.YES_OPTION) {
//        	ItemManagementVO addImVO = new ItemManagementVO();
//        	String carType = view.getCarTypeComboBox().getSelectedItem().toString();
//        	DecimalFormat df = new DecimalFormat("#.##");
//        	addImVO.setItem_num(Integer.parseInt(view.getProductNum().getText()));
//        	addImVO.setItem_name(view.getNameField().getText().strip());
//        	addImVO.setItem_stock(Integer.parseInt(view.getStockField().getText().replace(",", "")));
////        	addImVO.setItem_cost(Integer.parseInt(view.getCostField().getText()));
//        	addImVO.setItem_cost(Integer.parseInt(view.getCostField().getText().replace(",", "")));
////        	addImVO.setItem_price(Integer.parseInt(view.getPriceField().getText()));
//        	addImVO.setItem_price(Integer.parseInt(view.getPriceField().getText().replace(",", "")));
//        	addImVO.setItem_repair_cost(getRepairCost(carType));
//        	addImVO.setCar_type(carType);
//        	if(!new ItemManagementService().modifyImMember(addImVO)) {
//        		JOptionPane.showMessageDialog(view, "수정에 실패하였습니다.");
//        	}
//        	System.out.println("업데이트 : " + addImVO);
//        	ItemManagementMainView viewPanel = (ItemManagementMainView) view.getMainPanel();
//        	viewPanel.setInitialTableData();
//            
//            }else {
//            	
//            }
//            
//        	
//        	
////        	handleSaveChanges();
//        }
//        
//        else if (source == view.getDeleteButton()) {
//        	System.out.println("삭제 버튼 이벤트");
//        	new ItemManagementService().removeImMember(Integer.parseInt(view.getProductNum().getText()));
//        }
        else if (source == view.getRegisterButton()) {
        	
        	int result = JOptionPane.showConfirmDialog(view, "등록하시겠습니까?", "상품등록", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
             
            if(result == JOptionPane.YES_OPTION) {
         	ItemManagementVO addImVO = new ItemManagementVO();
         	String carType = view.getCarTypeComboBox().getSelectedItem().toString();
         	DecimalFormat df = new DecimalFormat("#.##");
         	addImVO.setItem_name(view.getNameField().getText().strip());
         	addImVO.setItem_stock(Integer.parseInt(view.getStockField().getText().replace(",", "")));
//         	addImVO.setItem_cost(Integer.parseInt(view.getCostField().getText()));
         	addImVO.setItem_cost(Integer.parseInt(view.getCostField().getText().replace(",", "")));
//         	addImVO.setItem_price(Integer.parseInt(view.getPriceField().getText()));
         	addImVO.setItem_price(Integer.parseInt(view.getPriceField().getText().replace(",", "")));
         	addImVO.setItem_repair_cost(getRepairCost(carType));
         	addImVO.setCar_type(carType);
         	if(!new ItemManagementService().addImMember(addImVO)) {
         		JOptionPane.showMessageDialog(view, "상품등록에 실패하였습니다.");
         	}
         	System.out.println("상품등록 : " + addImVO);
         	ItemManagementMainView viewPanel = (ItemManagementMainView) view.getMainPanel();
         	viewPanel.setInitialTableData();
         	view.dispose();
            }else {
             	
            }
             
         	
         	
        	
        	
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

//    private void handleSaveChanges() {
//        view.getTableModel().setValueAt(view.getNameField().getText(), view.getSelectedRow(), 1);
//        view.getTableModel().setValueAt(view.getCarTypeComboBox().getSelectedItem(), view.getSelectedRow(), 2);
//        view.getTableModel().setValueAt(view.getCostField().getText(), view.getSelectedRow(), 4);
//        view.getTableModel().setValueAt(view.getPriceField().getText(), view.getSelectedRow(), 5);
//        view.getTableModel().setValueAt(view.getStockField().getText(), view.getSelectedRow(), 3);
//        
//        view.dispose();
//    }

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
