package kr.co.sist.khb.view;

import kr.co.sist.kji.MemberVO; // MemberVO import 경로 확인 필요
import kr.co.sist.kyh.ItemVO;

import kr.co.sist.khb.event.OrderEvt; // 필요시 사용
import kr.co.sist.khb.vo.OrderVO;

import javax.swing.*;
import java.awt.*;
//import java.sql.SQLException; // View에서 직접 SQLException 처리 지양

public class OrderView extends JPanel {

    // UI 컴포넌트 선언 (기존과 동일)
    private JLabel itemNameLabel;
    private JLabel carTypeLabel;
    private JLabel itemPriceLabel;
    private JLabel repairCostLabel; // ItemVO에 수리비용 필드가 있다고 가정
    private JLabel totalAmountLabel;
    private JLabel memberNameLabel;
    private JLabel memberPhoneLabel;
    private JButton requestButton;
    private JButton cancelButton;
    private Font defaultFont;

    // 데이터 홀더
    private ItemVO itemVO; // 전달받은 ItemVO 저장
    private MemberVO memberVO; // 전달받은 MemberVO 저장
    // private OrderVO orderVO; // 필요하다면 유지 (예: 최종 주문 정보 생성용)

    // ===> 수정된 생성자 <===
    public OrderView(ItemVO itemVO, MemberVO memberVO) {
        this.itemVO = itemVO; // 전달받은 itemVO 저장
        this.memberVO = memberVO; // 전달받은 memberVO 저장

        defaultFont = new Font("맑은 고딕", Font.PLAIN, 14);
        setFont(defaultFont);
        setLayout(new BorderLayout());

        // --- UI 컴포넌트 초기화 및 배치 (기존 코드 활용) ---
        // 상단: 상품 정보 패널 생성 및 배치
        JPanel itemInfoPanel = createItemInfoPanel(); // 패널 생성 로직 분리 (가독성)
        add(itemInfoPanel, BorderLayout.NORTH);

        // 중간: 총 금액 패널 생성 및 배치
        JPanel totalAmountPanel = createTotalAmountPanel(); // 패널 생성 로직 분리
        add(totalAmountPanel, BorderLayout.CENTER);

        // 하단: 내 정보 및 버튼 패널 생성 및 배치
        JPanel bottomPanel = createBottomPanel(); // 패널 생성 로직 분리
        add(bottomPanel, BorderLayout.SOUTH);

        // --- 전달받은 데이터로 UI 업데이트 ---
        populateData(); // 데이터를 UI에 뿌려주는 메소드 호출
    }

    // 데이터를 UI에 표시하는 메소드
    private void populateData() {
        if (itemVO != null) {
            setItemInfo(itemVO.getItemName(), String.valueOf(itemVO.getCarNum()), itemVO.getItemPrice(), itemVO.getItemRepairCost()); // getCarNum(), getItemRepairCost() 등 메소드명은 실제 ItemVO에 맞게 조정
            int repairCost = itemVO.getItemRepairCost();
            setTotalAmount(itemVO.getItemPrice() + repairCost);
        } else {
            // itemVO가 null인 경우 기본값 설정 (오류 처리)
            setItemInfo("-", "-", 0, 0);
            setTotalAmount(0);
        }

        if (memberVO != null) {
            setMemberInfo(memberVO.getMemName(), memberVO.getMemTell()); // getName(), getTel() 등 메소드명은 실제 MemberVO에 맞게 조정
        } else {
            // memberVO가 null인 경우 기본값 설정 (오류 처리)
            setMemberInfo("-", "-");
        }
    }

    // --- 패널 생성 메소드들 (기존 생성자 코드를 분리) ---
    private JPanel createItemInfoPanel() {
        JPanel itemInfoPanel = new JPanel();
        itemInfoPanel.setLayout(new GridLayout(3, 3, 5, 5)); // 레이아웃 확인 필요 (3x3?)
        itemInfoPanel.setBorder(BorderFactory.createTitledBorder("상품 정보"));
        itemInfoPanel.setFont(defaultFont);

        // ... (기존 itemInfoPanel 구성 코드) ...
        // JLabel 초기화 부분을 이곳으로 이동
        itemNameLabel = new JLabel("-", SwingConstants.CENTER);
        itemNameLabel.setFont(defaultFont);
        carTypeLabel = new JLabel("-", SwingConstants.CENTER);
        carTypeLabel.setFont(defaultFont);
        itemPriceLabel = new JLabel("-", SwingConstants.RIGHT);
        itemPriceLabel.setFont(defaultFont);
        repairCostLabel = new JLabel("-", SwingConstants.RIGHT); // repairCostLabel 초기화 추가
        repairCostLabel.setFont(defaultFont);

        // ... (JLabel들을 패널에 add 하는 코드) ...
         itemInfoPanel.add(new JLabel()); // 빈 공간 (이미지 자리)

         JLabel label1 = new JLabel("상품명", SwingConstants.CENTER);
         label1.setFont(defaultFont);
         itemInfoPanel.add(label1);
         JLabel label2 = new JLabel("차종", SwingConstants.CENTER);
         label2.setFont(defaultFont);
         itemInfoPanel.add(label2);
         JLabel label3 = new JLabel("금액", SwingConstants.CENTER);
         label3.setFont(defaultFont);
         itemInfoPanel.add(label3);

         itemInfoPanel.add(new JLabel());

         itemInfoPanel.add(itemNameLabel);
         itemInfoPanel.add(carTypeLabel);
         itemInfoPanel.add(itemPriceLabel);


         JLabel label4 = new JLabel(" ", SwingConstants.CENTER);
         label4.setFont(defaultFont);
         itemInfoPanel.add(label4);
         JLabel label5 = new JLabel("정비 비용", SwingConstants.CENTER); // 불필요해 보임
         label5.setFont(defaultFont);
         itemInfoPanel.add(label5); // 불필요해 보임
         itemInfoPanel.add(new JLabel());
         itemInfoPanel.add(repairCostLabel);


        return itemInfoPanel;
    }

    private JPanel createTotalAmountPanel() {
        JPanel totalAmountPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalAmountPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 10));
        // ... (기존 totalAmountPanel 구성 코드) ...
        totalAmountLabel = new JLabel("0 원", SwingConstants.RIGHT);
        totalAmountLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        // ... (JLabel들을 패널에 add 하는 코드) ...
         JLabel totalLabel = new JLabel("총 금액", SwingConstants.CENTER);
         totalLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
         totalAmountPanel.add(totalLabel);
         totalAmountPanel.add(totalAmountLabel);
        return totalAmountPanel;
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        // ... (기존 bottomPanel 구성 코드) ...
        memberNameLabel = new JLabel("이름: -");
        memberNameLabel.setFont(defaultFont);
        memberPhoneLabel = new JLabel("전화번호: -");
        memberPhoneLabel.setFont(defaultFont);

        requestButton = new JButton("정비 요청");
        requestButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        cancelButton = new JButton("취소");
        cancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));

         JPanel memberInfoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
         memberInfoPanel.setBorder(BorderFactory.createTitledBorder("내 정보"));
         memberInfoPanel.setFont(defaultFont);
         memberInfoPanel.add(memberNameLabel);
         memberInfoPanel.add(memberPhoneLabel);
         bottomPanel.add(memberInfoPanel, BorderLayout.NORTH);

         JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
         buttonPanel.add(requestButton);
         buttonPanel.add(cancelButton);
         bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        return bottomPanel;
    }

    // --- 데이터 설정 메소드들 (기존과 유사하게 유지 또는 populateData로 통합) ---
    private void setItemInfo(String itemName, String carType, int itemPrice, int repairCost) {
        this.itemNameLabel.setText(itemName != null ? itemName : "-");
        this.carTypeLabel.setText(carType != null ? carType : "-");
        this.itemPriceLabel.setText(String.format("%,d 원", itemPrice));
        this.repairCostLabel.setText(String.format("%,d 원", repairCost));
    }

    private void setTotalAmount(int totalAmount) {
        this.totalAmountLabel.setText(String.format("%,d 원", totalAmount));
    }

    private void setMemberInfo(String memberName, String memberPhone) {
        this.memberNameLabel.setText("이름: " + (memberName != null ? memberName : "-"));
        this.memberPhoneLabel.setText("전화번호: " + (memberPhone != null ? memberPhone : "-"));
    }


    // --- Getter 메소드들 (Event 클래스에서 사용) ---
    public JButton getRequestButton() {
        return requestButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public OrderVO getFinalOrderDetails() {
        if (itemVO != null && memberVO != null) {
        	
            int itemNum = itemVO.getItemNum();
            String itemName = itemVO.getItemName();
            int itemPrice = itemVO.getItemPrice();
            int itemCost = itemVO.getItemCost();
            int repairCost = itemVO.getItemRepairCost();
            int memNum = memberVO.getMemNum();

            int carNum = itemVO.getCarNum(); // <- 이 부분은 실제 데이터 구조에 맞게 확인/수정 필요

            // totalPrice 계산
            int totalPrice = itemPrice + repairCost;

            // 객체 생성 및 반환
            return new OrderVO(itemNum, itemName, itemPrice, itemCost, repairCost, totalPrice, memNum, carNum);
        }
        // itemVO 또는 memberVO가 null이면 null 반환
        return null;
    }
    
    
}