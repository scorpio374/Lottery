package com.xmtq.lottery.parser;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmtq.lottery.bean.AccountDetailBean;
import com.xmtq.lottery.bean.AccountDetailResponse;
import com.xmtq.lottery.bean.RecomendHistoryBean;
import com.xmtq.lottery.bean.RecomendHistoryResponse;
import com.xmtq.lottery.bean.UserRegisterResponse;
import com.xmtq.lottery.bean.VersionResponse;
import com.xmtq.lottery.utils.JsonUtil;

public class RecomendHistoryParser extends BaseParser<RecomendHistoryResponse> {

	@Override
	public RecomendHistoryResponse parse(String xmlString) {
		String jsonString = JsonUtil.xml2JSON(xmlString);
		if (TextUtils.isEmpty(jsonString)) {
			return null;
		}

		RecomendHistoryResponse response = new RecomendHistoryResponse();
		JSONObject rootObj = JSON.parseObject(jsonString);
		JSONObject msgObj = rootObj.getJSONObject("message");
		parseMsg(msgObj, response);

		if (response.errorcode.equals("0")) {
			JSONObject bodyObj = msgObj.getJSONObject("body");
			if (bodyObj.containsKey("elements")) {
				JSONObject elementsObj = bodyObj.getJSONObject("elements");
				try {
					JSONArray ja = elementsObj.getJSONArray("element");
					if (ja != null) {
						for (int i = 0; i < ja.size(); i++) {
							JSONObject j = ja.getJSONObject(i);
							getParser(response, j);

						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					JSONObject j = elementsObj.getJSONObject("element");

					getParser(response, j);
				}

			}

		}

		return response;
	}

	private void getParser(RecomendHistoryResponse response, JSONObject j) {
		RecomendHistoryBean mRecomendHistoryBean = new RecomendHistoryBean();
		mRecomendHistoryBean.setAgainstVotes(j.getString("againstVotes"));
		mRecomendHistoryBean.setBqContent(j.getString("bqContent"));
		mRecomendHistoryBean.setBqHit(j.getString("bqHit"));
		mRecomendHistoryBean.setBqkj(j.getString("bqkj"));
		mRecomendHistoryBean.setCommendId(j.getString("commendId"));
		mRecomendHistoryBean.setCommendUser(j.getString("commendUser"));
		mRecomendHistoryBean.setCompanyId(j.getString("companyId"));
		mRecomendHistoryBean.setContent(j.getString("content"));
		mRecomendHistoryBean.setGameTime(j.getString("gameTime"));
		mRecomendHistoryBean.setHostTeam(j.getString("hostTeam"));
		mRecomendHistoryBean.setLeague(j.getString("league"));
		mRecomendHistoryBean.setMatchId(j.getString("matchId"));
		mRecomendHistoryBean.setMatchTeam(j.getString("matchTeam"));
		mRecomendHistoryBean.setSupportVotes(j.getString("supportVotes"));
		response.mRecomendHistoryList.add(mRecomendHistoryBean);
	}
}