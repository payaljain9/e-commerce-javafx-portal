package org.jain.product.dto;


public class UpdateProductResponse {
	
	UpdateProductRequest request;
	
	String responseMessage;
	String responseCode;
	
	public UpdateProductRequest getRequest() {
		return request;
	}
	public void setRequest(UpdateProductRequest request) {
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
