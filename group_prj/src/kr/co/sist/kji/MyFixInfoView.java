package kr.co.sist.kji;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class MyFixInfoView extends JDialog {

	private DefaultTableModel tableModel;
	private MemberService memberService = new MemberService();
	private List<FixPanelVO> allList; // 전체 회원 목록 저장
	private String id;
	private JButton confirmBtn;

	public MyFixInfoView(Frame owner, String id) {
		super(owner, "내 정비 목록", true); // 모달 설정
		this.id = id;

		setSize(1080, 700);
		setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		// 상단 타이틀
		JLabel shopLabel = new JLabel("쌍용 엔진오일 샵", SwingConstants.CENTER);
		shopLabel.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		shopLabel.setBounds(0, 0, 1080, 60);
		add(shopLabel);

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
		String[] columnNames = { "No", "상품", "접수번호", "견적금액", "정비 현황" };

		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // 수정 불가
			}
		};
		loadMemberList();

		JTable fixTable = new JTable(tableModel);
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

		MyFixInfoEvt mfie = new MyFixInfoEvt(this);
		confirmBtn.addActionListener(mfie);

		setLocationRelativeTo(owner);
		setVisible(true);
	} // MyFixInfoView

	private void loadMemberList() {
		String status = "";
		if (tableModel != null) {
			tableModel.setRowCount(0);
		}
		try {
			allList = memberService.searchAllFixinfo(id);
			if (allList != null && !allList.isEmpty()) {
				for (int i = 0; i < allList.size(); i++) {
					FixPanelVO fix = allList.get(i);
					if (fix.getFixStatus().equals("1")) {
						status = "접수완료";
					} else if (fix.getFixStatus().equals("2")) {
						status = "정비중";
					} else {
						status = "정비완료";
					}
					Object[] rowData = { i + 1, fix.getItemName(), fix.getFixNum(), fix.getTotal(), status };
					tableModel.addRow(rowData);
				}
			} else {
				JOptionPane.showMessageDialog(this, "정비 정보가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "데이터베이스 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	public String getId() {
		return id;
	}

	public JButton getConfirmBtn() {
		return confirmBtn;
	}
}
