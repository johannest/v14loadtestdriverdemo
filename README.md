[![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/vaadin-flow/Lobby#?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

# Bookstore App Starter for Vaadin Flow

A project example for a Vaadin application that only requires a Servlet 3.1 container to run (no other JEE dependencies). The UI is built with Java only.

The easiest way of using it is via [https://vaadin.com/start](https://vaadin.com/start) - you can choose the package naming you want.

## Prerequisites

The project can be imported into the IDE of your choice, with Java 8 or 11 installed, as a Maven project.

## Project Structure

The project consists of the following three modules:

- parent project: common metadata and configuration
- bookstore-starter-flow-ui: main application module that includes views
- bookstore-starter-flow-backend: POJO classes and mock services being used in the ui

## Workflow

To compile the entire project, run "mvn install" in the parent project.

Other basic workflow steps:

- getting started
- compiling the whole project
  - run `mvn install` in parent project
- developing the application
  - edit code in the ui module
  - run `mvn jetty:run` in ui module
  - open http://localhost:8080/
- creating a production mode war
  - run `mvn package -Pproduction` in the ui module or in the parent module
- running in production mode
  - run `mvn jetty:run -Pproduction` in ui module
  - open http://localhost:8080/

## LoadTestDriver demo

1. Make sure that AbstractViewTest.RUN_LOADTESTDRIVER = true
1. Start app server with ui project's war deployed
1. Download and add to the PATH [ChromeDriver](https://chromedriver.chromium.org/), if not yet done. Otherwise,
 you can specify a path to a driver via `System.setProperty("webdriver.chrome.driver", "path_to_driver\chromedriver.exe");`. 
1. Modify paths in AbstractViewTest.setupLoadTestDriver. For example to `.withPath("C:\dev\v14loadtestdriverdemo\v14loadtestdriverdemo-ui\src\test\scala")`
1. Run TestBench test SampleCrudViewIT
1. The test is converted to Gatling load test and saved into the specified path
1. Execute the Gatling test from command line e.g. `gatling -sf . -rsf .`