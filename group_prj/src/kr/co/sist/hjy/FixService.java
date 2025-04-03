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
		}//try~catch
		
		return list;
	}//viewAllFixList
	
}//class
