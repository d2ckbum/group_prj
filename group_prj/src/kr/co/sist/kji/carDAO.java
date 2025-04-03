package kr.co.sist.kji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.hjs.DbConnection;

public class carDAO {

	private static carDAO cDAO;

	private carDAO() {
			}// MemberDAO

	public static carDAO getInstance() {

		if (cDAO == null) {
			cDAO = new carDAO();
		} // end if

		return cDAO;
	}// getInstance

	public List<carVO> selectAllMember() throws SQLException {
		List<carVO> list = new ArrayList<carVO>();

		// 1.
		// 2.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConnection dbCon = DbConnection.getInstance();

		try {
			con = dbCon.getConn();
			// 3.
			StringBuilder selectMember = new StringBuilder();
			selectMember
			.append("	select	*	")
			.append("	from car	");
			pstmt = con.prepareStatement(selectMember.toString());
			// 4.
			// 5.
			rs = pstmt.executeQuery();

			carVO cVO = null;
			while (rs.next()) {// 레코드가 존재하는지?
				// 레코드의 컬럼 값을 VO에 저장하고
				cVO = new carVO();

				cVO.setCarNum(rs.getInt("car_num"));
				cVO.setCarType(rs.getString("car_type"));

				list.add(cVO);
			} // end while
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		}

		return list;
	}// selectAllMember

	public carVO selectOneMember(String car_type) throws SQLException {
		carVO cVO = null;

		// 1.
		// 2.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConnection dbCon = DbConnection.getInstance();

		try {
			con = dbCon.getConn();

			// 3.
			StringBuilder selectOneMember = new StringBuilder();
			selectOneMember
			.append("	select *		")
			.append("	from car						")
			.append("	where car_type =?						 		");
			pstmt = con.prepareStatement(selectOneMember.toString());
			System.out.println(pstmt);
			// 4.
			pstmt.setString(1, car_type);
			// 5.

			rs = pstmt.executeQuery();
			if (rs.next()) {
				cVO = new carVO();

				cVO.setCarNum(rs.getInt("car_num"));
				cVO.setCarType(rs.getString("car_type"));

			} // end if
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		} // end finally

		return cVO;

	}// selectOneMember

	public carVO selectOneMember(int car_num) throws SQLException {
		carVO cVO = null;

		// 1.
		// 2.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConnection dbCon = DbConnection.getInstance();

		try {
			con = dbCon.getConn();

			// 3.
			StringBuilder selectOneMember = new StringBuilder();
			selectOneMember
			.append("	select *		")
			.append("	from car						")
			.append("	where car_num =?						 		");
			pstmt = con.prepareStatement(selectOneMember.toString());
			System.out.println(pstmt);
			// 4.
			pstmt.setInt(1, car_num);
			// 5.

			rs = pstmt.executeQuery();
			if (rs.next()) {
				cVO = new carVO();

				cVO.setCarNum(rs.getInt("car_num"));
				cVO.setCarType(rs.getString("car_type"));

			} // end if
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		} // end finally

		return cVO;

	}// selectOneMember
public static void main(String[] args) {
	carDAO cDAO = carDAO.getInstance();
	try {
		System.out.println(cDAO.selectOneMember("대형"));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}// class
