package kr.co.sist.kyh;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import kr.co.sist.kji.MemberVO;

import java.awt.*;

public class OrderView extends JPanel {

    private ItemVO item;
    private MemberVO member;

    public OrderView(ItemVO item, MemberVO member) {
        this.item = item;
        this.member = member;

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(new Color(245, 245, 245));
        itemPanel.setPreferredSize(new Dimension(600, 250));

        TitledBorder itemBorder = BorderFactory.createTitledBorder("상품정보");
        itemBorder.setTitleFont(new Font("Dialog", Font.BOLD, 22));
        itemBorder.setTitleJustification(TitledBorder.CENTER);
        itemPanel.setBorder(itemBorder);

        itemPanel.add(infoLabel("상품명: " + item.getItemName()));
        itemPanel.add(infoLabel("차종: " + item.getCarNum()));
        itemPanel.add(infoLabel("상품금액: ₩" + item.getItemPrice()));
        itemPanel.add(infoLabel("정비비용: ₩" + item.getItemRepairCost()));
        itemPanel.add(infoLabel("총 금액: ₩" + (item.getItemPrice() + item.getItemRepairCost())));

        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        memberPanel.setBackground(new Color(245, 245, 245));
        memberPanel.setPreferredSize(new Dimension(600, 120));

        TitledBorder memberBorder = BorderFactory.createTitledBorder("내정보");
        memberBorder.setTitleFont(new Font("Dialog", Font.BOLD, 22));
        memberBorder.setTitleJustification(TitledBorder.CENTER);
        memberPanel.setBorder(memberBorder);

        memberPanel.add(infoLabel("이름: " + member.getMemName()));
        memberPanel.add(infoLabel("전화번호: " + member.getMemTell()));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton requestButton = new JButton("정비요청");
        JButton cancelButton = new JButton("취소");

        buttonPanel.add(requestButton);
        buttonPanel.add(cancelButton);

        cancelButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null) {
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "EngineOil");
            }
        });

        mainPanel.add(itemPanel);
        mainPanel.add(memberPanel);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel infoLabel(String text) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        JLabel label = new JLabel(text);
        label.setFont(new Font("Dialog", Font.BOLD, 17));
        panel.add(label);
        return panel;
    }
}