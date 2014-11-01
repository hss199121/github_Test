package com.anjoyo.register_login.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.anjoyo.register_login.DbUtil;
import com.anjoyo.register_login.bean.User;
import com.mysql.jdbc.Connection;


public class UserDao {
	private Connection conn;
	public static final UserDao istance=new UserDao();
	public static final UserDao getInstance(){
		return istance;
	}
	public UserDao() {
		conn=DbUtil.getConn();
	}
	/**
	 * 1 代表登入成功
	 * 2 代表帐号不存在
	 * 3 代表密码错误
	 * -1 代表系统错误
	 * @param user
	 * @return
	 */
	public int loginCheck(User user){
		String sql="select userName from user where userName=?";
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, user.getUserName());
			ResultSet resultset = prepareStatement.executeQuery();
			if(resultset.next()){//说明账号存在
				sql="select userName,password from user where userName=? and password=?";
				prepareStatement=conn.prepareStatement(sql);
				prepareStatement.setString(1, user.getUserName());
				prepareStatement.setString(2, user.getPassword());
				resultset = prepareStatement.executeQuery();
				if(resultset.next()){
					return 1;
				}else {
					return 3;
				}
			}else {
				return 2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	/***
	 * 向user表插入一条数据
	 * @param user
	 * @return
	 */
	public int insert(User user){
		String sql="insert into user(userName,password,tel) values(?,?,?)";
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, user.getUserName());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getTel());
			int result = prepareStatement.executeUpdate();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	/**
	 * 关闭与数据库的链接
	 */
	public void disconn(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public List<User> getUsers(){
		String sql="select * from user";
		try {
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			List<User> users=new ArrayList<User>();
			while(resultSet.next()){
				String userName = resultSet.getString("userName");
				String password = resultSet.getString("password");
				String tel = resultSet.getString("tel");
				users.add(new User(userName,password,tel));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
