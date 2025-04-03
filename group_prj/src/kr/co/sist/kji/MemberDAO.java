package kr.co.sist.kji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.hjs.DbConnection;

public class MemberDAO {

private static MemberDAO mDAO;
	
	private MemberDAO() {
	}//MemberDAO
	
	public static MemberDAO getInstance() {
		
		if(mDAO == null) {
			mDAO = new MemberDAO();
		}//end if
		
		return mDAO;
	}//getInstance
	
	
	
	
	
	public List<MemberVO> selectAllMember() throws SQLException {
		List<MemberVO> list = new ArrayList<MemberVO>();

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
					.append("	from member	");
			pstmt = con.prepareStatement(selectMember.toString());
			// 4.
			// 5.
			rs = pstmt.executeQuery();

			MemberVO mVO = null;
			while (rs.next()) {// 레코드가 존재하는지?
				// 레코드의 컬럼 값을 VO에 저장하고
				mVO = new MemberVO();
				
				mVO.setMemNum(rs.getInt("mem_num"));
				mVO.setMemId(rs.getString("mem_id"));
				mVO.setMemPass(rs.getString("mem_pass"));
				mVO.setMemName(rs.getString("mem_name"));
				mVO.setMemEmail(rs.getString("mem_email"));
				mVO.setMemTell(rs.getString("mem_tell"));
				mVO.setMemZipcode(rs.getString("mem_zipcode"));	
				mVO.setMemAddr1(rs.getString("mem_addr1"));	
				mVO.setMemAddr2(rs.getString("mem_addr2"));	
				mVO.setMemRegDate(rs.getDate("mem_reg_date"));	
				mVO.setMemFlag(rs.getString("mem_flag"));	
				mVO.setCarNum(rs.getInt("car_num"));
				mVO.setMfgNum(rs.getInt("mfg_num"));
				list.add(mVO);
			} // end while
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		}

		return list;
	}// selectAllMember
	
	
	public MemberVO selectOneMember(String memId) throws SQLException {
		MemberVO mVO = null;

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
			.append("	from member						")
			.append("	where mem_id =?						 		");
			pstmt = con.prepareStatement(selectOneMember.toString());
			System.out.println(pstmt);
			// 4.
			pstmt.setString(1, memId);
			// 5.

			rs = pstmt.executeQuery();
			if (rs.next()) {
				mVO = new MemberVO();
				
				mVO.setMemNum(rs.getInt("mem_num"));
				mVO.setMemId(rs.getString("mem_id"));
				mVO.setMemPass(rs.getString("mem_pass"));
				mVO.setMemName(rs.getString("mem_name"));
				mVO.setMemEmail(rs.getString("mem_email"));
				mVO.setMemTell(rs.getString("mem_tell"));
				mVO.setMemZipcode(rs.getString("mem_zipcode"));	
				mVO.setMemAddr1(rs.getString("mem_addr1"));	
				mVO.setMemAddr2(rs.getString("mem_addr2"));	
				mVO.setMemRegDate(rs.getDate("mem_reg_date"));	
				mVO.setMemFlag(rs.getString("mem_flag"));	
				mVO.setCarNum(rs.getInt("car_num"));
				mVO.setMfgNum(rs.getInt("mfg_num"));
			} // end if
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		} // end finally

		return mVO;

	}// selectOneMember
	
	public static void main(String[] args) {
		MemberDAO mDAO = MemberDAO.getInstance();
		try {
			System.out.println(mDAO.selectOneMember("kkk").toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}//class
