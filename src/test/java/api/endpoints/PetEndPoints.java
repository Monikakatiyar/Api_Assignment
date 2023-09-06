package api.endpoints;

import static io.restassured.RestAssured.given;
import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndPoints {

	public static Response addPet(Pet payload){
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.add_pet);
		
		return response;

	}
	
	public static Response getPet(Long petId){
		Response response = given()
				.pathParam("petId", petId)
		.when()
			.get(Routes.get_pet);
		
		return response;
	}

	public static Response updatePet(Pet payload){
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
		.when()
			.put(Routes.update_pet);
		
		return response;

	}
	
	public static Response deletePet(Long petId){
		Response response = given()
        		.pathParam("petId", petId)
		.when()
			.delete(Routes.delete_pet);
		
		return response;

	}
}
