package kr.co.sist.kyh;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import kr.co.sist.kji.MemberVO;

import java.awt.*;

public class ItemDetailView extends JPanel {

    private ItemVO item;
    private MemberVO member;

    public ItemDetailView(ItemVO item, MemberVO member) {
        this.item = item;
        this.member = member;

        setLayout(new BorderLayout());

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setBackground(new Color(245, 245, 245));

        TitledBorder titleBorder = BorderFactory.createTitledBorder("상품정보");
        titleBorder.setTitleFont(new Font("Dialog", Font.BOLD, 22));
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        productPanel.setBorder(titleBorder);

        productPanel.add(infoLabel("상품번호: " + item.getItemNum()));
        productPanel.add(infoLabel("상품명: " + item.getItemName()));
        productPanel.add(infoLabel("차종: " + item.getCarNum()));
        productPanel.add(infoLabel("금액: ₩" + item.getItemPrice()));

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

        add(productPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel infoLabel(String text) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel(text);
        label.setFont(new Font("Dialog", Font.BOLD, 17));
        panel.setBackground(new Color(245, 245, 245));
        panel.add(label);
        return panel;
    }
}