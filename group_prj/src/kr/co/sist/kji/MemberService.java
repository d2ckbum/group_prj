package kr.co.sist.kji;

import java.sql.SQLException;
import java.util.List;

public class MemberService {

	public MemberService() {
	}//MemberService
	
	
	public boolean addMember(MemberVO mVO) {
		boolean flag = false;
		MemberDAO  mDAO = MemberDAO.getInstance();
		
		try {
			mDAO.insertMember(mVO);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}// addPstmtMember
	
	
	public boolean modifyMember(MemberVO mVO) {
		boolean flag = false;

		MemberDAO mDAO = MemberDAO.getInstance();
		
		try {
			flag=(mDAO.updateMember(mVO)==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		

		return flag;
	}// modifyPstmtMember
	
	public boolean removeMember(String id) {
		boolean flag = false;

		MemberDAO mDAO = MemberDAO.getInstance();
		
		try {
			flag=(mDAO.deleteMember(id)==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		

		return flag;
	}// modifyPstmtMember
	
	
	public MemberVO login(LoginVO lVO) {
		MemberVO mVO= new MemberVO();
		
		try {
			MemberDAO  mDAO = MemberDAO.getInstance();
			mVO=mDAO.login(lVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return mVO;
	}//login
	
	public List<MemberVO> searchAllMember(){
		List<MemberVO> list = null;
		
		MemberDAO mDAO = MemberDAO.getInstance();
		try {
			list=mDAO.selectAllMember();
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
	
	public MemberDataDTO searchOneMemberData(String id) {
		MemberDataDTO mDTO = null;
		MemberDAO mDAO = MemberDAO.getInstance();
		try {
			mDTO = mDAO.selectOneMemberData(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end catch
		
		return mDTO;
	}//searchOneMemberData
	
}//class
