package kr.co.sist.kyh;

import javax.swing.*;

import kr.co.sist.cjw.view.Mem_Inquiry_View;
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
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
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
        btnLogout.setFocusPainted(false);
        
        topPanel.add(titleLabel);
        topPanel.add(welcomeLabel);
        topPanel.add(btnLogout);
        topPanel.add(buttonPanel);

        btnEngineOil.addActionListener(e -> {
            setButtonSelected(btnEngineOil);

            if (engineOilPanel != null) {
                mainPanel.remove(engineOilPanel); // 기존 패널 제거
            }

            engineOilPanel = createEngineOilPanel();
            mainPanel.add(engineOilPanel, "EngineOil");
            
            mainPanel.revalidate(); // 레이아웃 갱신
            mainPanel.repaint();

            cardLayout.show(mainPanel, "EngineOil");
        });

        btnRecommended.addActionListener(e -> {
        	setButtonSelected(btnRecommended);
            if (recommendPanel != null) {
            	mainPanel.remove(recommendPanel);
            }
            
            recommendPanel = new RecommendItemView(this, carNum);
            mainPanel.add(recommendPanel, "Recommended");
            
            mainPanel.revalidate(); // 레이아웃 갱신
            mainPanel.repaint();
            
            cardLayout.show(mainPanel, "Recommended");
        });

        btnMyInfo.addActionListener(e -> {
            setButtonSelected(btnMyInfo);
            if (myInfoPanel != null) {
            	mainPanel.remove(myInfoPanel);
            }
            myInfoPanel = new MyInfoView(id);
            mainPanel.add(myInfoPanel, "MyInfo");
            
            mainPanel.revalidate(); // 레이아웃 갱신
            mainPanel.repaint();
            
            cardLayout.show(mainPanel, "MyInfo");
        });
        
        btnInquiry.addActionListener(e -> {
            setButtonSelected(btnInquiry);
            new Mem_Inquiry_View(id);
        });

        btnLogout.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                this,
                "로그아웃하시겠습니까?",
                "로그아웃 확인",
                JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "로그아웃되었습니다.");
                dispose();
                new LoginpageView();
            }
        });

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
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

    // 전체 상품을 가져오는 메서드
    public JPanel createEngineOilPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel listPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        
        listPanel.removeAll();
        ItemRecommendDAO itemDAO = ItemRecommendDAO.getInstance();
        List<ItemVO> items = new ArrayList<>();

        try {
            items = itemDAO.getAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "상품 목록을 불러오는 데 실패했습니다.", "DB 오류", JOptionPane.ERROR_MESSAGE);
        }
        
        int count = 0; // 상품 수

        if (!items.isEmpty()) {
            for (ItemVO item : items) {
            	if(item.getItemStock() <= 0) {
            		continue;
            	}
            	
//                JPanel itemPanel = new JPanel(new BorderLayout());
//                itemPanel.setBackground(Color.WHITE);
//                itemPanel.setPreferredSize(new Dimension(220, 180)); // 카드 크기
//                itemPanel.setBorder(BorderFactory.createCompoundBorder(
//                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
//                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
//                ));
            	
            	// 커스텀 아이템 패널
                EngineOilShapePanel itemPanel = new EngineOilShapePanel();
                itemPanel.setLayout(new BorderLayout());
                itemPanel.setPreferredSize(new Dimension(280, 240));
                itemPanel.setOpaque(false);

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
                itemPanel.addMouseListener(new ItemEvt(this, member).getEngineOilClickListener(item));

                listPanel.add(itemPanel);
                count++;
            }
            
            // 아이템 수에 따라 높이 계산
            int itemRow = 2;
            int itemHeight = 200;
            int rowGap = 20;
            int rows = (int) Math.ceil(count / (double) itemRow);
            int totalHeight = (itemHeight + rowGap) * rows;
            listPanel.setPreferredSize(new Dimension(1000, totalHeight));
            
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);

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

	public JPanel getMyInfoPanel() {
		return myInfoPanel;
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
