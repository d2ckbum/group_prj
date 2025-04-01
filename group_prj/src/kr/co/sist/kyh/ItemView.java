package kr.co.sist.kyh;

import javax.swing.*;
import java.awt.*;

public class ItemView {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public ItemView() {
        frame = new JFrame("쌍용 엔진오일  샵");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 상단 패널을 GridBagLayout으로 설정
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        // 타이틀 패널
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        JLabel titleLabel = new JLabel("쌍용 엔진오일 샵", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        
        // 사용자 패널 (위로 이동)
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel welcomeLabel = new JLabel("OOO님 어서옵서예!");
        JButton logoutButton = new JButton("로그아웃");
        userPanel.add(welcomeLabel);
        userPanel.add(logoutButton);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        JButton btnEngineOil = new JButton("엔진오일");
        JButton btnRecommended = new JButton("추천상품");
        JButton btnInquiry = new JButton("문의게시판");
        JButton btnMyInfo = new JButton("내 정보");
        
        buttonPanel.add(btnEngineOil);
        buttonPanel.add(btnRecommended);
        buttonPanel.add(btnInquiry);
        buttonPanel.add(btnMyInfo);

        // GridBagLayout으로 배치 조정
        gbc.gridy = 0;
        topPanel.add(titlePanel, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        topPanel.add(userPanel, gbc);
        
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        topPanel.add(buttonPanel, gbc);

        // 각 패널 생성
        JPanel inquiryPanel = new JPanel();
        JPanel myInfoPanel = new JPanel();

        inquiryPanel.add(new JLabel("문의게시판"));
        myInfoPanel.add(new JLabel("내 정보"));

        // 메인 패널
        mainPanel.add(new RecommendItemView(), "Recommended");
        mainPanel.add(inquiryPanel, "Inquiry");
        mainPanel.add(myInfoPanel, "MyInfo");

        // 버튼 이벤트
        btnEngineOil.addActionListener(e -> {
            JPanel engineOilPanel = createEngineOilPanel();
            mainPanel.add(engineOilPanel, "EngineOil");
            cardLayout.show(mainPanel, "EngineOil");
        });
        
        btnRecommended.addActionListener(e -> {
            cardLayout.show(mainPanel, "Recommended");
        });

        btnInquiry.addActionListener(e -> cardLayout.show(mainPanel, "Inquiry"));
        btnMyInfo.addActionListener(e -> cardLayout.show(mainPanel, "MyInfo"));

        // 메인 프레임에 topPanel 추가
        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createEngineOilPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel(new GridLayout(0, 5, 10, 10));
        
        for (int i = 1; i <= 40; i++) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel("엔진오일 " + i, SwingConstants.CENTER);
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
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
}

