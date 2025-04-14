package kr.co.sist.khb.view;

import kr.co.sist.khb.event.SalesViewEvt;
import kr.co.sist.khb.vo.SalesSumVO;
import kr.co.sist.khb.dao.SalesDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.sql.Date; 
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class SalesView extends JPanel {

    private JLabel lblSelectedDate;
    private JButton btnShowCalendar;
    private JTable salesTable;
    private DefaultTableModel dtmSales;
    private JLabel lblDailyTotalSales;
    private JLabel lblDailyNetProfit;
    private NumberFormat numFormat;

    public SalesView() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10)); 

        numFormat = NumberFormat.getInstance();
        initUI();

        SalesViewEvt sve = new SalesViewEvt(this);
        btnShowCalendar.addActionListener(sve);

 
        fetchAndDisplayData(new java.sql.Date(System.currentTimeMillis()));
    }

    private void initUI() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(1000, 650)); 

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        northPanel.add(new JLabel("조회 날짜 : "));
        lblSelectedDate = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        btnShowCalendar = new JButton("달력");
        northPanel.add(lblSelectedDate); northPanel.add(btnShowCalendar);
        contentPanel.add(northPanel, BorderLayout.NORTH); 
        
        String[] columnNames = {"상품번호", "상품명", "수량", "순수익", "총 매출"};
        dtmSales = new DefaultTableModel(columnNames, 0) { 
        	 @Override
             public boolean isCellEditable(int row, int column) {
                 return false; // 모든 셀을 수정 불가능하게 설정
             }
        };
        salesTable = new JTable(dtmSales);
        
        JTableHeader tableHeader = salesTable.getTableHeader(); 
        tableHeader.setFont(new Font("맑은 고딕", Font.BOLD, 15)); 
        salesTable.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); 
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        for (int i = 0; i < salesTable.getColumnCount(); i++) {
        	salesTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer); 
        }
        salesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        salesTable.setGridColor(Color.BLACK); 
        tableHeader.setReorderingAllowed(false);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 30)); 
        tableHeader.setBackground(new Color(222, 222, 222));

        JScrollPane tableScrollPane = new JScrollPane(salesTable);
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        contentPanel.add(tableScrollPane, BorderLayout.CENTER); 

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        // southPanel.setPreferredSize(new Dimension(0, 40)); // 필요 시 높이 지정
        // southPanel.setBackground(Color.YELLOW); // 디버깅용
        lblDailyTotalSales = new JLabel("일일 매출 : 0 원");
        lblDailyNetProfit = new JLabel("총 순수익 : 0 원");
        Font summaryFont = new Font("맑은 고딕", Font.BOLD, 16); 
        lblDailyTotalSales.setFont(summaryFont); 
        lblDailyNetProfit.setFont(summaryFont);
        southPanel.add(lblDailyNetProfit); 
        southPanel.add(Box.createHorizontalStrut(20)); 
        southPanel.add(lblDailyTotalSales);
        contentPanel.add(southPanel, BorderLayout.SOUTH); 

        add(contentPanel); 
    }


    /**
     * 주어진 날짜의 매출 데이터를 조회하여 테이블과 요약 레이블을 업데이트합니다.
     * (이 메소드는 SalesViewEvt에서 호출됩니다)
     */
    public void fetchAndDisplayData(java.sql.Date date) {
        System.out.println("[SalesView] 데이터 조회/표시 시작: " + date);
        try {
            SalesDAO sDAO = SalesDAO.getInstance();

            List<SalesSumVO> summaryList = sDAO.getDailySalesSummaryByItem(date);

            int totalGrossSales = sDAO.getDailyTotalSales(date, false); // false: sales_total 합계 -> '일일 매출'
            int totalNetProfit = sDAO.getDailyTotalSales(date, true);   // true: sales_real_total 합계 -> '총 순수익'

            updateSalesTable(summaryList);
            
            updateSummaryLabels(totalGrossSales, totalNetProfit);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "데이터 조회 중 오류 발생: " + e.getMessage(), "조회 오류", JOptionPane.ERROR_MESSAGE);

            dtmSales.setRowCount(0);
            updateSummaryLabels(0, 0);
        } catch (Exception e) {
             e.printStackTrace();
             JOptionPane.showMessageDialog(this, "데이터 처리 중 오류 발생: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateSalesTable(List<SalesSumVO> summaryList) {
        dtmSales.setRowCount(0); // 테이블 초기화
        if (summaryList == null || summaryList.isEmpty()) {
             System.out.println("[SalesView] 표시할 테이블 데이터 없음");
             return;
        }
        for (SalesSumVO vo : summaryList) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(vo.getItemNum());
            rowData.add(vo.getItemName());
            rowData.add(vo.getQuantity());
            rowData.add(numFormat.format(vo.getRealTotal()) + " 원"); 
            rowData.add(numFormat.format(vo.getTotal()) + " 원");    
            dtmSales.addRow(rowData);
        }
         System.out.println("[SalesView] 테이블 업데이트 완료 (" + summaryList.size() + "건)");
    }
    public void updateSummaryLabels(int totalGross, int totalNet) {
        lblDailyTotalSales.setText("일일 매출 : " + numFormat.format(totalGross) + " 원");
        lblDailyNetProfit.setText("총 순수익 : " + numFormat.format(totalNet) + " 원");
         System.out.println("[SalesView] 요약 레이블 업데이트 완료");
    }

    public JLabel getLblSelectedDate() { 
    	return lblSelectedDate; 
    }
    public JButton getBtnShowCalendar() { 
    	return btnShowCalendar;
    }

//     // --- main 메소드 (테스트용) ---
//     public static void main(String[] args) {
//         try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception e) { e.printStackTrace(); }
//         JFrame frame = new JFrame("매출 조회 테스트");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.add(new SalesView()); // SalesView 패널 추가
//         frame.pack();
//         frame.setLocationRelativeTo(null);
//         frame.setVisible(true);
//    }
}