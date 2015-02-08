package com.xmtq.lottery.parser;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xmtq.lottery.bean.GameCanBetBean;
import com.xmtq.lottery.bean.GameCanBetResponse;
import com.xmtq.lottery.utils.JsonUtil;

public class GameCanBetParser extends BaseParser<GameCanBetResponse> {

	@Override
	public GameCanBetResponse parse(String xmlString) {
		String jsonString = JsonUtil.xml2JSON(xmlString);
		if (TextUtils.isEmpty(jsonString)) {
			return null;
		}

		GameCanBetResponse response = new GameCanBetResponse();
		JSONObject rootObj = JSON.parseObject(jsonString);
		JSONObject msgObj = rootObj.getJSONObject("message");
		parseMsg(msgObj, response);

		if (response.errorcode.equals("0")) {
			JSONObject bodyObj = msgObj.getJSONObject("body");
			JSONObject elementsObj = bodyObj.getJSONObject("elements");
			response.count = elementsObj.getString("count");

			try {
				JSONArray elementArray = elementsObj.getJSONArray("element");
				if (elementArray != null) {
					for (int i = 0; i < elementArray.size(); i++) {
						JSONObject j = elementArray.getJSONObject(i);
						GameCanBetBean gameCanBetBean = saveBean(j);
						response.gameCanBetBeans.add(gameCanBetBean);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				JSONObject j = elementsObj.getJSONObject("element");
				if (j != null) {
					GameCanBetBean gameCanBetBean = saveBean(j);
					response.gameCanBetBeans.add(gameCanBetBean);
				}
			}

		}
		return response;
	}

	private GameCanBetBean saveBean(JSONObject j) {
		GameCanBetBean gameCanBetBean = new GameCanBetBean();
		gameCanBetBean.setCommendId(j.getString("companyId"));
		gameCanBetBean.setLeague(j.getString("league"));
		gameCanBetBean.setMatchTeam(j.getString("matchTeam"));
		gameCanBetBean.setMatchId(j.getString("matchId"));
		gameCanBetBean.setHostTeam(j.getString("hostTeam"));
		gameCanBetBean.setBuyEndTime(j.getString("buyEndTime"));
		gameCanBetBean.setGameTime(j.getString("gameTime"));
		gameCanBetBean.setRqOdds(j.getString("rqOdds"));
		gameCanBetBean.setSpOdds(j.getString("spOdds"));
		gameCanBetBean.setBfOdds(j.getString("bfOdds"));
		gameCanBetBean.setBqOdds(j.getString("bqOdds"));
		gameCanBetBean.setJqOdds(j.getString("jqOdds"));
		gameCanBetBean.setCommendUser(j.getString("commendUser"));
		gameCanBetBean.setCompanyId(j.getString("commendId"));
		gameCanBetBean.setSupportVotes(j.getString("supportVotes"));
		gameCanBetBean.setAgainstVotes(j.getString("againstVotes"));
		gameCanBetBean.setContent(j.getString("content"));
		gameCanBetBean.setRqDg(j.getString("rqDg"));
		gameCanBetBean.setSpDg(j.getString("spDg"));
		gameCanBetBean.setBfDg(j.getString("bfDg"));
		gameCanBetBean.setBqDg(j.getString("bqDg"));
		gameCanBetBean.setJqDg(j.getString("jqDg"));

		if (j.containsKey("rqContent")) {
			gameCanBetBean.setRqContent(j.getString("rqContent"));
		}
		if (j.containsKey("spContent")) {
			gameCanBetBean.setSpContent(j.getString("spContent"));
		}
		if (j.containsKey("bfContent")) {
			gameCanBetBean.setBfContent(j.getString("bfContent"));
		}
		if (j.containsKey("bqContent")) {
			gameCanBetBean.setBqContent(j.getString("bqContent"));
		}
		if (j.containsKey("jqContent")) {
			gameCanBetBean.setJqContent(j.getString("jqContent"));
		}
		return gameCanBetBean;
	}
}
