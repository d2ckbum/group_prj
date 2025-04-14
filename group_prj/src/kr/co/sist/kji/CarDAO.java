package kr.co.sist.kji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnection.DbConnection;

public class CarDAO {

	private static CarDAO cDAO;

	private CarDAO() {
			}// MemberDAO

	public static CarDAO getInstance() {

		if (cDAO == null) {
			cDAO = new CarDAO();
		} // end if

		return cDAO;
	}// getInstance

	public List<CarVO> selectAllMember() throws SQLException {
		List<CarVO> list = new ArrayList<CarVO>();

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

			CarVO cVO = null;
			while (rs.next()) {// 레코드가 존재하는지?
				// 레코드의 컬럼 값을 VO에 저장하고
				cVO = new CarVO();

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

	public int selectOneCarNum(String car_type) throws SQLException {
		int carNum=-1;

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
			.append("	select car_num		")
			.append("	from car						")
			.append("	where car_type =?						 		");
			pstmt = con.prepareStatement(selectOneMember.toString());
			// 4.
			pstmt.setString(1, car_type);
			// 5.

			rs = pstmt.executeQuery();
			if (rs.next()) {
				

				carNum =rs.getInt("car_num");

			} // end if
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		} // end finally

		return carNum;

	}// selectOneMember
	

}// class
