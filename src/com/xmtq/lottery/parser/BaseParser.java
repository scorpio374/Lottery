package com.xmtq.lottery.parser;

import com.xmtq.lottery.bean.BaseResponse;


public abstract class BaseParser<T extends BaseResponse> {
	public static final String ERROR_CODE = "code";
	public static final String MSG = "msg";

	public abstract T parse(String inString);
}
