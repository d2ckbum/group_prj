package kr.co.sist.cjw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnection.DbConnection;
import kr.co.sist.cjw.vo.FAQ_VO;
import kr.co.sist.cjw.vo.Inquiry_VO;

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
			.append("	from faq	")
			.append("	order by Faq_reg_date desc	");
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
	
	
	public int updateFAQ(FAQ_VO fVO, Object faqSub) throws SQLException {
		int rowCnt=0;
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    DbConnection dbCon = DbConnection.getInstance();

	    try {
	        con = dbCon.getConn();
	        con.setAutoCommit(false); 

	        
	        StringBuilder updateFAQ = new StringBuilder();
	        updateFAQ
	            .append("	UPDATE FAQ	")
	            .append("	set Faq_title = ?, Faq_Contents = ?, Faq_Reg_Date = sysdate		")
	            .append("	where Faq_title = ? ");

	        pstmt = con.prepareStatement(updateFAQ.toString());
	        pstmt.setString(1, fVO.getFaq_title());
	        pstmt.setString(2, fVO.getFaq_contents());
	        pstmt.setObject(3, faqSub);

	        rowCnt = pstmt.executeUpdate();
	        con.commit(); 
	    } finally {
	        dbCon.closeDB(null, pstmt, con);
	    }//end finally
	    return rowCnt;
	}//updateFAQ
	
	public int deleteFAQ(Object faqSub) throws SQLException {
		int rowCnt=0;
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    DbConnection dbCon = DbConnection.getInstance();

	    try {
	        con = dbCon.getConn();
	        con.setAutoCommit(false); 

	        
	        StringBuilder deleteFAQ = new StringBuilder();
	        deleteFAQ
	            .append("	delete from FAQ "	)
	            .append("	where Faq_title = ? ");

	        pstmt = con.prepareStatement(deleteFAQ.toString());
	        pstmt.setObject(1, faqSub);

	        rowCnt = pstmt.executeUpdate();
	        con.commit(); 
	    } finally {
	        dbCon.closeDB(null, pstmt, con);
	    }//end finally
	    return rowCnt;
	}//updateFAQ
	
	
	
	public FAQ_VO select_Edit_FAQ(Object faqSub) throws SQLException {
		FAQ_VO faq = null;

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
			.append("	from faq	")
			.append("	where Faq_Title	= ? ");
			pstmt = con.prepareStatement(selectFAQ.toString());
			pstmt.setObject(1, faqSub);
			// 4.
			// 5.
			rs = pstmt.executeQuery();

			while (rs.next()) {// 레코드가 존재하는지?
				// 레코드의 컬럼 값을 VO에 저장하고
				faq = new FAQ_VO();

				faq.setFaq_title(rs.getString("Faq_Title"));
				faq.setFaq_contents(rs.getString("Faq_Contents"));
				faq.setFaq_reg_date(rs.getDate("Faq_Reg_Date"));

			} // end while
		} finally {
			// 6.
			dbCon.closeDB(rs, pstmt, con);
		}

		return faq;
	}//selectFAQ
	
	
	
	
	public List<Inquiry_VO> selectINQ() throws SQLException {
		List<Inquiry_VO> list = new ArrayList<Inquiry_VO>();

		// 1.
		// 2.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		DbConnection dbCon = DbConnection.getInstance();

		  try {
		        con = dbCon.getConn();
		        StringBuilder selectINQ = new StringBuilder();
		        selectINQ
		            .append("	SELECT I.Inq_Id Inq_Id, M.Mem_Name Mem_Name,I.Inq_Reg_Date Inq_Reg_Date, I.Inq_Title Inq_Title, I.REPLY, ")
		            .append("           CASE ")
		            .append("               WHEN REPLY IS NOT NULL THEN '답변 완료' ")
		            .append("               ELSE '답변 대기' ")
		            .append("           END AS Inq_Status ")
		            .append("	FROM INQUIRY I INNER JOIN MEMBER M ON I.MEM_NUM = M.MEM_NUM ")
		            .append("	order by Inq_Reg_Date desc ");

		        pstmt = con.prepareStatement(selectINQ.toString());
		        
		        rs = pstmt.executeQuery();
		        

		        Inquiry_VO iVO = null;
		        while (rs.next()) {
		            iVO = new Inquiry_VO();

		            iVO.setInq_Id(rs.getInt("Inq_Id"));
		            iVO.setMem_Name(rs.getString("Mem_Name")); 
		            iVO.setInq_Reg_Date(rs.getDate("Inq_Reg_Date"));
		            iVO.setInq_Title(rs.getString("Inq_Title"));
		            iVO.setInq_Status(rs.getString("Inq_Status")); 

		            list.add(iVO);
		        } // end while
		    } finally {
		        dbCon.closeDB(rs, pstmt, con);
		    }

		    return list;
		}//selectINQ
	
	public Inquiry_VO select_Edit_INQ(Object inqId) throws SQLException {
		Inquiry_VO inq = null;
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    DbConnection dbCon = DbConnection.getInstance();
	    try {
	        con = dbCon.getConn();
	        
	        StringBuilder selectINQ = new StringBuilder();
	        selectINQ
            .append("	SELECT I.Inq_Title Inq_Title, I.INQ_Contents INQ_Contents, I.Reply Reply, M.Mem_Name Mem_Name, I.Inq_Reg_Date Inq_Reg_Date, ")
            .append("           CASE ")
            .append("               WHEN REPLY IS NOT NULL THEN '답변 완료' ")
            .append("               ELSE '답변 대기' ")
            .append("           END AS Inq_Status ")
            .append("	FROM INQUIRY i, Member m	")
        	.append("	where (m.mem_num=i.mem_num)and i.Inq_id=? ")
        	.append("	order by I.Inq_Reg_Date desc ");
	        
	        pstmt = con.prepareStatement(selectINQ.toString());
	        pstmt.setObject(1, inqId);
	        
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            inq = new Inquiry_VO();
	            inq.setInq_Title(rs.getString("Inq_Title"));
	            inq.setInq_Contents(rs.getString("Inq_Contents"));
	            inq.setInq_Reply(rs.getString("Reply"));;
	            inq.setMem_Name(rs.getString("Mem_Name"));
	            inq.setInq_Reg_Date(rs.getDate("Inq_Reg_Date"));
	            inq.setInq_Status(rs.getString("Inq_Status"));
	        }//end if
	    } finally {
	    	dbCon.closeDB(rs, pstmt, con);
	    }//end finally
	    
	    return inq;
	}//selectINQ
	
	public int updateInq(Inquiry_VO iVO, Object inqId) throws SQLException {
		int rowCnt=0;
	    Connection con = null;
	    PreparedStatement pstmt = null;

	    DbConnection dbCon = DbConnection.getInstance();

	    try {
	        con = dbCon.getConn();
	        con.setAutoCommit(false); 

	        
	        StringBuilder updateINQ = new StringBuilder();
	        updateINQ
	            .append("	update INQUIRY	 ")
	            .append("	set  REPLY = ?, REPLY_REG_DATE = sysdate	")
	        	.append("	where INQ_ID = ?	");

	        pstmt = con.prepareStatement(updateINQ.toString());
	        pstmt.setString(1, iVO.getInq_Reply());
	        pstmt.setObject(2, inqId);

	        rowCnt = pstmt.executeUpdate();
	        con.commit(); 
	    } finally {
	        dbCon.closeDB(null, pstmt, con);
	    }//end finally
	    
	    
	    return rowCnt;
	}//updateInq

	
	
}//class
