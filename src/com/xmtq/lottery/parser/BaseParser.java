package com.xmtq.lottery.parser;

import com.alibaba.fastjson.JSONObject;
import com.xmtq.lottery.bean.BaseResponse;

public abstract class BaseParser<T extends BaseResponse> {
	public static final String ERROR_CODE = "code";
	public static final String MSG = "msg";

	public abstract T parse(String inString);

	/**
	 * 判断头部和错误信息
	 * 
	 * @param msgObj
	 * @param response
	 * @return
	 */
	public void parseMsg(JSONObject msgObj, BaseResponse response) {
		// header
		JSONObject headerObj = msgObj.getJSONObject("header");
		response.transactiontype = headerObj.getString("transactiontype");
		response.timestamp = headerObj.getString("timestamp");
		response.agenterid = headerObj.getString("agenterid");
		response.ipaddress = headerObj.getString("ipaddress");
		response.source = headerObj.getString("source");
		response.digest = headerObj.getString("digest");

		// error code
		JSONObject bodyObj = msgObj.getJSONObject("body");
		JSONObject errorObj = bodyObj.getJSONObject("oelement");
		response.errorcode = errorObj.getString("errorcode");
		response.errormsg = errorObj.getString("errormsg");
	}
}
