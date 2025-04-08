package kr.co.sist.hjy;

import java.sql.SQLException;
import java.util.List;

public class FixService {
	
	/*전체 리스트 뿌려주기*/
	public List<FixPanelVO> viewAllFixList() {
		List<FixPanelVO> list=null;
	
		try {
			list=FixDAO.getInstance().selectAllList();

		} catch (SQLException e) {
			e.printStackTrace();
		}//end try~catch
		
		return list;
	}//viewAllFixList
	

	
	/**
	 * 정비 상태 및 정비 메모 업데이트하기
	 * @param fixStatus
	 * @param fixMemo
	 * @param chosenFixNum
	 */
	public void modifyStatusAMemo(String fixStatus, String fixMemo, String chosenFixNum)  {
		try {
			FixDAO.getInstance().updateStatusAMemo(fixStatus, fixMemo, chosenFixNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end try~catch
	}//modifyStatusAMemo
	
	
	/**
	 * 정비메모 업데이트하기
	 * @param fixMemo
	 * @param chosenFixNum
	 */
	public void modifyMemo(String fixMemo, String chosenFixNum) {
		try {
			FixDAO.getInstance().updateMemo(fixMemo, chosenFixNum);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end try~catch
	}//modifyMemo
	
}//class
