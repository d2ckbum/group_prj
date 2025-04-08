package kr.co.sist.kji;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class MemberService {
	 Map<String, String> fieldMap = new HashMap<>();


	String errMsg="";
	
	public MemberService() {
		fieldMap.put("ID", "아이디");
		fieldMap.put("PASS", "비밀번호");
		fieldMap.put("NAME", "이름");
		fieldMap.put("EMAIL", "이메일");
		fieldMap.put("TELL", "전화번호");
		fieldMap.put("ZIPCODE", "우편번호");
		fieldMap.put("ADDR1", "주소");
		fieldMap.put("ADDR2", "상세주소");
	}// MemberService

	public List<FixPanelVO> searchAllFixinfo(String id) {
		List<FixPanelVO> list = null;
		MemberDAO mDAO = MemberDAO.getInstance();

		try {
			list = mDAO.selectAllMyFixinfo(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return list;
	}// searchOneMember

	public boolean addMember(MemberVO mVO) {
		boolean flag = false;
		MemberDAO mDAO = MemberDAO.getInstance();

		try {
			mDAO.insertMember(mVO);
			flag = true;
		} catch (SQLException e) {
			if (e.getErrorCode() == 12899) {
				errMsg= e.toString().substring(e.toString().lastIndexOf("_")+1,e.toString().lastIndexOf("\""));
				JOptionPane.showMessageDialog(null, fieldMap.get(errMsg)+"값이 너무 큽니다. ("+
						e.toString().substring(e.toString().lastIndexOf("(")+1));
				return flag;

			}
			e.printStackTrace();
		} // end catch

		return flag;
	}// addPstmtMember

	public boolean modifyMember(MemberVO mVO) {
		boolean flag = false;

		MemberDAO mDAO = MemberDAO.getInstance();

		try {
			flag = (mDAO.updateMember(mVO) == 1);
		} catch (SQLException e) {
			if (e.getErrorCode() == 12899) {
				errMsg= e.toString().substring(e.toString().lastIndexOf("_")+1,e.toString().lastIndexOf("\""));
				JOptionPane.showMessageDialog(null, fieldMap.get(errMsg)+"값이 너무 큽니다. ("+
						e.toString().substring(e.toString().lastIndexOf("(")+1));
				return flag;

			}
			e.printStackTrace();
		} // end catch

		return flag;
	}// modifyPstmtMember

	public boolean removeMember(String id) {
		boolean flag = false;

		MemberDAO mDAO = MemberDAO.getInstance();

		try {
			flag = (mDAO.deleteMember(id) == 1);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return flag;
	}// modifyPstmtMember

	public MemberVO login(LoginVO lVO) {
		MemberVO mVO = new MemberVO();

		try {
			MemberDAO mDAO = MemberDAO.getInstance();
			mVO = mDAO.login(lVO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return mVO;
	}// login

	public List<MemberVO> searchAllMember() {
		List<MemberVO> list = null;

		MemberDAO mDAO = MemberDAO.getInstance();
		try {
			list = mDAO.selectAllMember();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return list;
	}// searchAllMember

	public MemberVO searchOneMember(String id) {
		MemberVO mVO = null;
		MemberDAO mDAO = MemberDAO.getInstance();

		try {
			mVO = mDAO.selectOneMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return mVO;
	}// searchOneMember

	public MemberDataDTO searchOneMemberData(String id) {
		MemberDataDTO mDTO = null;
		MemberDAO mDAO = MemberDAO.getInstance();
		try {
			mDTO = mDAO.selectOneMemberData(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end catch

		return mDTO;
	}// searchOneMemberData

}// class
