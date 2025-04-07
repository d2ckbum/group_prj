package kr.co.sist.kji;

import java.sql.SQLException;
import java.util.List;

public class CarService {

	public CarService() {
	}//MemberService
	
	public List<CarVO> searchAllMember(){
		List<CarVO> list = null;
		
		CarDAO cDAO = CarDAO.getInstance();
		try {
			list=cDAO.selectAllMember();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		
		return list;
	}//searchAllMember
	
	
	
	public int searchOneCarNum(String id) {
		int carNum =-1;
		CarDAO cDAO = CarDAO.getInstance();
		
		try {
			carNum=cDAO.selectOneCarNum(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return carNum;
	}//searchOneMember
}//class
