package com.bob.bobmobileapp.http.beans;

public class ApplicativeResponse {

	public static final int FAILURE = -1;
	public static final int SUCCESS = 0;
	public static final int SUCCESS_WITH_MESSAGE = 1;


	private String msg;
	private int code;
	private String status;
	
	public ApplicativeResponse(){

	}
	
	
	public ApplicativeResponse(String msg,String stat,int code){
		this.msg = msg;
		this.code = code;
		this.status = stat;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setSuccess(){
		this.code = 0;
		this.msg = "OK";
		this.status = "Success";
	}
	
	public void setSuccessWithMsg(String msg){
		this.code = 0;
		this.msg = msg;
		this.status = "Success";
	}
	
	public void setFailure(String message, String status){
		this.code = 1;
		this.msg = message;
		this.status = status;
	}
	
}
