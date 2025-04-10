package kr.co.sist.khb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.khb.DbConnection;
import kr.co.sist.khb.vo.OrderVO;
import kr.co.sist.khb.vo.SalesSumVO;

public class SalesDAO {

    private static SalesDAO instance;
    private DbConnection dbConn;

    private SalesDAO() {
        dbConn = DbConnection.getInstance();
    }

    public static SalesDAO getInstance() {
        if (instance == null) {
            instance = new SalesDAO();
        }
        return instance;
    }

    /**
     * OrderVO 정보와 전달받은 fixNum을 사용하여 SALES 테이블에 매출 정보를 삽입합니다.
     * 외부 Connection을 사용하며, Connection을 닫지 않습니다.
     */
    public int insertSales(OrderVO oVO, String fixNum, Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        int rowsAffected = 0;

        // 데이터 계산
        int salesTotal = oVO.getTotalPrice(); 
        int salesRealTotal = oVO.getTotalPrice() - oVO.getItemCost(); 

        try {
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO sales (sales_num, sales_total, sales_real_total, ");
            sql.append("item_name, item_num, fix_num) ");
            sql.append("VALUES (SALES_SEQ.NEXTVAL, ?, ?, ?, ?, ?)"); 
         
            pstmt = conn.prepareStatement(sql.toString());

            pstmt.setInt(1, salesTotal);
            pstmt.setInt(2, salesRealTotal);
            pstmt.setString(3, oVO.getItemName());
            pstmt.setInt(4, oVO.getItemNum());
            if (fixNum == null || fixNum.isEmpty()) {
                 pstmt.setNull(5, Types.VARCHAR); 
                 System.err.println("[SalesDAO] 경고: insertSales 호출 시 fixNum이 null 또는 비어있습니다.");
            } else {
                pstmt.setString(5, fixNum); 
            }

            rowsAffected = pstmt.executeUpdate();

        } finally {
            if (pstmt != null) { pstmt.close(); }
        }
        return rowsAffected;
    }


    /**
     * 특정 날짜(fix_reg_date 기준)에 대해 상품별 집계 정보 조회 (날짜 파라미터 처리 변경)
     */
    public List<SalesSumVO> getDailySalesSummaryByItem(java.sql.Date queryDate) throws SQLException {
        List<SalesSumVO> summaryList = new ArrayList<>();
        Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
        DbConnection dbCon = DbConnection.getInstance();

        // java.sql.Date -> 'YYYY-MM-DD' 형식의 문자열로 변환
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(queryDate);
        System.out.println("[SalesDAO] getDailySalesSummaryByItem 시작. 조회 날짜 문자열: " + dateString); // 로그 수정

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s.item_num, s.item_name, COUNT(*) AS quantity, ")
           .append("NVL(SUM(s.sales_real_total), 0) AS total_net_profit, ")
           .append("NVL(SUM(s.sales_total), 0) AS total_gross_sales ")
           .append("FROM sales s JOIN fix f ON s.fix_num = f.fix_num ");
        // --- WHERE 절 수정: TO_DATE 함수 사용 ---
        sql.append("WHERE f.fix_reg_date >= TO_DATE(?, 'YYYY-MM-DD') "); // <<<=== 수정됨
        sql.append("  AND f.fix_reg_date < TO_DATE(?, 'YYYY-MM-DD') + 1 "); // <<<=== 수정됨
        // ------------------------------------
        sql.append("GROUP BY s.item_num, s.item_name ORDER BY s.item_num");

        try {
            conn = dbCon.getConn();
            String finalSql = sql.toString();
             System.out.println("[SalesDAO] 실행될 SQL (TO_DATE 사용): " + finalSql); // 로그 수정
            pstmt = conn.prepareStatement(finalSql);

            // --- 파라미터 설정 수정: setDate 대신 setString 사용 ---
            pstmt.setString(1, dateString); // <<<=== 수정됨
            pstmt.setString(2, dateString); // <<<=== 수정됨
            // ------------------------------------------------

            System.out.println("[SalesDAO] executeQuery 실행 직전...");
            rs = pstmt.executeQuery();
            System.out.println("[SalesDAO] executeQuery 실행 완료.");

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                System.out.println("[SalesDAO] rs.next() == true. 데이터 처리 중...");

                SalesSumVO summaryVO = new SalesSumVO();

                // <<<=== 컬럼 인덱스 대신 이름 또는 별칭 사용 ===>>>
                summaryVO.setItemNum(rs.getInt("item_num"));          // SELECT s.item_num
                summaryVO.setItemName(rs.getString("item_name"));      // SELECT s.item_name
                summaryVO.setQuantity(rs.getInt("quantity"));          // SELECT COUNT(*) AS quantity
                summaryVO.setRealTotal(rs.getInt("total_net_profit")); // SELECT ... AS total_net_profit
                summaryVO.setTotal(rs.getInt("total_gross_sales"));    // SELECT ... AS total_gross_sales
                // ---------------------------------------------

                summaryList.add(summaryVO);
                System.out.println("[SalesDAO] summaryVO 추가됨: " + summaryVO.toString());
            }
             if (!hasResults) {
                System.out.println("[SalesDAO] rs.next() == false. 조회된 결과 행 없음."); // 이 로그는 이제 안 나와야 함
            }
        } finally {
            if(dbCon != null) dbCon.closeDB(rs, pstmt, conn);
        }
         System.out.println("[SalesDAO] getDailySalesSummaryByItem 반환. 결과 리스트 크기: " + summaryList.size());
        return summaryList;
    }


    /**
     * 특정 날짜(fix_reg_date 기준)의 전체 합계 계산 (날짜 파라미터 처리 변경)
     */
    public int getDailyTotalSales(java.sql.Date specificDate, boolean useRealTotal) throws SQLException {
        int dailyTotal = 0;
        Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
        DbConnection dbCon = DbConnection.getInstance();
        String sumColumn = useRealTotal ? "s.sales_real_total" : "s.sales_total";

        // java.sql.Date -> 'YYYY-MM-DD' 형식의 문자열로 변환
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(specificDate);
        System.out.println("[SalesDAO] getDailyTotalSales 시작. 조회 날짜 문자열: " + dateString);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT NVL(SUM(").append(sumColumn).append("), 0) AS total_sum ");
        sql.append("FROM sales s JOIN fix f ON s.fix_num = f.fix_num ");
        // --- WHERE 절 수정: TO_DATE 함수 사용 ---
        sql.append("WHERE f.fix_reg_date >= TO_DATE(?, 'YYYY-MM-DD') "); // <<<=== 수정됨
        sql.append("AND f.fix_reg_date < TO_DATE(?, 'YYYY-MM-DD') + 1 "); // <<<=== 수정됨
        // ------------------------------------

        try {
            conn = dbCon.getConn();
            pstmt = conn.prepareStatement(sql.toString());

            // --- 파라미터 설정 수정: setDate 대신 setString 사용 ---
            pstmt.setString(1, dateString); // <<<=== 수정됨
            pstmt.setString(2, dateString); // <<<=== 수정됨
            // ------------------------------------------------

            rs = pstmt.executeQuery();
            if (rs.next()) { dailyTotal = rs.getInt("total_sum"); }
        } finally {
            if(dbConn != null) dbCon.closeDB(rs, pstmt, conn);
        }
        return dailyTotal;
    }


} // class SalesDAO