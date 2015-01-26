package com.xmtq.lottery.bean;

import java.io.Serializable;

public abstract class BaseResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5375804597574885028L;

	// Header
	public String transactiontype;
	public String timestamp;
	public String agenterid;
	public String ipaddress;
	public String source;
	public String digest;

	// ErrorCode
	public String errorcode;
	public String errormsg;

}
