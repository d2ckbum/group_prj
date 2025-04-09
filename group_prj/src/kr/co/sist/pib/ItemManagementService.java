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
	public boolean addImMember(ItemManagementVO imVO) {
		ItemManagementDAO imDAO = ItemManagementDAO.getInstance();
		try {
			imDAO.insertImMember(imVO);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error Message: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
		}//end catch
		return false;
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
			  String pattern = "\"([^\"]+)\"\\.\"([^\"]+)\"\\.\"([^\"]+)\".*실제:\\s*(\\d+)";
		        java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern);
		        java.util.regex.Matcher matcher = regex.matcher(e.getMessage());
		        
		        if (matcher.find()) {
		            // 컬럼 이름 (matcher.group(3))을 추출
		            System.out.println("Error Message Column: " + matcher.group(3));
		            
		        } else {
		            System.out.println("No match found.");
		        }
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
