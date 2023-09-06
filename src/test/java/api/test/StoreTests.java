package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndPoints;
import api.payload.Order;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StoreTests {

	Faker faker;
	Order orderPayload;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		orderPayload = new Order();
		
		orderPayload.setId(Long.valueOf(0));
		orderPayload.setPetId(0);
		orderPayload.setQuantity(1);
		orderPayload.setShipDate("2023-09-04T11:34:08.372Z");
		orderPayload.setStatus("placed");
		orderPayload.setComplete(true);
	}
	
	@Test(priority=1)
	public void testPlaceOrder()
	{
		Response response = StoreEndPoints.placeOrder(orderPayload);
		response.then().log().all();
		JsonPath jsonPathEvaluator = response.jsonPath();
		Long orderId = jsonPathEvaluator.get("id");
		orderPayload.setId(orderId);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testGetOrderDetails()
	{
		Response response = StoreEndPoints.getOrderDetails(this.orderPayload.getId());
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void testDeleteOrder()
	{
		Response response = StoreEndPoints.deleteOrder(this.orderPayload.getId());
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=4)
	public void testGetInventory()
	{
		Response response = StoreEndPoints.getInventory();
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
}
