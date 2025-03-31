package org.apiframework.tests;

import java.util.Map;

import org.apiframework.listeners.TestListener;
import org.apiframework.models.*;
import org.apiframework.services.PlaceService;
import org.apiframework.testdata.TestDataProvider;
import org.apiframework.utils.Utility;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.restassured.response.Response;

@Listeners(TestListener.class)
public class AppTest {
	Response response;
	PlaceService placeService;
	Map<String,String> queryParams;
	
	@Test(dataProvider="dynamicTestDataProvider",dataProviderClass=TestDataProvider.class)
	public void placeServiceAddPlaceVaidation(String excelTestCaseName,String endpoint,String jsonPayload, Map<String,String> queryParams) {
		placeService=new PlaceService();
		Place place1=Utility.convertJsonToPojo(jsonPayload, Place.class);
		String add=place1.getAddress();
		System.out.println(add);
		response=placeService.addPlace(endpoint,queryParams,place1);
		System.out.println(response.asPrettyString());

	}
	@Test(dataProvider="dynamicTestDataProvider",dataProviderClass=TestDataProvider.class)
	public void placeServiceGetPlaceVaidation(String excelTestCaseName,String endpoint,String jsonPayload, Map<String,String> queryParams) {
		placeService=new PlaceService();
		response=placeService.getPlace(endpoint,queryParams);
		System.out.println(response.asPrettyString());
		Assert.assertEquals(response.statusCode(), 200,"placeServiceGetPlaceSatuscodeVaidation");
		 
	}
}
