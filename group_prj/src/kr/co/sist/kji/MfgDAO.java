package kr.co.sist.kji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.hjs.DbConnection;

public class MfgDAO {

private static MfgDAO mfgDAO;
	
	private MfgDAO() {
	}//MemberDAO
	
	public static MfgDAO getInstance() {
		
		if(mfgDAO == null) {
			mfgDAO = new MfgDAO();
		}//end if
		
		return mfgDAO;
	}//getInstance
	
	
	public List<MfgVO> selectAllMember() throws SQLException {
		List<MfgVO> list = new ArrayList<MfgVO>();

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

			MfgVO mfgVO = null;
			while (rs.next()) {// 레코드가 존재하는지?
				// 레코드의 컬럼 값을 VO에 저장하고
				mfgVO = new MfgVO();
				
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
	
	
	public int selectOneMfgNum(String mfg_name) throws SQLException {
		int mfgNum = -1;

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
			.append("	select mfg_num		")
			.append("	from manufacturer						")
			.append("	where mfg_name =?						 		");
			pstmt = con.prepareStatement(selectOneMember.toString());
			System.out.println(pstmt);
			// 4.
			pstmt.setString(1, mfg_name);
			// 5.

			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				mfgNum=rs.getInt("mfg_num");
				
			} // end if
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		} // end finally

		return mfgNum;

	}// selectOneMember
	
}//class
