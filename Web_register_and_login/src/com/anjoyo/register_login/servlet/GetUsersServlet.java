package com.anjoyo.register_login.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anjoyo.register_login.bean.User;
import com.anjoyo.register_login.dao.UserDao;
import com.google.gson.Gson;

public class GetUsersServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserDao dao=UserDao.getInstance();
		List<User> users = dao.getUsers();
		PrintWriter writer = resp.getWriter();
		if(users!=null){
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("count", users.size());
			map.put("results", users);
			Gson gson=new Gson();
			String json = gson.toJson(map);
			writer.print(json);
		}else {
			writer.print("-1");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
