package kr.co.sist.kyh;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import kr.co.sist.khb.event.OrderEvt;
import kr.co.sist.khb.view.OrderView;
import kr.co.sist.kji.MemberVO;

import java.awt.*;

public class RecommendItemDetailView extends JPanel {
    private MemberVO member;
    private ItemVO item;

    public RecommendItemDetailView(ItemVO item, MemberVO member) {
        this.item = item;
        this.member = member;
        
        setLayout(new BorderLayout());

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setBackground(new Color(245, 245, 245));

        TitledBorder titleBorder = BorderFactory.createTitledBorder("상품정보");
        titleBorder.setTitleFont(new Font("맑은 고딕", Font.BOLD, 25));
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        productPanel.setBorder(titleBorder);

        productPanel.add(infoLabel("상품번호: " + item.getItemNum()));
        productPanel.add(infoLabel("상품명: " + item.getItemName()));
        productPanel.add(infoLabel("차종: " + item.getCarNum()));
        productPanel.add(infoLabel("금액: ₩" + item.getItemPrice()));

        JPanel buttonPanel = new JPanel();
        JButton selectButton = new JButton("선택");
        selectButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        selectButton.setBackground(new Color(217,217,217));
        
        JButton backButton = new JButton("돌아가기");
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        backButton.setBackground(new Color(217,217,217));

        buttonPanel.add(selectButton);
        buttonPanel.add(backButton);

        // "선택" 버튼
        selectButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null && parent.getLayout() instanceof CardLayout) {
                OrderView orderView = new OrderView(item, member);
                OrderEvt orderEvt = new OrderEvt(orderView);
                orderView.getRequestButton().addActionListener(orderEvt);
                orderView.getCancelButton().addActionListener(orderEvt);

                parent.add(orderView, "Order");
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "Order");
            } else {
                System.err.println("부모가 CardLayout이 아닙니다.");
                JOptionPane.showMessageDialog(this, "화면 전환 중 오류 발생", "오류", JOptionPane.ERROR_MESSAGE);
            }
        });

        // "돌아가기" 버튼
        backButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null) {
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "Recommended");
            }
        });

        add(productPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private JPanel infoLabel(String text) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label = new JLabel(text);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        panel.setBackground(new Color(245, 245, 245));
        panel.add(label);
        return panel;
    }
}