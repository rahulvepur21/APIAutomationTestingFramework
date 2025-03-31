package org.apiframework.tests;

import java.util.Map;

import org.apiframework.constants.APIEndpoints;
import org.apiframework.constants.BaseURI;
import org.apiframework.models.User;
import org.apiframework.services.UserService;
import org.apiframework.testdata.TestDataProvider;
import org.apiframework.utils.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

public class userServiceTests {
	User user;
	UserService userservice;
	Response response;
	JsonPath jsonpath;
	
	@Test
	public void createUserServiceValidtion() {
		user=null;
		userservice=new UserService();
		response=userservice.createUser(APIEndpoints.USER,user);
		jsonpath=new JsonPath(response.asString());
		String responseMesage=jsonpath.getString("message");
		System.out.println(response.asPrettyString());
		Assert.assertEquals(Integer.toString(user.getId()), responseMesage,"user id in request and Message in response should match");
	}
	
	@Test(dataProvider="dynamicTestDataProvider",dataProviderClass=TestDataProvider.class)
	public void createUserValid(String excelTestCaseName,String endpoint, String jsonPayload, Map<String,String> queryParams) throws JsonMappingException, JsonProcessingException {
		user=Utility.convertJsonToPojo(jsonPayload, User.class);
		userservice=new UserService();
		response=userservice.createUser(endpoint,user);
		jsonpath=new JsonPath(response.asString());
		String responseMesage=jsonpath.getString("message");
		System.out.println(response.asPrettyString());
		Assert.assertEquals(Integer.toString(user.getId()), responseMesage,"user id in request and Message in response should match");
	}
	
	@Test(dataProvider="dynamicTestDataProvider",dataProviderClass=TestDataProvider.class)
	public void getUserValid(String excelTestCaseName,String endpoint, String jsonPayload, Map<String,String> queryParams) throws JsonMappingException, JsonProcessingException {
		userservice=new UserService();
		response=userservice.getUser(endpoint,queryParams);
		System.out.println(response.asPrettyString());
		jsonpath=new JsonPath(response.asString());
		String responseUsername=jsonpath.getString("username");
		System.out.println(response.asPrettyString());
		Assert.assertEquals(queryParams.get("username"), responseUsername,"username in request and username in response should match");
//		System.out.println(RestAssured.given().queryParams(queryParams).log().all()
//		.get(BaseURI.BASE_URI_2+APIEndpoints.USER).asPrettyString());
	}
	
}
