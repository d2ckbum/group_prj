package kr.co.sist.kyh;

import javax.swing.*;

import kr.co.sist.kji.MemberVO;

import java.awt.*;

public class OrderView extends JPanel {
    
    private ItemVO item;
    private MemberVO member;
    
    public OrderView(ItemVO item, MemberVO member) {
        this.item = item;
        this.member = member;
        setLayout(new BorderLayout());

        // 메인 컨테이너 패널 (상단 정보 포함)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // 상품 정보 라벨
        JLabel itemInfoLabel = new JLabel("상품 정보");
        itemInfoLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        itemInfoLabel.setAlignmentX(CENTER_ALIGNMENT);
        itemInfoLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 10, 0));
        mainPanel.add(itemInfoLabel);
        
        // 상품 정보 패널
        JPanel itemPanel = new JPanel(new GridLayout(3, 1, 0, 0));

        // 차량 크기 변환 (carNum 기반)
        String carSize = convertCarNumToSize(item.getCarNum());

        // 1행: 상품명 - 차량 크기 - 금액
        JPanel row1 = new JPanel();
        row1.setBorder(BorderFactory.createEmptyBorder(40, 0, 2, 0));
        JLabel itemNameLabel = new JLabel("상품명: " + item.getItemName(), SwingConstants.CENTER);
        itemNameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        row1.add(itemNameLabel);
        JLabel carSizeLabel = new JLabel("차량 크기: " + carSize, SwingConstants.CENTER);
        carSizeLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        row1.add(carSizeLabel);
        JLabel priceLabel = new JLabel("상품 금액: ₩" + item.getItemPrice(), SwingConstants.CENTER);
        priceLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        row1.add(priceLabel);

        // 2행: 정비비용
        JPanel row2 = new JPanel();
        row2.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        JLabel repairCostLabel = new JLabel("정비비용: ₩" + item.getItemRepairCost(), SwingConstants.CENTER);
        repairCostLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        row2.add(repairCostLabel);

        // 3행: 총 금액
        JPanel row3 = new JPanel();
        row3.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
        JLabel totalCostLabel = new JLabel("총 금액: ₩" + (item.getItemPrice() + item.getItemRepairCost()), SwingConstants.CENTER);
        totalCostLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        row3.add(totalCostLabel);

        // 패널 추가
        itemPanel.add(row1);
        itemPanel.add(row2);
        itemPanel.add(row3);
        mainPanel.add(itemPanel);

        // 내 정보 라벨
        JLabel userInfoLabel = new JLabel("내 정보");
        userInfoLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        userInfoLabel.setAlignmentX(CENTER_ALIGNMENT);
        userInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(userInfoLabel);
        
        // 내 정보 패널
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        JLabel userNameLabel = new JLabel("이름: " + member.getMemName());
        userNameLabel.setAlignmentX(CENTER_ALIGNMENT);
        JLabel userPhoneLabel = new JLabel("전화번호: " + member.getMemTell());
        userPhoneLabel.setAlignmentX(CENTER_ALIGNMENT);

        userInfoPanel.add(userNameLabel);
        userInfoPanel.add(userPhoneLabel);
        mainPanel.add(userInfoPanel);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 20, 0));
        
        JButton requestButton = new JButton("정비요청");
        JButton cancelButton = new JButton("취소");
        
        buttonPanel.add(requestButton);
        buttonPanel.add(cancelButton);

        // 정비요청 버튼 이벤트
        requestButton.addActionListener(e -> {
            String requestId = "REQ" + System.currentTimeMillis();
            int result = JOptionPane.showConfirmDialog(this, "정비요청이 완료되었습니다.\n접수번호: " + requestId, 
                    "정비요청 완료", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                Container parent = getParent();
                if (parent != null) {
                    CardLayout layout = (CardLayout) parent.getLayout();
                    layout.show(parent, "EngineOil");
                }
            }
        });

        // 취소 버튼 이벤트
        cancelButton.addActionListener(e -> {
            Container parent = getParent();
            if (parent != null) {
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "EngineOil");
            }
        });

        // 패널 추가
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // 차량 크기 변환 메서드
    private String convertCarNumToSize(int carNum) {
        switch (carNum) {
            case 1: return "소형";
            case 2: return "중형";
            case 3: return "대형";
            default: return "알 수 없음";
        }
    }
}