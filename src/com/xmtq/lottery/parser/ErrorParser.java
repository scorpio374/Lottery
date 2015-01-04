package com.xmtq.lottery.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.xmtq.lottery.bean.ErrorResponse;

public class ErrorParser extends BaseParser<ErrorResponse> {

	@Override
	public ErrorResponse parse(String inString) {
		XmlPullParser parser = Xml.newPullParser();
		ErrorResponse er = null;
		try {
			InputStream inStream = new ByteArrayInputStream(inString.getBytes());
			parser.setInput(inStream, "UTF-8");
			int eventType = parser.getEventType();

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// 文档�?��事件,可以进行数据初始化处�?					er = new ErrorResponse();
					break;

				case XmlPullParser.START_TAG:// �?��元素事件
					String name = parser.getName();
					if (name.equalsIgnoreCase("code")) {
						er.code = parser.nextText();
					} else if (name.equalsIgnoreCase("message")) {
						er.message = parser.nextText();// 如果后面是Text元素,即返回它的�?
					}

					break;

				case XmlPullParser.END_TAG:// 结束元素事件
					if (parser.getName().equalsIgnoreCase("message")
							&& er != null) {

					}

					break;
				}

				eventType = parser.next();
			}

			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return er;
	}

}
