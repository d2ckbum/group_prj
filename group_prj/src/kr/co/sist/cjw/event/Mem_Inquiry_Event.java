package kr.co.sist.cjw.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

import kr.co.sist.cjw.service.Mem_Inquiry_Service;
import kr.co.sist.cjw.view.Mem_Inquiry_View;
import kr.co.sist.cjw.vo.FAQ_VO;
import kr.co.sist.cjw.vo.Inquiry_VO;
import kr.co.sist.kji.MyInfoView;

public class Mem_Inquiry_Event extends WindowAdapter implements ActionListener, KeyListener, MouseListener {
	
	private Mem_Inquiry_View miv;
	Object inqValue;
	
	
	
	public Mem_Inquiry_Event(Mem_Inquiry_View miv) {
		this.miv=miv;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent ae) {
		//mem_Inquiry_Main_View 영역
		if(ae.getSource() == miv.getCnlMainJbtn()) {
			miv.getMem_Inquiry_Main_View().dispose();
			new MyInfoView(miv.getId()); 
		}//end if
		
		
		if(ae.getSource() == miv.getAddJbtn()) {
			miv.mem_Inquiry_Write_View();
		}//end if
		
		//mem_Inquiry_Write_View
		if(ae.getSource() == miv.getSaveBtn()) {
			inqSaveConfirmDialog(miv.getMem_Inquiry_Write_View());
		}//end if
		
		if(ae.getSource() == miv.getCnlBtn()) {
			inqCancelConfirmDialog(miv.getMem_Inquiry_Write_View());
		}//end if
		
		
		//mem_Inquiry_Confirm_View
		if(ae.getSource() == miv.getConfirmBtn()) {
			miv.getMem_Inquiry_Confirm_View().dispose();
			miv.getMem_Inquiry_Main_View();
		}//end if
		
		//mem_Inquiry_Edit_View
		if(ae.getSource() == miv.getEditBtn()) {
			inqObject();
			inqEditDialog(miv.getMem_Inquiry_Edit_View());	
		}//end if
		
		if(ae.getSource() == miv.getCnlEditBtn()) {
			inqCancelEditDialog(miv.getMem_Inquiry_Edit_View());
		}//end if
		
	}//actionPerformed
	
	
	//FAQ 목록 최신화 method
	public void loadFAQData() {
		Mem_Inquiry_Service mem_Inq_Service = new Mem_Inquiry_Service();
	    List<FAQ_VO> faqList = mem_Inq_Service.searchFAQ();

	    miv.getFaqModel().setRowCount(0); 
	    
	    miv.getFaqTable().setModel(miv.getFaqModel());
        for (FAQ_VO faq : faqList) {
        	miv.getFaqModel().addRow(new Object[]{
                faq.getFaq_title(), 
                "⇒ " + faq.getFaq_contents(), 
                faq.getFaq_reg_date() 
            });
        }
        
        
        miv.getFaqTable().getColumnModel().getColumn(0).setPreferredWidth(400); 
        miv.getFaqTable().getColumnModel().getColumn(1).setPreferredWidth(600); 
        miv.getFaqTable().getColumnModel().getColumn(2).setPreferredWidth(100); 
        miv.getFaqTable().setModel(miv.getFaqModel());
	}//loadFAQData
	
	
	//inq 목록 최신화 method
		public void loadINQData() {
			Mem_Inquiry_Service inqService = new Mem_Inquiry_Service();
		    List<Inquiry_VO> inqList = inqService.searchINQ(miv.getId());

		    miv.getInqModel().setRowCount(0); 
		    
		    miv.getInqTable().setModel(miv.getInqModel());
	        for (Inquiry_VO inq : inqList) {
	        	miv.getInqModel().addRow(new Object[]{
	                inq.getInq_Id(), 
	                inq.getInq_Reg_Date(), 
	                inq.getInq_Title(), 
	                inq.getInq_Status() 
	            });
	        }
	        
	        //가운데 정렬 설정
	        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

	        // 문의번호, 날짜, 상태 열에 가운데 정렬 적용
	        miv.getInqTable().getColumnModel().getColumn(0).setCellRenderer(centerRenderer); // 문의번호
	        miv.getInqTable().getColumnModel().getColumn(1).setCellRenderer(centerRenderer); // 날짜
	        miv.getInqTable().getColumnModel().getColumn(2).setCellRenderer(centerRenderer); // 날짜
	        miv.getInqTable().getColumnModel().getColumn(3).setCellRenderer(centerRenderer); // 상태
	        
	        miv.getInqTable().getColumnModel().getColumn(0).setPreferredWidth(100); 
	        miv.getInqTable().getColumnModel().getColumn(1).setPreferredWidth(100); 
	        miv.getInqTable().getColumnModel().getColumn(2).setPreferredWidth(900);
	        miv.getInqTable().getColumnModel().getColumn(3).setPreferredWidth(150);
	        miv.getInqTable().setModel(miv.getInqModel());
		}//loadFAQData

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == miv.getSubWriteJta()) {
            e.consume();
            miv.getContentsWriteJta().requestFocus();
		}//end if
		if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == miv.getSubEditJta()) {
            e.consume();
            miv.getContentsEditJta().requestFocus();
		}//end if
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) { 
            int row = miv.getInqTable().getSelectedRow(); 
            Object value = miv.getInqTable().getValueAt(row, 0);
            
            if (row != -1) {
                String status = (String) miv.getInqTable().getValueAt(row, 3);

                Mem_Inquiry_Service memInqService=new Mem_Inquiry_Service();
                Inquiry_VO iVO=memInqService.search_Edit_INQ(value);
                
                
                if ("답변 완료".equals(status)) {
                    miv.mem_Inquiry_Confirm_View();
                    miv.getSubConfirmJta().setText(iVO.getInq_Title());
                    miv.getContentsConfirmJta().setText(iVO.getInq_Contents());
                    miv.getReplyConfirmJta().setText(iVO.getInq_Reply());
                    
                    
                } else if ("답변 대기".equals(status)) {
                    miv.mem_Inquiry_Edit_View(); //
                    miv.getSubEditJta().setText(iVO.getInq_Title());
                    miv.getContentsEditJta().setText(iVO.getInq_Contents());
                    
                }//else if
            }//end if
		}//end if
		
	}//mouseClicked
	
	public Object inqObject() {
		int row = miv.getInqTable().getSelectedRow(); 
		inqValue = miv.getInqTable().getValueAt(row, 0);
		
		return inqValue;
	}//inqObject

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	
	//저장 팝업 창
		public void inqSaveConfirmDialog(JFrame parentFrame) {
		    Object[] options = {"확인", "취소"};
		    int response = JOptionPane.showOptionDialog(
		            parentFrame,
		            "저장하시겠습니까?",
		            "저장(팝업)",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE,
		            null,
		            options,
		            options[0]
		    );

		    if (response == 0) { 
		    	 String title = miv.getSubWriteJta().getText().trim();
		         String contents = miv.getContentsWriteJta().getText().trim();

		            if (title.isEmpty() || contents.isEmpty()) {
		                JOptionPane.showMessageDialog(miv.getMem_Inquiry_Write_View(), "제목과 내용을 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            Inquiry_VO inq = new Inquiry_VO();
		            inq.setInq_Title(title);
		            inq.setInq_Contents(contents);

		            Mem_Inquiry_Service inqService = new Mem_Inquiry_Service();
		            boolean success = inqService.addInq(inq, miv.getId());

		            if (success) {
		                JOptionPane.showMessageDialog(miv.getMem_Inquiry_Write_View(), "문의가 등록되었습니다.", "등록 성공", JOptionPane.INFORMATION_MESSAGE);
		                miv.getMem_Inquiry_Write_View().dispose();
		                loadINQData();
		            } else {
		                JOptionPane.showMessageDialog(miv.getMem_Inquiry_Write_View(), "문의 등록 중 오류가 발생했습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
		            }
		        miv.getMem_Inquiry_Write_View().dispose();
		        System.out.println("저장 완료");
		    } else {
		    	inqCancelConfirmDialog(parentFrame);
		    }//end if
		}//inqSaveConfirmDialog

		//닫기 팝업 창
		public void inqCancelConfirmDialog(JFrame parentFrame) {
		    Object[] options = {"확인", "취소"};
		    int response = JOptionPane.showOptionDialog(
		            parentFrame,
		            "저장하지 않고 닫으시겠습니까?",
		            "닫기(팝업)",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE,
		            null,
		            options,
		            options[0] 
		    );

		    if (response == 0) { 
		        miv.getMem_Inquiry_Write_View().dispose();
		        System.out.println("창이 닫혔습니다");
		    }//end if
		}//inqCancelConfirmDialog
		
		
		
		public void inqEditDialog(JFrame parentFrame) {
		    Object[] options = {"확인", "취소"};
		    int response = JOptionPane.showOptionDialog(
		            parentFrame,
		            "수정하시겠습니까?",
		            "수정(팝업)",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE,
		            null,
		            options,
		            options[0]
		    );

		    if (response == 0) { 
		    	 String title = miv.getSubEditJta().getText().trim();
		         String contents = miv.getContentsEditJta().getText().trim();

		            if (title.isEmpty() || contents.isEmpty()) {
		                JOptionPane.showMessageDialog(miv.getMem_Inquiry_Edit_View(), "제목과 내용을 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            Inquiry_VO inq = new Inquiry_VO();
		            inq.setInq_Title(title);
		            inq.setInq_Contents(contents);

		            Mem_Inquiry_Service inqService = new Mem_Inquiry_Service();
		            System.out.println(inqValue);
		            boolean success = inqService.modifyInq(inq, inqValue);

		            if (success) {
		                JOptionPane.showMessageDialog(miv.getMem_Inquiry_Edit_View(), "문의가 수정되었습니다.", "수정 성공", JOptionPane.INFORMATION_MESSAGE);
		                miv.getMem_Inquiry_Edit_View().dispose();
		                loadINQData();
		            } else {
		                JOptionPane.showMessageDialog(miv.getMem_Inquiry_Edit_View(), "문의 등록 중 오류가 발생했습니다.", "수정 오류", JOptionPane.ERROR_MESSAGE);
		            }
		        miv.getMem_Inquiry_Edit_View().dispose();
		        System.out.println("수정 완료");
		    } else {
		    	inqCancelEditDialog(parentFrame);
		    }//end if
		}//inqSaveConfirmDialog

		//닫기 팝업 창
		public void inqCancelEditDialog(JFrame parentFrame) {
		    Object[] options = {"확인", "취소"};
		    int response = JOptionPane.showOptionDialog(
		            parentFrame,
		            "수정하지 않고 닫으시겠습니까?",
		            "닫기(팝업)",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE,
		            null,
		            options,
		            options[0] 
		    );

		    if (response == 0) { 
		        miv.getMem_Inquiry_Edit_View().dispose();
		        System.out.println("창이 닫혔습니다");
		    }//end if
		}//inqCancelConfirmDialog


		@Override
		public void windowClosing(WindowEvent e) {
			JFrame frame = (JFrame) e.getSource();
		    frame.dispose(); 
		}//windowClosing


		@Override
		public void windowOpened(WindowEvent e) {
			if (e.getSource() == miv.getMem_Inquiry_Main_View()){ 
			loadFAQData();
			loadINQData();
			}//end if
		}//windowOpened
		
		
	
	

}//class
