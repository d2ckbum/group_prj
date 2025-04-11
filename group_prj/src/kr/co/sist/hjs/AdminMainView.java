package kr.co.sist.hjs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AdminMainView extends JFrame {
	private JButton jbtnMember;
	private JButton jbtnItemManage;
	private JButton jbtnFixManage;
	private JButton jbtnSales;
	private JButton jbtnInquiry;
	private JLabel welcomeLabel;
	private JButton jbtnLogout;
	private AdminMainViewEvt amve; // 이벤트 처리 클래스
	
	private JLabel memberCountLabel; // 회원 수 표시 레이블
	private JLabel itemCountLabel; // 등록된 수 표시 레이블
	private JLabel fixCountLabel; // 정비 접수 완료 건수 표시 레이블
	private JLabel fixingCountLabel; // 정비 중 건수 표시 레이블
	private JLabel fixOkCountLabel; // 정비 완료 건수 표시 레이블
	private JLabel salesCountLabel;// 일 매출 현황 표시 레이블
	private JLabel inquiryCountLabel; // 문의 답변 대기 건수 표시 레이블
	private JLabel fixImageLabel;
	private JLabel salesImageLabel;
	private JLabel memberImageLabel;
	private JLabel inquiryImageLabel;
	private JLabel itemManagementImageLabel;
	
	
	private Color defaultButtonColor = new Color(217, 217, 217); // 기본 버튼 색상
	private Font buttonFont = new Font("맑은 고딕", Font.BOLD, 25);
	private Font countFont = new Font("맑은 고딕", Font.BOLD, 14);
	private final int BUTTON_WIDTH = 300; // 고정된 버튼 너비
	private final int BUTTON_HEIGHT = 330; // 고정된 버튼 높이 (버튼 + 라벨)

	public AdminMainView() {
		setTitle("관리자 페이지");
		setSize(1200, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		amve = new AdminMainViewEvt(this);
		setLayout(new BorderLayout());

		UIManager.put("Button.hoverborder", BorderFactory.createLineBorder(Color.RED, 3)); // 포커스 시 테두리 색상 및 두께 설정
		// 상단 패널
		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel titleLabel = new JLabel("관리자 페이지", SwingConstants.CENTER);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
//		topPanel.add(titleLabel, BorderLayout.CENTER);
		topPanel.add("North",titleLabel);
		
		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		welcomeLabel = new JLabel("관리자님 어서오세요");
		welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jbtnLogout = new JButton("종료");
		jbtnLogout.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jbtnLogout.setBackground(new Color(217, 217, 217));

		rightPanel.add(welcomeLabel);
		rightPanel.add(jbtnLogout);
		topPanel.add("East",rightPanel);
		
		rightPanel.setBorder(new EmptyBorder(0, 0, 0, 76)); // 상단 여백 추가
		add(topPanel, BorderLayout.NORTH);

		// 중앙 버튼 패널
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 가운데 정렬, 간격 설정
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 여백 설정
		add(centerPanel, BorderLayout.CENTER);

		// 회원 현황 버튼
		createMemberButton(centerPanel);

		// 상품 관리 버튼
		createItemManageButton(centerPanel);

		// 정비 관리 버튼
		createFixManageButton(centerPanel);

		// 매출 현황 버튼
		createSalesButton(centerPanel);

		// 문의 관리 버튼
		createInquiryButton(centerPanel);

		// 이벤트 등록
		jbtnMember.addActionListener(amve);
		jbtnItemManage.addActionListener(amve);
		jbtnFixManage.addActionListener(amve);
		jbtnSales.addActionListener(amve);
		jbtnInquiry.addActionListener(amve);
		jbtnLogout.addActionListener(e -> {
			int result=JOptionPane.showConfirmDialog(null, "종료하실?\n (종료시 로그인창으로 갑니다잉)","종료",JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
				dispose();
				new AdminLoginView();
			}//end if
		});

		// 윈도우가 활성화될 때마다 정보 업데이트
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				displayMemberCount();
				displayItemCount();
				displayFixCounts(); // 수정된 메소드 호출
				displaySalesCount();
				displayInquiryCount();
			}
		});

		// 초기 정보 표시 (프로그램 시작 시)
		displayMemberCount();
		displayItemCount();
		displayFixCounts(); // 수정된 메소드 호출
		displaySalesCount();
		displayInquiryCount();

		setVisible(true);
		 
	}

	private void createMemberButton(JPanel parentPanel) {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null); // absolute layout
		layeredPane.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT)); // JLayeredPane 크기 고정

		jbtnMember = new JButton("회원 현황"); // 클래스 멤버 변수에 할당
		jbtnMember.setFont(buttonFont);
		jbtnMember.setBackground(defaultButtonColor);
		jbtnMember.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);

		memberCountLabel = new JLabel("회원 0 명", SwingConstants.CENTER); // 클래스 멤버 변수에 할당
		memberCountLabel.setFont(countFont);
		memberCountLabel.setBounds(0, 50, BUTTON_WIDTH, BUTTON_HEIGHT);
		memberCountLabel.setOpaque(false);
		
	        
        memberImageLabel = new JLabel(getImage("/resources/images/member_icon.png"));
        memberImageLabel.setBounds(0, -80, BUTTON_WIDTH, BUTTON_HEIGHT);
		layeredPane.add(memberImageLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(jbtnMember, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(memberCountLabel, JLayeredPane.PALETTE_LAYER);

//        jbtnMember.setBorder(new LineBorder(Color.BLACK, 5)); // 두꺼운 테두리 설정
//        jbtnMember.setfocu

		parentPanel.add(layeredPane);
	}// createMemberButton

	private void createItemManageButton(JPanel parentPanel) {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

		jbtnItemManage = new JButton("상품 관리"); // 클래스 멤버 변수에 할당

		jbtnItemManage.setFont(buttonFont);
		jbtnItemManage.setBackground(defaultButtonColor);
		jbtnItemManage.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);

		itemCountLabel = new JLabel("등록 상품 0 개", SwingConstants.CENTER); // 클래스 멤버 변수에 할당
		itemCountLabel.setFont(countFont);
		itemCountLabel.setBounds(0, 50, BUTTON_WIDTH, BUTTON_HEIGHT);
		itemCountLabel.setOpaque(false);
		
		itemManagementImageLabel = new JLabel(getImage("/resources/images/item_management_icon.png"));
		itemManagementImageLabel.setBounds(0, -80, BUTTON_WIDTH, BUTTON_HEIGHT);

        
        layeredPane.add(itemManagementImageLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(jbtnItemManage, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(itemCountLabel, JLayeredPane.PALETTE_LAYER);

		parentPanel.add(layeredPane);
	}// createItemManageButton

	private void createFixManageButton(JPanel parentPanel) {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

		jbtnFixManage = new JButton("정비 관리"); // 클래스 멤버 변수에 할당
		jbtnFixManage.setFont(buttonFont);
		jbtnFixManage.setBackground(defaultButtonColor);
		jbtnFixManage.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);

		fixCountLabel = new JLabel("접수 완료 0 건", SwingConstants.CENTER); // 초기화 추가
		fixCountLabel.setFont(countFont);
		fixCountLabel.setBounds(0, 50, BUTTON_WIDTH, BUTTON_HEIGHT);
		fixCountLabel.setOpaque(false);

		fixingCountLabel = new JLabel("정비 중 0 건", SwingConstants.CENTER); // 초기화 추가
		fixingCountLabel.setFont(countFont);
		fixingCountLabel.setBounds(0, 70, BUTTON_WIDTH, BUTTON_HEIGHT);
		fixingCountLabel.setOpaque(false);

		fixOkCountLabel = new JLabel("정비 완료 0 건", SwingConstants.CENTER);
		fixOkCountLabel.setFont(countFont);
		fixOkCountLabel.setBounds(0, 90, BUTTON_WIDTH, BUTTON_HEIGHT);
		fixOkCountLabel.setOpaque(false);
		
		fixImageLabel = new JLabel(getImage("/resources/images/fix_icon.png"));
		fixImageLabel.setBounds(0, -80, BUTTON_WIDTH, BUTTON_HEIGHT);

        
        layeredPane.add(fixImageLabel, JLayeredPane.PALETTE_LAYER);
		
		
		layeredPane.add(jbtnFixManage, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(fixCountLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(fixingCountLabel, JLayeredPane.PALETTE_LAYER);
		layeredPane.add(fixOkCountLabel, JLayeredPane.PALETTE_LAYER);

		parentPanel.add(layeredPane);
	}// createFixManageButton

	private void createSalesButton(JPanel parentPanel) {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

		jbtnSales = new JButton("매출 현황"); // 클래스 멤버 변수에 할당
		jbtnSales.setFont(buttonFont);
		jbtnSales.setBackground(defaultButtonColor);
		jbtnSales.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);

		salesCountLabel = new JLabel("일일 매출 총 0 원", SwingConstants.CENTER); // 클래스 멤버 변수에 할당
		salesCountLabel.setFont(countFont);
		salesCountLabel.setBounds(0, 50, BUTTON_WIDTH, BUTTON_HEIGHT);
		salesCountLabel.setOpaque(false);
		
		salesImageLabel = new JLabel(getImage("/resources/images/sales_icon.png"));
		salesImageLabel.setBounds(0, -80, BUTTON_WIDTH, BUTTON_HEIGHT);

        
        layeredPane.add(salesImageLabel, JLayeredPane.PALETTE_LAYER);

		layeredPane.add(jbtnSales, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(salesCountLabel, JLayeredPane.PALETTE_LAYER);

		parentPanel.add(layeredPane);
	}// createSalesButton

	private void createInquiryButton(JPanel parentPanel) {
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

		jbtnInquiry = new JButton("문의 관리"); // 클래스 멤버 변수에 할당
		jbtnInquiry.setFont(buttonFont);
		jbtnInquiry.setBackground(defaultButtonColor);
		jbtnInquiry.setBounds(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);

		inquiryCountLabel = new JLabel("답변 대기 0 건", SwingConstants.CENTER); // 클래스 멤버 변수에 할당
		inquiryCountLabel.setFont(countFont);
		inquiryCountLabel.setBounds(0, 50, BUTTON_WIDTH, BUTTON_HEIGHT);
		inquiryCountLabel.setOpaque(false);
		
		
		inquiryImageLabel = new JLabel(getImage("/resources/images/inquiry.png"));
		inquiryImageLabel.setBounds(0, -80, BUTTON_WIDTH, BUTTON_HEIGHT);

        
        layeredPane.add(inquiryImageLabel, JLayeredPane.PALETTE_LAYER);

		layeredPane.add(jbtnInquiry, JLayeredPane.DEFAULT_LAYER);
		layeredPane.add(inquiryCountLabel, JLayeredPane.PALETTE_LAYER);

		parentPanel.add(layeredPane);
	}// createInquiryButton

	private void displayMemberCount() {
		AdminMainDAO model = new AdminMainDAO();
		try {
			int count = model.getMemberCount();
			if (memberCountLabel != null) {
				memberCountLabel.setText("회원 " + count + " 명");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (memberCountLabel != null) {
				memberCountLabel.setText("회원 수 오류");
			}
		}
	}// displayMemberCount

	private void displayItemCount() {
		AdminMainDAO model = new AdminMainDAO();
		try {
			int count = model.getItemCount();
			if (itemCountLabel != null) {
				itemCountLabel.setText("등록 상품 " + count + " 개");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (itemCountLabel != null) {
				itemCountLabel.setText("상품 오류");
			}
		}
	}// displayItemCount

	private void displayFixCounts() { // 메소드 이름 변경 (복수형)
		AdminMainDAO model = new AdminMainDAO();
		try {
			int[] counts = model.getFixCounts();
			if (fixCountLabel != null) {
				fixCountLabel.setText("접수 완료 " + counts[0] + " 건");
			}
			if (fixingCountLabel != null) {
				fixingCountLabel.setText("정비 중 " + counts[1] + " 건");
			}
			if (fixOkCountLabel != null) {
				fixOkCountLabel.setText("정비 완료 " + counts[2] + " 건");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (fixCountLabel != null) {
				fixCountLabel.setText("정비 오류");
			}
			if (fixingCountLabel != null) {
				fixingCountLabel.setText("정비 오류");
			}
			if (fixOkCountLabel != null) {
				fixOkCountLabel.setText("정비 오류");
			}
		}
	}// displayFixCounts

	private void displaySalesCount() {
		AdminMainDAO model = new AdminMainDAO();
		try {
			int count = model.getSalesCount();
			if (salesCountLabel != null) {
				salesCountLabel.setText("일일 매출 총 " + count + " 원");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (salesCountLabel != null) {
				salesCountLabel.setText("매출 오류");
			}
		}

	}//displaySalesCount

	private void displayInquiryCount() {
		AdminMainDAO model = new AdminMainDAO();
		try {
			int count = model.getInquiryCount();
			if (inquiryCountLabel != null) {
				inquiryCountLabel.setText("답변 대기 " + count + " 건");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (inquiryCountLabel != null) {
				inquiryCountLabel.setText("답변 오류");
			}
		}
	}// displayInquiryCount
	
	private ImageIcon getImage(String imagePath) {
	ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));  // 이미지 경로 설정
        
    // 이미지 크기 조정 ( 100x100)
    Image image = imageIcon.getImage();
    Image resizedImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);  // 이미지 크기 조정
    ImageIcon resizedIcon = new ImageIcon(resizedImage);  // 크기 조정된 이미지 아이콘 생성
    return resizedIcon;
	}

	public static void main(String[] args) {
		new AdminMainView();
	}

	public JButton getJbtnMember() {
		return jbtnMember;
	}

	public JButton getJbtnItemManage() {
		return jbtnItemManage;
	}

	public JButton getJbtnFixManage() {
		return jbtnFixManage;
	}

	public JButton getJbtnSales() {
		return jbtnSales;
	}

	public JButton getJbtnInquiry() {
		return jbtnInquiry;
	}

	public void closeAdminMainView() {
		dispose();
	}
}