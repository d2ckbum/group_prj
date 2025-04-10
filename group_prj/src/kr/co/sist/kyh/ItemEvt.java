package kr.co.sist.kyh;

import javax.swing.*;

import kr.co.sist.kji.MemberVO;

import java.awt.*;
import java.awt.event.*;

public class ItemEvt implements ActionListener {
    private ItemView itemView;
    private MemberVO member;

    public ItemEvt(ItemView itemView, MemberVO member) {
        this.itemView = itemView;
        this.member = member; 

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == itemView.getBtnEngineOil()) {
            itemView.getMainPanel().removeAll();  // 기존 패널 삭제
            JPanel engineOilPanel = itemView.createEngineOilPanel();
            itemView.getMainPanel().add(engineOilPanel, "EngineOil");
            itemView.getCardLayout().show(itemView.getMainPanel(), "EngineOil");

        }

    }

    // 상품 클릭 시 상세보기 화면으로 전환
    public MouseAdapter getEngineOilClickListener(ItemVO item) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel detailPanel = new ItemDetailView(item, member);
                Container parent = itemView.getMainPanel();
                parent.add(detailPanel, "Detail");
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "Detail");
            }
        };
    }
    
 // 추천상품 클릭 시 상세보기로 전환
    public MouseAdapter getRecommendClickListener(ItemVO item) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel detailPanel = new RecommendItemDetailView(item, member);
                Container parent = itemView.getMainPanel();
                parent.add(detailPanel, "RecommendDetail");
                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "RecommendDetail");
            }
        };
    }
}
