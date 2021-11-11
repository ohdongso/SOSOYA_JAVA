package sosoya.mvc.util;

import java.io.FileInputStream; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DbUtil {
	private static Properties proFile = new Properties();
	
	/**
	 * 로드
	 * */
	static {
		try {
			proFile.load(new FileInputStream("resources/dbInfo.properties"));
			Class.forName(proFile.getProperty("driverName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * x.properties파일을 로드 시킨, proFile객체 얻기
	 * */
	public static Properties getProFile() {
		return proFile;
	}
	
	/**
	 * connection객체 얻기
	 * */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(
			proFile.getProperty("url"),
			proFile.getProperty("userName"),
			proFile.getProperty("userPass"));
	}

	public static void close(Connection con, Statement st, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(st != null) st.close();
			if(con != null) con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}