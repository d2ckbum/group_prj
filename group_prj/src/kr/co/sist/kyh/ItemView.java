package kr.co.sist.kyh;

import javax.swing.*;

import kr.co.sist.kji.LoginpageView;
import kr.co.sist.kji.MemberVO;
import kr.co.sist.kji.MyInfoView;

import java.awt.*;
import java.nio.charset.CoderMalfunctionError;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemView extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    private JButton btnEngineOil;
    private JButton btnRecommended;
    private JButton btnInquiry;
    private JButton btnMyInfo;
    private JButton btnLogout;
    private JPanel engineOilPanel;
    private JPanel recommendPanel;
    private JPanel myInfoPanel;
    
    private Color defaultButtonColor = new Color(217, 217, 217);
    private Color selectButtonSelected = new Color(150, 150, 150);
    private JButton currentlySelectedButton = null;

	private String id;
    private int carNum;
    private MemberVO member;
    private JLabel welcomeLabel;

    public ItemView(int carNum, MemberVO member, String id) {
    	this.id = id;
        this.carNum = carNum;
        this.member = member;

        setTitle("쌍용 엔진오일 샵");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 800);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        JPanel topPanel = new JPanel(null); // 전체 수동 배치
        topPanel.setPreferredSize(new Dimension(1080, 160));

        JLabel titleLabel = new JLabel("쌍용 엔진오일 샵", SwingConstants.CENTER);
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        titleLabel.setBounds(0, 10, 1080, 40);

        // 환영 메시지 + 로그아웃
        String welcomeMsg = member.getMemName() + "님, 환영합니다!";
        welcomeLabel = new JLabel(welcomeMsg);
        welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        welcomeLabel.setBounds(760, 60, 180, 25);

        btnLogout = new JButton("로그아웃");
        btnLogout.setBackground(new Color(217,217,217));
        btnLogout.setBounds(920, 60, 100, 25);
        btnLogout.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBounds(250, 90, 700, 50);

        btnEngineOil = new JButton("엔진오일");
        btnEngineOil.setBackground(new Color(217,217,217));
        btnEngineOil.setBounds(0, 15, 120, 30);
        btnEngineOil.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        buttonPanel.add(btnEngineOil);

        btnRecommended = new JButton("추천상품");
        btnRecommended.setBackground(new Color(217,217,217));
        btnRecommended.setBounds(150, 15, 120, 30);
        btnRecommended.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        buttonPanel.add(btnRecommended);

        btnInquiry = new JButton("문의게시판");
        btnInquiry.setBackground(new Color(217,217,217));
        btnInquiry.setBounds(300, 15, 120, 30);
        btnInquiry.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        buttonPanel.add(btnInquiry);

        btnMyInfo = new JButton("내 정보");
        btnMyInfo.setBackground(new Color(217,217,217));
        btnMyInfo.setBounds(450, 15, 120, 30);
        btnMyInfo.setFont(new Font("맑은 고딕", Font.BOLD, 15));

        buttonPanel.add(btnMyInfo);
        
        btnEngineOil.setFocusPainted(false);
        btnRecommended.setFocusPainted(false);
        btnInquiry.setFocusPainted(false);
        btnMyInfo.setFocusPainted(false);
        
        topPanel.add(titleLabel);
        topPanel.add(welcomeLabel);
        topPanel.add(btnLogout);
        topPanel.add(buttonPanel);

        JPanel inquiryPanel = new JPanel();
        inquiryPanel.add(new JLabel("문의게시판"));


        mainPanel.add(inquiryPanel, "Inquiry");

        btnEngineOil.addActionListener(e -> {
        	setButtonSelected(btnEngineOil);
            if (engineOilPanel == null) {
                engineOilPanel = createEngineOilPanel();
                mainPanel.add(engineOilPanel, "EngineOil");
            }
            cardLayout.show(mainPanel, "EngineOil");
        });

        btnRecommended.addActionListener(e -> {
        	setButtonSelected(btnRecommended);
            if (recommendPanel == null) {
                recommendPanel = new RecommendItemView(this, carNum);
                mainPanel.add(recommendPanel, "Recommended");
            }
            cardLayout.show(mainPanel, "Recommended");
        });

        btnMyInfo.addActionListener(e -> {
            setButtonSelected(btnMyInfo);
            if (myInfoPanel == null) {
            	myInfoPanel = new MyInfoView(id);
                mainPanel.add(myInfoPanel, "MyInfo");
            }
            cardLayout.show(mainPanel, "MyInfo");
        });
        
        btnInquiry.addActionListener(e -> cardLayout.show(mainPanel, "Inquiry"));

        btnLogout.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "로그아웃되었습니다.");
            dispose();
            new LoginpageView();
        });

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }
    
    // 버튼 클릭 시 색 지정
    public void setButtonSelected(JButton button) {
        if (currentlySelectedButton != null && currentlySelectedButton != button) {
           currentlySelectedButton.setBackground(defaultButtonColor);
        }
        button.setBackground(selectButtonSelected);
        currentlySelectedButton = button;
     }

    // 환영 메시지 메서드
    public void updateWelcomeMessage() {
        String welcomeMsg = member.getMemName() + "님, 환영합니다!";
        welcomeLabel.setText(welcomeMsg);
    }

    // 전체 상품을 가져오는 메서드
    public JPanel createEngineOilPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel listPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        listPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        listPanel.setPreferredSize(new Dimension(1080, 300));

        ItemRecommendDAO itemDAO = ItemRecommendDAO.getInstance();
        List<ItemVO> items = new ArrayList<>();

        try {
            items = itemDAO.getAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!items.isEmpty()) {
            for (ItemVO item : items) {
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

                itemPanel.addMouseListener(new ItemEvt(this, member).getEngineOilClickListener(item));

                listPanel.add(itemPanel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15); // 부드러운 스크롤

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public JButton getBtnEngineOil() {
		return btnEngineOil;
	}

	public JButton getBtnRecommended() {
		return btnRecommended;
	}

	public JButton getBtnInquiry() {
		return btnInquiry;
	}

	public JButton getBtnMyInfo() {
		return btnMyInfo;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}

	public JPanel getEngineOilPanel() {
		return engineOilPanel;
	}

	public JPanel getRecommendPanel() {
		return recommendPanel;
	}

	public int getCarNum() {
		return carNum;
	}

	public MemberVO getMember() {
		return member;
	}

	public JLabel getWelcomeLabel() {
		return welcomeLabel;
	}
    
}
