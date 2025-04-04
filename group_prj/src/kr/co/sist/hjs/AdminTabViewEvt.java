package kr.co.sist.hjs;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import kr.co.sist.cjw.view.Admin_Inquiry_View; // Admin_Inquiry_View 임포트

public class AdminTabViewEvt implements ActionListener {
    private AdminTabView view;

    public AdminTabViewEvt(AdminTabView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getJbtnMember()) {
            view.getCl().show(view.getMainPanel(), "memp");
        }
        if (e.getSource() == view.getJbtnItemManage()) {
            view.getCl().show(view.getMainPanel(), "itemp");
        }
        if (e.getSource() == view.getJbtnFixManage()) {
            view.getCl().show(view.getMainPanel(), "fixp");
        }
        if (e.getSource() == view.getJbtnSales()) {
            view.getCl().show(view.getMainPanel(), "salesp");
        }
        if (e.getSource() == view.getJbtnInquiryManage()) {
            // 문의관리 버튼 클릭 시 새 창 열기
            Admin_Inquiry_View inquiryView = new Admin_Inquiry_View();
            inquiryView.admin_Inquiry_Main_View(); // 새 창 열기
        }
        if (e.getSource() == view.getJbtnLogout()) {
            JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
            view.dispose();
        }
        // 다른 버튼에 대한 이벤트 처리 추가
    }
}