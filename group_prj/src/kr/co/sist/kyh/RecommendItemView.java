package kr.co.sist.kyh;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RecommendItemView extends JPanel {
    private int carNum;
    private ItemView parent;
    private JPanel listPanel;

    public RecommendItemView(ItemView parent, int carNum) {
        this.parent = parent;
        this.carNum = carNum;

        setLayout(new BorderLayout());

        listPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        loadRecommendedItems();

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadRecommendedItems() {
        listPanel.removeAll(); // 기존 목록 초기화
        ItemRecommendDAO itemDAO = ItemRecommendDAO.getInstance();
        List<ItemVO> recommendedItems = null;

        try {
            recommendedItems = itemDAO.getRecommendedItems(carNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int count = 0;

        if (recommendedItems != null) {
            for (ItemVO item : recommendedItems) {
            	if(item.getItemStock() <= 0) {
            		continue;
            	}
            	
                JPanel itemPanel = new JPanel(new BorderLayout());
                itemPanel.setBackground(Color.WHITE);
                itemPanel.setPreferredSize(new Dimension(220, 180)); // 카드 크기
                itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));

                JLabel nameLabel = new JLabel(item.getItemName(), SwingConstants.CENTER);
                nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));

                JLabel priceLabel = new JLabel("₩" + item.getItemPrice(), SwingConstants.CENTER);
                priceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
                priceLabel.setForeground(new Color(70, 70, 70));

                JPanel textPanel = new JPanel(new GridLayout(2, 1));
                textPanel.setOpaque(false);
                textPanel.add(nameLabel);
                textPanel.add(priceLabel);

                itemPanel.add(textPanel, BorderLayout.CENTER);
                itemPanel.addMouseListener(new ItemEvt(parent, parent.getMember()).getEngineOilClickListener(item));

                listPanel.add(itemPanel);
                count++;
            }
            
            // 추천상품 개수 기반으로 높이 설정
            int itemRow = 3;
            int itemHeight = 200;
            int rows = (int) Math.ceil(count / (double) itemRow);
            listPanel.setPreferredSize(new Dimension(800, rows * itemHeight));
        }
    }
}
