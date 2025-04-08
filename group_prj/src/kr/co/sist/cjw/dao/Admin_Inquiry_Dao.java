package kr.co.sist.cjw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.cjw.vo.FAQ_VO;

public class Admin_Inquiry_Dao {
	
	private static Admin_Inquiry_Dao aIDAO;

	private Admin_Inquiry_Dao() {

	}
	
	public static Admin_Inquiry_Dao getInstance() {
		if (aIDAO == null) {
			aIDAO = new Admin_Inquiry_Dao();
		} // end if
		return aIDAO;
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
	
	
	public void insertFAQ(FAQ_VO aIVO) throws SQLException {
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    DbConnection dbCon = DbConnection.getInstance();

	    try {
	        con = dbCon.getConn();
	        con.setAutoCommit(false); 

	        
	        StringBuilder insertFAQ = new StringBuilder();
	        insertFAQ
	            .append("INSERT INTO FAQ(FAQ_ID, faq_title, faq_contents) ")
	            .append("VALUES(FAQ_SEQ.NEXTVAL, ?, ?)");

	        pstmt = con.prepareStatement(insertFAQ.toString());
	        pstmt.setString(1, aIVO.getFaq_title());
	        pstmt.setString(2, aIVO.getFaq_contents());

	        pstmt.executeUpdate();
	        con.commit(); 
	    } finally {
	        dbCon.closeDB(null, pstmt, con);
	    }//end finally
	}//insertFAQ
	
	
}//class
