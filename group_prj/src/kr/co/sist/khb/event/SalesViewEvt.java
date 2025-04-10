package kr.co.sist.khb.event; 

import kr.co.sist.khb.dao.SalesDAO;
import kr.co.sist.khb.view.SalesCalView;
import kr.co.sist.khb.view.SalesView;
import kr.co.sist.khb.vo.SalesSumVO;

import javax.swing.*;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SalesViewEvt implements ActionListener {

    private SalesView salesView; 

    public SalesViewEvt(SalesView salesView) {
        this.salesView = salesView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salesView.getBtnShowCalendar()) {
            showCalendarAndFetchData();
        }
    }

    /**
     * SalesCalView 다이얼로그를 보여주고, 날짜가 선택되면 SalesView의 데이터 로딩/업데이트를 트리거합니다.
     */
    private void showCalendarAndFetchData() {
        Frame owner = (Frame) SwingUtilities.getWindowAncestor(salesView);
        SalesCalView calendarDialog = new SalesCalView(owner);
        calendarDialog.setVisible(true); 

        java.util.Date selectedUtilDate = calendarDialog.getSelectedDate();

        if (selectedUtilDate != null) { 
            java.sql.Date selectedSqlDate = new java.sql.Date(selectedUtilDate.getTime());

            salesView.getLblSelectedDate().setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedUtilDate));

            salesView.fetchAndDisplayData(selectedSqlDate);

        } else {
            System.out.println("[SalesViewEvt] 달력에서 날짜가 선택되지 않았습니다.");
        }
    }
    
    /**
     * 주어진 날짜의 매출 데이터를 SalesDAO를 통해 조회하고 SalesView 화면을 업데이트합니다.
     * @param date 조회할 날짜 (java.sql.Date)
     */
    private void fetchAndDisplaySalesData(java.sql.Date date) {
        System.out.println("[SalesViewEvt] fetchAndDisplaySalesData 호출됨. 조회 날짜: " + date);
        try {
            // DAO 인스턴스 얻기
            SalesDAO sDAO = SalesDAO.getInstance();

            // 1. 상품별 집계 데이터 조회 (SalesSumVO 사용)
            List<SalesSumVO> summaryList = sDAO.getDailySalesSummaryByItem(date);

            // 2. 하단 요약 정보 조회 (합계 기준 확인 필요)
            // false: sales_total 합계 ('일일 매출'), true: sales_real_total 합계 ('총 순수익')
            int totalGrossSales = sDAO.getDailyTotalSales(date, false);
            int totalNetProfit = sDAO.getDailyTotalSales(date, true);

            // 3. SalesView의 UI 업데이트 메소드 호출
            // (SalesView에 updateSalesTable, updateSummaryLabels 메소드가 구현되어 있어야 함)
            salesView.updateSalesTable(summaryList); // 테이블 업데이트
            salesView.updateSummaryLabels(totalGrossSales, totalNetProfit); // 하단 레이블 업데이트

        } catch (SQLException e) {
            // 데이터베이스 오류 처리
            e.printStackTrace();
            JOptionPane.showMessageDialog(salesView, "데이터 조회 중 오류 발생: " + e.getMessage(), "조회 오류", JOptionPane.ERROR_MESSAGE);
            // 오류 발생 시 화면 초기화 (선택 사항)
            // salesView.updateSalesTable(new ArrayList<>());
            // salesView.updateSummaryLabels(0, 0);
        } catch (Exception e) {
            // 기타 예외 처리
             e.printStackTrace();
             JOptionPane.showMessageDialog(salesView, "데이터 처리 중 오류 발생: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

} // class SalesViewEvt