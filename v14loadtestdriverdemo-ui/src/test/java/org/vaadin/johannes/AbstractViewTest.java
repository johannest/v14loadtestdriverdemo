package org.vaadin.johannes;

import com.vaadin.testbench.TestBenchTestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.flow.theme.AbstractTheme;
import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.parallel.ParallelTest;
import org.vaadin.johannest.loadtestdriver.LoadTestDriver;
import org.vaadin.johannest.loadtestdriver.LoadTestDriverBuilder;

/**
 * Base class for ITs
 * <p>
 * The tests use Chrome driver (see pom.xml for integration-tests profile) to
 * run integration tests on a headless Chrome. If a property {@code test.use
 * .hub} is set to true, {@code AbstractViewTest} will assume that the
 * TestBench test is running in a CI environment. In order to keep the this
 * class light, it makes certain assumptions about the CI environment (such
 * as available environment variables). It is not advisable to use this class
 * as a base class for you own TestBench tests.
 * <p>
 * To learn more about TestBench, visit
 * <a href="https://vaadin.com/docs/v10/testbench/testbench-overview.html">Vaadin TestBench</a>.
 */
public abstract class AbstractViewTest extends TestBenchTestCase {
    private static final int SERVER_PORT = 9999;
    private static final boolean RUN_LOADTESTDRIVER = true;

    private final String route;

    @Rule
    public ScreenshotOnFailureRule rule = new ScreenshotOnFailureRule(this,
            false);

    public AbstractViewTest() {
        this("");
    }

    protected AbstractViewTest(String route) {
        this.route = route;
    }

    @Before
    public void setup() throws Exception {
        if (RUN_LOADTESTDRIVER) {
            setupLoadTestDriver();
        } else {
            setDriver(TestBench.createDriver(new ChromeDriver()));
        }
        getDriver().get(getURL(route));
    }

    public void setupLoadTestDriver() {
//        WebDriver driver = new LoadTestDriverBuilder().
//                withIpAddress(LoadTestDriver.getLocalIpAddress()).
//                withTestName("CrudAddProduct").
//                withNumberOfConcurrentUsers(1).
//                withRampUpTimeInSeconds(1).
//                withPath("C:\\dev\\idea\\v14loadtestdriverdemo\\v14loadtestdriverdemo-ui\\src\\test\\scala").
//                withResourcesPath("C:\\dev\\idea\\v14loadtestdriverdemo\\v14loadtestdriverdemo-ui\\src\\test\\scala").
//                withStaticResourcesIngnoring().
//                withTestRefactoring().
//                withHeadlessEnabled(false).
//                build();
        WebDriver driver = new ChromeDriver();
        setDriver(driver);
    }

    /**
     * Asserts that the given {@code element} is rendered using a theme
     * identified by {@code themeClass}. If the theme is not found, JUnit
     * assert will fail the test case.
     *
     * @param element       web element to check for the theme
     * @param themeClass    theme class (such as {@code Lumo.class}
     */
    protected void assertThemePresentOnElement(
            WebElement element, Class<? extends AbstractTheme> themeClass) {
        String themeName = themeClass.getSimpleName().toLowerCase();
        Boolean hasStyle = (Boolean) executeScript("" +
                "var styles = Array.from(arguments[0]._template.content" +
                ".querySelectorAll('style'))" +
                ".filter(style => style.textContent.indexOf('" +
                themeName + "') > -1);" +
                "return styles.length > 0;", element);

        Assert.assertTrue("Element '" + element.getTagName() + "' should have" +
                        " had theme '" + themeClass.getSimpleName() + "'.",
                hasStyle);
    }

    /**
     * Returns deployment host name concatenated with route.
     *
     * @return URL to route
     */
    private static String getURL(String route) {
        return LoadTestDriver.getLocalIpAddressWithPortAndContextPath(9999, route);
    }

    @After
    public void tearDown() {
        getDriver().close();
    }
}
