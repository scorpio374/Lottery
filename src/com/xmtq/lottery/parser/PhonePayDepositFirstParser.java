package com.xmtq.lottery.parser;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmtq.lottery.bean.ExtractCashResponse;
import com.xmtq.lottery.bean.ImproveUserInfoResponse;
import com.xmtq.lottery.bean.PhoneMessageResponse;
import com.xmtq.lottery.bean.PhonePayDepositFirstResponse;
import com.xmtq.lottery.bean.PhonePayFirstResponse;
import com.xmtq.lottery.bean.RepasswordResponse;
import com.xmtq.lottery.bean.UserRegisterResponse;
import com.xmtq.lottery.utils.JsonUtil;

public class PhonePayDepositFirstParser extends
		BaseParser<PhoneMessageResponse> {

	@Override
	public PhoneMessageResponse parse(String xmlString) {
		String jsonString = JsonUtil.xml2JSON(xmlString);
		if (TextUtils.isEmpty(jsonString)) {
			return null;
		}

		PhoneMessageResponse response = new PhoneMessageResponse();
		JSONObject rootObj = JSON.parseObject(jsonString);
		JSONObject msgObj = rootObj.getJSONObject("message");
		parseMsg(msgObj, response);

		return response;
	}
}
