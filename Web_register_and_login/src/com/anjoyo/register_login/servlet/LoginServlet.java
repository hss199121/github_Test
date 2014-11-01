package com.anjoyo.register_login.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anjoyo.register_login.bean.User;
import com.anjoyo.register_login.dao.UserDao;

public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		System.out.println("userName:"+userName);
		System.out.println("password:"+password);
		
		//验证账号
		User user=new User(userName,password,null);
		UserDao dao=UserDao.getInstance();
		int result = dao.loginCheck(user);
		PrintWriter writer = resp.getWriter();
		writer.print(result);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
