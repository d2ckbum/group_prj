package kr.co.sist.hjs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import kr.co.sist.kji.MemberDataDTO;
import kr.co.sist.kji.MemberService;
import kr.co.sist.kji.MemberVO;

public class MemberPane extends JPanel {
	private DefaultTableModel tableModel;
	private JTable memberTable;
	private MemberService memberService;
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private List<MemberVO> allMemberList; // 전체 회원 목록 저장

	public MemberPane() {
		setLayout(new BorderLayout());
		setSize(new Dimension(1200, 700));
//		setBorder(BorderFactory.createLineBorder(Color.RED));

		mainPanel = new JPanel(null);
		add(mainPanel, BorderLayout.CENTER);

		// 테이블 모델 생성 (편집 불가능하도록 설정)
		String[] columnNames = { "No.", "회원번호", "아이디", "이름", "이메일", "전화번호", "생성일", "탈퇴 Y/N" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // 편집 불가능하도록 설정
			}
		};

		// 테이블 생성
		memberTable = new JTable(tableModel);
		memberTable.setRowHeight(30);

		// 헤더 스타일
		JTableHeader tableHeader = memberTable.getTableHeader();
		tableHeader.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		// 셀 내용 가운데 정렬
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < memberTable.getColumnCount(); i++) {
			memberTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// 컬럼 이동 불가 설정
		memberTable.getTableHeader().setReorderingAllowed(false);
		// 단일 행 선택 모델 설정
		memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 스크롤 페인 생성 및 위치 설정
		scrollPane = new JScrollPane(memberTable);
		scrollPane.setBounds(100, 0, 1000, 600);

		// GridBagConstraints 설정 및 스크롤 페인 추가
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		mainPanel.add(scrollPane, gbc);

		// MemberService 객체 생성
		memberService = new MemberService();

		// 회원 리스트를 테이블에 표시 및 전체 목록 저장
		loadMemberList();

		// **테이블 클릭 이벤트 처리 (상세 페이지 이동)**
		memberTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int selectedRow = memberTable.getSelectedRow();
					if (selectedRow != -1) {
						// 선택된 행에서 회원 ID 가져오기
						String selectedMemId = (String) tableModel.getValueAt(selectedRow, 2);
						try {
							// MemberService를 통해 MemberDataDTO 가져오기
							MemberDataDTO memberData = memberService.searchOneMemberData(selectedMemId);
							if (memberData != null) {
								// MemberDetailView 생성 및 MemberDataDTO 전달
								new MemberDetailView(MemberPane.this, memberData);
							} else {
								JOptionPane.showMessageDialog(MemberPane.this, "해당 회원 정보를 찾을 수 없습니다.", "알림",
										JOptionPane.INFORMATION_MESSAGE);
							} // end if
						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(MemberPane.this, "데이터베이스 오류 발생: " + ex.getMessage(), "오류",
									JOptionPane.ERROR_MESSAGE);
						} // end catch
					} // end if
				} // end if
			}// mouseClicked
		});

		setVisible(true);
	}// MemberPane

	private void loadMemberList() {
	    // 기존 테이블 데이터 초기화
	    if (tableModel != null) {
	        tableModel.setRowCount(0);
	    } // end if

	    try {
	        // MemberService를 통해 회원 목록 가져오기
	        allMemberList = memberService.searchAllMember();

	        // 활성 회원 (memFlag = 'n') 목록을 먼저 추가
	        if (allMemberList != null && !allMemberList.isEmpty()) {
	            int rowNumber = 1; // 테이블에 표시될 순번
	            for (MemberVO member : allMemberList) {
	                if ("n".equalsIgnoreCase(member.getMemFlag())) {
	                    Object[] rowData = { rowNumber++, // No.
	                            member.getMemNum(), member.getMemId(), member.getMemName(), member.getMemEmail(),
	                            member.getMemTell(), member.getMemRegDate(), member.getMemFlag() };
	                    tableModel.addRow(rowData);
	                }
	            }

	            // 탈퇴 회원 (memFlag = 'y') 목록을 추가
	            for (MemberVO member : allMemberList) {
	                if ("y".equalsIgnoreCase(member.getMemFlag())) {
	                    Object[] rowData = { rowNumber++, // No. (계속 증가)
	                            member.getMemNum(), member.getMemId(), member.getMemName(), member.getMemEmail(),
	                            member.getMemTell(), member.getMemRegDate(), member.getMemFlag() };
	                    tableModel.addRow(rowData);
	                }
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "회원 정보가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "데이터베이스 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
	    }
	}
}// class