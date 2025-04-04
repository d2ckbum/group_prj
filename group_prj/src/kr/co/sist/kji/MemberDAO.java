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
	
	public void insertMember(MemberVO mVO) throws SQLException {
		// 1. 드라이버로딩
		// 2. 커넥션 얻기
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConnection dbCon = DbConnection.getInstance();
		try {
			con = dbCon.getConn();
			// 3. 쿼리문을 넣어서 쿼리문 생성객체 얻기
			StringBuilder insertMember = new StringBuilder();
			insertMember
			.append("insert into member(mem_num,mem_id,mem_pass,mem_name,mem_email,mem_tell,")
			.append("mem_zipcode,mem_addr1,mem_addr2,car_num,mfg_num)	")
			.append("values(seq_mem_num.nextval, ?,?,?,?,?,?,?,?,?,?)");
			pstmt = con.prepareStatement(insertMember.toString());
			// 4. 바인드변수에 값 할당
			pstmt.setString(1, mVO.getMemId());
			pstmt.setString(2, mVO.getMemPass());
			pstmt.setString(3, mVO.getMemName());
			pstmt.setString(4, mVO.getMemEmail());
			pstmt.setString(5, mVO.getMemTell());
			pstmt.setString(6, mVO.getMemZipcode());
			pstmt.setString(7, mVO.getMemAddr1());
			pstmt.setString(8, mVO.getMemAddr2());
			pstmt.setInt(9, mVO.getCarNum());
			pstmt.setInt(10, mVO.getMfgNum());

			// 5. 쿼리문 수행 후 결과 얻기
			pstmt.executeUpdate();
		} finally {
			// 6. 연결 끊기
			dbCon.closeDB(null, pstmt, con);

		} // end finally

	}// insertPstmtMember
	
	
	
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
	
	public MemberDataDTO selectOneMemberData(String memId) throws SQLException {
		MemberDataDTO mDTO = null;

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
			.append("	select mem.MEM_NUM, mem.MEM_ID, mem.MEM_PASS, mem.MEM_NAME, mem.MEM_EMAIL,")
			.append("mem.MEM_TELL, mem.MEM_ZIPCODE, mem.MEM_ADDR1, mem.MEM_ADDR2, mem.MEM_REG_DATE, ")
			.append("mem.MEM_FLAG,car.CAR_TYPE ,mfg.MFG_NAME		")
			.append("	from member mem, car car, MANUFACTURER mfg					")
			.append("	where(car.car_num=mem.car_num and mfg.mfg_num = mem.mfg_num)and mem_id =?						 		");
			pstmt = con.prepareStatement(selectOneMember.toString());
			System.out.println(pstmt);
			// 4.
			pstmt.setString(1, memId);
			// 5.

			rs = pstmt.executeQuery();
			if (rs.next()) {
				mDTO = new MemberDataDTO();
				
				mDTO.getmVO().setMemNum(rs.getInt("mem_num"));
				mDTO.getmVO().setMemId(rs.getString("mem_id"));
				mDTO.getmVO().setMemPass(rs.getString("mem_pass"));
				mDTO.getmVO().setMemName(rs.getString("mem_name"));
				mDTO.getmVO().setMemEmail(rs.getString("mem_email"));
				mDTO.getmVO().setMemTell(rs.getString("mem_tell"));
				mDTO.getmVO().setMemZipcode(rs.getString("mem_zipcode"));	
				mDTO.getmVO().setMemAddr1(rs.getString("mem_addr1"));	
				mDTO.getmVO().setMemAddr2(rs.getString("mem_addr2"));	
				mDTO.getmVO().setMemRegDate(rs.getDate("mem_reg_date"));	
				mDTO.getmVO().setMemFlag(rs.getString("mem_flag"));	
				mDTO.setCarType(rs.getString("car_type"));
				mDTO.setMfgName(rs.getString("mfg_name"));
			} // end if
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		} // end finally

		return mDTO;

	}// selectOneMember
	
	public static void main(String[] args) {
		MemberDAO mDAO = MemberDAO.getInstance();
		try {
			System.out.println(mDAO.selectOneMemberData("kkk").toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}//class
