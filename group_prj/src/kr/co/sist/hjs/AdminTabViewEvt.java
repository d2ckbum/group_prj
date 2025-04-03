package kr.co.sist.hjs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminTabViewEvt implements ActionListener {
    private AdminTabView view;

    public AdminTabViewEvt(AdminTabView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        CardLayout cl = view.getCl();
        JPanel mainPanel = view.getMainPanel();

        switch (command) {
            case "회원현황":
                cl.show(mainPanel, "memp");
                break;
            case "상품관리":
                cl.show(mainPanel, "itemp");
//                pack();
                break;
            case "정비관리":
                cl.show(mainPanel, "fixp");
                break;
            case "매출현황":
                cl.show(mainPanel, "salesp");
                break;
            case "문의관리":
                cl.show(mainPanel, "inquiryp");
                break;
            case "로그아웃":
                // 로그아웃 처리 (예: 로그인 화면으로 전환)
                JOptionPane.showMessageDialog(view, "로그아웃 되었습니다.");
                view.dispose(); // 현재 관리자 창 닫기
                // new LoginView(); // 로그인 화면 다시 열기 (가정)
                break;
        }//end switch
        
    }//
}