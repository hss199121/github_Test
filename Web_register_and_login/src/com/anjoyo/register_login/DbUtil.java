package com.anjoyo.register_login;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


public class DbUtil {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/test";
	public static final String USER_NAME = "root";  //用户名
	public static final String PASSWORD = "root"; // 密码
	/**
	 * 获取与数据库的连接
	 * @return
	 */
	public static Connection getConn(){
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = (Connection) DriverManager.getConnection(
					URL, USER_NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

