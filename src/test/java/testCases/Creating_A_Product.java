package testCases;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Creating_A_Product {
	
	@Test
	public void Create_one_Product() {
		String path = ".\\src\\main\\java\\data\\Create.json";
		Response response = given().
		baseUri("https://techfios.com/api-prod/api/product").
		header("Content-Type","application/json; charset=UTF-8")
		.body(new File(path)).		
		when().
		post("/create.php").
		then()
		.extract().response();
		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode, 201);
		
		String bodyCreated = response.getBody().asString();
		System.out.println("Response Body" + bodyCreated);
		
		JsonPath jp = new JsonPath(bodyCreated);
		String actualResponseMessage = jp.get("message");
		Assert.assertEquals(actualResponseMessage, "Product was created.");
		
		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		if(actualResponseTime<=2000) {
			System.out.println("Response time with in range");
		}
		else {
			System.out.println("Response time with out of range");
		}
	}

}
