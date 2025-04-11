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

import kr.co.sist.cjw.service.Admin_Inquiry_Service;
import kr.co.sist.cjw.view.Admin_Inquiry_View;
import kr.co.sist.cjw.vo.FAQ_VO;
import kr.co.sist.cjw.vo.Inquiry_VO;

public class Admin_Inquiry_Event extends WindowAdapter implements ActionListener, KeyListener, MouseListener {
	private Admin_Inquiry_View aiv;
	Object inqId;
	Object faqSub;
	
	public Admin_Inquiry_Event(Admin_Inquiry_View aiv) {
		this.aiv=aiv;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}//keyTyped

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == aiv.getSubFAQJta()) {
            e.consume();
            aiv.getContentsFAQJta().requestFocus();
		}//end if
		if (e.getKeyCode() == KeyEvent.VK_ENTER && e.getSource() == aiv.getSubFAQEditJta()) {
            e.consume();
            aiv.getContentsFAQEditJta().requestFocus();
		}//end if
	}//keyPressed

	@Override
	public void keyReleased(KeyEvent e) {
		
	}//keyReleased

	
	
	public void loadFAQData() {
		Admin_Inquiry_Service admin_Inq_Service = new Admin_Inquiry_Service();
	    List<FAQ_VO> faqList = admin_Inq_Service.searchFAQ();
	    
	    aiv.getFaqModel().setRowCount(0);

	    aiv.getFaqTable().setModel(aiv.getFaqModel());
        for (FAQ_VO faq : faqList) {
        	aiv.getFaqModel().addRow(new Object[]{
                faq.getFaq_title(), 
                "⇒ " + faq.getFaq_contents(), 
                faq.getFaq_reg_date() 
            });
        }
        
        
        aiv.getFaqTable().getColumnModel().getColumn(0).setPreferredWidth(400); 
        aiv.getFaqTable().getColumnModel().getColumn(1).setPreferredWidth(600); 
        aiv.getFaqTable().getColumnModel().getColumn(2).setPreferredWidth(100);
        aiv.getFaqTable().setModel(aiv.getFaqModel());
	}//loadFAQData
	
	public void loadINQData() {
		Admin_Inquiry_Service admin_Inq_Service = new Admin_Inquiry_Service();
		List<Inquiry_VO> inqList = admin_Inq_Service.searchINQ();

		aiv.getInqModel().setRowCount(0);
	    
	    aiv.getInqTable().setModel(aiv.getInqModel());
        for (Inquiry_VO inq : inqList) {
        	aiv.getInqModel().addRow(new Object[]{
                inq.getInq_Id(), 
                inq.getMem_Name(), 
                inq.getInq_Reg_Date(), 
                inq.getInq_Title(), 
                inq.getInq_Status() 
            });
        }//end for
        
        //가운데 정렬 설정
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        //가운데 정렬 적용
        aiv.getInqTable().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        aiv.getInqTable().getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        aiv.getInqTable().getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        aiv.getInqTable().getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        aiv.getInqTable().getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        
        aiv.getInqTable().getColumnModel().getColumn(0).setPreferredWidth(100); 
        aiv.getInqTable().getColumnModel().getColumn(1).setPreferredWidth(100); 
        aiv.getInqTable().getColumnModel().getColumn(2).setPreferredWidth(100); 
        aiv.getInqTable().getColumnModel().getColumn(3).setPreferredWidth(900);
        aiv.getInqTable().getColumnModel().getColumn(4).setPreferredWidth(150);
        aiv.getInqTable().setModel(aiv.getInqModel());
	
		
	}//loadINQData
	
	
	
	public Object faqSubObject() {
		int row = aiv.getFaqTable().getSelectedRow(); 
		faqSub = aiv.getFaqTable().getValueAt(row, 0);
		
		
		return faqSub;
	}//inqIdObject
	
	
	// FAQ저장 팝업 창
	public void faqSaveDialog(JFrame parentFrame) {
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
	    	 String title = aiv.getSubFAQJta().getText().trim();
	            String contents = aiv.getContentsFAQJta().getText().trim();

	            if (title.isEmpty() || contents.isEmpty()) {
	                JOptionPane.showMessageDialog(aiv.getAdminFAQWriteView(), "제목과 내용을 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            FAQ_VO faq = new FAQ_VO();
	            faq.setFaq_title(title);
	            faq.setFaq_contents(contents);

	            Admin_Inquiry_Service faqService = new Admin_Inquiry_Service();
	            boolean success = faqService.addFAQ(faq);

	            if (success) {
	                JOptionPane.showMessageDialog(aiv.getAdminFAQWriteView(), "FAQ가 등록되었습니다.", "등록 성공", JOptionPane.INFORMATION_MESSAGE);
	                aiv.getAdminFAQWriteView().dispose(); 
	                loadFAQData();
	            } else {
	                JOptionPane.showMessageDialog(aiv.getAdminFAQWriteView(), "FAQ 등록 중 오류가 발생했습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
	            }
	        aiv.getAdminFAQWriteView().dispose(); 
	    } else {
	    	faqCancelDialog(parentFrame);
	    }//end if
	}//saveConfirmDialog

	
	//닫기 팝업 창
	public void faqCancelDialog(JFrame parentFrame) {
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
	        aiv.getAdminFAQWriteView().dispose(); 
	    }//end if
	}//faqCancelDialog
	
	
	
	// FAQ 수정 팝업 창
	public void faqEditDialog(JFrame parentFrame) {
	    Object[] options = {"확인", "취소"};
	    int response = JOptionPane.showOptionDialog(
	            parentFrame,
	            "수정하시겠습니까?",
	            "저장(팝업)",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            options,
	            options[0]
	    );

	    if (response == 0) { 
	    	 String title = aiv.getSubFAQEditJta().getText().trim();
	            String contents = aiv.getContentsFAQEditJta().getText().trim();

	            if (title.isEmpty() || contents.isEmpty()) {
	                JOptionPane.showMessageDialog(aiv.getAdmin_FAQ_Edit_View(), "제목과 내용을 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            FAQ_VO faq = new FAQ_VO();
	            faq.setFaq_title(title);
	            faq.setFaq_contents(contents);

	            Admin_Inquiry_Service faqService = new Admin_Inquiry_Service();
	            boolean success = faqService.modifyFAQ(faq, faqSub);

	            if (success) {
	                JOptionPane.showMessageDialog(aiv.getAdmin_FAQ_Edit_View(), "FAQ가 수정되었습니다.", "수정 성공", JOptionPane.INFORMATION_MESSAGE);
	                aiv.getAdmin_FAQ_Edit_View().dispose(); 
	                loadFAQData();
	            } else {
	                JOptionPane.showMessageDialog(aiv.getAdmin_FAQ_Edit_View(), "FAQ 수정 중 오류가 발생했습니다.", "수정 오류", JOptionPane.ERROR_MESSAGE);
	            }
	        aiv.getAdmin_FAQ_Edit_View().dispose(); 
	    } else {
	    	faqEditCancelDialog(parentFrame);
	    }//end if
	}//saveConfirmDialog
	

	//닫기 팝업 창
	public void faqEditCancelDialog(JFrame parentFrame) {
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
	        aiv.getAdmin_FAQ_Edit_View().dispose(); 
	    }//end if
	}//cancelConfirmDialog
	
	
	
	
	// FAQ 삭제 팝업 창
	public void faqRemoveDialog(JFrame parentFrame) {
	    Object[] options = {"확인", "취소"};
	    int response = JOptionPane.showOptionDialog(
	            parentFrame,
	            "삭제하시겠습니까?",
	            "삭제(팝업)",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            options,
	            options[0]
	    );

	    if (response == 0) { 

	            Admin_Inquiry_Service faqService = new Admin_Inquiry_Service();
	            boolean success = faqService.removeFAQ(faqSub);

	            if (success) {
	                JOptionPane.showMessageDialog(aiv.getAdmin_FAQ_Edit_View(), "FAQ가 삭제되었습니다.", "삭제 성공", JOptionPane.INFORMATION_MESSAGE);
	                aiv.getAdmin_FAQ_Edit_View().dispose(); 
	                loadFAQData();
	            } else {
	                JOptionPane.showMessageDialog(aiv.getAdmin_FAQ_Edit_View(), "FAQ 삭제 중 오류가 발생했습니다.", "삭제 오류", JOptionPane.ERROR_MESSAGE);
	            }
	        aiv.getAdmin_FAQ_Edit_View().dispose(); 
	    } else {
	    	faqRemoveCancelDialog(parentFrame);
	    }//end if
	}//faqRemoveDialog
	

	//닫기 팝업 창
	public void faqRemoveCancelDialog(JFrame parentFrame) {
	    Object[] options = {"확인", "취소"};
	    int response = JOptionPane.showOptionDialog(
	            parentFrame, 
	            "삭제하지 않고 닫으시겠습니까?",
	            "닫기(팝업)",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            options,
	            options[0] 
	    );

	    if (response == 0) { 
	        aiv.getAdmin_FAQ_Edit_View().dispose(); 
	    }//end if
	}//faqRemoveCancelDialog
	
	
	
	
	public Object inqIdObject() {
		int row = aiv.getInqTable().getSelectedRow(); 
		inqId = aiv.getInqTable().getValueAt(row, 0);
		
		
		return inqId;
	}//inqIdObject
	
	
	
	//답변 저장 팝업 창
	public void inqReplayAddDialog(JFrame parentFrame) {
	    Object[] options = {"확인", "취소"};
	    int response = JOptionPane.showOptionDialog(
	            parentFrame,
	            "등록하시겠습니까?",
	            "등록(팝업)",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            options,
	            options[0]
	    );

	    if (response == 0) { 
            String reply = aiv.getReplyWriteJta().getText().trim();

	            if (reply.isEmpty()) {
	                JOptionPane.showMessageDialog(aiv.getAdmin_Inquiry_Write_View(), "답변을 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            Inquiry_VO inq = new Inquiry_VO();
	            inq.setInq_Reply(reply);
	            

	            Admin_Inquiry_Service inqService = new Admin_Inquiry_Service();
	            boolean success = inqService.addReplyInq(inq,inqId);

	            if (success) {
	                JOptionPane.showMessageDialog(aiv.getAdmin_Inquiry_Write_View(), "답변이 등록되었습니다.", "등록 성공", JOptionPane.INFORMATION_MESSAGE);
	                aiv.getAdmin_Inquiry_Write_View().dispose(); 
	                loadINQData();
	            } else {
	                JOptionPane.showMessageDialog(aiv.getAdmin_Inquiry_Write_View(), "답변 등록 중 오류가 발생했습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
	            }
	        aiv.getAdmin_Inquiry_Write_View().dispose(); 
	    } else {
	    	inqReplyCancelDialog(parentFrame);
	    }//end if
	}//inqReplayAddDialog

	//닫기 팝업 창
	public void inqReplyCancelDialog(JFrame parentFrame) {
	    Object[] options = {"확인", "취소"};
	    int response = JOptionPane.showOptionDialog(
	            parentFrame, 
	            "등록하지 않고 닫으시겠습니까?",
	            "닫기(팝업)",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            options,
	            options[0] 
	    );

	    if (response == 0) { 
	        aiv.getAdmin_Inquiry_Write_View().dispose(); 
	    }//end if
	}//inqReplyCancelDialog
	
	
	//답변 수정 팝업 창
	public void inqReplayEditDialog(JFrame parentFrame) {
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
            String reply = aiv.getReplyEditJta().getText().trim();

	            if (reply.isEmpty()) {
	                JOptionPane.showMessageDialog(aiv.getAdmin_Inquiry_Edit_View(), "답변을 입력하세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            Inquiry_VO inq = new Inquiry_VO();
	            inq.setInq_Reply(reply);
	            

	            Admin_Inquiry_Service inqService = new Admin_Inquiry_Service();
	            boolean success = inqService.modifyReplyInq(inq,inqId);

	            if (success) {
	                JOptionPane.showMessageDialog(aiv.getAdmin_Inquiry_Edit_View(), "답변이 수정되었습니다.", "수정 성공", JOptionPane.INFORMATION_MESSAGE);
	                aiv.getAdmin_Inquiry_Edit_View().dispose(); 
	                loadINQData();
	            } else {
	                JOptionPane.showMessageDialog(aiv.getAdmin_Inquiry_Edit_View(), "답변 수정 중 오류가 발생했습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
	            }
	        aiv.getAdmin_Inquiry_Edit_View().dispose(); 
	    } else {
	    	inqReplyEditCancelDialog(parentFrame);
	    }//end if
	}//inqReplayEditDialog

	//닫기 팝업 창
	public void inqReplyEditCancelDialog(JFrame parentFrame) {
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
	        aiv.getAdmin_Inquiry_Edit_View().dispose(); 
	    }//end if
	}//inqReplyEditCancelDialog
	
	

	@Override
	public void windowClosing(WindowEvent e) {
		JFrame frame = (JFrame) e.getSource();
	    frame.dispose(); 
	}
	
	
	
	@Override
	public void windowOpened(WindowEvent e) {
		if (e.getSource() == aiv.getAdmin_Inquiry_Main_View()){ 
		loadFAQData();
		loadINQData();
		}//end if
	}//windowOpened

	@Override
	public void mouseClicked(MouseEvent e) {
		if ( e.getClickCount() == 2 && e.getSource() == aiv.getFaqTable()) {
	        int faq_Row = aiv.getFaqTable().getSelectedRow();
	        Object faq_Value= aiv.getFaqTable().getValueAt(faq_Row, 0);
	        
	        Admin_Inquiry_Service faqService=new Admin_Inquiry_Service();
	        FAQ_VO fVO=faqService.search_Edit_FAQ(faq_Value);
	        
	        if (faq_Row != -1) {
	            // FAQ 수정/삭제 창 열기
	            aiv.admin_FAQ_Edit_View();
	            aiv.getSubFAQEditJta().setText(fVO.getFaq_title());
	            aiv.getContentsFAQEditJta().setText(fVO.getFaq_contents());
	        }
	    } else if ( e.getClickCount() == 2 && e.getSource() == aiv.getInqTable()) {
	        int inq_Row = aiv.getInqTable().getSelectedRow();
	        Object inq_Value = aiv.getInqTable().getValueAt(inq_Row, 0);
	        
	        Admin_Inquiry_Service memInqService=new Admin_Inquiry_Service();
            Inquiry_VO iVO=memInqService.search_Edit_INQ(inq_Value);
	        
	        if (inq_Row != -1) {
	            String status = (String) aiv.getInqTable().getValueAt(inq_Row, 4);
	            if ("답변 완료".equals(status)) {
	                aiv.admin_Inquiry_Edit_View();
	                aiv.getContentsEditJta().setText(iVO.getInq_Contents());
                    aiv.getReplyEditJta().setText(iVO.getInq_Reply());
                    aiv.getInqSub().setText("문의 제목: " + iVO.getInq_Title());
                    aiv.getMemName().setText("고객명: " + iVO.getMem_Name());
                    aiv.getInqDate().setText("날 짜: " + iVO.getInq_Reg_Date());
                    aiv.getInqStatus().setText("상 태: " + iVO.getInq_Status());
                     
                    
                    
	            } else if ("답변 대기".equals(status)) {
	                aiv.admin_Inquiry_Write_View();
	                aiv.getContentsInqJta().setText(iVO.getInq_Contents());
	                aiv.getInqSub().setText("문의 제목: " + iVO.getInq_Title());
                    aiv.getMemName().setText("고객명: " + iVO.getMem_Name());
                    aiv.getInqDate().setText("날 짜: " + iVO.getInq_Reg_Date());
                    aiv.getInqStatus().setText("상 태: " + iVO.getInq_Status());
	            }//else if
	        }//end if
	    }//else if

	}//mouseClicked

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

	@Override
	public void actionPerformed(ActionEvent ae) {
		//admin_Inquiry_Main_View 영역
		if(ae.getSource() == aiv.getAddJbtn()) {
			aiv.admin_FAQ_Write_View();
		}//end if
		if(ae.getSource() == aiv.getCnlJbtn()) {
			aiv.getAdmin_Inquiry_Main_View().dispose();
		}//end if
		
		//admin_FAQ_Write_View 영역
		if(ae.getSource() == aiv.getSaveFAQBtn()) {
			faqSaveDialog(aiv.getAdminFAQWriteView());
		}//end if
		if(ae.getSource() == aiv.getCnlFAQBtn()) {
			faqCancelDialog(aiv.getAdminFAQWriteView());
		}//end if
		
		//admin_FAQ_Edit_View 영역
		if(ae.getSource() == aiv.getEditFAQBtn()) {
			faqSubObject();
			faqEditDialog(aiv.getAdmin_FAQ_Edit_View());
		}//end if
		if(ae.getSource() == aiv.getDelFAQBtn()) {
			faqSubObject();
			faqRemoveDialog(aiv.getAdmin_FAQ_Edit_View());
		}//end if
		if(ae.getSource() == aiv.getCnlFAQEditBtn()) {
			faqEditCancelDialog(aiv.getAdmin_FAQ_Edit_View());
		}//end if
		
		//admin_Inquiry_Write_View 영역
		if(ae.getSource() == aiv.getSaveInqBtn()) {
			inqIdObject();
			inqReplayAddDialog(aiv.getAdmin_Inquiry_Write_View());
		}//end if
		if(ae.getSource() == aiv.getCnlInqBtn()) {
			inqReplyCancelDialog(aiv.getAdmin_Inquiry_Write_View());
		}//end if
		
		
		//admin_Inquiry_Edit_View
		if(ae.getSource() == aiv.getEditInqBtn()) {
			inqIdObject();
			inqReplayEditDialog(aiv.getAdmin_Inquiry_Edit_View());
		}//end if
		if(ae.getSource() == aiv.getCnlInqEditBtn()) {
			inqReplyEditCancelDialog(aiv.getAdmin_Inquiry_Edit_View());
		}//end if
		
	}//actionPerformed

}//class
