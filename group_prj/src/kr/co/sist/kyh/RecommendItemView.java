package kr.co.sist.kyh;

import javax.swing.*;
import java.awt.*;

public class RecommendItemView extends JPanel {
    
    public RecommendItemView( ) {
        setLayout(new BorderLayout());
        JPanel listPanel = new JPanel(new GridLayout(0, 5, 10, 10));
        
        for (int i = 1; i <= 40; i++) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel("추천상품 " + i, SwingConstants.CENTER);
            JLabel priceLabel = new JLabel("₩" + (i * 10000), SwingConstants.CENTER);
            JPanel textPanel = new JPanel(new GridLayout(2, 1));
            textPanel.add(nameLabel);
            textPanel.add(priceLabel);
            itemPanel.add(textPanel, BorderLayout.SOUTH);
            listPanel.add(itemPanel);
        }
        
        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listPanel.setPreferredSize(new Dimension(700, 900));
        add(scrollPane, BorderLayout.CENTER);
    }
}