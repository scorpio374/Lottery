package com.xmtq.lottery.network;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.dvt.lottery.util.MD5;
import com.xmtq.lottery.Consts;
import com.xmtq.lottery.utils.LogUtil;

public class RequestMaker {

	private Request request;

	public Request getRequest() {
		return request;
	}

	private RequestMaker(String url) {
		if (TextUtils.isEmpty(url)) {
			url = Consts.host;
		}
		request = new Request(url);

	}

	private static RequestMaker requestMaker = null;

	/**
	 * 得到JsonMaker的实例
	 * 
	 * @param context
	 * @return
	 */
	public static RequestMaker getInstance(String url) {
		if (requestMaker == null) {
			requestMaker = new RequestMaker(url);
			return requestMaker;
		} else {
			return requestMaker;
		}
	}

	/**
	 * @param sb
	 */
	protected String makeTag(String name, String value) {
		return "<" + name + ">" + (value == null ? "" : value) + "</" + name
				+ ">";
	}

	/**
	 * 生成XML请求参数
	 * 
	 * @param body
	 * @return
	 */
	public String makeXml(String body, String transactiontype) {
		String source = Consts.agenterkey;
		if (body != null) {
			source += body;
		}
		String digest = MD5.md5(source);

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		sb.append("<message version=\"1.0\">");
		sb.append(makeHeader(digest, transactiontype));
		if (body != null) {
			sb.append(body);
		}
		sb.append("</message>");
		return sb.toString();
	}

	/**
	 * 生成HTTP POST请求头
	 */
	@SuppressLint("SimpleDateFormat")
	protected String makeHeader(String digest, String transactiontype) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());
		StringBuilder sb = new StringBuilder();

		sb.append("<header>");
		sb.append(makeTag("transactiontype", transactiontype));
		sb.append(makeTag("timestamp", timestamp));
		sb.append(makeTag("digest", digest));
		sb.append(makeTag("agenterid", Consts.agenterid));
		sb.append(makeTag("ipaddress", "223131123122"));
		sb.append(makeTag("source", "WEB"));
		sb.append("</header>");

		return sb.toString();
	}

	/**
	 * 用户注册
	 */
	public Request getUserRegister(String username, String mail,
			String actpassword, String mobile, String serialuid, String type) {

		String body = createUserRegister(username, mail, actpassword, mobile,
				serialuid, type);
		String xmlBody = makeXml(body, "10001_1.1");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createUserRegister(String username, String mail,
			String actpassword, String mobile, String serialuid, String type) {

		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("username", "tangqi"));
		sb.append(makeTag("mail", "hntangqi374@163.com"));
		sb.append(makeTag("actpassword", "tq123456"));
		sb.append(makeTag("mobile", "13632809278"));
		sb.append(makeTag("serialuid", "12563245125"));
		sb.append(makeTag("type", "1"));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 完善用户信息(no pass)
	 */
	public Request getPerfectUserInfo(String uid, String realname,
			String cardid, String phone, String bankname, String bankcardid,
			String bankaddress, String actpassword) {

		String body = createPerfectUserInfo(uid, realname, cardid, phone,
				bankname, bankcardid, bankaddress, actpassword);
		String xmlBody = makeXml(body, "20003_1.1");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createPerfectUserInfo(String uid, String realname,
			String cardid, String phone, String bankname, String bankcardid,
			String bankaddress, String actpassword) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element drawaltype=\"0\">");
		sb.append(makeTag("uid", "14244"));
		sb.append(makeTag("realname", "唐麒"));
		sb.append(makeTag("cardid", "431121199003080798"));
		sb.append(makeTag("phone", "13632809278"));
		sb.append(makeTag("bankname", "招商银行"));
		sb.append(makeTag("bankcardid", "204943197136761361"));
		sb.append(makeTag("bankaddress", "广东深圳"));
		sb.append(makeTag("actpassword", "tq111111"));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 修改用户密码
	 */
	public Request getModifyPassword(String uid, String oldpassword,
			String newpassword) {

		String body = createModifyPassword(uid, oldpassword, newpassword);
		String xmlBody = makeXml(body, "10003_1.1");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createModifyPassword(String uid, String oldpassword,
			String newpassword) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("uid", "14244"));
		sb.append(makeTag("oldpassword", "tq123456"));
		sb.append(makeTag("newpassword", "tq111111"));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 新用户登陆
	 */
	public Request getUserLogin(String username, String actpassword) {

		String body = createUserLogin(username, actpassword);
		String xmlBody = makeXml(body, "10008_1.1");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createUserLogin(String username, String actpassword) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("username", "tangqi"));
		sb.append(makeTag("actpassword", "tq111111"));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 检测用户名/手机/邮箱是否存在
	 */
	public Request getCheckUser(String parameter) {

		String body = createCheckUser(parameter);
		String xmlBody = makeXml(body, "10010");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createCheckUser(String parameter) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("parameter", "hntangqi374@163.com"));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * /** 发送短信验证码
	 * 
	 * @param tel
	 *            手机号
	 * @param type
	 *            01：用户注册 02：修改密码
	 * @return
	 */
	public Request getMessageVerification(String tel, String type) {

		String body = createMessageVerification(tel, type);
		String xmlBody = makeXml(body, "10011");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createMessageVerification(String tel, String type) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("tel", "13632809278"));
		sb.append(makeTag("type", "01"));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 用户账户明细查询(no pass)
	 */
	public Request getAccountDetail(String startDate, String endDate,
			String uid, String mflag) {

		String body = createAccountDetail(startDate, endDate, uid, mflag);
		String xmlBody = makeXml(body, "11008_1.1");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createAccountDetail(String startDate, String endDate,
			String uid, String mflag) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("startDate", "2015-01-01"));
		sb.append(makeTag("endDate", "2015-01-20"));
		sb.append(makeTag("uid", "14244"));
		sb.append("<mflag/>");
		sb.append(makeTag("pageNum", "1"));
		sb.append(makeTag("pageSize", "10"));

		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 用户详细信息查询
	 */
	public Request getUserInfo(String uid) {

		String body = createUserInfo(uid);
		String xmlBody = makeXml(body, "20001_1.1");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createUserInfo(String uid) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("uid", "14244"));

		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 3.1.10提取现金（20003_1.1）
	 * 
	 * @param uid
	 * @param password
	 * @param drawalmoney
	 * @return
	 */
	public Request getExtractCash(String uid, String password,
			String drawalmoney) {

		String body = createExtractCash(uid, password, drawalmoney);
		String xmlBody = makeXml(body, "20003_1.1");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createExtractCash(String uid, String password,
			String drawalmoney) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element drawaltype=\"0\">");
		sb.append(makeTag("uid", "14244"));
		sb.append(makeTag("password", "tq111111"));
		sb.append(makeTag("drawalmoney", "10"));

		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.1投注（12006_1.1）
	 * 
	 * @param uid
	 * @param lotteryid
	 * @param votetype
	 * @param votenums
	 * @param multiple
	 * @param voteinfo
	 * @param totalmoney
	 * @param playtype
	 * @param passtype
	 * @param buymoney
	 * @param protype
	 * @return
	 */
	public Request getBettingBusiness(String uid, String lotteryid,
			String votetype, String votenums, String multiple, String voteinfo,
			String totalmoney, String playtype, String passtype,
			String buymoney, String protype) {

		String body = createBettingBusiness(uid, lotteryid, votetype, votenums,
				multiple, voteinfo, totalmoney, playtype, passtype, buymoney,
				protype);
		String xmlBody = makeXml(body, "12006_1.1");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createBettingBusiness(String uid, String lotteryid,
			String votetype, String votenums, String multiple, String voteinfo,
			String totalmoney, String playtype, String passtype,
			String buymoney, String protype) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("lotteryid", lotteryid));
		sb.append(makeTag("uid", uid));
		sb.append(makeTag("votetype", votetype));
		sb.append(makeTag("votenums", votenums));
		sb.append(makeTag("multiple", multiple));

		sb.append(makeTag("voteinfo", voteinfo));

		sb.append(makeTag("totalmoney", totalmoney));

		sb.append(makeTag("playtype", playtype));

		sb.append(makeTag("passtype", passtype));
		sb.append(makeTag("buymoney", buymoney));
		sb.append(makeTag("protype", protype));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.2竞彩购买记录查询（12021_1.1）
	 * 
	 * @param uid
	 * @param lotteryid
	 * @param startdate
	 * @param enddate
	 * @param investtype
	 * @param pageindex
	 * @param pagesize
	 * @param statue
	 * @return
	 */
	public Request getPurchaseRecords(String uid, String lotteryid,
			String startdate, String enddate, String investtype,
			String pageindex, String pagesize, String statue) {

		String body = createPurchaseRecords(uid, lotteryid, startdate, enddate,
				investtype, pageindex, pagesize, statue);
		String xmlBody = makeXml(body, "12021_1.1");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createPurchaseRecords(String uid, String lotteryid,
			String startdate, String enddate, String investtype,
			String pageindex, String pagesize, String statue) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("uid", uid));
		sb.append(makeTag("lotteryid", lotteryid));
		sb.append(makeTag("startdate", startdate));
		sb.append(makeTag("enddate", enddate));
		sb.append(makeTag("investtype", investtype));
		sb.append(makeTag("pageindex", pageindex));
		sb.append(makeTag("pagesize", pagesize));
		sb.append(makeTag("statue", statue));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.3竞彩购买记录详情查询（12022_1.1）
	 * 
	 * @param serialid
	 * @return
	 */
	public Request getPurchaseRecordsDetail(String serialid) {

		String body = createPurchaseRecordsDetail(serialid);
		String xmlBody = makeXml(body, "12022_1.1");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createPurchaseRecordsDetail(String serialid) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("serialid", serialid));

		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.4竞彩推荐赛事历史查询（12025）
	 * 
	 * @param date
	 * @return
	 */
	public Request getGameHistorySearch(String date) {

		String body = createGameHistorySearch(date);
		String xmlBody = makeXml(body, "12025");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createGameHistorySearch(String date) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("date", date));

		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.5竞彩历史推荐日期列表（12026）
	 * 
	 * @param startdate
	 * @param enddate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Request getGameHistoryDateList(String startdate, String enddate,
			String pageNum, String pageSize) {

		String body = createGameHistoryDateList(startdate, enddate, pageNum,
				pageSize);
		String xmlBody = makeXml(body, "12026");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createGameHistoryDateList(String startdate, String enddate,
			String pageNum, String pageSize) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("startdate", startdate));
		sb.append(makeTag("enddate", enddate));
		sb.append(makeTag("pageNum", pageNum));
		sb.append(makeTag("pageSize", pageSize));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.6竞彩今天推荐赛事投票（12028）
	 * 
	 * @param uid
	 * @param matchId
	 * @param vote
	 * @param content
	 * @return
	 */
	public Request getGameTodayRecomend(String uid, String matchId,
			String vote, String content) {

		String body = createGameTodayRecomend(uid, matchId, vote, content);
		String xmlBody = makeXml(body, "12028");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createGameTodayRecomend(String uid, String matchId,
			String vote, String content) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("uid", uid));
		sb.append(makeTag("matchId", matchId));
		sb.append(makeTag("vote", vote));
		sb.append(makeTag("content", content));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.7竞彩中奖记录查询（12029）
	 * 
	 * @param uid
	 * @param matchId
	 * @param vote
	 * @param content
	 * @return
	 */
	public Request getGameWinRecord(String size) {

		String body = createGameWinRecord(size);
		String xmlBody = makeXml(body, "12029");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createGameWinRecord(String size) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("size", size));

		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.8竞彩可投注赛事查询（12030）
	 * 
	 * @param pagenum
	 * @param pagesize
	 * @return
	 */
	public Request getGameCanBet(String pagenum, String pagesize) {

		String body = createGameCanBet(pagenum, pagesize);
		String xmlBody = makeXml(body, "12030");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createGameCanBet(String pagenum, String pagesize) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("pagenum", pagenum));
		sb.append(makeTag("pagesize", pagesize));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.9创建丰付支付订单(15006)
	 * 
	 * @param userIdIdentity
	 * @param totalPrice
	 * @return
	 */
	public Request getFengPay(String userIdIdentity, String totalPrice) {

		String body = createFengPay(userIdIdentity, totalPrice);
		String xmlBody = makeXml(body, "15006");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createFengPay(String userIdIdentity, String totalPrice) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("userIdIdentity", userIdIdentity));
		sb.append(makeTag("totalPrice", totalPrice));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.10丰付首次快捷支付（15007）
	 * 
	 * @param requestOrderId
	 * @param bankCode
	 * @param bankAccount
	 * @param bankCardType
	 * @param validDate
	 * @param cvnCode
	 * @param idType
	 * @param idNumber
	 * @param name
	 * @param mobilePhone
	 * @param isNeedBind
	 * @param userIdIdentity
	 * @param randomValidateId
	 * @param randomCode
	 * @param tradeId
	 * @return
	 */
	public Request getFengPayFirst(String requestOrderId, String bankCode,
			String bankAccount, String bankCardType, String validDate,
			String cvnCode, String idType, String idNumber, String name,
			String mobilePhone, String isNeedBind, String userIdIdentity,
			String randomValidateId, String randomCode, String tradeId) {

		String body = createFengPayFirst(requestOrderId, bankCode, bankAccount,
				bankCardType, validDate, cvnCode, idType, idNumber, name,
				mobilePhone, isNeedBind, userIdIdentity, randomValidateId,
				randomCode, tradeId);
		String xmlBody = makeXml(body, "15007");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createFengPayFirst(String requestOrderId, String bankCode,
			String bankAccount, String bankCardType, String validDate,
			String cvnCode, String idType, String idNumber, String name,
			String mobilePhone, String isNeedBind, String userIdIdentity,
			String randomValidateId, String randomCode, String tradeId) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("requestOrderId", requestOrderId));
		sb.append(makeTag("bankCode", bankCode));
		sb.append(makeTag("bankAccount", bankAccount));
		sb.append(makeTag("bankCardType", bankCardType));
		sb.append(makeTag("validDate", validDate));
		sb.append(makeTag("cvnCode", cvnCode));
		sb.append(makeTag("idType", idType));
		sb.append(makeTag("idNumber", idNumber));
		sb.append(makeTag("name", name));
		sb.append(makeTag("mobilePhone", mobilePhone));
		sb.append(makeTag("isNeedBind", isNeedBind));
		sb.append(makeTag("userIdIdentity", userIdIdentity));
		sb.append(makeTag("randomValidateId", randomValidateId));
		sb.append(makeTag("randomCode", randomCode));
		sb.append(makeTag("tradeId", tradeId));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.11丰付非首次快捷支付（15008）
	 */
	public Request getFengPayNotFirst(String requestOrderId, String bankCode,
			String bindId, String userIdIdentity, String randomValidateId,
			String randomCode, String tradeId) {

		String body = createFengPayNotFirst(requestOrderId, bankCode, bindId,
				userIdIdentity, randomValidateId, randomCode, tradeId);
		String xmlBody = makeXml(body, "15008");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createFengPayNotFirst(String requestOrderId,
			String bankCode, String bindId, String userIdIdentity,
			String randomValidateId, String randomCode, String tradeId) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("requestOrderId", requestOrderId));
		sb.append(makeTag("bankCode", bankCode));
		sb.append(makeTag("bindId", bindId));
		sb.append(makeTag("userIdIdentity", userIdIdentity));
		sb.append(makeTag("randomValidateId", randomValidateId));
		sb.append(makeTag("randomCode", randomCode));
		sb.append(makeTag("tradeId", tradeId));

		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.12丰付首次快捷支付短信验证（15009）
	 * 
	 * @param requestOrderId
	 * @param bankCode
	 * @param bankAccount
	 * @param validDate
	 * @param bankCardType
	 * @param cvnCode
	 * @param idType
	 * @param idNumber
	 * @param name
	 * @param mobilePhone
	 * @param userIdIdentity
	 * @return
	 */
	public Request getFengMessagePayFirst(String requestOrderId,
			String bankCode, String bankAccount, String validDate,
			String bankCardType, String cvnCode, String idType,
			String idNumber, String name, String mobilePhone,
			String userIdIdentity) {

		String body = createFengMessagePayFirst(requestOrderId, bankCode,
				bankAccount, validDate, bankCardType, cvnCode, idType,
				idNumber, name, mobilePhone, userIdIdentity);
		String xmlBody = makeXml(body, "15008");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createFengMessagePayFirst(String requestOrderId,
			String bankCode, String bankAccount, String validDate,
			String bankCardType, String cvnCode, String idType,
			String idNumber, String name, String mobilePhone,
			String userIdIdentity) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("requestOrderId", requestOrderId));
		sb.append(makeTag("bankCode", bankCode));
		sb.append(makeTag("bankAccount", bankAccount));
		sb.append(makeTag("bankCardType", bankCardType));
		sb.append(makeTag("validDate", validDate));

		sb.append(makeTag("cvnCode", cvnCode));

		sb.append(makeTag("idType", idType));
		sb.append(makeTag("idNumber", idNumber));
		sb.append(makeTag("name", name));
		sb.append(makeTag("mobilePhone", mobilePhone));
		sb.append(makeTag("userIdIdentity", userIdIdentity));
		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}

	/**
	 * 4.1.13丰付非首次快捷支付短信验证（15010）
	 * 
	 * @param requestOrderId
	 * @param bankCode
	 * @param bindId
	 * @param userIdIdentity
	 * @return
	 */
	public Request getFengMessagePayNotFirst(String requestOrderId,
			String bankCode, String bindId, String userIdIdentity) {

		String body = createFengMessagePayNotFirst(requestOrderId, bankCode,
				bindId, userIdIdentity);
		String xmlBody = makeXml(body, "15008");
		LogUtil.log("xmlBody:" + xmlBody);

		request.setBody(xmlBody);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
		request.setXmlParser(null);
		return request;
	}

	private String createFengMessagePayNotFirst(String requestOrderId,
			String bankCode, String bindId, String userIdIdentity) {
		StringBuilder sb = new StringBuilder();

		sb.append("<body>");
		sb.append("<elements>");
		sb.append("<element>");
		sb.append(makeTag("requestOrderId", requestOrderId));
		sb.append(makeTag("bankCode", bankCode));
		sb.append(makeTag("bindId", bindId));
		sb.append(makeTag("userIdIdentity", userIdIdentity));

		sb.append("</element>");
		sb.append("</elements>");
		sb.append("</body>");

		return sb.toString();
	}
}
