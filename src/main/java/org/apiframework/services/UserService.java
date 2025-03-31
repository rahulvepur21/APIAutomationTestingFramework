package org.apiframework.services;

import java.util.HashMap;
import java.util.Map;

import org.apiframework.constants.APIEndpoints;
import org.apiframework.models.User;

import io.restassured.response.Response;

public class UserService extends BaseService{

	public Response getUser(String endpoint,Map<String,String> queryparams) {
		addQueryParamsToReqSpec(queryparams);
		return getRequest(endpoint);
	}
	
	public Response createUser(String endpoint,User user) {
		return postRequest(endpoint,user);
	}
}
