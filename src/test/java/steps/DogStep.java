package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import service.DogService;
import io.cucumber.java.en.Then;

public class DogStep {
	
	DogService agi = new DogService();

    // ---------- GIVEN ----------
    @Given("que acesso a API {string}")
    public void que_acesso_a_api(String url) {
       agi.accessApi(url);
    }

    // ---------- WHEN ----------
    @When("realizo uma request GET para {string}")
    public void realizo_uma_request_get_para(String endpoint) {
        agi.sendRequestGETMethod(endpoint);
    }

    // ---------- THEN ----------
    @Then("valido a listagem de raças")
    public void valido_a_listagem_de_racas() {
        // implementar na Page
    }

    @Then("valido as imagens da raça")
    public void valido_as_imagens_da_raca() {
        // implementar na Page
    }

    @Then("valido a imagem aleatória retornada")
    public void valido_a_imagem_aleatoria_retornada() {
        // implementar na Page
    }

    @Then("valido o retorno de erro para raça inválida")
    public void valido_o_retorno_de_erro_para_raca_invalida() {
        // implementar na Page
    }
}