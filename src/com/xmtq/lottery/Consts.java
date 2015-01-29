package com.xmtq.lottery;

public class Consts {

	/**
	 * 服务器地址（正式上线后，需修改）
	 */
	public static final String host = "http://220.167.29.50:8380/lotteryxml.php";

	/**
	 * 密码密钥
	 */
	public static final String passwordkey = "97d2dcb2ea177246d44f1114e2d5e9c5";

	/**
	 * Agent密钥（加密请求数据）
	 */
	public static final String agenterkey = "c5d1eb92ced441225554a64e07694fa0";

	/**
	 * 渠道ID
	 */
	public static final String agenterid = "10000001";

	/**
	 * 手机注册
	 */
	public static final String PHONE_REGISTER = "1";

	/**
	 * 邮箱注册
	 */
	public static final String EMAIL_REGISTER = "2";

	/**
	 * 手机注册验证
	 */
	public static final String PHONE_REGISTER_VERI = "01";

	/**
	 * 找回密码验证
	 */
	public static final String FIND_PASSWORD_VERI = "02";
}
