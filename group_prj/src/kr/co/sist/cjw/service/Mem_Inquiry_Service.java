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
	
	
	public boolean addInq(Inquiry_VO iVO, String id){
		boolean flag = false;
		Mem_Inquiry_Dao mIDAO = Mem_Inquiry_Dao.getInstance(); 
			
			try {
				mIDAO.insertInq(iVO, id);
				flag = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
			
			return flag;
		
	}//addInq
	
	public Inquiry_VO search_Edit_INQ(Object inqId){
		Inquiry_VO inq = null;
		
		Mem_Inquiry_Dao mIDAO = Mem_Inquiry_Dao.getInstance(); 
		try {
			inq=mIDAO.select_Edit_INQ(inqId);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return inq;
	}//search_Edit_INQ
	
	public boolean modifyInq(Inquiry_VO iVO, String inqId){
		boolean flag= false;
		
		Mem_Inquiry_Dao mIDAO = Mem_Inquiry_Dao.getInstance(); 
		try {
			flag=(mIDAO.updateInq(iVO,inqId) ==1);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return flag;
	}//search_Edit_INQ

}//class
