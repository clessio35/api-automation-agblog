package service;

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
	
	public void validateCompleteProductsList() {
		ReportUtils.logInfo("Validate List products");
		response.then().statusCode(200).log().all().extract().jsonPath();

		ReportUtils.attachEvidence(response, Hooks.getScenarioName());
	}
	
}
