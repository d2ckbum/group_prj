package kr.co.sist.kji;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnection.DbConnection;

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

	}// insertMember

	public List<FixPanelVO> selectAllMyFixinfo(String memId) throws SQLException {
		List<FixPanelVO> list = new ArrayList<FixPanelVO>();

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
			.append("	select f.ITEM_NAME, f.FIX_NUM, f.TOTAL_PRICE, f.FIX_STATUS")
			.append("	from fix f,member m						")
			.append("	where (m.mem_num = f.mem_num) and m.mem_id=?				 		");
			pstmt = con.prepareStatement(selectOneMember.toString());
			System.out.println(pstmt);
			// 4.
			pstmt.setString(1, memId);
			// 5.

			rs = pstmt.executeQuery();
			
			
			
			FixPanelVO fVO = null;
			while (rs.next()) {// 레코드가 존재하는지?
				fVO = new FixPanelVO();
				fVO.setItemName(rs.getString("item_name"));
				fVO.setFixNum(rs.getString("fix_num"));
				fVO.setTotal(rs.getInt("total_price"));
				fVO.setFixStatus(rs.getString("fix_status"));
				list.add(fVO);
			} // end while
			if (rs.next()) {
				
				
				
			} // end if
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		} // end finally

		return list;

	}// selectOneMember
	
	public int updateMember(MemberVO mVO) throws SQLException {
		int rowCnt = 0;

		// 1.드라이버 로딩
		// 2.커넥션 얻기
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConnection dbCon = DbConnection.getInstance();
		try {

			// 3.쿼리문 생성객체 얻기
			con = dbCon.getConn();
				// 3. 쿼리문을 넣어서 쿼리문 생성객체 얻기
				StringBuilder updateMember = new StringBuilder();
				updateMember
				.append("	update member	")
				.append("	set mem_name=?,mem_pass=?,mem_email=?,mem_tell=?,mem_zipcode=?, 	")
				.append("	mem_addr1=?,mem_addr2=?,car_num=?,mfg_num=?	")
				.append("	where mem_id=?	");
				pstmt = con.prepareStatement(updateMember.toString());
				// 4. 바인드변수에 값 할당
				pstmt.setString(1, mVO.getMemName());
				pstmt.setString(2, mVO.getMemPass());
				pstmt.setString(3, mVO.getMemEmail());
				pstmt.setString(4, mVO.getMemTell());
				pstmt.setString(5, mVO.getMemZipcode());
				pstmt.setString(6, mVO.getMemAddr1());
				pstmt.setString(7, mVO.getMemAddr2());
				pstmt.setInt(8, mVO.getCarNum());
				pstmt.setInt(9, mVO.getMfgNum());
				pstmt.setString(10, mVO.getMemId());
			// 5.쿼리문 수행 후 결과 얻기
			rowCnt = pstmt.executeUpdate();
		} finally {
			// 6.연결 끊기
			dbCon.closeDB(null, pstmt, con);
		} // end finally

		return rowCnt;
	}// updatePstmtMember
	
	
	public int deleteMember(String id) throws SQLException {
		int rowCnt = 0;

		// 1.드라이버 로딩
		// 2.커넥션 얻기
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConnection dbCon = DbConnection.getInstance();
		try {

			// 3.쿼리문 생성객체 얻기
			con = dbCon.getConn();
				// 3. 쿼리문을 넣어서 쿼리문 생성객체 얻기
				StringBuilder updateMember = new StringBuilder();
				updateMember
				.append("	update member	")
				.append("	set mem_flag='Y' 	")
				.append("	where mem_id=?	");
				pstmt = con.prepareStatement(updateMember.toString());
				// 4. 바인드변수에 값 할당
				pstmt.setString(1, id);
			// 5.쿼리문 수행 후 결과 얻기
			rowCnt = pstmt.executeUpdate();
		} finally {
			// 6.연결 끊기
			dbCon.closeDB(null, pstmt, con);
		} // end finally

		return rowCnt;
	}// updatePstmtMember
	
	
	public MemberVO login(LoginVO lVO) throws SQLException{

		MemberVO mVO = new MemberVO();
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
			.append("	select mem_name, mem_flag	")
			.append("	from member	")
			.append("	where mem_id=? and mem_pass =?   ")
			;
			pstmt= con.prepareStatement(selectName.toString());
		//4.바인드 변수에 값 설정
			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPass());
		//5. 쿼리문 수행 후 결과 얻기
			System.out.println(selectName);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				mVO.setMemName( rs.getString("mem_name"));
				mVO.setMemFlag(rs.getString("mem_flag"));
			}//end if
		}finally {
			//5. 연결 끊기
			dbCon.closeDB(rs, pstmt, con);
		}//end finally
		
		return mVO;
	}//selectLogin
	
	
	
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
	}//main
	
	
	
}//class
