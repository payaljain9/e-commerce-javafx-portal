package org.jain.product.dto;


public class AddProductResponse {
	
	AddProductRequest request;
	
	Integer productId;
	String responseMessage;
	String responseCode;
	
	public AddProductRequest getRequest() {
		return request;
	}
	public void setRequest(AddProductRequest request) {
		this.request = request;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
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
