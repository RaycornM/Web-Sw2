package com.tedu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 单例模式 
 * 1.在内存中 有且只有这个类一个实体对象(实例)
 * 3要素：
 * a.一个静态的引用
 * b.构造方法私有化
 * c.唯一的一个开发的获取这个对象的静态方法
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
	//synchronized 线程保护
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
	
//	所有的包都是 java.sql包下的
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







