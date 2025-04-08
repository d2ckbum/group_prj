package kr.co.sist.cjw.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.cjw.service.Mem_Inquiry_Service;
import kr.co.sist.cjw.view.Mem_Inquiry_View;
import kr.co.sist.cjw.vo.FAQ_VO;
import kr.co.sist.cjw.vo.Inquiry_VO;
import kr.co.sist.kji.MyInfoView;

public class Mem_Inquiry_Event extends WindowAdapter implements ActionListener, KeyListener, MouseListener {
	
	private Mem_Inquiry_View miv;
	
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
			inqSaveConfirmDialog(miv.getMem_Inquiry_Confirm_View());
		}//end if
		
		if(ae.getSource() == miv.getCnlBtn()) {
			miv.getMem_Inquiry_Write_View().dispose();
			miv.getMem_Inquiry_Main_View();
		}//end if
		
		
		//mem_Inquiry_Confirm_View
		if(ae.getSource() == miv.getConfirmBtn()) {
			miv.getMem_Inquiry_Confirm_View().dispose();
			miv.getMem_Inquiry_Main_View();
		}//end if
		
		//mem_Inquiry_Edit_View
		if(ae.getSource() == miv.getCnlEditBtn()) {
			miv.getMem_Inquiry_Edit_View().dispose();
			miv.getMem_Inquiry_Main_View();
		}//end if
		
	}//actionPerformed
	
	
	//FAQ 목록 최신화 method
	public void loadFAQData() {
	    Mem_Inquiry_Service faqService = new Mem_Inquiry_Service();
	    List<FAQ_VO> faqList = faqService.searchFAQ(); 

	    DefaultTableModel model = (DefaultTableModel) miv.getFaqTable().getModel();
	    model.setRowCount(0); 

	    for (FAQ_VO faq : faqList) {
            model.addRow(new Object[]{
                faq.getFaq_title(), 
                "⇒ " + faq.getFaq_contents(),
                faq.getFaq_reg_date()
            });
	    }//end for
	}//loadFAQData
	
	
	//inq 목록 최신화 method
		public void loadINQData() {
			Mem_Inquiry_Service inqService = new Mem_Inquiry_Service();
		    List<Inquiry_VO> inqList = inqService.searchINQ(miv.getId()); 

		    DefaultTableModel model = (DefaultTableModel) miv.getInqTable().getModel();
		    model.setRowCount(0); 

		    for (Inquiry_VO inq : inqList) {
	        	model.addRow(new Object[]{
	                inq.getInq_Id(), 
	                inq.getInq_Reg_Date(), 
	                inq.getInq_Title(), 
	                inq.getInq_Status() 
	            });
		    }//end for
		}//loadFAQData

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) { 
            int row = miv.getInqTable().getSelectedRow(); 
            if (row != -1) {
                String status = (String) miv.getInqTable().getValueAt(row, 3);

                if ("답변 완료".equals(status)) {
                    miv.mem_Inquiry_Confirm_View();
                } else if ("답변 대기".equals(status)) {
                    miv.mem_Inquiry_Edit_View(); //
                }//else if
            }//end if
		}//end if
		
	}//mouseClicked

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
	
	

}//class
