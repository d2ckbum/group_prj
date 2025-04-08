package kr.co.sist.cjw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.cjw.view.Mem_Inquiry_View;
import kr.co.sist.cjw.vo.FAQ_VO;
import kr.co.sist.cjw.vo.Inquiry_VO;

public class Mem_Inquiry_Dao {
	
	private static Mem_Inquiry_Dao mIDAO;
	
	public void mem_Inquiry_Dao() {
		
	}//mem_Inquiry_Dao
	
	public static Mem_Inquiry_Dao getInstance() {
		if (mIDAO == null) {
			mIDAO = new Mem_Inquiry_Dao();
		} // end if
		return mIDAO;
	}// getInstance
	
	public List<FAQ_VO> selectFAQ() throws SQLException {
		List<FAQ_VO> list = new ArrayList<FAQ_VO>();

		// 1.
		// 2.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConnection dbCon = DbConnection.getInstance();

		try {
			con = dbCon.getConn();
			// 3.
			StringBuilder selectFAQ = new StringBuilder();
			selectFAQ
			.append("	select	*	")
			.append("	from faq	");
			pstmt = con.prepareStatement(selectFAQ.toString());
			// 4.
			// 5.
			rs = pstmt.executeQuery();

			FAQ_VO fVO = null;
			while (rs.next()) {// 레코드가 존재하는지?
				// 레코드의 컬럼 값을 VO에 저장하고
				fVO = new FAQ_VO();

				fVO.setFaq_title(rs.getString("Faq_title"));
				fVO.setFaq_contents(rs.getString("Faq_contents"));
				fVO.setFaq_reg_date(rs.getDate("Faq_reg_date"));

				list.add(fVO);
			} // end while
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		}

		return list;
	}//selectFAQ
	
	public List<Inquiry_VO> selectINQ(String id) throws SQLException {
		List<Inquiry_VO> list = new ArrayList<Inquiry_VO>();

		// 1.
		// 2.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConnection dbCon = DbConnection.getInstance();

		  try {
		        con = dbCon.getConn();
		        // SQL 쿼리 수정: 상태 컬럼 추가
		        StringBuilder selectINQ = new StringBuilder();
		        selectINQ
		            .append("	SELECT i.Inq_Id, i.Inq_Reg_Date, i.Inq_Title, i.REPLY, ")
		            .append("           CASE ")
		            .append("               WHEN REPLY IS NOT NULL THEN '답변 완료' ")
		            .append("               ELSE '답변 대기' ")
		            .append("           END AS Inq_Status ")
		            .append("	FROM INQUIRY i, Member m	")
		        	.append("	where (m.mem_num=i.mem_num)and m.mem_id=?	");

		        pstmt = con.prepareStatement(selectINQ.toString());
		        
		        pstmt.setString(1, id);
		        rs = pstmt.executeQuery();
		        

		        Inquiry_VO iVO = null;
		        while (rs.next()) {
		            iVO = new Inquiry_VO();

		            iVO.setInq_Id(rs.getInt("Inq_Id"));
		            iVO.setInq_Reg_Date(rs.getDate("Inq_Reg_Date"));
		            iVO.setInq_Title(rs.getString("Inq_Title"));
		            iVO.setInq_Status(rs.getString("Inq_Status")); // 수정된 부분

		            list.add(iVO);
		        } // end while
		    } finally {
		        dbCon.closeDB(rs, pstmt, con);
		    }

		    return list;
		}//selectINQ
	
	
	
	public void insertInq(Inquiry_VO mIVO, String id) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    DbConnection dbCon = DbConnection.getInstance();

	    try {
	        con = dbCon.getConn();
	        con.setAutoCommit(false); 

	        
	        StringBuilder insertInq = new StringBuilder();
	        insertInq
	            .append("INSERT INTO Inquiry(inq_id, inq_title, inq_contents, mem_NUM) ")
	            .append("select INQ_SEQ.NEXTVAL, ?, ?,  mem_num from MEMBER where MEM_ID=  ? ");

	        
	        pstmt = con.prepareStatement(insertInq.toString());
	        pstmt.setString(1, mIVO.getInq_Title());
	        pstmt.setString(2, mIVO.getInq_Contents());
	        pstmt.setString(3, id);
	        	        
	        pstmt.executeUpdate();
	        con.commit(); 
	    } finally {
	        dbCon.closeDB(null, pstmt, con);
	    }//end finally
	}//insertFAQ
	
}//class
