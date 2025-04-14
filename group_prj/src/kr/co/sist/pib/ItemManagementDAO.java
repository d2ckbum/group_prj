package kr.co.sist.pib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnection.DbConnection;

public class ItemManagementDAO {
	private static ItemManagementDAO imDAO;
	
	
	private ItemManagementDAO() {
		
	}

	
	public static ItemManagementDAO getInstance() {
		if(imDAO == null) {
			imDAO = new ItemManagementDAO();
		}
		
		return imDAO;
		
	}//end getInstance
	
	public void insertImMember(ItemManagementVO imVO) throws SQLException {
		Connection con = null;
	    PreparedStatement pstmt = null;
//	    DbConnetion dbCon = DbConnection.getInstance();
	    DbConnection dbCon = DbConnection.getInstance();
	    
	    try {
	        con = dbCon.getConn();
	     
	        StringBuilder insertMember = new StringBuilder();
	        insertMember
	        .append("INSERT INTO item ")
	        .append("(ITEM_NUM, ITEM_NAME, ITEM_STOCK, ITEM_COST, ITEM_PRICE, ITEM_REPAIR_COST, CAR_NUM) ")
	        .append("SELECT ")
	        .append("seq_item_num.nextval, ") // 시퀀스
	        .append("?, ?, ?, ?, ?, ")      // 바인딩 파라미터들
	        .append("car_num ")
	        .append("FROM car ")
	        .append("WHERE car_type = ? ");
	        
	        
	        
	        pstmt = con.prepareStatement(insertMember.toString());
	        pstmt.setString(1, imVO.getItem_name());
	        pstmt.setInt(2, imVO.getItem_stock());
	        pstmt.setInt(3, imVO.getItem_cost());
	        pstmt.setInt(4, imVO.getItem_price());
	        pstmt.setInt(5, imVO.getItem_repair_cost());
	        pstmt.setString(6, imVO.getCar_type());
	        
	        pstmt.executeUpdate();
	        
	        
	    } finally {
	        dbCon.closeDB(null, pstmt, con);
	    }
	}//end insertImMember
	
	
	public int updateImMember(ItemManagementVO imVO) throws SQLException {
		Connection con = null;
	    PreparedStatement pstmt = null;
	    DbConnection dbCon = DbConnection.getInstance();
	    int num = 0;
	    try {
	        con = dbCon.getConn();
	        
	        StringBuilder updateMember = new StringBuilder();
	        updateMember
	        .append("UPDATE item ")
	        .append("SET item_name = ?, ")
	        .append("    item_stock = ?, ")
	        .append("    item_cost = ?, ")
	        .append("    item_price = ?, ")
	        .append("    item_repair_cost = ?, ")
	        .append("    car_num = (SELECT car_num ")
	        .append("               FROM car ")
	        .append("               WHERE car_type = ?) ")
	        .append("WHERE item_num = ?");
	        
	        pstmt = con.prepareStatement(updateMember.toString());
	        pstmt.setString(1, imVO.getItem_name());
	        pstmt.setInt(2, imVO.getItem_stock());
	        pstmt.setInt(3, imVO.getItem_cost());
	        pstmt.setInt(4, imVO.getItem_price());
	        pstmt.setInt(5, imVO.getItem_repair_cost());
	        pstmt.setString(6, imVO.getCar_type());
	        pstmt.setInt(7, imVO.getItem_num());
	        
	        num = pstmt.executeUpdate();
	        
	        
	    } finally {
	        dbCon.closeDB(null, pstmt, con);
	    }
	    return num; // 실행된 결과 반환
	}//end updateImMember
	
	
	public int deleteImMember(int productnum) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    DbConnection dbCon = DbConnection.getInstance();
	    int num = 0;
	    try {
	        con = dbCon.getConn();
	        
	        StringBuilder deleteMember = new StringBuilder();
	        deleteMember.append("DELETE FROM item ")
	                    .append("WHERE item_num = ?");
	        
	        pstmt = con.prepareStatement(deleteMember.toString());
	        pstmt.setInt(1, productnum);
	        
	        num = pstmt.executeUpdate();
	        
	        
	    } finally {
	        dbCon.closeDB(null, pstmt, con);
	    }
	    return num; // 실행된 결과 반환
	}//end deleteImMember
	
	public List<ItemManagementVO> selectAllMember() throws SQLException{
		List<ItemManagementVO> tempVO_list = new ArrayList<ItemManagementVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DbConnection dbCon = DbConnection.getInstance();
		try {
			con = dbCon.getConn();
			StringBuilder selectAllMember = new StringBuilder();
			selectAllMember
			.append("SELECT item.item_num, item.item_name, item.item_stock, item.item_cost, ")
            .append("item.item_price, item.item_repair_cost, item.item_reg_date, car.car_type ")
            .append("FROM item ")
            .append("JOIN car ON item.car_num = car.car_num ")
			.append("ORDER BY item.item_name");
			
			pstmt = con.prepareStatement(selectAllMember.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ItemManagementVO tempimVO = new ItemManagementVO();
				
				tempimVO.setItem_num(rs.getInt(1));
				tempimVO.setItem_name(rs.getString(2));
				tempimVO.setItem_stock(rs.getInt(3));
				tempimVO.setItem_cost(rs.getInt(4));
				tempimVO.setItem_price(rs.getInt(5));
				tempimVO.setItem_reg_date(rs.getDate(7));
				tempimVO.setCar_type(rs.getString(8));
				
//				System.out.println(tempimVO);
				
				tempVO_list.add(tempimVO);
			}//end while
		
		}finally {
			dbCon.closeDB(rs, pstmt, con);
		}
		return tempVO_list;
		
		
	}//end selectAllMember
	
	public ItemManagementVO selectOneMember(int num) throws SQLException {
		return null;
	}
	
	
}
