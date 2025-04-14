package kr.co.sist.kyh;

import java.sql.*;
import java.util.*;

import DBConnection.DbConnection;

public class ItemRecommendDAO {

    private static ItemRecommendDAO instance;

    private ItemRecommendDAO() {}

    public static ItemRecommendDAO getInstance() {
        if (instance == null) {
            instance = new ItemRecommendDAO();
        }
        return instance;
    }

    // 추천 상품 목록 조회(차량식별번호 기반 필터링)
    public List<ItemVO> getRecommendedItems(int carNum) throws SQLException {
        List<ItemVO> recommendedItems = new ArrayList<>();

        String query = "SELECT item_num, item_name, item_stock, item_cost, item_price, " +
                       "item_repair_cost, item_reg_date, car_num " +
                       "FROM item " +
                       "WHERE car_num = ?";
        
        System.out.println("Executing query with carNum: " + carNum);

        DbConnection dbConn = DbConnection.getInstance();

        // 자동으로 리소스 해제
        try (Connection con = dbConn.getConn();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, carNum);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ItemVO item = new ItemVO(
                        rs.getInt("item_num"),
                        rs.getString("item_name"),
                        rs.getInt("item_stock"),
                        rs.getInt("item_cost"),
                        rs.getInt("item_price"),
                        rs.getInt("item_repair_cost"),
                        rs.getDate("item_reg_date"),
                        rs.getInt("car_num")
                    );
                    recommendedItems.add(item);
                }
            }
        }

        return recommendedItems;
    }

    // 전체 상품 조회
    public List<ItemVO> getAllItems() throws SQLException {
        List<ItemVO> allItems = new ArrayList<>();
        String query = "SELECT item_num, item_name, item_stock, item_cost, item_price, " +
                       "item_repair_cost, item_reg_date, car_num FROM item";

        DbConnection dbConn = DbConnection.getInstance();

        try (Connection con = dbConn.getConn();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ItemVO item = new ItemVO(
                    rs.getInt("item_num"),
                    rs.getString("item_name"),
                    rs.getInt("item_stock"),
                    rs.getInt("item_cost"),
                    rs.getInt("item_price"),
                    rs.getInt("item_repair_cost"),
                    rs.getDate("item_reg_date"),
                    rs.getInt("car_num")
                );
                allItems.add(item);
            }
        }
        return allItems;
    }
}
