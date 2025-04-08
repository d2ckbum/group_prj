package kr.co.sist.cjw.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.cjw.dao.Mem_Inquiry_Dao;
import kr.co.sist.cjw.vo.FAQ_VO;
import kr.co.sist.cjw.vo.Inquiry_VO;

public class Mem_Inquiry_Service {
	
	public List<FAQ_VO> searchFAQ(){
		List<FAQ_VO> list = null;
		
		Mem_Inquiry_Dao aIDAO = Mem_Inquiry_Dao.getInstance();
		try {
			list=aIDAO.selectFAQ();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return list;
	}//searchFAQ
	
	public List<Inquiry_VO> searchINQ(String id){
		List<Inquiry_VO> list = null;
		
		Mem_Inquiry_Dao aIDAO = Mem_Inquiry_Dao.getInstance();
		try {
			list=aIDAO.selectINQ(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return list;
	}//searchINQ
	
	
	public boolean addInq(Inquiry_VO inq, String id){
		boolean flag = false;
		Mem_Inquiry_Dao mIDAO = Mem_Inquiry_Dao.getInstance(); 
			
			try {
				mIDAO.insertInq(inq, id);
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
			return flag;
		
	}//addInq

}//class
