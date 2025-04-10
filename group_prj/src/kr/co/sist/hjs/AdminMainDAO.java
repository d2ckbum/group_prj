package kr.co.sist.hjs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMainDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	DbConnection dbConn = DbConnection.getInstance();

	public int getMemberCount() throws SQLException {
		int memberCount = 0;
		try {
			con = dbConn.getConn();
			String sql = "select count(*) from member where mem_flag = 'N'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberCount = rs.getInt(1);
			}
		} finally {
			dbConn.closeDB(rs, pstmt, con);
		}
		return memberCount;
	}// getMemberCount

	public int getItemCount() throws SQLException {
		int itemCount = 0;
		try {
			con = dbConn.getConn();
			String sql = "select count(distinct item_name) from item";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				itemCount = rs.getInt(1);
			}
		} finally {
			dbConn.closeDB(rs, pstmt, con);
		}
		return itemCount;
	}// getItemCount

	/**
	 * 정비 상태별 건수를 배열로 반환합니다. 인덱스 0: 접수 완료 건수 (fix_status = 1) 인덱스 1: 정비 중 건수
	 * (fix_status = 2) 인덱스 2: 정비 완료 건수 (fix_status = 3)
	 * 
	 * @return 정비 상태별 건수를 담은 int 배열
	 * @throws SQLException
	 */
	public int[] getFixCounts() throws SQLException {
		int[] counts = new int[3];
		try {
			con = dbConn.getConn();
			String sql = "select fix_status, count(*) from fix where fix_status in (1, 2, 3) group by fix_status";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int status = rs.getInt("fix_status");
				int count = rs.getInt("COUNT(*)");
				if (status == 1) {
					counts[0] = count; // 접수 완료
				} else if (status == 2) {
					counts[1] = count; // 정비 중
				} else if (status == 3) {
					counts[2] = count; // 정비 완료
				}
			}
		} finally {
			dbConn.closeDB(rs, pstmt, con);
		}
		return counts;
	}// getFixCounts

	public int getSalesCount() throws SQLException {
		int salesCount = 0;
		try {
			con = dbConn.getConn();
			String sql = "select sum(sales_total) from sales";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				salesCount = rs.getInt(1); // 첫 번째 컬럼 값 가져오기
			}
		} finally {
			dbConn.closeDB(rs, pstmt, con);
		} // end finally

		return salesCount;
	}

	public int getInquiryCount() throws SQLException {
		int inquiryCount = 0;

		try {
			con = dbConn.getConn();
			String sql = "select count(*) from inquiry where reply is null";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				inquiryCount = rs.getInt(1);
			} // end if
		} finally {
			dbConn.closeDB(rs, pstmt, con);
		} // end finally

		return inquiryCount;
	}// getInquiryCount

}// class