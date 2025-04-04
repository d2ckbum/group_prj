package kr.co.sist.pib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemManagementDAO {
	private static ItemManagementDAO imDAO;
	
	
	private ItemManagementDAO() {
		
	}
	
	public static ItemManagementDAO getInstance() {
		if(imDAO == null) {
			imDAO = new ItemManagementDAO();
		}
		
		return imDAO;
		
	}
	
	public void insertImMember(ItemManagementVO imVO) throws SQLException {
		
	}
	
	public int updateImMember(ItemManagementVO imVO) throws SQLException {
		Connection con = null;
	    PreparedStatement pstmt = null;
	    DbConnection dbCon = DbConnection.getInstance();
	    
	    try {
	        con = dbCon.getConn();
	        
	        // 자동 커밋을 끄고 트랜잭션을 수동으로 관리
	        con.setAutoCommit(false); // 자동 커밋 비활성화
	        
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
	        
	        // executeUpdate() 실행 (커밋은 하지 않음)
	        int result = pstmt.executeUpdate();
	        
	        // 트랜잭션을 커밋하지 않으므로, commit() 호출 안 함
	        // con.commit(); <- 이 줄은 주석 처리 또는 삭제
	        
	        return result; // 실행된 결과 반환
	    } catch (SQLException e) {
	        // 예외 발생 시 롤백
	        if (con != null) {
	            con.rollback(); // 롤백 호출
	        }
	        throw e; // 예외를 다시 던짐
	    } finally {
	        // DB 연결 자원 정리
	        if (con != null) {
	            con.setAutoCommit(true); // 다시 자동 커밋 모드로 복귀
	        }
	        dbCon.closeDB(null, pstmt, con);
	    }
	}
	
	
	public int deleteImMember(int productnum) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    DbConnection dbCon = DbConnection.getInstance();
	    
	    try {
	        con = dbCon.getConn();
	        
	        // 자동 커밋을 끄고 트랜잭션을 수동으로 관리
	        con.setAutoCommit(false); // 자동 커밋 비활성화
	        
	        StringBuilder deleteMember = new StringBuilder();
//	        deleteMember.append("DELETE FROM item ")
//	                    .append("WHERE item_num = ?");
	        
	        pstmt = con.prepareStatement(deleteMember.toString());
	        pstmt.setInt(1, productnum);
	        
	        // executeUpdate() 실행 (커밋은 하지 않음)
	        int result = pstmt.executeUpdate();
	        
	        // 트랜잭션을 커밋하지 않으므로, commit() 호출 안 함
	        // con.commit(); <- 이 줄은 주석 처리 또는 삭제
	        
	        return result; // 실행된 결과 반환
	    } catch (SQLException e) {
	        // 예외 발생 시 롤백
	        if (con != null) {
	            con.rollback(); // 롤백 호출
	        }
	        throw e; // 예외를 다시 던짐
	    } finally {
	        // DB 연결 자원 정리
	        if (con != null) {
	            con.setAutoCommit(true); // 다시 자동 커밋 모드로 복귀
	        }
	        dbCon.closeDB(null, pstmt, con);
	    }
	}
	
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
            .append("JOIN car ON item.car_num = car.car_num");
			
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
				
			}
		
		}finally {
			dbCon.closeDB(rs, pstmt, con);
		}
		
		
		return tempVO_list;
		
		
	}
	
	public ItemManagementVO selectOneMember(int num) throws SQLException {
		return null;
	}
	
	
}
