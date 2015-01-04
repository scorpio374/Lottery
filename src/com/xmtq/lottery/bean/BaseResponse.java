package com.xmtq.lottery.bean;

import java.io.Serializable;

public abstract class BaseResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5375804597574885028L;
	public String code = "500";
	public String message;
}
