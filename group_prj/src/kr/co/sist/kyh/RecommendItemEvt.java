package kr.co.sist.kyh;

import javax.swing.*;

import kr.co.sist.kji.MemberVO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RecommendItemEvt {
    
    private ItemView itemView;
    private MemberVO member;

    public RecommendItemEvt(ItemView itemView, MemberVO member) {
        this.itemView = itemView;
        this.member = member;
    }

    // 상품 클릭 시 상세보기 화면으로 전환
    public MouseAdapter getEngineOilClickListener(ItemVO item) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RecommendItemDetailView detailView = new RecommendItemDetailView(item, member);
                itemView.getMainPanel().add(detailView, "RecommendDetail");
                itemView.getCardLayout().show(itemView.getMainPanel(), "RecommendDetail");
            }
        };
    }
}
