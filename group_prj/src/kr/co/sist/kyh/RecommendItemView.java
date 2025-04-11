package kr.co.sist.kyh;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RecommendItemView extends JPanel {
    private ItemView parent;
    private JPanel listPanel;
    private int carNum;

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
    // 커스텀 클래스
    class EngineOilShapePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(20, 30, width - 40, height - 60, 30, 30);

            g2.setColor(new Color(160, 160, 160));
            g2.fillRect(width / 2 - 25, 10, 50, 20);


            g2.setColor(new Color(100, 100, 100));
            g2.drawRoundRect(20, 30, width - 40, height - 60, 30, 30);
            g2.drawRect(width / 2 - 25, 10, 50, 20);
        }
    }

    private void loadRecommendedItems() {
        listPanel.removeAll();
        ItemRecommendDAO itemDAO = ItemRecommendDAO.getInstance();
        List<ItemVO> recommendedItems = null;
        
        if (parent.getMember() == null) {
            JOptionPane.showMessageDialog(this, "차량 정보가 없습니다. 회원 정보를 확인하세요.", "오류", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int carNum = parent.getMember().getCarNum();

        try {
            recommendedItems = itemDAO.getRecommendedItems(carNum);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "추천 상품을 불러오는 데 실패했습니다.", "DB 오류", JOptionPane.ERROR_MESSAGE);
        }

        int count = 0;
        if (recommendedItems != null) {
            RecommendItemEvt evt = new RecommendItemEvt(parent, parent.getMember());

            for (ItemVO item : recommendedItems) {
                if (item.getItemStock() <= 0) continue;

//                JPanel itemPanel = new JPanel(new BorderLayout());
//                itemPanel.setBackground(Color.WHITE);
//                itemPanel.setPreferredSize(new Dimension(220, 180));
//                itemPanel.setBorder(BorderFactory.createCompoundBorder(
//                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
//                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
//                ));
                
                // 커스텀 아이템 패널
                EngineOilShapePanel itemPanel = new EngineOilShapePanel(); // 커스텀 패널 사용
                itemPanel.setLayout(new BorderLayout());
                itemPanel.setPreferredSize(new Dimension(280, 240));
                itemPanel.setOpaque(false); // 배경은 직접 그림

                JLabel nameLabel = new JLabel(item.getItemName(), SwingConstants.CENTER);
                nameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));

                JLabel priceLabel = new JLabel("₩" + String.format("%,d", item.getItemPrice()), SwingConstants.CENTER);
                priceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
                priceLabel.setForeground(new Color(70, 70, 70));

                JPanel textPanel = new JPanel(new GridLayout(2, 1));
                textPanel.setOpaque(false);
                textPanel.add(nameLabel);
                textPanel.add(priceLabel);

                itemPanel.add(textPanel, BorderLayout.CENTER);
                itemPanel.addMouseListener(evt.getEngineOilClickListener(item));

                listPanel.add(itemPanel);
                count++;
            }

            int itemRow = 2;
            int itemHeight = 200;
            int rowGap = 20;
            int rows = (int) Math.ceil(count / (double) itemRow);
            int totalHeight = (itemHeight + rowGap) * rows;
            listPanel.setPreferredSize(new Dimension(1000, totalHeight));
        }

        revalidate();
        repaint();
     
    }
}
