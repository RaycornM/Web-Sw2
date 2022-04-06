package com.tedu.bean;

public class UserBean {
	private String uname;
	private String upwd;
	private String utel;
	private int age;
	
	@Override
	public String toString() {
		return "UserBean [uname=" + uname + ", upwd=" + upwd + ", utel=" + utel + ", age=" + age + "]";
	}

	public UserBean() {
		super();
	}
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
}
