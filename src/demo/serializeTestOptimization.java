package demo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Pojo.Location;
import Pojo.addPlaceAPI;

public class serializeTestOptimization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		// RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		addPlaceAPI p = new addPlaceAPI();
		
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		List <String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		
		RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").build();
		
		ResponseSpecification specforresponse =  new ResponseSpecBuilder().expectStatusCode(200).build();
		
		RequestSpecification postmethod = given().log().all().spec(request).body(p);
		
		Response getresponsefrompost = postmethod.when().post("maps/api/place/add/json").then().spec(specforresponse).extract().response();
		
		// Response res = given().
		// queryParam("key", "qaclick1").
		// body(p).
		// when().post("maps/api/place/add/json").
		// then().assertThat().statusCode(200).extract().response();
		
		String responseString = getresponsefrompost.asString();
		System.out.println(responseString);

	}

}
