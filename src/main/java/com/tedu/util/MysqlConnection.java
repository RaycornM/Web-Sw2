package com.tedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ����ģʽ 
 * 1.���ڴ��� ����ֻ�������һ��ʵ�����(ʵ��)
 * 3Ҫ�أ�
 * a.һ����̬������
 * b.���췽��˽�л�
 * c.Ψһ��һ�������Ļ�ȡ�������ľ�̬����
 */

public class MysqlConnection {
	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/servlet";
	private static String name="root";
	private static String pwd="1111";
	
	private static Connection conn=null;
	
	private MysqlConnection() {}
	
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//synchronized �̱߳���
	public synchronized static Connection getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, name, pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
	
//	���еİ����� java.sql���µ�
	public static void close(
			ResultSet set,
			Statement sta,
			Connection conn
			) {
		if(set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sta !=null) {
			try {
				sta.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn !=null) {
			try {
				if(conn.getAutoCommit() == false) {
					conn.setAutoCommit(true);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		Connection conn = MysqlConnection.getConnection();
		System.out.println(conn);
	}
	
	
}







