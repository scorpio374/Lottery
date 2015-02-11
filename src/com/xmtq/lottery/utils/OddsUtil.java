package com.xmtq.lottery.utils;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import com.xmtq.lottery.bean.Odds;
import com.xmtq.lottery.bean.SpOdds;

public class OddsUtil {
	/**
	 * 解析胜负平、让球胜负平赔率
	 * 
	 * @param data
	 * @return
	 */
	public static SpOdds getSpOdds(String data) {
		if (TextUtils.isEmpty(data)) {
			return null;
		}
		SpOdds spOdds = new SpOdds();
		String[] resultArray = data.split("\\$");
		for (int i = 0; i < resultArray.length; i++) {
			if (resultArray[i].contains("胜")) {
				spOdds.setWinOdds(resultArray[i].replace("胜@", ""));
			} else if (resultArray[i].contains("平")) {
				spOdds.setDrawOdds(resultArray[i].replace("平@", ""));
			} else if (resultArray[i].contains("负")) {
				spOdds.setLoseOdds(resultArray[i].replace("负@", ""));
			}
		}

		return spOdds;
	}

	/**
	 * 获取赔率
	 * 
	 * @param data
	 * @return
	 */
	public static List<Odds> getOdds(String data) {
		if (TextUtils.isEmpty(data)) {
			return null;
		}

		List<Odds> oddsList = new ArrayList<Odds>();
		String[] resultArray = data.split("\\$");
		for (int i = 0; i < resultArray.length; i++) {
			Odds odds = new Odds();
			String oddsString = resultArray[i];
			String[] oddsArray = oddsString.split("\\@");
			if (oddsArray != null && oddsArray.length > 1) {
				odds.setResult(oddsArray[0]);
				odds.setOdds(oddsArray[1]);
				// LogUtil.log("result:"+oddsArray[0]);
				// LogUtil.log("odds:"+oddsArray[1]);
			}

			oddsList.add(odds);
		}

		return oddsList;
	}

	/**
	 * 获取比赛日期
	 * 
	 * @param data
	 * @return
	 */
	public static String getGameData(String data) {
		String time = null;
		if (TextUtils.isEmpty(data)) {
			return time;
		}
		String[] str = data.split(" ");
		if (str.length > 0) {
			String[] hsm = str[0].split("-");
			time = hsm[1] + "/" + hsm[2];
		}
		return time;
	}

	/**
	 * 获取比赛时间
	 * 
	 * @param data
	 * @return
	 */
	public static String getGameTime(String data) {
		String time = null;
		if (TextUtils.isEmpty(data)) {
			return time;
		}
		String[] str = data.split(" ");
		if (str.length > 1) {
			String[] hsm = str[1].split(":");
			time = hsm[0] + ":" + hsm[1];
		}
		return time;
	}

}
