package com.jonatasferreira.demo.resources.exceptions;

public class StandardError {

	private Integer status;
	private String msg;
	private Long timeStamp;
	
	public StandardError(Integer status, String msg, Long timeStamp) {
		this.status = status;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}
	
}
