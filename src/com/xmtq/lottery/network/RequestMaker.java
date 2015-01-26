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
	 * 提取现金
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
	 * 4.投注业务
	 */

	public Request getBettingBusiness(String uid, String password,
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

}
