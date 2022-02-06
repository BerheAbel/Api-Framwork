package testCases;
import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Reading_All_Products {
	
	@Test
	public void read_products() {
		
	Response response = given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.header("Content-Type","application/json; charset=UTF-8").
	when()
		.get("/read.php").
	then()
		.extract().response();
		
	  int actualStatusCode = response.getStatusCode();
	  Assert.assertEquals(actualStatusCode, 200);
	  
	  
	}

}
