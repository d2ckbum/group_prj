package kr.co.sist.kji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.hjs.DbConnection;

public class mfgDAO {

private static mfgDAO mfgDAO;
	
	private mfgDAO() {
	}//MemberDAO
	
	public static mfgDAO getInstance() {
		
		if(mfgDAO == null) {
			mfgDAO = new mfgDAO();
		}//end if
		
		return mfgDAO;
	}//getInstance
	
	
	public List<mfgVO> selectAllMember() throws SQLException {
		List<mfgVO> list = new ArrayList<mfgVO>();

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
			selectMember.append("	select	*	")
					.append("	from manufacturer	");
			pstmt = con.prepareStatement(selectMember.toString());
			// 4.
			// 5.
			rs = pstmt.executeQuery();

			mfgVO mfgVO = null;
			while (rs.next()) {// 레코드가 존재하는지?
				// 레코드의 컬럼 값을 VO에 저장하고
				mfgVO = new mfgVO();
				
				mfgVO.setMfgNum(rs.getInt("mfg_num"));
				mfgVO.setMfgName(rs.getString("mfg_name"));
				
				list.add(mfgVO);
			} // end while
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		}

		return list;
	}// selectAllMember
	
	
	public mfgVO selectOneMember(String mfg_name) throws SQLException {
		mfgVO mfgVO = null;

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
			.append("	from manufacturer						")
			.append("	where mfg_name =?						 		");
			pstmt = con.prepareStatement(selectOneMember.toString());
			System.out.println(pstmt);
			// 4.
			pstmt.setString(1, mfg_name);
			// 5.

			rs = pstmt.executeQuery();
			if (rs.next()) {
				mfgVO = new mfgVO();
				
				mfgVO.setMfgNum(rs.getInt("mfg_num"));
				mfgVO.setMfgName(rs.getString("mfg_name"));
				
			} // end if
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		} // end finally

		return mfgVO;

	}// selectOneMember
	
	public mfgVO selectOneMember(int mfg_num) throws SQLException {
		mfgVO mfgVO = null;

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
			.append("	from manufacturer						")
			.append("	where mfg_num =?						 		");
			pstmt = con.prepareStatement(selectOneMember.toString());
			System.out.println(pstmt);
			// 4.
			pstmt.setInt(1, mfg_num);
			// 5.

			rs = pstmt.executeQuery();
			if (rs.next()) {
				mfgVO = new mfgVO();
				
				mfgVO.setMfgNum(rs.getInt("mfg_num"));
				mfgVO.setMfgName(rs.getString("mfg_name"));
				
			} // end if
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		} // end finally

		return mfgVO;

	}// selectOneMember
	
}//class
