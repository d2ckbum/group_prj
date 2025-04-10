package kr.co.sist.hjs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import kr.co.sist.cjw.view.Admin_Inquiry_View; // Admin_Inquiry_View 임포트

public class AdminTabViewEvt implements ActionListener {
	private AdminTabView view;
//	private Color defaultButtonColor = new Color(217, 217, 217); // 기본 버튼 색상
//	private Color selectedButtonColor = new Color(150, 150, 150); // 진한 회색
//	private JButton currentlySelectedButton = null;
//	

	public AdminTabViewEvt(AdminTabView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clickedButton = (JButton) e.getSource();

		if (clickedButton == view.getJbtnMember()) {
			view.getCl().show(view.getMainPanel(), "memp");
			view.setButtonSelected(view.getJbtnMember());
			
			
		} else if (clickedButton == view.getJbtnItemManage()) {
			view.getCl().show(view.getMainPanel(), "itemp");
			view.setButtonSelected(view.getJbtnItemManage());
			
		} else if (clickedButton == view.getJbtnFixManage()) {
			view.getCl().show(view.getMainPanel(), "fixp");
			view.setButtonSelected(view.getJbtnFixManage());
			
		} else if (clickedButton == view.getJbtnSales()) {
			view.getCl().show(view.getMainPanel(), "salesp");
			view.setButtonSelected(view.getJbtnSales());
			
		} else if (clickedButton == view.getJbtnInquiryManage()) {
			// 문의관리 버튼 클릭 시 새 창 열기
			Admin_Inquiry_View inquiryView = new Admin_Inquiry_View();
			inquiryView.admin_Inquiry_Main_View(); // 새 창 열기
			view.setButtonSelected(view.getJbtnInquiryManage());
			
		} else if (clickedButton == view.getJbtnLogout()) {
//			JOptionPane.showMessageDialog(null, "종료 되었습니다.");
			JOptionPane.showConfirmDialog(null, "종료하실?\n (종료시 로그인창으로 갑니다잉)","종료",JOptionPane.YES_NO_CANCEL_OPTION);
			view.dispose();
			new AdminLoginView();
			
		}else if(clickedButton == view.getJbtnBack()) {
			new AdminMainView();
			view.dispose();
		}
		
	}//actionPerformed
	
	
}//class