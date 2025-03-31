package org.apiframework.services;

import java.util.HashMap;
import java.util.Map;

import org.apiframework.constants.APIEndpoints;
import org.apiframework.models.Place;

import io.restassured.response.Response;

public class PlaceService extends BaseService{

	
	
	public Response addPlace(String endpoint,Map<String, String> queryparams,Place payload) {
		addQueryParamsToReqSpec(queryparams);
		return postRequest(endpoint,payload);
	}
	
	public Response getPlace(String endpoint,Map<String, String> queryparams) {
		addQueryParamsToReqSpec(queryparams);
		return getRequest(endpoint);
	}
}
