package kr.co.sist.kji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.DbConnection;

public class LoginDAO {
	
	private static LoginDAO lDAO;
	
	private LoginDAO() {
	}

	public static LoginDAO getInstance() {
		
		if(lDAO==null) {
			lDAO = new LoginDAO();
		}//end if
		return lDAO;
	}//getInstance
	

	
	public String selectPstmtLogin(LoginVO lVO) throws SQLException{
		String name=""; 
		
		//1. 드라이버 로딩
		//2. 커넥션 얻기
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConnection dbCon = DbConnection.getInstance();
		try {
			con = dbCon.getConn();
		//3. 쿼리문 생성 객체 얻기
			StringBuilder selectName=new StringBuilder();
			
			selectName
			.append("	select mem_name	")
			.append("	from member	")
			.append("	where mem_id=? and mem_pass =? ")
			;
			pstmt= con.prepareStatement(selectName.toString());
		//4.바인드 변수에 값 설정
			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPass());
		//5. 쿼리문 수행 후 결과 얻기
			System.out.println(selectName);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("mem_name");
			}//end if
		}finally {
			//5. 연결 끊기
			dbCon.closeDB(rs, pstmt, con);
		}//end finally
		
		return name;
	}//selectPstmtLogin
	
	
}//class
