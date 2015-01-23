package com.xmtq.lottery.bean;

import java.io.Serializable;

public abstract class BaseResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5375804597574885028L;

	// Header
	private String transactiontype;
	private String timestamp;
	private String agenterid;
	private String ipaddress;
	private String source;
	private String digest;

	// ErrorCode
	private String errorcode;
	private String errormsg;

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setAgenterid(String agenterid) {
		this.agenterid = agenterid;
	}

	public String getAgenterid() {
		return agenterid;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSource() {
		return source;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getDigest() {
		return digest;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public String getErrormsg() {
		return errormsg;
	}
}
