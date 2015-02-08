package com.xmtq.lottery.parser;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmtq.lottery.bean.ExtractCashResponse;
import com.xmtq.lottery.bean.ImproveUserInfoResponse;
import com.xmtq.lottery.bean.PhoneMessageNotFirstResponse;
import com.xmtq.lottery.bean.PhoneMessageResponse;
import com.xmtq.lottery.bean.PhonePayFirstResponse;
import com.xmtq.lottery.bean.RepasswordResponse;
import com.xmtq.lottery.bean.UserRegisterResponse;
import com.xmtq.lottery.utils.JsonUtil;

/**
 * 非首次短信
 * 
 * @author mwz123
 * 
 */
public class PhoneMessageNotFirstParser extends
		BaseParser<PhoneMessageNotFirstResponse> {

	@Override
	public PhoneMessageNotFirstResponse parse(String xmlString) {
		String jsonString = JsonUtil.xml2JSON(xmlString);
		if (TextUtils.isEmpty(jsonString)) {
			return null;
		}

		PhoneMessageNotFirstResponse response = new PhoneMessageNotFirstResponse();
		JSONObject rootObj = JSON.parseObject(jsonString);
		JSONObject msgObj = rootObj.getJSONObject("message");
		parseMsg(msgObj, response);

		return response;
	}
}
