package api.endpoints;

import static io.restassured.RestAssured.given;
import api.payload.Order;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {

	public static Response placeOrder(Order payload){
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.place_order);
		
		return response;

	}
	
	public static Response getOrderDetails(Long orderId){
		Response response = given()
				.pathParam("orderId", orderId)
		.when()
			.get(Routes.get_order_details);
		
		return response;

	}
	
	public static Response deleteOrder(Long orderId){
		Response response = given()
				.pathParam("orderId", orderId)
		.when()
			.delete(Routes.delete_order);
		
		return response;
	}
	
	public static Response getInventory(){
		Response response = given()
		.when()
			.get(Routes.get_inventory);
		
		return response;

	}
}
