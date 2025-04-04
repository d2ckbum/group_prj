package kr.co.sist.hjs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

@SuppressWarnings("serial")
public class ItemPane extends JPanel {

    private DefaultTableModel dtm;
    private JButton registerButton;
    private JButton updateButton;
    private JTable item_management_table;
    private JPanel mainPanel; // 모든 UI를 담을 메인 패널

    public ItemPane() {
        // JFrame 기본 설정
    	setSize(1200,700);
        setBorder(BorderFactory.createLineBorder(Color.RED));

        // 메인 패널 생성 (모든 UI가 들어갈 컨테이너)
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel); // JFrame에 패널 추가

        // UI 초기화 메서드 호출
        initializeUI();

        // 프레임을 보이도록 설정
        setVisible(true);
    }

    private void initializeUI() {
        // 이벤트 핸들러 객체 생성

        // 테이블 모델 생성 (컬럼명 설정)
        String[] columns = {"상품번호", "상품명", "차종", "재고", "판매가", "등록일"};
        dtm = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 모든 셀을 수정 불가능하게 설정
            }
        };

        // JTable 생성
        item_management_table = new JTable(dtm);
        item_management_table.setRowHeight(25);
        item_management_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        item_management_table.getTableHeader().setReorderingAllowed(false);

        // JScrollPane 생성 및 크기 설정
        JScrollPane scrollPane = new JScrollPane(item_management_table);
        scrollPane.setPreferredSize(new Dimension(1000, 600));
        item_management_table.setPreferredScrollableViewportSize(new Dimension(800, 250));

        // 중앙 정렬을 위한 패널 설정
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setSize(900, 700);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(scrollPane, gbc);

        // 테이블을 중앙에 배치
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // 상품 등록 버튼
        registerButton = new JButton("상품 등록");

        // 수정 버튼
        updateButton = new JButton("수정");

        // 버튼 패널에 버튼 추가
        buttonPanel.add(registerButton);
        buttonPanel.add(updateButton);

        // 버튼 패널을 하단에 배치
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JTable getItem_management_table() {
        return item_management_table;
    }

}

