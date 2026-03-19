package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ReportUtils;

public class Hooks {

	 private static ThreadLocal<String> scenarioName = new ThreadLocal<>();

	    @Before
	    public void before(Scenario scenario) {
	        String name = scenario.getName();
	        scenarioName.set(name);
	        System.out.println(">>>>> Hook executado - cenário: " + name);

	        ReportUtils.createTest(name);
	    }

	    @After
	    public void after(Scenario scenario) {
	        scenarioName.remove();
	    }

	    public static String getScenarioName() {
	        return scenarioName.get();
	    }
}
