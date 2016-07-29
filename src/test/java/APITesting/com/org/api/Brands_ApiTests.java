package APITesting.com.org.api;

import org.json.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.*;

public class Brands_ApiTests {
	String Json;
	String GetBrandUrlById;

	@Test(priority=1)
	public void Brands_DeleteAllApiTest(){
		System.out.println("Test 1 started to delete all Brands in the Store ...");
		System.out.println("----------------------------------------------------");

		Response resp = given().auth().basic("dineshprabhu", "f74c95e01f047baf95b5d0b43829948222e31d8c").
				when().
				delete("https://store-ybmmysy2kr.mybigcommerce.com/api/v2/brands");

		Assert.assertEquals(resp.getStatusCode(), 204);
		System.out.println("Deleted all Brands in Store: The API response code is : " + resp.getStatusCode() );
		System.out.println("----------------------------------------------------");

	}

	//@Test
	public void Brands_PostApiTest(){

		Response resp = given().auth().basic("dineshprabhu", "f74c95e01f047baf95b5d0b43829948222e31d8c").
				body(" {  \"name\": \"Zmen\","
						+ "\"page_title\": \"Z_men_brand\" } "
						).
				when().
				contentType(ContentType.JSON).
				post("https://store-ybmmysy2kr.mybigcommerce.com/api/v2/brands.json");

		Assert.assertEquals(resp.getStatusCode(), 201);
		System.out.println("The API response code is : " + resp.getStatusCode() );
		System.out.println("The API Response is : " + resp.asString());
		System.out.println("----------------------------------------------------");

	}

	@Test(priority=2)
	public void Brands_PostApiTest_usingObjects(){
		System.out.println("Test 2 started to Post API to add a brand ...");
		System.out.println("----------------------------------------------------");

		Postapi_data postdata = new Postapi_data();
		postdata.setName("randomname");
		postdata.setpage_title("random_page_title");

		Response resp = given().auth().basic("dineshprabhu", "f74c95e01f047baf95b5d0b43829948222e31d8c").
				body(postdata).
				when().
				contentType(ContentType.JSON).
				post("https://store-ybmmysy2kr.mybigcommerce.com/api/v2/brands.json");

		Assert.assertEquals(resp.getStatusCode(), 201);
		System.out.println("The API response code is : " + resp.getStatusCode() );
		System.out.println("The API Response is : " + resp.asString());
		System.out.println("----------------------------------------------------");

	}


	@Test(priority=3)
	public void Brands_GetCountApiTest(){

		System.out.println("Test 3 started to get total count of brand ...");
		System.out.println("----------------------------------------------------");

		Response resp = given().auth().basic("dineshprabhu", "f74c95e01f047baf95b5d0b43829948222e31d8c").
				when().
				get("https://store-ybmmysy2kr.mybigcommerce.com/api/v2/brands/count.json");
		Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println("The API response code is : " + resp.getStatusCode() );
		System.out.println("The API Response is : " + resp.asString());
		System.out.println("----------------------------------------------------");

	}

	@Test(priority=4)
	public void Brands_GetApiTest(){

		System.out.println("Test 4 started to get Brands ...");
		System.out.println("----------------------------------------------------");

		Response resp = given().auth().basic("dineshprabhu", "f74c95e01f047baf95b5d0b43829948222e31d8c").
				when().
				get("https://store-ybmmysy2kr.mybigcommerce.com/api/v2/brands.json");
		Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println("The API response code is : " + resp.getStatusCode() );
		Json = resp.asString();
		System.out.println("----------------------------------------------------");

	}

	@Test(priority=5)
	public void Test_GetBrandById(){


		System.out.println("Test 5 started to get Brands by ID ...");
		System.out.println("----------------------------------------------------");

		Json=Json.replace("[","");
		String b = Json.replace("]","");

		JSONObject jsonObject = new JSONObject(b);
		int id = jsonObject.getInt("id");
		System.out.println("The Id number of the element is :"+ id);

		String GetBrandUrl = "https://store-ybmmysy2kr.mybigcommerce.com/api/v2/brands/";
		GetBrandUrlById = GetBrandUrl+id+".json";

		Response resp1 = given().auth().basic("dineshprabhu", "f74c95e01f047baf95b5d0b43829948222e31d8c").
				when().
				get(GetBrandUrlById);
		Assert.assertEquals(resp1.getStatusCode(), 200);
		System.out.println("The API response code is : " + resp1.getStatusCode() );
		System.out.println("The API Response of the element with id :"+ id +" is "+ resp1.asString());
		System.out.println("----------------------------------------------------");

	}

	@Test (priority=6)
	public void Brands_PutApiTest_usingObjects(){
		System.out.println("Test 6 started to Put/Update a Brands by ID ...");
		System.out.println("----------------------------------------------------");

		Postapi_data postdata = new Postapi_data();
		postdata.setName("updatedName");
		postdata.setpage_title("Updated_page_title");

		Response resp = given().auth().basic("dineshprabhu", "f74c95e01f047baf95b5d0b43829948222e31d8c").
				body(postdata).
				when().
				contentType(ContentType.JSON).
				put(GetBrandUrlById);

		Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println("The API response code is : " + resp.getStatusCode() );
		System.out.println("The API Response is : " + resp.asString());
		System.out.println("----------------------------------------------------");

	}

	
	//@Test(priority=7)
	public void Brands_DeleteApiTest(){

		Response resp = given().auth().basic("dineshprabhu", "f74c95e01f047baf95b5d0b43829948222e31d8c").
				when().
				delete("https://store-ybmmysy2kr.mybigcommerce.com/api/v2/brands/35");

		//Assert.assertEquals(resp.getStatusCode(), 200);
		System.out.println("The API response code is : " + resp.getStatusCode() );
		System.out.println("The API Response is : " + resp.asString());

	}



}
