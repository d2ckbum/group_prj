package kr.co.sist.hjs;

import java.sql.SQLException;

public class AdminLoginService {
	public String login(AdminLoginVO lVO) {
		String name = "";
		
		try {
			//Statement : SQLInjection 발생 > 대비코드 작성
//			name = LoginDAO.getInstance().selectLogin(lVO);
			//PreparedStatement : SQLInjection 발생  x
			name = AdminLoginDAO.getInstance().selectPstmtLogin(lVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
		
	}//login

}//class
