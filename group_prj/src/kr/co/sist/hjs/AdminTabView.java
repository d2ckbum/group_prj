package kr.co.sist.hjs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import kr.co.sist.hjy.FixPanel;

public class AdminTabView extends JFrame {
	private CardLayout cl;
	private JPanel mainPanel;
	private MemberPane memp;
	private ItemPane itemp;
	private FixPanel fixp;
	private SalesPane salesp;
	private InquiryPane inquiryp;
	private JButton jbtnLogout;
	private JButton jbtnMember, jbtnItemManage, jbtnFixManage, jbtnSales, jbtnInquiryManage;
	private JButton[] buttons;
	private JLabel jlblAdminTitle, jlblWelcomeAdmin;
	private Dimension defaultSize = new Dimension(1200, 900); // 기본 창 크기
	private AdminTabViewEvt ate; // 이벤트 처리 클래스
	private Dimension buttonSize = new Dimension(180, 50); // 원하는 버튼 크기 설정

	public AdminTabView() {

		// 이벤트 처리 객체 생성
		ate = new AdminTabViewEvt(this);

		// 상단 패널 생성 및 컴포넌트 추가
		JPanel jpTop = new JPanel(new BorderLayout());
		jlblAdminTitle = new JLabel("관리자 페이지", SwingConstants.CENTER);
		jlblAdminTitle.setFont(new Font("맑은 고딕", Font.BOLD, 24)); // 타이틀 폰트 설정
		jpTop.add("North", jlblAdminTitle);

		JPanel jpTopRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jlblWelcomeAdmin = new JLabel("관리자님 어서오세요");
		jlblWelcomeAdmin.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jbtnLogout = new JButton("로그아웃");
		jbtnLogout.setPreferredSize(new Dimension(100, 40));
		jpTopRight.add(jlblWelcomeAdmin);
		jpTopRight.add(jbtnLogout);
		jpTop.add("East", jpTopRight);

		jpTop.setBorder(new EmptyBorder(0, 0, 0, 76)); // 상단 여백 추가

		// 메뉴 버튼 패널
		JPanel jpNorth = new JPanel();
		jbtnMember = new JButton("회원현황");
		jbtnMember.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jbtnMember.setBackground(new Color(217, 217, 217));
		jbtnMember.setPreferredSize(buttonSize);

		jbtnItemManage = new JButton("상품관리");
		jbtnItemManage.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jbtnItemManage.setBackground(new Color(217, 217, 217));
		jbtnItemManage.setPreferredSize(buttonSize);

		jbtnFixManage = new JButton("정비관리");
		jbtnFixManage.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jbtnFixManage.setBackground(new Color(217, 217, 217));
		jbtnFixManage.setPreferredSize(buttonSize);

		jbtnSales = new JButton("매출현황");
		jbtnSales.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jbtnSales.setBackground(new Color(217, 217, 217));
		jbtnSales.setPreferredSize(buttonSize);

		jbtnInquiryManage = new JButton("문의관리");
		jbtnInquiryManage.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		jbtnInquiryManage.setBackground(new Color(217, 217, 217));
		jbtnInquiryManage.setPreferredSize(buttonSize);

		buttons = new JButton[] { jbtnMember, jbtnItemManage, jbtnFixManage, jbtnSales, jbtnInquiryManage };

		jpNorth.add(jbtnMember);
		jpNorth.add(jbtnItemManage);
		jpNorth.add(jbtnFixManage);
		jpNorth.add(jbtnSales);
		jpNorth.add(jbtnInquiryManage);

		// CardLayout을 위한 메인 패널
		cl = new CardLayout();
		mainPanel = new JPanel(cl);
		mainPanel.setPreferredSize(new Dimension(1200, 700));

		memp = new MemberPane();
		itemp = new ItemPane();
		fixp = new FixPanel();
		salesp = new SalesPane();
		inquiryp = new InquiryPane();

		mainPanel.add(memp, "memp");
		mainPanel.add(itemp, "itemp");
		mainPanel.add(fixp, "fixp");
		mainPanel.add(salesp, "salesp");
		mainPanel.add(inquiryp, "inquiryp");

		add("North", jpTop);
		add("Center", jpNorth);
		add("South", mainPanel);

		setSize(defaultSize); // 초기 창 크기 설정
		setVisible(true);
		setResizable(false); // 창 크기 고정
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 이벤트 등록
		jbtnMember.addActionListener(ate);
		jbtnItemManage.addActionListener(ate);
		jbtnFixManage.addActionListener(ate);
		jbtnSales.addActionListener(ate);
		jbtnInquiryManage.addActionListener(ate);
		jbtnLogout.addActionListener(ate); // 로그아웃 버튼에도 이벤트 등록 (필요한 경우)

		// 클릭 시 생기는 테두리 없애기
		jbtnMember.setFocusPainted(false);
		jbtnItemManage.setFocusPainted(false);
		jbtnFixManage.setFocusPainted(false);
		jbtnSales.setFocusPainted(false);
		jbtnInquiryManage.setFocusPainted(false);

		// 로그아웃 버튼 기능 추가
		jbtnLogout.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
			dispose();
		});
	}// AdminTabView

	public JButton getJbtnLogout() {
		return jbtnLogout;
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

	public JButton getJbtnInquiryManage() {
		return jbtnInquiryManage;
	}

	public CardLayout getCl() {
		return cl;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public static void main(String[] args) {
		new AdminTabView();
	}
}
