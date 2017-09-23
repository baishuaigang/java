package com.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.Page;
import com.dao.UserDao;
import com.entity.User;

@WebServlet("*.do")
public class Service extends HttpServlet {
	UserDao dao = null ;
	public Service() {
		 dao = new UserDao();
	}
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");// 通知服务器发送数据时查阅的码表
		resp.setContentType("text/html;charset=utf-8");// 通知浏览器以何种码表打开
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		if (action.equals("list")) {//列表逻辑
			lists(req,resp);
		} else if (action.equals("login")) {//登陆逻辑
			login(req,resp);
		} else if (action.equals("delete")) {//删除
			delete(req,resp);	
		} else if (action.equals("update")) {//修改信息跳转到页面
			update(req,resp);
		} else if (action.equals("updateUser")) {//修改用户
			updateUser(req,resp);
		} else if (action.equals("addUser")) {//增加用户
			addUser(req,resp);
		} else if (action.equals("loginout")) {//退出登陆
			loginout(req,resp);
		} else if (action.equals("register")) {//注册用户
			register(req,resp);
		}
	}
	public void register(HttpServletRequest req, HttpServletResponse resp) {
		String user = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		User u = dao.checkUser(user);
		if (u != null) {
			req.setAttribute("msg", "此用户名已被注册");
			try {
				req.getRequestDispatcher("register.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			 u = new User(user, pwd, email);
			dao.addUser(u);
			req.getSession().setAttribute("user", user);
			try {
				resp.sendRedirect("list.do");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void loginout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().removeAttribute("user");
		try {
			resp.sendRedirect("login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void addUser(HttpServletRequest req, HttpServletResponse resp) {
		String user = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		User u = new User(user, pwd, email);
		dao.addUser(u);
		try {
			resp.sendRedirect("list.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void updateUser(HttpServletRequest req, HttpServletResponse resp) {
		String uid = req.getParameter("uid");
		int uidint = Integer.parseInt(uid);
		String user = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		User u = new User(uidint, user, pwd, email);
		dao.setUser(u);
		try {
			resp.sendRedirect("list.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update(HttpServletRequest req, HttpServletResponse resp) {
		String uid = req.getParameter("uid");
		int uidint = Integer.parseInt(uid);
		User u = dao.updateDao(uidint);
		req.setAttribute("user", u);
		try {
			req.getRequestDispatcher("/update.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void delete(HttpServletRequest req, HttpServletResponse resp) {
		String uid = req.getParameter("uid");
		int uidint = Integer.parseInt(uid);
		dao.deleteDao(uidint);
		try {
			resp.sendRedirect("list.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//列表
	public void lists(HttpServletRequest req, HttpServletResponse resp){
		int count = dao.getAmount();
		int pagesize = 4;
		String c = req.getParameter("curpage") == null ? "1" : req.getParameter("curpage");
		int curr = Integer.parseInt(c);
		Page page = new Page(count, curr, pagesize);
		List<User> list = dao.getSelect(page);
		req.setAttribute("page", page);
		req.setAttribute("list", list);
		try {
			req.getRequestDispatcher("/list.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//登陆
	public void login(HttpServletRequest req, HttpServletResponse resp) {
		String user = req.getParameter("user");
		String pwd = req.getParameter("pwd");
		User u = dao.checkUser(user);
		if (u != null) {
			if (user.equals(u.getUsername()) && pwd.equals(u.getPassword()) && u.getUsername() != null) {
				req.getSession().setAttribute("user", user);
				try {
					resp.sendRedirect("list.do");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				req.setAttribute("msg", "用户名或密码错误");
				try {
					req.getRequestDispatcher("login.jsp").forward(req, resp);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			req.setAttribute("msg", "没有此用户");
			try {
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
