package kr.co.sist.kji;

import java.sql.SQLException;
import java.util.List;

public class MfgService {

	public MfgService() {
	}//MemberService
	
	public List<MfgVO> searchAllMember(){
		List<MfgVO> list = null;
		
		MfgDAO mfgDAO = MfgDAO.getInstance();
		try {
			list=mfgDAO.selectAllMember();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return list;
	}//searchAllMember
	
	
	
	public int searchOneCarNum(String id) {
		int mfgNum =-1;
		MfgDAO mfgDAO = MfgDAO.getInstance();
		
		try {
			mfgNum=mfgDAO.selectOneMfgNum(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return mfgNum;
	}//searchOneMember
}//class
