package service;

import java.util.List;
import java.util.Map;

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
		// Pega o mapa de raças
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
	
}
