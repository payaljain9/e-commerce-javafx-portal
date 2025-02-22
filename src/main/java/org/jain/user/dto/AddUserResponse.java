package org.jain.user.dto;


public class AddUserResponse {

	AddUserRequest request;
	
	Integer userId;
	String responseMessage;
	String responseCode;
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public AddUserRequest getRequest() {
		return request;
	}
	public void setRequest(AddUserRequest request) {
		this.request = request;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	
}
