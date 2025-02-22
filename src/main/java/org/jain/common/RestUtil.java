package org.jain.common;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static <T>T postRequest(String url, Object body, Class<?> responseClassType) throws IOException, InterruptedException
	{
		//convert body object to string data which is needed to send in request.
		//object to string(marshalling)
		String strBody = objectMapper.writeValueAsString(body);
		
		//rest client---postman
		//effective java code  class      static method that returns obj
		HttpClient client = HttpClient.newHttpClient();
		
		//request
		HttpRequest request = HttpRequest
				.newBuilder()
				.uri(URI.create(url))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(strBody))
				.build();
		
		//response
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		
		//string response to object
		T responseObject = (T) objectMapper.readValue(response.body(), responseClassType);
		
		return responseObject;
	}
	
	
	public static <T>T getRequest(String url, Class<?> responseClassType) throws IOException, InterruptedException
	{
		//rest client---postman
		HttpClient client = HttpClient.newHttpClient();
		
		//request
		HttpRequest request = HttpRequest
				.newBuilder()
				.uri(URI.create(url))
				.GET()
				.build();
		
		//response
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		//string response to object
		T responseObject = (T) objectMapper.readValue(response.body(), responseClassType);
				
		return responseObject;
	}
	
	
	public static <T>T deleteRequest(String url, Class<?> responseClassType) throws IOException, InterruptedException
	{
		//rest client---postman
		HttpClient client = HttpClient.newHttpClient();
		
		//request
		HttpRequest request = HttpRequest
				.newBuilder()
				.uri(URI.create(url))
				.DELETE()
				.build();
		
		//response
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		//string response to object
		T responseObject = (T) objectMapper.readValue(response.body(), responseClassType);
		
		return responseObject;
	}
}
