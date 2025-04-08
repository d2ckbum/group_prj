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
        listPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        listPanel.setPreferredSize(new Dimension(1080, 300));
        
        loadRecommendedItems();

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15); // 부드러운 스크롤
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

        if (recommendedItems != null) {
            for (ItemVO item : recommendedItems) {
                JPanel itemPanel = new JPanel(new BorderLayout());
                itemPanel.setBackground(Color.WHITE);
                itemPanel.setPreferredSize(new Dimension(200, 180));
                itemPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));

                JLabel nameLabel = new JLabel(item.getItemName(), SwingConstants.CENTER);
                nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));

                JLabel priceLabel = new JLabel("₩" + item.getItemPrice(), SwingConstants.CENTER);
                priceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
                priceLabel.setForeground(new Color(70, 70, 70));

                JPanel textPanel = new JPanel(new GridLayout(2, 1));
                textPanel.setOpaque(false);
                textPanel.add(nameLabel);
                textPanel.add(priceLabel);

                itemPanel.add(textPanel, BorderLayout.CENTER);

                itemPanel.addMouseListener(new ItemEvt(parent, parent.getMember()).getEngineOilClickListener(item));

                listPanel.add(itemPanel);
            }
        }
    }
}
