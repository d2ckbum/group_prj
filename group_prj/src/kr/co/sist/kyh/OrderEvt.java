package kr.co.sist.kyh;

import javax.swing.*;

import kr.co.sist.kji.MemberVO;

import java.awt.*;
import java.awt.event.*;

public class OrderEvt implements ActionListener {
    
    private JPanel parentPanel;
    private CardLayout cardLayout;
    private ItemVO item;
    private MemberVO member;

    public OrderEvt(JPanel parentPanel, CardLayout cardLayout, ItemVO item, MemberVO member) {
        this.parentPanel = parentPanel;
        this.cardLayout = cardLayout;
        this.item = item;
        this.member = member;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.getText().equals("정비요청")) {
            // 접수번호 생성
            String requestId = "REQ" + System.currentTimeMillis();

            JOptionPane.showMessageDialog(null, 
                "정비요청이 완료되었습니다.\n접수번호: " + requestId, 
                "정비요청 완료", 
                JOptionPane.INFORMATION_MESSAGE
            );

            cardLayout.show(parentPanel, "EngineOil");

        } else if (source.getText().equals("취소")) {
            cardLayout.show(parentPanel, "EngineOil");
        }
    }
}
