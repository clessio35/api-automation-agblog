package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    private static ThreadLocal<Scenario> scenarioThread = new ThreadLocal<>();

    @Before
    public void before(Scenario scenario) {
        scenarioThread.set(scenario); 
    }

    @After
    public void after(Scenario scenario) {
        scenarioThread.remove();
    }

    public static Scenario getScenario() {
        return scenarioThread.get();
    }

    public static String getScenarioName() {
        Scenario scenario = scenarioThread.get();
        return scenario != null ? scenario.getName() : "unknown_scenario";
    }
}