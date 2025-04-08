package kr.co.sist.hjs;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import kr.co.sist.cjw.view.Admin_Inquiry_View;

public class AdminMainViewEvt implements ActionListener {
	private AdminMainView amv;

	public AdminMainViewEvt(AdminMainView amv) {
		this.amv = amv;
	}

	public AdminMainViewEvt() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == amv.getJbtnMember()) {
			// 회원현황 버튼 클릭 시 AdminTabView를 보여주고 MemberPane을 활성화
			AdminTabView atv = new AdminTabView();
			CardLayout cl = atv.getCl();
			JPanel mainPanel = atv.getMainPanel();
			cl.show(mainPanel, "memp");

			amv.closeAdminMainView();

		} else if (e.getSource() == amv.getJbtnItemManage()) {
			// 상품관리 버튼 클릭 시 AdminTabView를 보여주고 ItemPane을 활성화
			AdminTabView atv = new AdminTabView();
			CardLayout cl = atv.getCl();
			JPanel mainPanel = atv.getMainPanel();
			cl.show(mainPanel, "itemp");
			amv.closeAdminMainView();

		} else if (e.getSource() == amv.getJbtnFixManage()) {
			// 정비관리 버튼 클릭 시 AdminTabView를 보여주고 FixPane을 활성화
			AdminTabView atv = new AdminTabView();
			CardLayout cl = atv.getCl();
			JPanel mainPanel = atv.getMainPanel();
			cl.show(mainPanel, "fixp");

			amv.closeAdminMainView();
		} else if (e.getSource() == amv.getJbtnSales()) {
			// 매출현황 버튼 클릭 시 AdminTabView를 보여주고 SalesPane을 활성화
			AdminTabView atv = new AdminTabView();
			CardLayout cl = atv.getCl();
			JPanel mainPanel = atv.getMainPanel();
			cl.show(mainPanel, "salesp");
			amv.closeAdminMainView();

		} else if (e.getSource() == amv.getJbtnInquiry()) {
			// 문의관리 버튼 클릭 시 AdminTabView를 보여주고 InquiryPane을 활성화
			Admin_Inquiry_View inquiryView = new Admin_Inquiry_View();
			inquiryView.admin_Inquiry_Main_View(); // 새 창 열기
//			amv.closeAdminMainView();
		}
	}
}