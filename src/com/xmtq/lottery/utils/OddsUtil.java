package com.xmtq.lottery.utils;

import android.text.TextUtils;

import com.xmtq.lottery.bean.SpOdds;

public class OddsUtil {
	public static SpOdds getSpOdds(String data) {
		if (TextUtils.isEmpty(data)) {
			return null;
		}
		SpOdds spOdds = new SpOdds();
		String[] oddsArray = data.split("\\$");
		for (int i = 0; i < oddsArray.length; i++) {
			if (oddsArray[i].contains("胜")) {
				spOdds.setWinOdds(oddsArray[i].replace("胜@", ""));
			} else if (oddsArray[i].contains("平")) {
				spOdds.setDrawOdds(oddsArray[i].replace("平@", ""));
			} else if (oddsArray[i].contains("负")) {
				spOdds.setLoseOdds(oddsArray[i].replace("负@", ""));
			}
		}

		return spOdds;
	}

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
