package com.tedu.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tedu.bean.UserBean;
import com.tedu.util.MysqlConnection;

@WebServlet(value = {"/responseTest.do","/login.do"})
//value是这个注解中记录路由的属性，本身就是一个数组
//这里的含义是这个servlet由两个url绑定路径
public class ResponseTest extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String uname = req.getParameter("uname");
		String upwd = req.getParameter("upwd");
		//需要访问数据库，查询等操作
		//假设查询成功，跳回到主页面
		System.out.println("uname" + uname);
		req.setAttribute("utel", "1145141919810");
		
		//boolean bl = false; //假设用户登录 false为失败 true成功
		
		UserBean bean=login(uname,upwd);
		
		if(bean != null){
		
		//重点强调：在服务器内部一个servlet和另外一个servlet(jsp)交互
		//使用转发功能 同时这个功能可以在不同的servlet中传递参数
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		//forward(req, resp)转发并携带数据
		}else {
			//resp.sendRedirect("no.html"); //写的是相对路径
			resp.sendRedirect("https://www.baidu.com");
		}
	}
	
//	用来链接数据库，处理数据
	public UserBean login(String uname,String upwd) {
		Connection conn = MysqlConnection.getConnection();
		PreparedStatement sta=null;
		ResultSet set =null;
		String sql="select * from tb_user where uname=? and upwd=?";
//		就是执行SQL语句，并将查询出来的结果 封装到 UserBean中去
		UserBean bean = null;
		try {
			sta = conn.prepareStatement(sql); //预加载
			sta.setString(1, uname);
			sta.setString(2, upwd);
			set = sta.executeQuery(); //这里现在不要有sql传入
			if(set.next()) {
				bean = new UserBean();
				bean.setUname(set.getString("uname"));
				bean.setUpwd(set.getString("upwd"));
				bean.setAge(set.getInt("age"));
				bean.setUtel(set.getString("utel"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			MysqlConnection.close(set,sta,conn);
		}
		
		return bean;
	}
}
/**
1.建立数据库表，添加默认数据
create table tb_user(
uname varchar(20) not null primary key,
upwd  varchar(20) not null,
utel  varchar(20) null,
age   int(11) default 18
);
insert into tb_user value('aaa','1111','15966668888',16);
2.按照数据库表字段名称 按需求定义 UserBean
尽量保证 数据库的字段名称就是 javabean中的属性名称

3.编写我们的login
 */

