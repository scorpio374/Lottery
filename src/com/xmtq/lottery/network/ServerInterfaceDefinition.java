package com.xmtq.lottery.network;

public enum ServerInterfaceDefinition {

	/**
	 * 获取内容详情
	 */
	OPT_GETCONTENTDETAIL("GetContentDetail"),

	/**
	 * 获取节目单列�?
	 */
	OPT_GETSCHEDULELIST("GetScheduleList"),

	/**
	 * 用户播放日志上报接口
	 */
	OPT_PLAYLOGREPORT("PlayLogReport"),

	/**
	 * 获取频道列表
	 */
	OPT_GETCHANNELLIST("GetChannelList");

	private String opt;
	private RequestMethod requestMethod = RequestMethod.POST;
	private int retryNumber = 1;

	private ServerInterfaceDefinition(String opt) {
		this.opt = opt;
	}

	private ServerInterfaceDefinition(String opt, RequestMethod requestMethod) {
		this.opt = opt;
		this.requestMethod = requestMethod;
	}

	private ServerInterfaceDefinition(String opt, RequestMethod requestMethod,
			int retryNumber) {
		this.opt = opt;
		this.requestMethod = requestMethod;
		this.retryNumber = retryNumber;
	}

	public String getOpt() {
		return opt;
	}

	public RequestMethod getRequestMethod() {
		return requestMethod;
	}

	public int getRetryNumber() {
		return retryNumber;
	}

	public enum RequestMethod {
		POST("POST");
		private String requestMethodName;

		RequestMethod(String requestMethodName) {
			this.requestMethodName = requestMethodName;
		}

		public String getRequestMethodName() {
			return requestMethodName;
		}
	}
}
