package com.keepthinker.example.general.test.mokito;

public class Result {
	private boolean success;
	private String msg;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Result(boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	public Result(){
	}
	@Override
	public String toString() {
		return "Result [success=" + success + ", msg=" + msg + "]";
	}
	
	
	
}
