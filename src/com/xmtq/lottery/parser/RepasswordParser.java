package com.xmtq.lottery.parser;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmtq.lottery.bean.ImproveUserInfoResponse;
import com.xmtq.lottery.bean.RepasswordResponse;
import com.xmtq.lottery.bean.UserRegisterResponse;
import com.xmtq.lottery.utils.JsonUtil;

public class RepasswordParser extends BaseParser<RepasswordResponse> {

	@Override
	public RepasswordResponse parse(String xmlString) {
		String jsonString = JsonUtil.xml2JSON(xmlString);
		if (TextUtils.isEmpty(jsonString)) {
			return null;
		}

		RepasswordResponse response = new RepasswordResponse();
		JSONObject rootObj = JSON.parseObject(jsonString);
		JSONObject msgObj = rootObj.getJSONObject("message");
		parseMsg(msgObj, response);

		// if (response.errorcode.equals("0")) {
		// JSONObject bodyObj = msgObj.getJSONObject("body");
		// JSONObject j = bodyObj.getJSONObject("element");
		// response.improveUserInfoBean.setBankAddress(j
		// .getString("bankaddress"));
		// response.improveUserInfoBean.setBankCardId(j
		// .getString("bankcardid"));
		// response.improveUserInfoBean.setBankName(j.getString("bankname"));
		// response.improveUserInfoBean.setCardId(j.getString("cardid"));
		// response.improveUserInfoBean.setPhone(j.getString("phone"));
		// response.improveUserInfoBean.setRealName(j.getString("realname"));
		// }

		return response;
	}
}
