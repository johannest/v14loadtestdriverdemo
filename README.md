# LoadTestDriver test project

A project (based on Bookstore App Starter for Vaadin Flow) demonstrating the usage of the [LoadTestDriver add-on](https://github.com/johannest/loadtestdriver). The UI is built with Java only.

## Prerequisites

The project can be imported into the IDE of your choice, with Java 8 or 11 installed, as a Maven project.

## Project Structure
The project consists of the following three modules:

- parent project: common metadata and configuration
- bookstore-starter-flow-ui: main application module that includes views, and TestBench tests
- bookstore-starter-flow-backend: POJO classes and mock services being used in the ui

## Workflow

To compile the entire project, run "mvn install" in the parent project.

## LoadTestDriver demo

1. Make sure that AbstractViewTest.RUN_LOADTESTDRIVER = true
1. Start app server with ui project's war deployed
1. Download and add to the PATH [ChromeDriver](https://chromedriver.chromium.org/), if not yet done. Otherwise,
 you can specify a path to a driver via `System.setProperty("webdriver.chrome.driver", "path_to_driver\chromedriver.exe");`. 
1. Modify paths in AbstractViewTest.setupLoadTestDriver. For example to `.withPath("C:\dev\v14loadtestdriverdemo\v14loadtestdriverdemo-ui\src\test\scala")`
1. Run TestBench test SampleCrudViewIT
1. The test is converted to Gatling load test and saved into the specified path
1. If the Gatling test and its requests are stored into src/test/scala, you can execute test by Gatling Maven plugin: `mvn -Pscalability gatling:test`
1. Otherwise, navigate to the folder containing the test and resources and then execute the Gatling test from the command line e.g. `gatling -sf . -rsf .`

For more information on Gatling Maven Plugin: https://gatling.io/docs/3.0/extensions/maven_plugin/

