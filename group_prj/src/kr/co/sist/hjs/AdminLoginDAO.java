package kr.co.sist.hjs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBConnection.DbConnection;

public class AdminLoginDAO {

	private static AdminLoginDAO lDAO;

	private AdminLoginDAO() {

	}

	public static AdminLoginDAO getInstance() {
		if (lDAO == null) {
			lDAO = new AdminLoginDAO();
		} // end if
		return lDAO;
	}// getInstance

	public String selectPstmtLogin(AdminLoginVO lVO) throws SQLException {
		String name = "";

		// 1. 드라이버 로딩
		// 2. 커넥션 얻기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConnection dbCon = DbConnection.getInstance();
		try {
			con = dbCon.getConn();
			// 3. 쿼리문생성 객체 얻기
			StringBuilder selectName = new StringBuilder();
			selectName.append("	select admin_id	").append("	from admin	").append("	where admin_id=? and admin_pass=?");

			pstmt = con.prepareStatement(selectName.toString());
			// 4. 바인드 변수에 값 설정
			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPass());
			System.out.println(selectName);

			// 5.쿼리문 수행 후 결과 얻기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				name = rs.getString("admin_id");
			}
		} finally {
			// 5.연결 끊기
			dbCon.closeDB(rs, pstmt, con);
		} // end finally
		return name;
	}// selectLogin

	public String blockinjection(String sql) {
		String temp = sql;
		if (temp != null && !temp.isEmpty()) {
			temp = temp.replaceAll("'", "").replaceAll(" ", "").replaceAll("--", "");
		} // end if
		return temp;
	}// blockinjection

}// class
