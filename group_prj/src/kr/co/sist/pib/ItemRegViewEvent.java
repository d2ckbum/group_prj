package kr.co.sist.pib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

public class ItemRegViewEvent implements ActionListener, FocusListener {
    
    private ItemRegView view;
    private String initialProductName;
    private String initialCost;
    private String initialSellPrice;
    private int maxItemPrice = 2000000000;
    private DocumentFilter defaultDF;

    public ItemRegViewEvent(ItemRegView view) {
        this.view = view;
        initialProductName = view.getNameField().getText();
        initialCost = view.getCostField().getText();
        initialSellPrice = view.getPriceField().getText();
        defaultDF = new DocumentFilter();
        
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
        } else if (source == view.getRegisterButton()) {
        	
        	int result = JOptionPane.showConfirmDialog(view, "등록하시겠습니까?", "상품등록", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
             
            if(result == JOptionPane.YES_OPTION) {
            
            if(!fieldInputCheck()) {
            	return;
            }
            
         	ItemManagementVO addImVO = new ItemManagementVO();
         	String carType = view.getCarTypeComboBox().getSelectedItem().toString();
         	addImVO.setItem_name(view.getNameField().getText().strip());
         	addImVO.setItem_stock(Integer.parseInt(view.getStockField().getText().replace(",", "")));
         	addImVO.setItem_cost(Integer.parseInt(view.getCostField().getText().replace(",", "")));
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
    
    
    private boolean fieldInputCheck() {
    	if(view.getNameField().getText().equals(initialProductName)) {
         	JOptionPane.showMessageDialog(view, "상품명을 입력해주세요.", "등록실패", JOptionPane.ERROR_MESSAGE);
         	return false;
         }
         if(view.getCostField().getText().equals(initialCost)) {
         	JOptionPane.showMessageDialog(view, "원가를 입력해주세요.", "등록실패", JOptionPane.ERROR_MESSAGE);
         	return false;
         }
         if(view.getPriceField().getText().equals(initialSellPrice)) {
         	JOptionPane.showMessageDialog(view, "판매가를 입력해주세요.", "등록실패", JOptionPane.ERROR_MESSAGE);
         	return false;
         }
         return true;
    }

    private void handleDecreaseStock() {
    	try {
            int currentStock = Integer.parseInt(view.getStockField().getText());
            int quantityUnit = Integer.parseInt(view.getQuantityUnitField().getText().replace(",", ""));
            if(quantityUnit < 0) {
            	JOptionPane.showMessageDialog(view, "재고 변경 단위에는 음수가 들어갈 수 없습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
            	return;
            }
            int finalStock = Math.max(0, currentStock - quantityUnit);
           
            
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
            
            int finalStock = 0;
            try {
            	finalStock = Math.addExact(currentStock, quantityUnit);
            }catch(ArithmeticException ae){
            
            	finalStock = Integer.MAX_VALUE;	
            }
            view.getStockField().setText(String.valueOf(finalStock));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "유효한 숫자를 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
        }
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
				setInitialText(view.getCostField(), initialCost);
			}
		}
		
		if(e.getSource() == view.getPriceField()) {
			if(view.getPriceField().getText().isBlank()) {
				view.getPriceField().setForeground(Color.GRAY);
				setInitialText(view.getPriceField(), initialSellPrice);
			}
		}
	}
	
	private void setInitialText(JTextField jtf, String initialtext) {
		AbstractDocument doc = (AbstractDocument) jtf.getDocument();
		try {
			 SimpleAttributeSet attrs = new SimpleAttributeSet();
			 DocumentFilter numericDF = doc.getDocumentFilter();
			 doc.setDocumentFilter(defaultDF);
			 doc.replace(0, doc.getLength(), initialtext, attrs);
			 doc.setDocumentFilter(numericDF);
			 
			 
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
