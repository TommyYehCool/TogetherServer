package com.exfantasy.server.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OpResult {
	private int resultCode;

	private String resultMsg;
	
	public OpResult() {
	}

	@JsonCreator
	public OpResult(@JsonProperty("resultCode") int resultCode) {
		this.resultCode = resultCode;
	}
	
	@JsonCreator
	public OpResult(@JsonProperty("resultCode") int resultCode, 
					@JsonProperty("resultMsg") String resultMsg) {
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
