package kr.co.sist.cjw.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.cjw.dao.Admin_Inquiry_Dao;
import kr.co.sist.cjw.vo.FAQ_VO;

public class Admin_Inquiry_Service {
	
	
	public List<FAQ_VO> searchFAQ(){
		List<FAQ_VO> list = null;
		
		Admin_Inquiry_Dao aIDAO = Admin_Inquiry_Dao.getInstance();
		try {
			list=aIDAO.selectFAQ();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return list;
	}//searchAllMember
	
	public boolean addFAQ(FAQ_VO aIVO) {
		boolean flag = false;
		Admin_Inquiry_Dao  aIDAO = Admin_Inquiry_Dao.getInstance();
		
		try {
			aIDAO.insertFAQ(aIVO);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}// addFAQ
	

}//class
