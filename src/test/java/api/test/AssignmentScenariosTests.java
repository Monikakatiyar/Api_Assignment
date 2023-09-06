package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import api.payload.Tag;
import api.payload.Category;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AssignmentScenariosTests {

	Faker faker;
	Pet petPayload;
	Category categoryPayload;
	Tag tagPayload;
	Tag newTagPayload;
	
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		petPayload = new Pet();
		categoryPayload = new Category();
		tagPayload = new Tag();

		ArrayList<String> photoUrls = new ArrayList<String>(1); 
		photoUrls.add("");

		ArrayList<Tag> tags = new ArrayList<Tag>(2); 
		tags.add(tagPayload);
		tags.add(newTagPayload);


		categoryPayload.setId(Long.valueOf(0));
		categoryPayload.setName("pajaro");

		tagPayload.setId(Long.valueOf(0));
		tagPayload.setName(faker.dog().breed());
		
		petPayload.setId(Long.valueOf(0));
		petPayload.setCategory(categoryPayload);
		petPayload.setName("pupo");
		petPayload.setPhotoUrls(photoUrls);
		petPayload.setTags(tags);
		petPayload.setStatus("available");
	}
	
	@Test(priority=1)
	public void testScenario1()
	{
		Response response = PetEndPoints.addPet(petPayload);
		response.then().log().all();
		JsonPath jsonPathEvaluator = response.jsonPath();
		Long petId = jsonPathEvaluator.get("id");
		petPayload.setId(petId);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void testScenario2()
	{
		// updating payload
		petPayload.setName("kurikuri");	
		categoryPayload.setName("Pomeranian");
		newTagPayload = new Tag();

		tagPayload.setId(Long.valueOf(1));
		tagPayload.setName("Super Cute");
		
		Response response = PetEndPoints.updatePet(petPayload);
		response.then().log().all();

		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		//verify data
		
		Response responseAfterUpdate = PetEndPoints.getPet(this.petPayload.getId());
		response.then().log().body();
		
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(), 200);
	}
	
}
