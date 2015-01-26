package com.xmtq.lottery.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class SharedPrefHelper {
	/**
	 * SharedPreferences的名字
	 */
	private static final String SP_FILE_NAME = "APPLICATION_SP";

	private static SharedPrefHelper sharedPrefHelper = null;
	private static SharedPreferences sharedPreferences;
	/**
	 * 新浪微博相关信息
	 */
	private static final String KEY_SINA_UID = "sina_uid";
	private static final String KEY_SINA_NAME = "sinaName";
	private static final String KEY_SINA_ACCESS_TOKEN = "sina_access_token";
	private static final String KEY_SINA_EXPIRES_IN = "sina_expires_in";
	/**
	 * QQ相关信息
	 */
	private static final String KEY_QQ_ID = "qqid";
	private static final String KEY_QQ_ACCESS_TOKEN = "qq_access_token";
	private static final String KEY_QQ_EXPIRES_IN = "qq_expires_in";
	private static final String KEY_QQ_NAME = "qqName";

	public static synchronized SharedPrefHelper getInstance(Context c) {
		if (null == sharedPrefHelper) {
			sharedPrefHelper = new SharedPrefHelper(c);
		}
		return sharedPrefHelper;
	}

	private SharedPrefHelper(Context c) {
		sharedPreferences = c.getSharedPreferences(SP_FILE_NAME,
				Context.MODE_PRIVATE);
	}

	public static SharedPreferences sp(Context c) {
		return c.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * 应用第一次登陆标识
	 * 
	 */
	public void setFirstLogin(boolean isFirst) {
		sharedPreferences.edit().putBoolean("firstlogin1", isFirst).commit();
	}

	public boolean getFirstLogin() {
		return sharedPreferences.getBoolean("firstlogin1", true);
	}

	// /**
	// * 新浪微博相关信息保存
	// */
	// public void writeSinaToken(Oauth2AccessToken token) {
	// Editor editor = sharedPreferences.edit();
	// editor.putString(KEY_SINA_UID, token.getUid());
	// editor.putString(KEY_SINA_ACCESS_TOKEN, token.getToken());
	// editor.putLong(KEY_SINA_EXPIRES_IN, token.getExpiresTime());
	// editor.commit();
	// }

	/**
	 * 新浪微博相关信息保存
	 */
	public void writeSinaName(String name) {
		Editor editor = sharedPreferences.edit();
		editor.putString(KEY_SINA_NAME, name);
		editor.commit();
	}

	public String sinaName() {
		return sharedPreferences.getString(KEY_SINA_NAME, "");
	}

	// public Oauth2AccessToken readSinaToken() {
	// Oauth2AccessToken token = new Oauth2AccessToken();
	// token.setUid(sharedPreferences.getString(KEY_SINA_UID, ""));
	// token.setToken(sharedPreferences.getString(KEY_SINA_ACCESS_TOKEN, ""));
	// token.setExpiresTime(sharedPreferences.getLong(KEY_SINA_EXPIRES_IN, 0));
	// return token;
	// }

	public void cleanSinaToken() {
		Editor editor = sharedPreferences.edit();
		editor.putString(KEY_SINA_UID, "");
		editor.putString(KEY_SINA_ACCESS_TOKEN, "");
		editor.putLong(KEY_SINA_EXPIRES_IN, 0);
		editor.commit();
	}

	// /**
	// * QQ相关信息保存
	// */
	//
	// public void writeQQToken(QQToken token) {
	// Editor editor = sharedPreferences.edit();
	// editor.putString(KEY_QQ_ID, token.getQqid());
	// editor.putString(KEY_QQ_ACCESS_TOKEN, token.getQqToken());
	// editor.putString(KEY_QQ_EXPIRES_IN, token.getQqExpires_in());
	// editor.putString(KEY_QQ_NAME, token.getQqName());
	//
	// editor.commit();
	// }
	//
	// public QQToken readQQToken() {
	// QQToken token = new QQToken();
	// token.setQqid(sharedPreferences.getString(KEY_QQ_ID, ""));
	// token.setQqToken(sharedPreferences.getString(KEY_QQ_ACCESS_TOKEN, ""));
	// token.setQqExpires_in(sharedPreferences
	// .getString(KEY_QQ_EXPIRES_IN, ""));
	// token.setQqName(sharedPreferences.getString(KEY_QQ_NAME, ""));
	// return token;
	// }

	// public void cleanQQToken() {
	// Editor editor = sharedPreferences.edit();
	// editor.putString(KEY_QQ_ID, "");
	// editor.putString(KEY_QQ_ACCESS_TOKEN, "");
	// editor.putLong(KEY_QQ_EXPIRES_IN, 0);
	// editor.commit();
	// }

	// /**
	// * 用户基本信息
	// */
	// public void writeUserBean(UserBean userBean) {
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// ObjectOutputStream oos;
	// try {
	// oos = new ObjectOutputStream(baos);
	// oos.writeObject(userBean);
	//
	// String personBase64 = new String(Base64.encode(baos.toByteArray(),
	// Base64.DEFAULT));
	// Editor editor = sharedPreferences.edit();
	// editor.putString("userbean", personBase64);
	// editor.commit();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// public UserBean readUserBean() {
	// UserBean userBean = null;
	// String personBase64 = sharedPreferences.getString("userbean", "");
	// byte[] base64Bytes = Base64.decode(personBase64.getBytes(),
	// Base64.DEFAULT);
	// ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
	// ObjectInputStream ois;
	// try {
	// ois = new ObjectInputStream(bais);
	// userBean = (UserBean) ois.readObject();
	// } catch (StreamCorruptedException e) {
	// // TODO Auto-generated catch block
	// // e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// // e.printStackTrace();
	// } catch (ClassNotFoundException e) {
	// // TODO Auto-generated catch block
	// // e.printStackTrace();
	// }
	// return userBean;
	// }

	public void cleanUserBean() {
		Editor editor = sharedPreferences.edit();
		editor.putString("userbean", "");
		editor.commit();
	}

	/**
	 * 是否登录
	 * 
	 */
	public void setIsLogin(boolean isLogin) {
		sharedPreferences.edit().putBoolean("isLogin", isLogin).commit();
	}

	public boolean getIsLogin() {
		return sharedPreferences.getBoolean("isLogin", true);
	}

}
