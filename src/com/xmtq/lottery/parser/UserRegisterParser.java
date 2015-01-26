package com.xmtq.lottery.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.xmtq.lottery.bean.UserRegisterBean;
import com.xmtq.lottery.bean.UserRegisterResponse;
import com.xmtq.lottery.utils.LogUtil;

public class UserRegisterParser extends BaseParser<UserRegisterResponse> {

	@Override
	public UserRegisterResponse parse(String inString) {
		XmlPullParser parser = Xml.newPullParser();
		UserRegisterResponse response = null;
		UserRegisterBean bean = null;

		try {
			InputStream inStream = new ByteArrayInputStream(inString.getBytes());
			parser.setInput(inStream, "UTF-8");
			int eventType = parser.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档开始事件,可以进行数据初始化处理
					response = new UserRegisterResponse();
					break;

				case XmlPullParser.START_TAG:// 开始元素事件
					String name = parser.getName();
					if (parseHeaser(name, parser, response)) {
						// TODO
						if(response.getErrorcode().equals("0")){
							return response;
						}
					} else if (name.equalsIgnoreCase("uid")) {
						response.userRegisterBean.setUid(parser.nextText());
					} else if (name.equalsIgnoreCase("username")) {
						response.userRegisterBean
								.setUsername(parser.nextText());
					} else if (name.equalsIgnoreCase("money")) {
						response.userRegisterBean.setMoney(parser.nextText());
					} else if (name.equalsIgnoreCase("prizeMoney")) {
						response.userRegisterBean.setPrizeMoney(parser
								.nextText());
					} else if (name.equalsIgnoreCase("perfectFlag")) {
						response.userRegisterBean.setPerfectFlag(parser
								.nextText());
					}

					break;

				case XmlPullParser.END_TAG:// 结束元素事件

					break;
				}

				eventType = parser.next();
			}

			inStream.close();
		} catch (Exception e) {
			LogUtil.log("paseError");
			e.printStackTrace();
		}

		return response;
	}
}
