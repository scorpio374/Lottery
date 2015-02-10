package com.xmtq.lottery.utils;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;

import com.xmtq.lottery.bean.Odds;
import com.xmtq.lottery.bean.SpOdds;

public class OddsUtil {

	/**
	 * 半全场结果转换
	 */
	private static String[][] bqOddsMap = new String[][] { { "胜胜", "3-3" },
			{ "胜平", "3-1" }, { "胜负", "3-0" }, { "平胜", "1-3" }, { "平平", "1-1" },
			{ "平负", "1-0" }, { "负胜", "0-3" }, { "负平", "0-1" }, { "负负", "0-0" } };

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

	/**
	 * 胜负平拼接
	 * 
	 * @param oddsList
	 * @return
	 */
	public static String getSpOddsData(List<Odds> oddsList) {
		StringBuilder sb = new StringBuilder();
		if (oddsList.size() > 0) {
			List<String> spCheckList = new ArrayList<String>();
			for (Odds odds : oddsList) {
				if (odds.isChecked()) {
					if (odds.getResult().equals("胜")) {
						spCheckList.add("3");
					} else if (odds.getResult().equals("平")) {
						spCheckList.add("1");
					} else if (odds.getResult().equals("负")) {
						spCheckList.add("0");
					}
				}
			}
			if (spCheckList.size() > 0) {
				sb.append("SP=");
				for (int i = 0; i < spCheckList.size(); i++) {
					if (i == 0) {
						sb.append(spCheckList.get(i));
					} else {
						sb.append("/" + spCheckList.get(i));
					}
				}
			}
		}

		return sb.toString();
	}

	/**
	 * 让球胜负平拼接
	 * 
	 * @param oddsList
	 * @return
	 */
	public static String getRqOddsData(List<Odds> oddsList) {
		StringBuilder sb = new StringBuilder();
		if (oddsList.size() > 0) {
			List<String> spCheckList = new ArrayList<String>();
			for (Odds odds : oddsList) {
				if (odds.isChecked()) {
					if (odds.getResult().equals("胜")) {
						spCheckList.add("3");
					} else if (odds.getResult().equals("平")) {
						spCheckList.add("1");
					} else if (odds.getResult().equals("负")) {
						spCheckList.add("0");
					}
				}
			}
			if (spCheckList.size() > 0) {
				sb.append("RQ=");
				for (int i = 0; i < spCheckList.size(); i++) {
					if (i == 0) {
						sb.append(spCheckList.get(i));
					} else {
						sb.append("/" + spCheckList.get(i));
					}
				}
			}
		}

		return sb.toString();
	}

	/**
	 * 比分拼接
	 * 
	 * @param oddsList
	 * @return
	 */
	public static String getBfOddsData(List<Odds> oddsList) {
		StringBuilder sb = new StringBuilder();
		if (oddsList.size() > 0) {
			List<String> spCheckList = new ArrayList<String>();
			for (Odds odds : oddsList) {
				if (odds.isChecked()) {
					if (odds.getResult().equals("胜其它")) {
						spCheckList.add("9:0");
					} else if (odds.getResult().equals("平其它")) {
						spCheckList.add("9:9");
					} else if (odds.getResult().equals("负其它")) {
						spCheckList.add("0:9");
					} else {
						spCheckList.add(odds.getResult());
					}
				}
			}
			if (spCheckList.size() > 0) {
				sb.append("BF=");
				for (int i = 0; i < spCheckList.size(); i++) {
					if (i == 0) {
						sb.append(spCheckList.get(i));
					} else {
						sb.append("/" + spCheckList.get(i));
					}
				}
			}
		}

		return sb.toString();
	}

	/**
	 * 进球拼接
	 * 
	 * @param oddsList
	 * @return
	 */
	public static String getJqOddsData(List<Odds> oddsList) {
		StringBuilder sb = new StringBuilder();
		if (oddsList.size() > 0) {
			List<String> spCheckList = new ArrayList<String>();
			for (Odds odds : oddsList) {
				if (odds.isChecked()) {
					if (odds.getResult().equals("7+")) {
						spCheckList.add("7");
					} else {
						spCheckList.add(odds.getResult());
					}
				}
			}
			if (spCheckList.size() > 0) {
				sb.append("JQ=");
				for (int i = 0; i < spCheckList.size(); i++) {
					if (i == 0) {
						sb.append(spCheckList.get(i));
					} else {
						sb.append("/" + spCheckList.get(i));
					}
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 半全场拼接
	 * 
	 * @param oddsList
	 * @return
	 */
	public static String getBqOddsData(List<Odds> oddsList) {
		StringBuilder sb = new StringBuilder();
		if (oddsList.size() > 0) {
			List<String> spCheckList = new ArrayList<String>();
			for (Odds odds : oddsList) {
				if (odds.isChecked()) {
					for (int i = 0; i < bqOddsMap.length; i++) {
						if (odds.getResult().equals(bqOddsMap[i][0])) {
							spCheckList.add(bqOddsMap[i][1]);
						}
					}
				}
			}
			if (spCheckList.size() > 0) {
				sb.append("BQ=");
				for (int i = 0; i < spCheckList.size(); i++) {
					if (i == 0) {
						sb.append(spCheckList.get(i));
					} else {
						sb.append("/" + spCheckList.get(i));
					}
				}
			}
		}
		return sb.toString();
	}
}
