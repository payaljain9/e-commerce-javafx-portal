package org.jain.user.dto;



public class UpdateUserResponse {
	
	UpdateUserRequest request;
	
	String responseMessage;
	String responseCode;
	
	public UpdateUserRequest getRequest() {
		return request;
	}
	public void setRequest(UpdateUserRequest request) {
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
