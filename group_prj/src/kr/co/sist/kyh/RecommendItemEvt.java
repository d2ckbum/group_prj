package kr.co.sist.kyh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import kr.co.sist.kji.MemberVO;

public class RecommendItemEvt {

    private ItemView itemView;
    private MemberVO member;

    public RecommendItemEvt(ItemView itemView, MemberVO member) {
        this.itemView = itemView;
        this.member = member;
    }

    // 추천상품 클릭 시 상세뷰로 전환
    public MouseAdapter getEngineOilClickListener(ItemVO item) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RecommendItemDetailView detailView = new RecommendItemDetailView(item, member);
                
                Container parent = itemView.getMainPanel(); // ✅ JPanel 반환
                parent.add(detailView, "RecommendDetail");

                CardLayout layout = (CardLayout) parent.getLayout();
                layout.show(parent, "RecommendDetail");
            }
        };
    }
}
