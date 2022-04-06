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
//value�����ע���м�¼·�ɵ����ԣ��������һ������
//����ĺ��������servlet������url��·��
public class ResponseTest extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String uname = req.getParameter("uname");
		String upwd = req.getParameter("upwd");
		//��Ҫ�������ݿ⣬��ѯ�Ȳ���
		//�����ѯ�ɹ������ص���ҳ��
		System.out.println("uname" + uname);
		req.setAttribute("utel", "1145141919810");
		
		//boolean bl = false; //�����û���¼ falseΪʧ�� true�ɹ�
		
		UserBean bean=login(uname,upwd);
		
		if(bean != null){
		
		//�ص�ǿ�����ڷ������ڲ�һ��servlet������һ��servlet(jsp)����
		//ʹ��ת������ ͬʱ������ܿ����ڲ�ͬ��servlet�д��ݲ���
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		//forward(req, resp)ת����Я������
		}else {
			//resp.sendRedirect("no.html"); //д�������·��
			resp.sendRedirect("https://www.baidu.com");
		}
	}
	
//	�����������ݿ⣬��������
	public UserBean login(String uname,String upwd) {
		Connection conn = MysqlConnection.getConnection();
		PreparedStatement sta=null;
		ResultSet set =null;
		String sql="select * from tb_user where uname=? and upwd=?";
//		����ִ��SQL��䣬������ѯ�����Ľ�� ��װ�� UserBean��ȥ
		UserBean bean = null;
		try {
			sta = conn.prepareStatement(sql); //Ԥ����
			sta.setString(1, uname);
			sta.setString(2, upwd);
			set = sta.executeQuery(); //�������ڲ�Ҫ��sql����
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
1.�������ݿ�����Ĭ������
create table tb_user(
uname varchar(20) not null primary key,
upwd  varchar(20) not null,
utel  varchar(20) null,
age   int(11) default 18
);
insert into tb_user value('aaa','1111','15966668888',16);
2.�������ݿ���ֶ����� �������� UserBean
������֤ ���ݿ���ֶ����ƾ��� javabean�е���������

3.��д���ǵ�login
 */

