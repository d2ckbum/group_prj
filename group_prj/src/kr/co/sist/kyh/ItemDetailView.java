package kr.co.sist.kyh;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import kr.co.sist.khb.event.OrderEvt;
import kr.co.sist.khb.view.OrderView;
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
        titleBorder.setTitleFont(new Font("맑은 고딕", Font.BOLD, 25));
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        productPanel.setBorder(titleBorder);

        String carType;
        switch (item.getCarNum()) {
            case 1:
            	carType = "소형";
                break;
            case 2:
            	carType = "중형";
                break;
            case 3:
            	carType = "대형";
                break;
            default:
            	carType = "알 수 없음";
        }
        
        productPanel.add(infoLabel("상품번호: " + item.getItemNum()));
        productPanel.add(infoLabel("상품명: " + item.getItemName()));
        productPanel.add(infoLabel("차종: " + carType));
        productPanel.add(infoLabel("금액: ₩" + String.format("%,d", item.getItemPrice())));

        JPanel buttonPanel = new JPanel();
        JButton selectButton = new JButton("선택");
        selectButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        selectButton.setBackground(new Color(217,217,217));
        
        JButton backButton = new JButton("돌아가기");
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        backButton.setBackground(new Color(217,217,217));

        buttonPanel.add(selectButton);
        buttonPanel.add(backButton);

        // "선택" 버튼 ActionListener
        selectButton.addActionListener(e -> {
            Container parent = getParent(); 
          
            if (parent != null && parent.getLayout() instanceof CardLayout) {
                OrderView orderView = new OrderView(item, member);

                // 2. OrderEvt 생성 및 OrderView의 버튼들에 연결 
                OrderEvt orderEvt = new OrderEvt(orderView); // OrderEvt 생성, OrderView 인스턴스 전달
                orderView.getRequestButton().addActionListener(orderEvt); // "정비 요청" 버튼 리스너 연결
                orderView.getCancelButton().addActionListener(orderEvt);  // "취소" 버튼 리스너 연결

                parent.add(orderView, "Order");
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "Order");

            } else {
                 JOptionPane.showMessageDialog(this, "화면 전환 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
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
        label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        panel.setBackground(new Color(245, 245, 245));
        panel.add(label);
        return panel;
    }
}