package org.apiframework.services;

import static io.restassured.RestAssured.*;
import org.apiframework.filters.LoggingFilter;

import java.util.Map;

import org.apiframework.constants.BaseURI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {

	//protected RequestSpecification requestSpec;
	private static final ThreadLocal<RequestSpecification> requestSpec = new ThreadLocal<>();

	static {
		RestAssured.filters(new LoggingFilter());
	}

	//	public BaseService() {
	//		requestSpec = given().baseUri(BaseURI.BASE_URI_1);
	//	}
	//	


	protected RequestSpecification getRequestSpec() {
		RequestSpecification spec = requestSpec.get();
		if (spec == null) {
			spec = initRequestSpec();
			requestSpec.set(spec);
		}
		return spec;
	}

	private RequestSpecification initRequestSpec() {
		return RestAssured.given().contentType(ContentType.JSON).baseUri(BaseURI.BASE_URI_1);
	}

	protected void clearRequestSpec() {
		requestSpec.remove();
	}
	public RequestSpecification addQueryParamsToReqSpec(Map<String,String> queryparams) {

		getRequestSpec().queryParams(queryparams);
		return getRequestSpec();
	}
	public Response postRequest(String endpoint,Object payload) {
		return getRequestSpec().body(payload).post(endpoint); 
	}

	public Response getRequest(String endpoint) {
		return getRequestSpec().get(endpoint);
	}
}
