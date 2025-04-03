package kr.co.sist.kji;

import java.sql.SQLException;

public class LoginService {

	public String login(LoginVO lVO) {
		String name="";
		
		try {
			//Statement : SQLInjection발생 > 대비코드 작성
//			name=LoginDAO.getInstance().selectLogin(lVO);
			
			//PreparedStatement : SQLInjection 발생
			name=LoginDAO.getInstance().selectPstmtLogin(lVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return name;
	}//login
	
	
}//class

