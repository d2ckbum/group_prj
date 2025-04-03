package kr.co.sist.kji;

import java.sql.SQLException;
import java.util.List;

public class MemberService {

	public MemberService() {
	}//MemberService
	
	public List<MemberVO> searchAllMember(){
		List<MemberVO> list = null;
		
		MemberDAO pDAO = MemberDAO.getInstance();
		try {
			list=pDAO.selectAllMember();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return list;
	}//searchAllMember
	
	
	
	public MemberVO searchOneMember(String id) {
		MemberVO mVO =null;
		MemberDAO mDAO = MemberDAO.getInstance();
		
		try {
			mVO=mDAO.selectOneMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return mVO;
	}//searchOneMember
}//class
