package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;

import hooks.Hooks;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ReportUtils;

public class DogService {

	private Response response;

	public void accessApi(String url) {
		System.out.println("Access API " + url);
		RestAssured.baseURI = url;
	}

	public void sendRequestGETMethod(String endpoint) {
		ReportUtils.logInfo("Send request for endpoint: " + endpoint);
		response = RestAssured.given().log().all()
			.contentType(ContentType.JSON).get(endpoint);
		ReportUtils.logInfo("Status code: " + response.getStatusCode());
	}
	
	
	public void validateCompleteDogsList() {
		ReportUtils.logInfo("Validate List breed dogs");
		response.then().statusCode(200).log().all().extract().jsonPath();

		Map<String, List<String>> racas = response.jsonPath().getMap("message");

		for (Map.Entry<String, List<String>> entry : racas.entrySet()) {
		    String breed = entry.getKey();
		    List<String> subBreeds = entry.getValue();

		    for (String subBreed : subBreeds) {
		        System.out.println("raça: " + breed + " , sub-raça:  " + subBreed);
		    }
		}

		String status = response.jsonPath().getString("status");
		if ("success".equals(status)) {
		    System.out.println("status succes: " + status);
		} else {
		    System.out.println("status diferente do esperado ->: " + status);
		}
		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}

	public String captureBreedDog(String listAllEndpoint) {
		ReportUtils.logInfo("Capture breed dogs");
        response = RestAssured.given().log().body()
                .when().contentType(ContentType.JSON)
                .get(listAllEndpoint);
        response.then().statusCode(200);
        Map<String, List<String>> message = response.jsonPath().getMap("message");
        List<String> allBreeds = new ArrayList<>(message.keySet());

        Random rand = new Random();
        return allBreeds.get(rand.nextInt(allBreeds.size()));
    }

    public void sendRequestGETMethodWithSpecificBreed(String baseEndpoint) {
    	ReportUtils.logInfo("send request get breed dogs");
        String breed = captureBreedDog("https://dog.ceo/api/breeds/list/all");
        String endpointCompleto = baseEndpoint + breed + "/images";
        
        response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .get(endpointCompleto);

        System.out.println("\n endpoint completo: " + endpointCompleto + "\n");
        System.out.println(" status code: " + response.getStatusCode() + "\n");
        ReportUtils.attachEvidence(response, Hooks.getScenarioName());
    }

    public void validateCompleteImagesDogs() {
    	ReportUtils.logInfo("validate image breed dogs");
        response.then().statusCode(200);

        String status = response.jsonPath().getString("status");
        Assert.assertEquals("success", status);

        List<String> images = response.jsonPath().getList("message");
        MatcherAssert.assertThat(images, Matchers.not(Matchers.empty()));

        for (String imageUrl : images) {
            MatcherAssert.assertThat(imageUrl, Matchers.startsWith("https://"));
        }
        ReportUtils.attachEvidence(response, Hooks.getScenarioName());
    }
	
}
