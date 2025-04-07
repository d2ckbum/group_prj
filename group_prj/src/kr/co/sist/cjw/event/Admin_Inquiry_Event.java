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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import kr.co.sist.cjw.service.Admin_Inquiry_Service;
import kr.co.sist.cjw.view.Admin_Inquiry_View;
import kr.co.sist.cjw.vo.FAQ_VO;
import kr.co.sist.hjs.AdminMainView;

public class Admin_Inquiry_Event extends WindowAdapter implements ActionListener, KeyListener, MouseListener {
	private Admin_Inquiry_View aiv;
	
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
	}//keyPressed

	@Override
	public void keyReleased(KeyEvent e) {
		
	}//keyReleased

	@Override
	public void actionPerformed(ActionEvent ae) {
		//admin_Inquiry_Main_View 영역
		if(ae.getSource() == aiv.getAddJbtn()) {
			aiv.admin_FAQ_Write_View();
			
		}//end if
		if(ae.getSource() == aiv.getCnlJbtn()) {
			aiv.getAdmin_Inquiry_Main_View().dispose();
			new AdminMainView();
		}
		
		//admin_FAQ_Write_View 영역
		if(ae.getSource() == aiv.getSaveFAQBtn()) {
			faqSaveConfirmDialog(aiv.getAdminFAQWriteView());
		}//end if
		if(ae.getSource() == aiv.getCnlFAQBtn()) {
			faqCancelConfirmDialog(aiv.getAdminFAQWriteView());
		}//end if
		
		
		
	}//actionPerformed
	
	
	
	//저장 팝업 창
	public void faqSaveConfirmDialog(JFrame parentFrame) {
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
	                aiv.getAdminFAQWriteView().dispose(); // 등록 후 창 닫기
	                loadFAQData();
	            } else {
	                JOptionPane.showMessageDialog(aiv.getAdminFAQWriteView(), "FAQ 등록 중 오류가 발생했습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
	            }
	        System.out.println("저장 완료");
	        parentFrame.dispose();
	    } else {
	    	faqCancelConfirmDialog(parentFrame);
	    }//end if
	}//saveConfirmDialog

	//닫기 팝업 창
	public void faqCancelConfirmDialog(JFrame parentFrame) {
	    Object[] options = {"확인", "취소"};
	    int response = JOptionPane.showOptionDialog(
	            parentFrame, // this -> parentFrame으로 변경
	            "저장하지 않고 닫으시겠습니까?",
	            "닫기(팝업)",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null,
	            options,
	            options[0] // 기본 선택 버튼
	    );

	    if (response == 0) { 
	        parentFrame.dispose(); 
	        System.out.println("창이 닫혔습니다");
	    }//end if
	}//cancelConfirmDialog
	
	public void loadFAQData() {
	    Admin_Inquiry_Service faqService = new Admin_Inquiry_Service();
	    List<FAQ_VO> faqList = faqService.searchFAQ(); // 최신 FAQ 목록 가져오기

	    DefaultTableModel model = (DefaultTableModel) aiv.getFaqTable().getModel();
	    model.setRowCount(0); // 기존 데이터 초기화

	    for (FAQ_VO faq : faqList) {
            model.addRow(new Object[]{
                faq.getFaq_title(), 
                "⇒ " + faq.getFaq_contents(), // 답변 앞에 기호 추가
                faq.getFaq_reg_date() // 등록일
            });
	    }
	}

	@Override
	public void windowClosing(WindowEvent e) {
		aiv.getAdmin_Inquiry_Main_View().dispose();
		 new AdminMainView();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

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

	

}//class
