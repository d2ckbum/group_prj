package kr.co.sist.pib;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

public class ItemModifyEvent implements ActionListener, FocusListener {
    
    private ItemModifyView view;
    private String initialProductName;
    private String initialCost;
    private String initialSellPrice;
    private int maxItemPrice = 2000000000;

    public ItemModifyEvent(ItemModifyView view) {
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
        } 
        
        else if (source == view.getIncreaseButton()) {
            handleIncreaseStock();
        } 
        
        else if (source == view.getSaveButton()) {
        	ItemManagementMainView viewPanel = (ItemManagementMainView) view.getMainPanel();
        	int lastSelectedRow = viewPanel.getItem_management_table().getSelectedRow();
        	   
            int result = JOptionPane.showConfirmDialog(view, "수정하시겠습니까?", "수정", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if(result == JOptionPane.YES_OPTION) {
        	try {
            ItemManagementVO addImVO = new ItemManagementVO();
        	String carType = view.getCarTypeComboBox().getSelectedItem().toString();
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
        		JOptionPane.showMessageDialog(view, "수정에 실패하였습니다.", "수정실패", JOptionPane.PLAIN_MESSAGE);
        		return;
        		
        	}
        	
        	System.out.println("업데이트 : " + addImVO);
        	viewPanel.setInitialTableData();
        	viewPanel.getItem_management_table().setRowSelectionInterval(lastSelectedRow, lastSelectedRow);
        	JOptionPane.showMessageDialog(view, "상품정보를 수정하였습니다.", "수정성공", JOptionPane.PLAIN_MESSAGE);
        	view.dispose();
        	}catch(NumberFormatException n){
        		
        		JOptionPane.showMessageDialog(view, "원가와 판매가에는 숫자만 입력가능합니다", "수정실패", JOptionPane.PLAIN_MESSAGE);
        		
        	}
            
            }
            
        	
        	
//        	handleSaveChanges();
        }
        
        else if (source == view.getDeleteButton()) {
//        	System.out.println("삭제 버튼 이벤트");
        	
        	int optionResult = JOptionPane.showConfirmDialog(view, "상품을 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        	if(optionResult == JOptionPane.YES_OPTION) {
	        	boolean deleteFlag = new ItemManagementService().removeImMember(Integer.parseInt(view.getProductNum().getText()));
	        	
	        	if(deleteFlag) {
	        		ItemManagementMainView viewPanel = (ItemManagementMainView) view.getMainPanel();
	        		viewPanel.setInitialTableData();
	        		view.dispose();
	        		JOptionPane.showMessageDialog(view, "상품을 삭제하였습니다.", "삭제성공", JOptionPane.PLAIN_MESSAGE);
	        		
	        	}else {
	        	JOptionPane.showMessageDialog(view, "삭제에 실패하였습니다.", "삭제실패", JOptionPane.PLAIN_MESSAGE);
	        	}
        	}
        	
        	
        }
        
        else if (source == view.getCloseButton()) {
        	view.dispose();
        }
    }

    private void handleDecreaseStock() {
        try {
            int currentStock = Integer.parseInt(view.getStockField().getText());
            int quantityUnit = Integer.parseInt(view.getQuantityUnitField().getText().replace(",", ""));
            if(quantityUnit < 0) {
            	JOptionPane.showMessageDialog(view, "재고 변경 단위에는 음수가 들어갈 수 없습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            	return;
            }
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
            int quantityUnit = Integer.parseInt(view.getQuantityUnitField().getText().replace(",", ""));
            if(quantityUnit < 0) {
            	JOptionPane.showMessageDialog(view, "재고 변경 단위에는 음수가 들어갈 수 없습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            	return;
            }
            int desireStock = currentStock + quantityUnit;
            int finalStock = Math.max(desireStock, 0);
            view.getStockField().setText(String.valueOf(finalStock));
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
	public void registerNumericDocumentFilter(JTextField jtf) {
//		((AbstractDocument) jtf.getDocument()).setDocumentFilter(new NumericDocumentFilter());
		AbstractDocument doc = (AbstractDocument) jtf.getDocument();
        doc.putProperty("text-component", jtf);
        doc.setDocumentFilter(new NumericDocumentFilter());
	}
	
	
	
	

	
	class NumericDocumentFilter extends DocumentFilter {
		// 텍스트 삽입 시 숫자만 허용
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
			if (string.matches("[0-9]*") ) { // 숫자만 허용
				super.insertString(fb, offset, string, attr);
			}
		}
		
		//텍스트 삭제 시 포맷 적용
		@Override
	    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
			super.remove(fb, offset, length);
			JTextComponent textComponent = (JTextComponent) fb.getDocument().getProperty("text-component");
			if(textComponent.getText() != null && !textComponent.getText().isEmpty() ) {
			changeFieldFormat(fb);
			}
		
		}
		
		// 텍스트 교체 시 숫자만 허용
		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
					
//			System.out.println(text);
//			System.out.println("replace 이벤트");
			
			if (checkCanWrite(text)) {
				try {
				super.replace(fb, offset, length, text, attrs);
				changeFieldFormat(fb);
				}catch(NumberFormatException ne){
					super.replace(fb, 0, fb.getDocument().getLength(), "" ,null);
				}
				return;
				}else if(text.isEmpty()) {
						super.replace(fb, offset, length, "", attrs);
					}
				}
		
		private boolean checkCanWrite(String text) {
			String commaRemovedText = text.replace(",", "").strip();
			return text != null && !text.isEmpty() && commaRemovedText.matches("[0-9]*");
		}
		
		private void changeFieldFormat(FilterBypass fb) throws BadLocationException {
			JTextComponent textComponent = (JTextComponent) fb.getDocument().getProperty("text-component");
			Document doc = fb.getDocument();
			DecimalFormat df = new DecimalFormat("#,###");
			long parsedValue = Math.min(maxItemPrice, Long.parseLong(textComponent.getText().replace(",", "").strip()));
			String formatted = df.format(parsedValue);
			super.replace(fb, 0, doc.getLength(), formatted ,null);
		}
		

	}
	

}
    
    
