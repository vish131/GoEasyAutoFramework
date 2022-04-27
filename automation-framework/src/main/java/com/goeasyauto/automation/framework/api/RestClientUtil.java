package com.goeasyauto.automation.framework.api;

import java.util.Map;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;

import com.github.dockerjava.transport.DockerHttpClient.Request.Method;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClientUtil {

	private static Respond respond = null;
	private static final Logger log = LogManager.getLogger(RestClientUtil.class);
	
	/**
	 * Perform RESTful request without request body, path, query parameter
	 * 
	 * @param base Uri - URL of the API
	 * @param methodType - Type of the method (GET, POST, PUT, DELETE, PATCH)
	 * @param header - Headers for the request
	 * @param path - Path if any which required for the request URL. 
	 * @return REspond object 
	 *  
	 * **/
		
	public static Response doHttpRequestNoBody(String baseUri, Method methodType, Map<String,String> headers, String path) {

		RequestSpecification requestSpecifications = initRequestSpecifications(headers, null, null, null, baseUri);
	response = sendRequestSpecifications(baseUri, requestSpecifications, methodType, path);
	return response;
}
	
	/***
	 * Perform RESTful request without request body, path, query parameter
	 * 
	 * @param base Uri - URL of the API
	 * @param methodType - Type of the method (GET, POST, PUT, DELETE, PATCH)
	 * @param header - Headers for the request
	 * @param path - Path if any which required for the request URL. 
	 * 
	 * @param expectedStatusCode - Expected status code of the request
	 * @return REspond body as a string
	 * 
	 * */
	
	public static String doHttpRequestNoBody(String baseUri, Method methodType,
			Map<String,String> headers, String path, int expectedStatusCode) {
		
		String responseString;
		RequestSpecification requestSpecifications = initRequestSpecifications(headers, null, null, null, baseUri);
	response = sendRequestSpecifications(baseUri, requestSpecifications, methodType, path);
	response.then().statusCode(expectedStatusCode);
	responseString = response.body().asString();
	return responseString;
	}
	
	public static String doHttpRequestNoBody(String baseUri, Method methodType, Map<String, String> headers, 
			Map<String, String> queryParameters, String path, int expectedStatusCode ) {
		
		String responseString;
		RequestSpecification requestSpecifications = initRequestSpecifications(headers, null, null,
				queryParameters, baseUri);
	response = sendRequestSpecifications(baseUri, requestSpecifications, methodType, path);
	response.then().statusCode(expectedStatusCode);
	responseString = response.body().asString();
	return responseString;
	}
	
	public static String doHttpRequestNoBody(String baseUri, Method methodType, Map<String, String> headers, 
			Map<String, String> pathParameters,	Map<String, String> queryParameters, String path,
			int expectedStatusCode ) {
		
		String responseString;
		RequestSpecification requestSpecifications = initRequestSpecifications(headers, null, null,
				queryParameters, baseUri);
	response = sendRequestSpecifications(baseUri, requestSpecifications, methodType, path);
	response.then().statusCode(expectedStatusCode);
	responseString = response.body().asString();
	return responseString;
	}
	
	//<T> - Type of request body
	
	public static <T> String doHttpRequestWithBody(String baseUri, Method methodType, Map<String, String> headers,
							T requestBody, Map<String, String> queryParameters, String path,
							int expectedStatusCode) {
		
		String responseString;
		RequestSpecification requestSpecifications = initRequestSpecifications(headers, requestBody, null,
				queryParameters, baseUri);
		response = sendRequestSpecifications(baseUri, requestSpecifications, methodType, path);
		response.then().statusCode(expectedStatusCode);
		responseString = response.body().asString();
		return responseString;
	}
	
	public static <T> String doHttpRequestWithBody(String baseUri, Method methodType, Map<String, String> headers,
			T requestBody, Map<String, String> pathParameters, Map<String, String> queryParameters, String path,
			int expectedStatusCode) {

		String responseString;
		RequestSpecification requestSpecifications = initRequestSpecifications(headers, requestBody, pathParameters,
								queryParameters, baseUri);
		response = sendRequestSpecifications(baseUri, requestSpecifications, methodType, path);
		response.then().statusCode(expectedStatusCode);
		responseString = response.body().asString();
		return responseString;
	}
	
	public static Response doHttpRequestNoBody(String baseUri, Method methodType, Map<String, String> headers,
			Map<String, String> queryParameters, String path) {
		
		RequestSpecification requestSpecifications = initRequestSpecification(headers, null, null, queryParameters, baseUri);
		response = sendRequestSpecifications(baseUri, requestSpecifications, methodType, path);
		return response;
	}
	
	private static <T> RequestSpecification initiRequestSPecifications( Map<String, String> headers,
			T requestBody, Map<String, String> pathParameters, Map<String, String> queryParameters,String baseUri) {
		
		RequestSpecification request = RestAssured.given();
		request.config(RestAssured.config().encoderConfig(
				EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
		.baseUri(baseUri);
		
		if(headers != null) {
			request.headers(headers);
		}
		if(headers != null) {
			request.body(requestbody);
		}
		if(pathParameters != null) {
			request.pathParams(pathParameters);
		}
		if(queryParameters != null) {
			request.queryParams(queryParameters);
		}
		return request;
		
	}
	
	private static Response sendRequestSpecifications(String baseUri, RequestSpecification request,
			Method methodType, String path) {
		String pathvalue = "";
		
		if (path != null) {
			pathvalue = path;
	}
		request.log().all();
		try {
			switch(methodType) {
			case GET:
				response = request.get(pathvalue);
				break;
			case PUT:
				response = request.put(pathvalue);
				break;
			case POST:
				response = request.post(pathvalue);
				break;
			case DELETE:
				response = request.delete(pathvalue);
				break;
			case HEAD:
				response = request.head(pathvalue);
				break;
			case OPTIONS:
				response = request.options(pathvalue);
				break;
			case PATCH:
				response = request.patch(pathvalue);
				break;
			default:
				log.error(methodType + "method not available for the api: " + baseUri);
			}
			
		} catch (Exception e) {
			try {
				request.proxy(DataManager.getProxyHost(), DataManager.getProxyPort()); 
				switch (methodType) {
				case GET:
					response = request.get(pathvalue);
					break;
				case PUT:
					response = request.put(pathvalue);
					break;
				case POST:
					response = request.post(pathvalue);
					break;
				case DELETE:
					response = request.delete(pathvalue);
					break;
				case HEAD:
					response = request.head(pathvalue);
					break;
				case OPTIONS:
					response = request.options(pathvalue);
					break;
				case PATCH:
					response = request.patch(pathvalue);
					break;
				default:
					log.error(methodType + "method not available for the api: " + baseUri);
				}
				
			} catch (Exception e) {
				log.error(methodType + " method not available for the api: " + baseUri)
			}
		}
		response.then().log.all();
		return response;
	}
		
		}
	
	
	
	
	
	
