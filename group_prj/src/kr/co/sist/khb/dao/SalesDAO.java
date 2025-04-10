package kr.co.sist.khb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
            sql.append("                   item_name, item_num, fix_num) ");
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
     * 특정 날짜(fix_reg_date 기준)에 대해 상품별로 집계된 매출 요약 정보를 조회합니다. (JOIN, 범위 비교)
     */
    public List<SalesSumVO> getDailySalesSummaryByItem(java.sql.Date queryDate) throws SQLException {
        List<SalesSumVO> summaryList = new ArrayList<>();
        Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT s.item_num, s.item_name, COUNT(*) AS quantity, ")
           .append("       NVL(SUM(s.sales_real_total), 0) AS total_net_profit, ") 
           .append("       NVL(SUM(s.sales_total), 0) AS total_gross_sales ")      
           .append("FROM sales s JOIN fix f ON s.fix_num = f.fix_num ")
           .append("WHERE f.fix_reg_date >= ? AND f.fix_reg_date < ? + 1 ") 
           .append("GROUP BY s.item_num, s.item_name ORDER BY s.item_num");

        try {
            conn = dbConn.getConn(); 
            pstmt = conn.prepareStatement(sql.toString());
            
            pstmt.setDate(1, queryDate);
            pstmt.setDate(2, queryDate);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                SalesSumVO summaryVO = new SalesSumVO();
                summaryVO.setItemNum(rs.getInt("item_num"));
                summaryVO.setItemName(rs.getString("item_name"));
                summaryVO.setQuantity(rs.getInt("quantity"));
                summaryVO.setRealTotal(rs.getInt("total_net_profit"));
                summaryVO.setTotal(rs.getInt("total_gross_sales"));
                summaryList.add(summaryVO);
            }
        } finally {
            if(dbConn != null) dbConn.closeDB(rs, pstmt, conn);
        }
        return summaryList;
    }

    /**
     * 특정 날짜(fix_reg_date 기준)의 전체 매출 합계 또는 실매출 합계를 계산합니다. (JOIN, 범위 비교)
     */
    public int getDailyTotalSales(java.sql.Date specificDate, boolean useRealTotal) throws SQLException {
        int dailyTotal = 0;
        Connection conn = null; PreparedStatement pstmt = null; ResultSet rs = null;
        String sumColumn = useRealTotal ? "s.sales_real_total" : "s.sales_total";

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT NVL(SUM(").append(sumColumn).append("), 0) AS total_sum ");
        sql.append("FROM sales s JOIN fix f ON s.fix_num = f.fix_num ");
        sql.append("WHERE f.fix_reg_date >= ? AND f.fix_reg_date < ? + 1 ");

        try {
            conn = dbConn.getConn(); 
            pstmt = conn.prepareStatement(sql.toString());
            
            pstmt.setDate(1, specificDate);
            pstmt.setDate(2, specificDate);

            rs = pstmt.executeQuery();
            if (rs.next()) { dailyTotal = rs.getInt("total_sum"); }
        } finally {
            if(dbConn != null) dbConn.closeDB(rs, pstmt, conn);
        }
        return dailyTotal;
    }

   

} // class SalesDAO