package com.speedpay.bean;

import java.io.Serializable;

public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int USER_CUSTOM=0;
	public static final int USER_MARKET=1;
	public static final int USER_SHOP=2;
	public static final int USER_ATM=3;
	
	private int user_id;
	private String user_name;
	private String user_phoneNum;
	private String user_password;
	private String user_bankcardNum;
	private String user_IDNum;
	private int user_isChecked;
	private double user_balance;
	private int user_type;
	private int user_level;
	
	
	
	public User() {
		super();
	}




	public User(int user_id, String user_name, String user_phoneNum,
			String user_password, String user_bankcardNum, String user_IDNum,
			int user_isChecked, double user_balance, int user_type,
			int user_level) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_phoneNum = user_phoneNum;
		this.user_password = user_password;
		this.user_bankcardNum = user_bankcardNum;
		this.user_IDNum = user_IDNum;
		this.user_isChecked = user_isChecked;
		this.user_balance = user_balance;
		this.user_type = user_type;
		this.user_level = user_level;
	}




	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phoneNum() {
		return user_phoneNum;
	}
	public void setUser_phoneNum(String user_phoneNum) {
		this.user_phoneNum = user_phoneNum;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_bankcardNum() {
		return user_bankcardNum;
	}
	public void setUser_bankcardNum(String user_bankcardNum) {
		this.user_bankcardNum = user_bankcardNum;
	}
	public String getUser_IDNum() {
		return user_IDNum;
	}
	public void setUser_IDNum(String user_IDNum) {
		this.user_IDNum = user_IDNum;
	}
	public int isUser_isChecked() {
		return user_isChecked;
	}
	public void setUser_isChecked(int user_isChecked) {
		this.user_isChecked = user_isChecked;
	}
	public double getUser_balance() {
		return user_balance;
	}
	public void setUser_balance(double user_balance) {
		this.user_balance = user_balance;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public int getUser_level() {
		return user_level;
	}

	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}

	public int getUser_isChecked() {
		return user_isChecked;
	}




	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name
				+ ", user_phoneNum=" + user_phoneNum + ", user_password="
				+ user_password + ", user_bankcardNum=" + user_bankcardNum
				+ ", user_IDNum=" + user_IDNum + ", user_isChecked="
				+ user_isChecked + ", user_balance=" + user_balance
				+ ", user_type=" + user_type + ", user_level=" + user_level
				+ "]";
	}
	
	
	
}
