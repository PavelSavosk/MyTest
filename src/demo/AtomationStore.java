package demo;

import Pojo.LoginToAutomatedStore;
import Pojo.OrderDetails;
import Pojo.Orders;
import Pojo.ResponseAfterLoginAutoStore;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AtomationStore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		
		// only base URI here
		RequestSpecification login =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		
		LoginToAutomatedStore log = new LoginToAutomatedStore();
		log.setUserEmail("savoskapavel@gmail.com");
		log.setUserPassword("12859Test!");
		
		
		//Login using login specification and added UserEmail and Password
		RequestSpecification logintomyaccount = given().log().all().spec(login).body(log);
		
		// SpecBuilder to validate status code 200
		ResponseSpecification responsefrompostLogin = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		//  Extract Response after login
		ResponseAfterLoginAutoStore responsefrompostLoginToAccount = logintomyaccount.when().post("api/ecom/auth/login").then().log().all().spec(responsefrompostLogin).extract().response().as(ResponseAfterLoginAutoStore.class);
		
	    String tokenafterloging = responsefrompostLoginToAccount.getToken();
	    String userId = responsefrompostLoginToAccount.getUserId();
	    
	    
	    //Add Product
	    /*

	    RequestSpecification forproduct = new RequestSpecBuilder().
	    		setBaseUri("https://rahulshettyacademy.com").
	    		addHeader("authorization", tokenafterloging).
	    		build();
	    
	    RequestSpecification postproductrequest = 
	    		given().log().all().spec(forproduct).
	    param("productName", "laptop").
	    param("productAddedBy", userId).
	    param("productCategory", "laptop").
	    param("productSubCategory", "laptop").
	    param("productPrice", "1").
	    param("productDescription", "laptop").
	    param("productFor", "all").
	    multiPart("productImage", new File ("C:\\Users\\Pavel\\workspace\\laptop.jpg"));
	    
	    
	    String addproductresponse = postproductrequest.when().post("api/ecom/product/add-product").then().log().all().extract().asString();
	    JsonPath p = new JsonPath(addproductresponse);
	    System.out.println(addproductresponse);
	    
	    String productId = p.getString("productId");
	    System.out.println("My added product ID = " + productId);
	    
	    */
	    
	    //Create Order
	    
	    /*
	    RequestSpecification addorder = new RequestSpecBuilder().
	    		setBaseUri("https://rahulshettyacademy.com").
	    		addHeader("authorization", tokenafterloging).
	    		setContentType(ContentType.JSON).build();
	   
	    OrderDetails orderDetails = new OrderDetails();
	    orderDetails.setCountry("India");
	    orderDetails.setProductOrderedId(productId);
	    
	    List <OrderDetails> orderdetailslist = new ArrayList<OrderDetails>();
	    orderdetailslist.add(orderDetails);
	    
	    Orders orders = new Orders();
	    orders.setOrders(orderdetailslist);
	    
	    RequestSpecification createOrderRequest = given().log().all().spec(addorder).body(orders);
	    String responsefromaddorder = createOrderRequest.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
	    
	    */
	    
	    
	    // Delete product
	    
	    RequestSpecification deleteProdBaseReqSpec = new RequestSpecBuilder().
	    		setBaseUri("https://rahulshettyacademy.com").
	    		addHeader("authorization", tokenafterloging).
	    		build();
	    
	    String productId = "65f999d8a86f8f74dca40c91";
	    
	    RequestSpecification deleteProdRequest = given().log().all().spec(deleteProdBaseReqSpec).pathParam("productId", productId);
	    
	    String responseFromDeleteProduct = deleteProdRequest.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
	   
	    JsonPath d = new JsonPath(responseFromDeleteProduct);
	    String messageifsuccess = d.getString("message");
	    System.out.println(messageifsuccess);
	    
	    

	

	}

}
