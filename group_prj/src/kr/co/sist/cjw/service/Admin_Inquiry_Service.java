package kr.co.sist.cjw.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.cjw.dao.Admin_Inquiry_Dao;
import kr.co.sist.cjw.vo.FAQ_VO;
import kr.co.sist.cjw.vo.Inquiry_VO;

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
	}//searchFAQ
	
	
	public FAQ_VO search_Edit_FAQ(Object faqSub){
		FAQ_VO faq = null;
		
		Admin_Inquiry_Dao aIDAO = Admin_Inquiry_Dao.getInstance();
		try {
			faq=aIDAO.select_Edit_FAQ(faqSub);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return faq;
	}//search_Edit_FAQ
	
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
	
	public List<Inquiry_VO> searchINQ(){
		List<Inquiry_VO> list = null;
		
		Admin_Inquiry_Dao aIDAO = Admin_Inquiry_Dao.getInstance();
		try {
			list=aIDAO.selectINQ();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return list;
	}//searchINQ
	
	public Inquiry_VO search_Edit_INQ(Object inqId){
		Inquiry_VO inq = null;
		
		Admin_Inquiry_Dao aIDAO = Admin_Inquiry_Dao.getInstance(); 
		try {
			inq=aIDAO.select_Edit_INQ(inqId);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return inq;
	}//search_Edit_INQ
	

}//class
