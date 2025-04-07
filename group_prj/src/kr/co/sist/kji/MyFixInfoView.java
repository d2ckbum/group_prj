package kr.co.sist.kji;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class MyFixInfoView extends JFrame {

	private String id;
	private JButton logoutBtn;
	private JButton confirmBtn;

	public MyFixInfoView(String id) {
		this.id = id;
		
		setTitle("내 정비 목록");
		setSize(1080, 700);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 상단 타이틀
		JLabel shopLabel = new JLabel("쌍용 엔진오일 샵", SwingConstants.CENTER);
		shopLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		shopLabel.setBounds(0, 0, 1080, 60);
		add(shopLabel);

		// 오른쪽 상단
		JLabel welcomeLabel = new JLabel("000님 어서옵서예!");
		welcomeLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		welcomeLabel.setBounds(850, 20, 200, 30);
		add(welcomeLabel);

		logoutBtn = new JButton("로그아웃");
		logoutBtn.setBounds(970, 20, 80, 25);
		add(logoutBtn);

		// 정비 목록 타이틀
		JLabel titleLabel = new JLabel("내 정비 목록");
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		titleLabel.setBounds(150, 250, 200, 30);
		add(titleLabel);

		// 구분선
		JSeparator separator = new JSeparator();
		separator.setBounds(150, 280, 780, 1);
		add(separator);

		// JTable 정비 목록
		String[] columnNames = { "이미지", "상품", "접수번호", "견적금액", "정비 현황" };
		Object[][] data = { { "이미지", "상품명", "20250322", "90,000 원", "정비 중" },
				{ "이미지", "상품명", "20250222", "30,000 원", "정비 완료" }, { "이미지", "상품명", "20241212", "30,000 원", "정비 완료" } };

		DefaultTableModel model = new DefaultTableModel(data, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // 수정 불가
			}
		};

		JTable fixTable = new JTable(model);
		fixTable.setRowHeight(60);
		fixTable.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		fixTable.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane(fixTable);
		scrollPane.setBounds(150, 300, 780, 200);
		add(scrollPane);

		// 총 금액 라벨
		JLabel totalText = new JLabel("총 견적 금액");
		totalText.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		totalText.setBounds(700, 360, 120, 30);
		add(totalText);

		JLabel totalPriceLabel = new JLabel("150,000 원");
		totalPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		totalPriceLabel.setBounds(820, 360, 150, 30);
		add(totalPriceLabel);

		// 확인 버튼
		confirmBtn = new JButton("확인");
		confirmBtn.setBounds(490, 550, 100, 35);
		confirmBtn.setBackground(new Color(33, 102, 172));
		confirmBtn.setForeground(Color.WHITE);
		confirmBtn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		add(confirmBtn);

		MyFixInfoEvt mfie =new MyFixInfoEvt(this);
		confirmBtn.addActionListener(mfie);
		
		
		setVisible(true);
	}//MyFixInfoView

	
	
	
	
	public String getId() {
		return id;
	}





	public JButton getLogoutBtn() {
		return logoutBtn;
	}





	public JButton getConfirmBtn() {
		return confirmBtn;
	}





	public static void main(String[] args) {
		new MyFixInfoView("kkk");
	}
}
