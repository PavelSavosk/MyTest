package demo;
import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.groovy.json.internal.JsonParserCharArray;
import org.testng.Assert;
import Pojo.Api;
import Pojo.GetCourse;
import Pojo.Mobile;
import Pojo.WebAutomation;
import io.restassured.path.json.JsonPath;
public class OAuthTest {

	public static void main(String[] args) {
		
	String[] automtitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};
	String[] apititles = {"Rest Assured Automation using Java", "SoapUI Webservices testing"};
	String[] mobiletitles = {"Appium-Mobile Automation using Java"};
		
		// TODO Auto-generated method stub
		String response = given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath( response );
		String accessToken = js.getString("access_token");
		System.out.println("Access token is " + accessToken);
		
		GetCourse responsefromget = given()
		.queryParam("access_token", accessToken)
		.when()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		
		System.out.println(responsefromget.getInstructor());
		System.out.println(responsefromget.getLinkedIn());
		System.out.println(responsefromget.getCourses());
		System.out.println(responsefromget.getCourses().getApi().get(1).getCourseTitle());
		
	
		
		// Get API courses titles
		List<String> apicoursestitles = new ArrayList<String>();
		List<Api> apiCourses = responsefromget.getCourses().getApi(); 
		for (int i = 0; i < apiCourses.size(); i++ ) 
		{
		apicoursestitles.add(apiCourses.get(i).getCourseTitle());
		}
		List<String> expectedAPICourses = Arrays.asList(apititles);
		
		//Assert.assertTrue(apicoursestitles.equals(expectedAPICourses));
		boolean assertionresultAPICourses = apicoursestitles.equals(expectedAPICourses);
		if (assertionresultAPICourses) {
			
			System.out.println("API Corses Assertion is true! Courses match as expected");
		}
		else 
		{
			 System.out.println("API Assertion is false! Courses do not match as expected.");
			 
		}
		
		
		// Get webautomation courses titles
		ArrayList<String> webautomationnames = new ArrayList<String>();
		List<WebAutomation> webauto = responsefromget.getCourses().getWebAutomation();
		for (int i = 0; i < webauto.size(); i++) 
		{
	    webautomationnames.add(webauto.get(i).getCourseTitle()); 	
		}
		List<String> expectedListOfWebAutoCourses = Arrays.asList(automtitles);
		Assert.assertTrue(webautomationnames.equals(expectedListOfWebAutoCourses));
		
		
		
		// Get Mobile Courses titles
		
		// Create a List of actual mobile courses. 
	//	ArrayList<String> actualmobilecoursestitles = new ArrayList<String>();
		
		// Response to get mobile app courses
	//	List <Mobile> listmobilecourses = responsefromget.getCourses().getMobile();
		// Get each course from response
	//	for (int i = 0; i < listmobilecourses.size(); i++) {
	//	actualmobilecoursestitles.add( listmobilecourses.get(i).getCourseTitle());
	//	List<String> expectedmobilecourses = Arrays.asList(mobiletitles);
	//	Assert.assertTrue(actualmobilecoursestitles.equals(expectedmobilecourses));
		// if (listmobilecourses.get(i).getCourseTitle().equalsIgnoreCase("Appium-Mobile Automation using Java"))
		//System.out.println( listmobilecourses.get(i).getPrice());
		
	//	}
		
		// Get Mobile Courses titles
		ArrayList<String> am = new ArrayList<String>();
		List<Api> mobcourses = responsefromget.getCourses().getApi();
		for (int i = 0; i < mobcourses.size(); i++) 
		
		{
			am.add(mobcourses.get(i).getCourseTitle());
			
		}
		
		List<String> givenAPI = Arrays.asList(apititles);
		Assert.assertTrue(givenAPI.equals(am));
		
		
	}
	{
	
	System.out.println("TestGit");
	System.out.println("TestGit1");
	
	System.out.println("Ok. It looks good to me");
	System.out.println("Lets to with this");
	
	System.out.println("Added");
	System.out.println("New");
	
	System.out.println("Branch");
	System.out.println("Lets checkit out");
	
	System.out.println("Lets checkit out");
	
	System.out.println("Added to develop");

	}
}
