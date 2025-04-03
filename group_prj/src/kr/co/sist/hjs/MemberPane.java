package kr.co.sist.hjs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
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

import kr.co.sist.kji.MemberService;
import kr.co.sist.kji.MemberVO;

public class MemberPane extends JPanel {
    private DefaultTableModel dtm;
    private JTable memberTable;
    private DefaultTableModel tableModel;
    private MemberService memberService;
    private JPanel mainPanel;
    private JScrollPane scrollPane; // 스크롤 페인 멤버 변수로 선언

    public MemberPane() {
        setLayout(new BorderLayout());
        setSize(new Dimension(1200, 700));
        setBorder(BorderFactory.createLineBorder(Color.RED));

        mainPanel = new JPanel(null); // GridBagLayout 사용
        add(mainPanel, BorderLayout.CENTER); // 메인 패널을 중앙에 배치

        // 테이블 모델 생성
        String[] columnNames = {"No.", "회원번호", "아이디", "이름", "이메일", "전화번호", "생성일", "탈퇴 Y/N"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // 테이블 생성
        memberTable = new JTable(tableModel);
        memberTable.setRowHeight(30);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // 모든 열에 대해 중앙 정렬 적용
        for (int i = 0; i < memberTable.getColumnCount(); i++) {
        	memberTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //컬럼 고정
        memberTable.getTableHeader().setReorderingAllowed(false);
        //리스트 단일 선택
        memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // 스크롤 페인 생성
        scrollPane = new JScrollPane(memberTable);
//        scrollPane.setPreferredSize(new Dimension(1000, 600)); // 스크롤 페인 크기 설정
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

        // 회원 리스트를 테이블에 표시
        loadMemberList();

        setVisible(true);
    }//MemberPane

    private void loadMemberList() {
        // 기존 테이블 데이터 초기화
        if (tableModel != null) {
            tableModel.setRowCount(0);
        }

        try {
            // MemberService를 통해 회원 목록 가져오기
            List<MemberVO> memberList = memberService.searchAllMember();

            // 가져온 회원 목록을 테이블 모델에 추가
            if (memberList != null && !memberList.isEmpty()) {
                for (int i = 0; i < memberList.size(); i++) {
                    MemberVO member = memberList.get(i);
                    Object[] rowData = {
                            i + 1, // No.
                            member.getMemNum(),
                            member.getMemId(),
                            member.getMemName(),
                            member.getMemEmail(),
                            member.getMemTell(),
                            member.getMemRegDate(),
                            member.getMemFlag(),
                    };
                    tableModel.addRow(rowData);
                }
            } else {
                JOptionPane.showMessageDialog(this, "회원 정보가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터베이스 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }//loadMemberList
}//class