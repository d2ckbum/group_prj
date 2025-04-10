package kr.co.sist.khb.view;

import kr.co.sist.kji.MemberVO;
import kr.co.sist.kyh.ItemVO;
import kr.co.sist.khb.vo.OrderVO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OrderView extends JPanel {

    private ItemVO itemVO;
    private MemberVO memberVO;

    private JButton requestButton;
    private JButton cancelButton;

    public OrderView(ItemVO itemVO, MemberVO memberVO) {
        this.itemVO = itemVO;
        this.memberVO = memberVO;

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // --- 상품정보 패널 (상단 중앙) ---
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(245, 245, 245));
        infoPanel.setPreferredSize(new Dimension(600, 250));

        TitledBorder infoTitle = BorderFactory.createTitledBorder("주문 정보");
        infoTitle.setTitleFont(new Font("맑은 고딕", Font.BOLD, 25));
        infoTitle.setTitleJustification(TitledBorder.CENTER);
        infoPanel.setBorder(infoTitle);

        infoPanel.add(infoLabel("상품명: " + itemVO.getItemName()));
        infoPanel.add(infoLabel("차종: " + itemVO.getCarNum()));
        infoPanel.add(infoLabel("상품금액: ₩" + String.format("%,d", itemVO.getItemPrice())));
        infoPanel.add(infoLabel("정비비용: ₩" + String.format("%,d", itemVO.getItemRepairCost())));
        int total = itemVO.getItemPrice() + itemVO.getItemRepairCost();
        infoPanel.add(infoLabel("총 금액: ₩" + String.format("%,d", total)));

        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        memberPanel.setBackground(new Color(245, 245, 245));
        memberPanel.setPreferredSize(new Dimension(600, 120));
        
        TitledBorder memberTitle = BorderFactory.createTitledBorder("내 정보");
        memberTitle.setTitleFont(new Font("맑은 고딕", Font.BOLD, 25));
        memberTitle.setTitleJustification(TitledBorder.CENTER);
        memberPanel.setBorder(memberTitle);

        memberPanel.add(infoLabel("이름: " + memberVO.getMemName()));
        memberPanel.add(infoLabel("전화번호: " + memberVO.getMemTell()));

        // --- 버튼 패널 ---
        JPanel buttonPanel = new JPanel();
        requestButton = new JButton("정비 요청");
        cancelButton = new JButton("취소");

        requestButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        requestButton.setBackground(new Color(217,217,217));
        
        cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        cancelButton.setBackground(new Color(217,217,217));

        buttonPanel.add(requestButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(infoPanel);
        mainPanel.add(memberPanel);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // 라벨 생성 메서드
    private JPanel infoLabel(String text) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        JLabel label = new JLabel(text);
        label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        panel.add(label);
        return panel;
    }

    // 버튼 Getter
    public JButton getRequestButton() {
        return requestButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    // 주문 정보 전달용 메서드
    public OrderVO getFinalOrderDetails() {
        if (itemVO != null && memberVO != null) {
            int totalPrice = itemVO.getItemPrice() + itemVO.getItemRepairCost();
            return new OrderVO(
                itemVO.getItemNum(),
                itemVO.getItemName(),
                itemVO.getItemPrice(),
                itemVO.getItemCost(),
                itemVO.getItemRepairCost(),
                totalPrice,
                memberVO.getMemNum(),
                itemVO.getCarNum()
            );
        }
        return null;
    }
}