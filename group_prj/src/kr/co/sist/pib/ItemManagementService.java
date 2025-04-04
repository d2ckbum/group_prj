package kr.co.sist.pib;

import java.sql.SQLException;
import java.util.List;


public class ItemManagementService {

	public ItemManagementService() {
		
		
	}
	
	//전체 데이터 조회
	public List<ItemManagementVO> searchAllMember(){
		
		List<ItemManagementVO> list = null;
		
		ItemManagementDAO imDAO = ItemManagementDAO.getInstance();
		try {
			list = imDAO.selectAllMember();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return list;
		
	}
	
	//한 행 조회
	public ItemManagementVO searchOneMember(int num) {
		ItemManagementVO pmVO = null;
		ItemManagementDAO imDAO = ItemManagementDAO.getInstance();
		try {
			pmVO=imDAO.selectOneMember(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return pmVO;
		
		
	}
	
	//데이터 추가 업무
	public int addImMember(ItemManagementVO imVO) {
		
		return 0;
	}
	
	//데이터 수정 업무
	public boolean modifyImMember(ItemManagementVO imVO) {
		ItemManagementDAO imDAO = ItemManagementDAO.getInstance();
		
		try {
			if(imDAO.updateImMember(imVO) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//데이터 삭제 업무
	public boolean removeImMember(int productnum) {
		ItemManagementDAO imDAO = ItemManagementDAO.getInstance();
	
		try {
			if(imDAO.deleteImMember(productnum) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}
		
}
