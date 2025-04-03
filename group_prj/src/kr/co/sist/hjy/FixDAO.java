package kr.co.sist.hjy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FixDAO {
	private static FixDAO fDAO;
	
	public FixDAO() {
	
	}//FixDAO
	
	/**
	 * 여기저기에서 객체로 많이 생성될 것 같아서, 그냥 Singleton으로 생성함.
	 * @return FixDAO
	 */
	public static FixDAO getInstance() {
		if(fDAO == null) {
			fDAO = new FixDAO();
		}//end if
		return fDAO;
	}//getInstance
	
	//우선 join을 이용해서, 내가 필요한 상품명, 상품코드 등을 가져오자.
	
	
	
	/**
	 * 모든 테이블 레코드 반환
	 * select * from fix의 결과와 같다.
	 * @return List<FixVO> listAll
	 * @throws SQLException
	 */
	public List<FixPanelVO> selectAllList() throws SQLException{
		List<FixPanelVO> listAll=new ArrayList<FixPanelVO>();
		
		StringBuilder selectAllFix=new StringBuilder();
		selectAllFix
		.append("	select	f.FIX_NUM, f.FIX_REG_DATE,  i.ITEM_NAME,   			")
		.append("	mf.mfg_name,   c.CAR_TYPE,   m.mem_num,   m.MEM_NAME,		")
		.append("i.item_price+i.item_repair_cost as total,   f.fix_status, f.fix_memo, m.mem_tell 		")
		.append("	from	fix f, item i, car c, member m,manufacturer mf		")
		.append("	where (f.mem_num=m.mem_num and i.item_num=f.item_num and m.car_num=c.car_num and m.mfg_num=mf.mfg_num)		")
		.append("   order by f.fix_num desc")
		;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection dbCon=DbConnection.getInstance();
		
		try {
			con=dbCon.getConn();
						
			pstmt=con.prepareStatement(selectAllFix.toString());
			rs=pstmt.executeQuery();
		
			FixPanelVO fixPanelVO=null;
			while(rs.next()) {
				fixPanelVO=new FixPanelVO(
						rs.getString("fix_num"),rs.getTimestamp("fix_reg_date"),rs.getClob("fix_memo"),
						rs.getString("item_name"),rs.getString("mfg_name"),
						rs.getString("car_type"), rs.getInt("mem_num"), rs.getString("mem_name"),
						rs.getInt("total"),rs.getString("fix_status"),rs.getString("mem_tell"));	
	
				listAll.add(fixPanelVO);		
			}//end while
		}catch (SQLException se) {
			se.printStackTrace();
			dbCon.closeDB(rs, pstmt, con);
		}//end try~catch
		
		return listAll;
	}//selectAllList
	

	
}//class
