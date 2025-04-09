package kr.co.sist.pib;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.Iterator;

@SuppressWarnings("serial")
public class ItemManagementMainView extends JPanel {

    private DefaultTableModel dtm;
    private JButton registerButton;
    private JButton updateButton;
    private JTable item_management_table;
//    private JPanel mainPanel; // 모든 UI를 담을 메인 패널

    public ItemManagementMainView() {
        // JFrame 기본 설정

        // 메인 패널 생성 (모든 UI가 들어갈 컨테이너)
//        mainPanel = new JPanel(new BorderLayout());
//        add(mainPanel); // JFrame에 패널 추가
        setSize(1200,700);
        setLayout(new BorderLayout());

        // UI 초기화 메서드 호출
        initializeUI();
        // 프레임을 보이도록 설정
        setVisible(true);
        
        
//        JFrame testFrame = new JFrame();
//        testFrame.setTitle("상품 관리");
////        setTitle("상품 관리");
//        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        testFrame.setLayout(null);
//        testFrame.setSize(1200,900);
//        testFrame.add(this); // JFrame에 패널 추가
//        testFrame.setVisible(true);
//        System.out.println((JFrame)getRootPane().getParent() == testFrame);
    }

    private void initializeUI() {
        // 이벤트 핸들러 객체 생성
        ItemManagementMainEvent imEvt = new ItemManagementMainEvent(this);

        // 테이블 모델 생성 (컬럼명 설정)
        String[] columns = {"상품번호", "상품명", "차종", "재고", "원가", "판매가", "등록일"};
        dtm = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 모든 셀을 수정 불가능하게 설정
            }
        };
        
        setInitialTableData();

        
        // JTable 생성
        item_management_table = new JTable(dtm);
        item_management_table.setShowGrid(true);
        item_management_table.setFocusable(false);
        setColumnSize();
        JTableHeader tableHeader = item_management_table.getTableHeader();
        tableHeader.setFont(new Font("맑은 고딕", Font.BOLD, 15)); // 폰트 설정
        tableHeader.setBackground(new Color(217, 217, 217));
        item_management_table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // 모든 열에 대해 중앙 정렬 적용
        for (int i = 0; i < item_management_table.getColumnCount(); i++) {
            item_management_table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
//        item_management_table.setPreferredScrollableViewportSize(new Dimension(1000, 600));
        item_management_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        item_management_table.setBackground(new Color(240, 240, 240));
        
        item_management_table.setGridColor(Color.BLACK);
//        item_management_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        item_management_table.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(100, 100, 255), 1));
        tableHeader.setReorderingAllowed(false);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30));
        tableHeader.setBackground(new Color(222, 222, 222));
        
        

        // JScrollPane 생성 및 크기 설정
        JScrollPane scrollPane = new JScrollPane(item_management_table);
//        scrollPane.setPreferredSize(new Dimension(1000, 600));
        //바운드로 크기 처리
        scrollPane.setBounds(100, 0, 1000, 600);

        // 중앙 정렬을 위한 패널 설정
        JPanel centerPanel = new JPanel(null);
//        centerPanel.setBackground(Color.WHITE);
//        JPanel centerPanel = new JPanel(new GridBagLayout());
//        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setSize(1200, 700);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(scrollPane, gbc);
//        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // 테이블을 중앙에 배치
//        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBackground(Color.WHITE);
//        buttonPanel.setBorder(new LineBorder(Color.BLACK, 2));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // 상품 등록 버튼
        registerButton = new JButton("상품 등록");
        registerButton.setPreferredSize(new Dimension(90, 35));
        registerButton.setBackground(new Color(217,217,217));
        registerButton.addActionListener(imEvt);
        registerButton.setFocusPainted(false);

        // 수정 버튼
        updateButton = new JButton("수정");
        updateButton.setPreferredSize(new Dimension(70, 35));
        updateButton.setBackground(new Color(217,217,217));
        updateButton.addActionListener(imEvt);
        updateButton.setFocusPainted(false);

        // 버튼 패널에 버튼 추가
        buttonPanel.add(registerButton);
        buttonPanel.add(updateButton);

        // 버튼 패널을 하단에 배치
//        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void setColumnSize() {
    	item_management_table.getColumnModel().getColumn(0).setPreferredWidth(80); // "상품번호" 열 너비 
        item_management_table.getColumnModel().getColumn(1).setPreferredWidth(200); // "상품명" 열 너비
        item_management_table.getColumnModel().getColumn(2).setPreferredWidth(80); // "차종" 열 너비 
        item_management_table.getColumnModel().getColumn(3).setPreferredWidth(80); // "재고" 열 너비 
        item_management_table.getColumnModel().getColumn(4).setPreferredWidth(80); // "원가" 열 너비 
        item_management_table.getColumnModel().getColumn(5).setPreferredWidth(80); // "판매가" 열 너비 
        item_management_table.getColumnModel().getColumn(6).setPreferredWidth(150); // "등록일" 열 너비 
    }
    
    
    public void setInitialTableData() {
    	var ls_imVO = new ItemManagementService().searchAllMember();
    	dtm.setRowCount(0);
    	DecimalFormat df = new DecimalFormat("#,###");
    	
    	Iterator<ItemManagementVO> it = ls_imVO.iterator();
    	while(it.hasNext()) {
    		var tempImVO = it.next();
    		String[] row = {String.valueOf(tempImVO.getItem_num()), tempImVO.getItem_name(), tempImVO.getCar_type(), 
    				String.valueOf(tempImVO.getItem_stock()), df.format(tempImVO.getItem_cost()), 
    				df.format(tempImVO.getItem_price()), tempImVO.getItem_reg_date().toString()};
    		dtm.addRow(row);
    	}
    	
    	
    }
    
    

//    public JPanel getMainPanel() {
//        return mainPanel;
//    }

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
