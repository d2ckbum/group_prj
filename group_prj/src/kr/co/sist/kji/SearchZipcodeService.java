package kr.co.sist.kji;

import java.sql.SQLException;
import java.util.List;

public class SearchZipcodeService {

	public List<ZipcodeVO> searchZipcode(String dong){
		List<ZipcodeVO> list =null;
		
		try {
			list=ZipcodeDAO.getInstance().selectZipcode(dong);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
	}
}//class
