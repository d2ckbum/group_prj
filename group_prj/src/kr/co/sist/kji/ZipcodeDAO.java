package kr.co.sist.kji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.hjs.DbConnection;

public class ZipcodeDAO {
	private static ZipcodeDAO zDAO;
	private ZipcodeDAO() {
	}
	
	public static ZipcodeDAO getInstance() {
		if(zDAO == null) {
			zDAO = new ZipcodeDAO();
		}//end if
		return zDAO;
	}//getInstance

	public List<ZipcodeVO> selectZipcode(String dong) throws SQLException{
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		//1.
		//2.
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		
		try {
			con = dbCon.getConn();
		//3.
			StringBuilder selectZipcode = new StringBuilder();
			selectZipcode
			.append("	select zipcode, sido, gugun, dong, 	")
			.append("	nvl(bunji, ' ') bunji	")
			.append("	from zipcode	")
			.append("	where dong like ?||'%'	")
			;
			
			pstmt= con.prepareStatement(selectZipcode.toString());
		//4.
			pstmt.setString(1, dong);
		//5.
			rs=pstmt.executeQuery();
			
			ZipcodeVO zVO = null;
			while(rs.next()) {
				zVO = new ZipcodeVO(rs.getString("zipcode"), 
						rs.getString("sido"), 
						rs.getString("gugun"), 
						rs.getString("dong"), 
						rs.getString("bunji"));
				list.add(zVO);
			}//end while
		}finally {
			//6.
			dbCon.closeDB(rs, pstmt, con);
		}//end finally
		
		
		
		return list;
		
	}//selectZipcode
	
	
}//class
