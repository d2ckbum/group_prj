package kr.co.sist.kyh;

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
		
	}

	public static DbConnection getInstance() {
		
		if(dbCon == null) {
			dbCon = new DbConnection();
		}
		return dbCon;
	}
	
	public Connection getConn() throws SQLException{

		String currentDir =System.getProperty("user.dir");
		File file = new File(currentDir+"/src/properties/database.properties");
		
		if(!file.exists()) {
			throw new SQLException("database.properties가 지정된 경로에 존재하지 않습니다.");
		}
		

		Properties prop = new Properties();

		String driver="";
		String url="";
		String id="";
		String pass="";
		
		try {
			prop.load(new FileInputStream(file));
			driver=prop.getProperty("driverClass");
			url=prop.getProperty("url");
			id=prop.getProperty("id");
			pass=prop.getProperty("pass");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Class.forName(driver);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null;
		
		con = DriverManager.getConnection(url, id, pass);
		
		return con;
	}
	
	public static void main(String[] args ) {
		
		try {
			System.out.println(new DbConnection().getConn());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDB(ResultSet rs, Statement stmt, Connection con)  throws SQLException{
		
		try {
		if(rs != null) {rs.close();}
		if(stmt != null) {stmt.close();}
		
		} finally {
		if(con != null) {con.close();}
		}
	}
}
