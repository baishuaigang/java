package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.DBUtil;
import com.Page;
import com.entity.User;

public class UserDao {
	DBUtil db = null;
	boolean flag;

	public UserDao() {
		db = new DBUtil();
	}

	// 查询用户列表
	public List<User> getSelect(Page page) {
		Connection conn = db.getConnection();
		String sql = "select * from user limit ?,?";
		List<User> list = new ArrayList<User>();
		PreparedStatement pst = null;
		try {
			pst = (PreparedStatement) conn.prepareStatement(sql);
		    pst.setInt(1, page.getStart());
		    pst.setInt(2, page.getSize());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//用户登陆
	public User checkUser(String name) {
		User u = null;
		Connection conn = db.getConnection();
		String sql = "select * from user where username=? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs==null) {
				return null;
			}
			while(rs.next()) {
				if (rs.getString("username").equals(name)) {
					u = new User();
					u.setUid(rs.getInt("uid"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setEmail(rs.getString("email"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	// 获取条数
		public int getAmount() {
			Connection conn = db.getConnection();
			String sql = "SELECT COUNT(*) FROM user";
			int count = 0;
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return count;
		}
		// 删除用户信息
		public boolean deleteDao(int uid) {
			Connection conn = db.getConnection();
			String sql = "delete from user where uid=? ";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, uid);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   return true;
		}
		// 根据id查询一条记录
		public User updateDao(int uid) {
			Connection conn = db.getConnection();
			User u = null;
			String sql = "select * from user where uid=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, uid);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					u = new User();
					u.setUid(rs.getInt("uid"));
					u.setUsername(rs.getString("username"));
					u.setPassword(rs.getString("password"));
					u.setEmail(rs.getString("email"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return u;
		}
		// 修改用户信息
		public boolean setUser(User u) {
			Connection conn = db.getConnection();
			String sql = "update user set username=? , password=?,email=? where uid=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, u.getUsername());
				ps.setString(2, u.getPassword());
				ps.setString(3, u.getEmail());
				ps.setInt(4, u.getUid());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("错误");
			}
		  return true;
		}
		// 添加一条用户记录
		public boolean addUser(User u) {
			Connection conn = db.getConnection();
			String sql = "insert into user(username,password,email)values(?,?,?)";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, u.getUsername());
				ps.setString(2, u.getPassword());
				ps.setString(3, u.getEmail());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
}
