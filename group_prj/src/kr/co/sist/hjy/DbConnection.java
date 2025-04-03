package kr.co.sist.hjy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class DbConnection {
	private static DbConnection dbCon;
	
	private DbConnection() {
		
	}//DbConnection
	
	public static DbConnection getInstance() {
		if(dbCon==null) {
			dbCon=new DbConnection();
		}//end if
		return dbCon;
	}//getInstance
	
	public Connection getConn() throws SQLException {
		//properties 파일 사용(소스코드에 계정정보를 하드코딩하지 않는다.)
		String currentDir=System.getProperty("user.dir");
		System.out.println(currentDir);
//		File file=new File(currentDir+"/FirstProject/src/Properties/database.properties"); //집
		File file=new File(currentDir+"/src/Properties/database.properties"); //properties가 꼭 이 위치에 있어야 하는건 아니고, 내가 원하는 지점에 있으면 된다.
		if(!file.exists()) {
			throw new SQLException("database.properties가 지정된 경로에 존재하지 않습니다."); //early return의 효과가 있지.
		}//end if

		//생성
		Properties prop=new Properties();
		String driver="";
		String url="";
		String id="";
		String pass="";
	
		//파일 로딩
		try {
			prop.load(new FileInputStream(file));
			driver=prop.getProperty("driverClass");
			url=prop.getProperty("url");
			id=prop.getProperty("id");
			pass=prop.getProperty("pass");
		}catch (IOException e) {
			e.printStackTrace();
		}//try~catch
		
		//1.드라이버 로딩
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//try~catch
		
		
		Connection con=null;
		
		//2.connection 얻기	
		con=DriverManager.getConnection(url,id,pass);
		
		return con;
	}//getConn
	
	
	public void closeDB(ResultSet rs, Statement stmt, Connection con) throws SQLException{
		try {
			if(rs != null) {rs.close();}//end if
			if(stmt != null) {stmt.close();}//end if
		}finally {
			if(con != null) {con.close();}//end if - 얘는 반드시 끊어져야 함. 그래서 finally로 잡아준다.
		}//end try~finally
		
	}//closeDB
	
}//class
