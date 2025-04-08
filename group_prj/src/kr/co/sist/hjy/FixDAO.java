package kr.co.sist.hjy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
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
	
	

	
	
	public StringBuilder clobConvert(Clob clob) {
		StringBuilder sb=null;
		String memo="";
		
		Reader reader=null;
		BufferedReader br=null;
		
		try {
			reader=clob.getCharacterStream();
			br=new BufferedReader(reader);
			
			while((memo=br.readLine())!=null){
				sb.append(memo);
			}//end while
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}finally{
			if(reader != null) {try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			if(br != null) {try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		
		
		return sb;
	}//clobConvert
	
	/**
	 * 처리 상태와 정비 메모 수정
	 * @param fixStatus 처리상태
	 * @param fixMemo 정비메모
	 * @param chosenFixNum 선택한 정비접수번호
	 * @throws SQLException
	 */
	public void updateStatusAMemo(String fixStatus, String fixMemo, String chosenFixNum) throws SQLException {
		//update하고 나서, 해당 List<FixPanelVO>도 바꿔주야한다.
		//흠...이건 service 부분에서 처리하는게 낫겠다.
		StringBuilder updateStatusMemo=new StringBuilder();

			updateStatusMemo
			.append("	update	fix")
			.append("	set		fix_status=?, fix_memo=?")
			.append("	where	fix_num=?")
			;

		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConnection dbCon=DbConnection.getInstance();
		
		try {
			con=dbCon.getConn();
						
			pstmt=con.prepareStatement(updateStatusMemo.toString());

			pstmt.setString(1, fixStatus);//정비 상태 변경
			pstmt.setString(2, fixMemo);//setStringtoClob
			pstmt.setString(3, chosenFixNum);//변경할 상태,메모의 정비 접수 번호

			int returnVal=pstmt.executeUpdate(); //업데이트 실행
//			System.out.println("returnVal   "+returnVal);
			
		}catch (SQLException se) {
			se.printStackTrace();
			dbCon.closeDB(null, pstmt, con);
		}//end try~catch
	
	
	}//updateStatusMemo
	
	/**
	 * 정비메모 변경
	 * @param fixStatus
	 * @param fixMemo
	 * @param chosenFixNum
	 * @throws SQLException
	 */
	public void updateMemo(String fixMemo, String chosenFixNum) throws SQLException {
		//update하고 나서, 해당 List<FixPanelVO>도 바꿔주야한다.
		//흠...이건 service 부분에서 처리하는게 낫겠다.
		StringBuilder updateStatusMemo=new StringBuilder();
		

		updateStatusMemo
		.append("	update	fix")
		.append("	set		fix_memo=?")
		.append("	where	fix_num=?")
		;

		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConnection dbCon=DbConnection.getInstance();
		
		try {
			con=dbCon.getConn();
						
			pstmt=con.prepareStatement(updateStatusMemo.toString());
			
		
			pstmt.setString(1, fixMemo);//정비 메모 변경
			pstmt.setString(2, chosenFixNum);//변경할 상태,메모의 정비 접수 번호
		

			
			int returnVal=pstmt.executeUpdate(); //업데이트 실행
//			System.out.println("returnVal   "+returnVal);
			
		}catch (SQLException se) {
			se.printStackTrace();
			dbCon.closeDB(null, pstmt, con);
		}//end try~catch
		
		
	}//updateMmemo
	

	
}//class
