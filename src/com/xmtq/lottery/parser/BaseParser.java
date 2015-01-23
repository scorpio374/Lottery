package com.xmtq.lottery.parser;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.xmtq.lottery.bean.BaseResponse;

public abstract class BaseParser<T extends BaseResponse> {
	public static final String ERROR_CODE = "code";
	public static final String MSG = "msg";

	public abstract T parse(String inString);

	/**
	 * 判断头部和错误信息
	 * @param tagName
	 * @param parser
	 * @param response
	 * @return
	 */
	public boolean parseHeaser(String tagName, XmlPullParser parser,
			BaseResponse response) {
		try {
			if (tagName.equalsIgnoreCase("header")) {
				// TODO
			} else if (tagName.equalsIgnoreCase("transactiontype")) {
				response.setTransactiontype(parser.nextText());
			} else if (tagName.equalsIgnoreCase("timestamp")) {
				response.setTimestamp(parser.nextText());
			} else if (tagName.equalsIgnoreCase("agenterid")) {
				response.setAgenterid(parser.nextText());
			} else if (tagName.equalsIgnoreCase("ipaddress")) {
				response.setIpaddress(parser.nextText());
			} else if (tagName.equalsIgnoreCase("source")) {
				response.setSource(parser.nextText());
			} else if (tagName.equalsIgnoreCase("digest")) {
				response.setDigest(parser.nextText());
			} else if (tagName.equalsIgnoreCase("body")) {
				// TODO
			} else if (tagName.equalsIgnoreCase("oelement")) {
				// TODO
			} else if (tagName.equalsIgnoreCase("errorcode")) {
				response.setErrorcode(parser.nextText());
			} else if (tagName.equalsIgnoreCase("errormsg")) {
				response.setErrormsg(parser.nextText());
			} else{
				return false;
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
