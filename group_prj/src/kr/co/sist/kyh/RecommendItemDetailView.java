package kr.co.sist.kyh;

import javax.swing.*;

import kr.co.sist.kji.MemberVO;

import java.awt.*;

public class RecommendItemDetailView extends JPanel {
    private MemberVO member;

    public RecommendItemDetailView(ItemVO item, MemberVO member) {
        this.member = member;

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        JButton selectButton = new JButton("선택");
        JButton backButton = new JButton("돌아가기");
        buttonPanel.add(selectButton);
        buttonPanel.add(backButton);
        
        selectButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null) {
                parent.add(new OrderView(item, member), "Order");
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "Order");
            }
        });

        backButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null) {
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "EngineOil");
            }
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }
}