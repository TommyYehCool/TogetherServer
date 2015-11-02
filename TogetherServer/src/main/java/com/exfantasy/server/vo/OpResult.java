package com.exfantasy.server.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "op_result")
public class OpResult {
	/**
	 * 操作結果
	 */
	private int resultCode;
	/**
	 * 操作結果訊息
	 */
	private String resultMsg;
	
	public OpResult() {
	}
	
	public OpResult(int resultCode) {
		this.resultCode = resultCode;
	}
	
	public OpResult(int resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	@Override
	public String toString() {
		return "OpResult [resultCode=" + resultCode + ", resultMsg=" + resultMsg + "]";
	}
}
