package com.xmtq.lottery.network;

import java.io.StringWriter;

import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import com.xmtq.lottery.parser.ErrorParser;

public class RequestMaker {

	private Request request;

	public Request getRequest() {
		return request;
	}

	private RequestMaker(String url) {
		request = new Request(url);

	}

	private static RequestMaker requestMaker = null;

	/**
	 * 得到JsonMaker的实�?
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
	 * 组装获取内容详情的xml
	 * 
	 * @param contentId
	 *            对象内容id
	 * @param userId
	 *            用户id
	 * @param contentType
	 *            内容类型�?vod节目 2频道 3节目�?4连续�?
	 * @param userToken
	 *            身份证明
	 * @return
	 */
	private String contentDetailXMLData(String contentId, String userId,
			String userToken, String contentType) {
		XmlSerializer xml = Xml.newSerializer();
		StringWriter sw = new StringWriter();
		try {
			xml.setOutput(sw);
			xml.startDocument("UTF-8", false);
			// �?��
			xml.startTag(null, "GetContentDetail");
			// contentId
			xml.startTag(null, "contentId");
			xml.text(contentId);
			xml.endTag(null, "contentId");
			// userId
			xml.startTag(null, "userId");
			xml.text(userId);
			xml.endTag(null, "userId");
			// userToken
			xml.startTag(null, "userToken");
			xml.text(userToken);
			xml.endTag(null, "userToken");
			if (contentType != null) {
				// contentType
				xml.startTag(null, "contentType");
				xml.text(contentType);
				xml.endTag(null, "contentType");
			}
			//
			xml.endTag(null, "GetContentDetail");
			//
			xml.endDocument();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	/**
	 * 返回获取内容详情的请�?
	 * 
	 * @param contentId
	 *            对象内容id
	 * @param userId
	 *            用户id
	 * @param contentType
	 *            （非必需）内容类型：1vod节目 2频道 3节目�?4连续�?
	 * @param userToken
	 *            身份证明
	 * @return
	 */
	public Request getContentDetail(String contentId, String contentType,
			String userId, String userToken) {

		String body = contentDetailXMLData(contentId, userId, contentType,
				userToken);
		// Request request = new Request(
		// ServerInterfaceDefinition.OPT_GETCONTENTDETAIL, body,
		// new ErrorParser());

		request.setBody(body);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCONTENTDETAIL);
		request.setXmlParser(new ErrorParser());
		return request;
	}

	private String createChannelList(String group, String count, String offset,
			String userId, String userToken) {
		XmlSerializer xml = Xml.newSerializer();
		StringWriter sw = new StringWriter();
		try {
			xml.setOutput(sw);
			xml.startDocument("UTF-8", false);
			// �?��
			xml.startTag(null, "GetChannelList");
			// group
			xml.startTag(null, "group");
			xml.text(group);
			xml.endTag(null, "group");
			// count
			xml.startTag(null, "count");
			xml.text(count);
			xml.endTag(null, "count");
			// offset
			xml.startTag(null, "offset");
			xml.text(offset);
			xml.endTag(null, "offset");
			// userId
			xml.startTag(null, "userId");
			xml.text(userId);
			xml.endTag(null, "userId");
			// userToken
			xml.startTag(null, "userToken");
			xml.text(userToken);
			xml.endTag(null, "userToken");

			//
			xml.endTag(null, "GetChannelList");
			//
			xml.endDocument();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

//	/**
//	 * 获得用户可以观看的频道列�?
//	 * 
//	 * @param group
//	 *            频道分组名称，如“本地频道�?�?如为空则取全部频�?
//	 * @param count
//	 *            取多少条
//	 * @param offset
//	 *            相对第一条的偏移�?
//	 * @param userId
//	 *            用户业务编号
//	 * @param userToken
//	 *            业务管理平台为该用户分配的临时身份证�?
//	 * @return
//	 */
//	public Request getChannelList(String group, String count, String offset,
//			String userId, String userToken) {
//
//		String body = createChannelList(group, count, offset, userId, userToken);
//		// body = body.replace(" standalone='no' ", "");
//		// Request request = new Request(
//		// ServerInterfaceDefinition.OPT_GETCHANNELLIST, body,
//		// new ErrorParser());
//		request.setBody(body);
//		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETCHANNELLIST);
//		request.setXmlParser(new ChannelParser());
//		return request;
//	}

	private String createScheduleList(String count, String channelId,
			String offset, String startDateTime, String endDateTime,
			String userId, String userToken) {
		XmlSerializer xml = Xml.newSerializer();
		StringWriter sw = new StringWriter();
		try {
			xml.setOutput(sw);
			xml.startDocument("UTF-8", false);
			// �?��
			xml.startTag(null, "GetScheduleList");
			// count
			xml.startTag(null, "count");
			xml.text(count);
			xml.endTag(null, "count");
			// channelId
			xml.startTag(null, "channelId");
			xml.text(channelId);
			xml.endTag(null, "channelId");
			// offset
			xml.startTag(null, "offset");
			xml.text(offset);
			xml.endTag(null, "offset");
			// startDateTime
			xml.startTag(null, "startDateTime");
			xml.text(startDateTime);
			xml.endTag(null, "startDateTime");
			// endDateTime
			xml.startTag(null, "endDateTime");
			xml.text(endDateTime);
			xml.endTag(null, "endDateTime");
			// userId
			xml.startTag(null, "userId");
			xml.text(userId);
			xml.endTag(null, "userId");
			// userToken
			xml.startTag(null, "userToken");
			xml.text(userToken);
			xml.endTag(null, "userToken");
			//
			xml.endTag(null, "GetScheduleList");
			//
			xml.endDocument();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

//	/**
//	 * 获得节目信息请求格式
//	 * 
//	 * @param count
//	 *            取多少条
//	 * @param channelId
//	 *            请求的频道的contentId
//	 * @param offset
//	 *            相对第一条的偏移�?
//	 * @param startDateTime
//	 *            起始时间参数 ，格式为YYYYMMDDHH24MISS �?
//	 * @param endDateTime
//	 *            结束时间参数，格式为YYYYMMDDHH24MISS
//	 *            ，前端服务需要返回由startDateTime和endDateTime确定的时间段内的节目单�?
//	 * @param userId
//	 *            用户业务编号
//	 * @param userToken
//	 *            业务管理平台为该用户分配的临时身份证�?
//	 * @return
//	 */
//	public Request getScheduleList(String count, String channelId,
//			String offset, String startDateTime, String endDateTime,
//			String userId, String userToken) {
//
//		String body = createScheduleList(count, channelId, offset,
//				startDateTime, endDateTime, userId, userToken);
//		// Request request = new Request(
//		// ServerInterfaceDefinition.OPT_GETSCHEDULELIST, body,
//		// new ScheduleParser());
//		request.setBody(body);
//		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_GETSCHEDULELIST);
//		request.setXmlParser(new ScheduleParser());
//		return request;
//	}

	private String playLogXMLData(String contentId, String contentType,
			String startTime, String endTime, String TVODStartTime,
			String duration, String timeshiftDuration, String terminalId,
			String userId, String userToken) {
		XmlSerializer xml = Xml.newSerializer();
		StringWriter sw = new StringWriter();
		try {
			xml.setOutput(sw);
			xml.startDocument("UTF-8", false);
			// �?��
			xml.startTag(null, "PlayLogReport");
			// contentId
			xml.startTag(null, "contentId");
			xml.text(contentId);
			xml.endTag(null, "contentId");
			// contentType
			xml.startTag(null, "contentType");
			xml.text(contentType);
			xml.endTag(null, "contentType");
			// startTime
			xml.startTag(null, "startTime");
			xml.text(startTime);
			xml.endTag(null, "startTime");
			// endTime
			xml.startTag(null, "endTime");
			xml.text(endTime);
			xml.endTag(null, "endTime");
			// TVODStartTime
			xml.startTag(null, "TVODStartTime");
			xml.text(TVODStartTime);
			xml.endTag(null, "TVODStartTime");
			// duration
			xml.startTag(null, "duration");
			xml.text(duration);
			xml.endTag(null, "duration");
			if (timeshiftDuration != null) {
				// timeshiftDuration
				xml.startTag(null, "timeshiftDuration");
				xml.text(timeshiftDuration);
				xml.endTag(null, "timeshiftDuration");
			}
			// userId
			xml.startTag(null, "userId");
			xml.text(userId);
			xml.endTag(null, "userId");
			// userToken
			xml.startTag(null, "userToken");
			xml.text(userToken);
			xml.endTag(null, "userToken");
			// terminalId
			xml.startTag(null, "terminalId");
			xml.text(terminalId);
			xml.endTag(null, "terminalId");
			//
			xml.endTag(null, "PlayLogReport");
			//
			xml.endDocument();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	/**
	 * @param contentId
	 *            当前选择的节目ID 当contenttype�?�?时为频道的contentid
	 * @param contentType
	 *            节目类型 1 点播 2 频道 3 回看
	 * @param startTime
	 *            起始时间，格�?YYYY-MM-DD HH24:MM:SS
	 * @param endTime
	 *            结束时间,格式 YYYY-MM-DD HH24:MM:SS
	 * @param TVODStartTime
	 *            （非必需，但是contenttype==3时必�?��TVOD�?��时间,
	 *            对应于Schedule上的startdate和starttime, 格式 YYYY-MM-DD HH24:MM:SS
	 * @param duration
	 *            播放时长 (�?
	 * @param timeshiftDuration
	 *            （非必需�?时移播放时长(�?
	 * @param terminalId
	 *            终端设备ID
	 * @param userId
	 *            用户业务编号
	 * @param userToken
	 *            业务管理平台为该用户分配的临时身份证�?
	 * @return
	 */
	public Request getPlayLogReport(String contentId, String contentType,
			String startTime, String endTime, String TVODStartTime,
			String duration, String timeshiftDuration, String terminalId,
			String userId, String userToken) {

		String body = playLogXMLData(contentId, contentType, startTime,
				endTime, TVODStartTime, duration, timeshiftDuration,
				terminalId, userId, userToken);
		// request = new Request(ServerInterfaceDefinition.OPT_PLAYLOGREPORT,
		// body, new ErrorParser());
		request.setBody(body);
		request.setServerInterfaceDefinition(ServerInterfaceDefinition.OPT_PLAYLOGREPORT);
		request.setXmlParser(new ErrorParser());
		return request;
	}
}
