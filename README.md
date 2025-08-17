
# 🧪 BDD Cucumber Automation Framework – (Selenium + Java + JUnit )

Automates **login success and logout scenarios** using **Selenium WebDriver**, **Cucumber (BDD)**, and **JUnit**.  
This framework demonstrates a **clean and extensible design** for building behavior-driven automated tests.

----------

## 🚀 Key Highlights

| Feature         | Description                                                       |
|-----------------|-------------------------------------------------------------------|
| 🥒 BDD Approach | Gherkin feature files for human-readable scenarios                |
| 🧩 Modular Design | Step Definitions, Hooks, Runners, and Utilities separated         |
| 📊 Reporting     | HTML & JSON reports auto-generated after execution                |
| 🔄 Rerun Logic   | Failed scenarios captured and re-executed via `rerun.txt`         |
| ⚙️ Configurable  | Centralized property loader with utility class                    |
| 🚀 CI-Ready      | Works seamlessly with Jenkins or GitHub Actions                   |



----------

## 🧠 Design Patterns

-   **Behavior Driven Development (BDD)** → Gherkin syntax + Step Definitions mapping
    
-   **Hooks** → Global setup/teardown for driver/session management
    
-   **Utils** → Centralized helpers (ConfigReader, Driver actions)
    
-   **Runners** → Clean separation between main execution and failed reruns
    

----------

## 📁 Project Structure

```bash
src/
├── main/
│    └──java/
│       ├── config/
│       │    └── ConfigLoader.java
│       ├── driver/
│       │    └── DriverFactory.java
│       ├── utils/
│       │    ├── DriverUtils.java
│       │    ├── ReportManager.java
│       │
│       └── pageObjects/
│           └── <PageName>.java
│		
├── test/
│   ├── java/
│   │   ├── features/
│   │   │    └── E2E.feature
│   │   ├── stepDefinitions/
│   │   │    ├── Hooks.java
│   │   │    └── Steps.java
│   │   └── runners/
│   │        ├── TestRun.java
│   │        └── FailedTestRun.java
│   └── resources/
│        ├── config.properties
│        └── log4j2.xml
└── logs/
│    └── logfile.log
└── reports/
│    ├── cucumber-report.html
│    ├── cucumber-report.json
│    ├── cucumber-rerun-report.html
│    ├── cucumber-rerun-report.json
│    └── ExtentReport.html
├── pom.xml
└── run.bat


```

----------

## ✅ 1. Core Framework Source

### 📦 `Features/`

Defines scenarios in **Gherkin syntax**.

**Example:**

```gherkin
Feature: End-to-End User Login and Logout

  Background:
    Given User launches the browser
    And navigates to the application URL

  Scenario: User logs in and logs out successfully
    When User enters valid credentials
    And clicks on Submit
    Then User lands on the Login Success page
    When User clicks on Logout
    Then User is logged out successfully

```

----------

### 📦 `stepDefinitions/`

Contains the Java code mapping to feature steps.

-   Uses `Assertions` to validate UI behavior
    
-   Leverages Selenium WebDriver for interaction
    

----------

### 📦 `hooks/`

#### 🔹 `ApplicationHooks.java`

-   `@Before` → Launch browser, load config, initialize driver
    
-   `@After` → Quit browser, cleanup
    

----------

### 📦 `utils/`

- 📟 **ConfigLoader**  
    Reads configuration values (URL, credentials, browser type) from property files.  
    Provides a centralized way to fetch environment-specific data.
    
-   💿 **DriverFactory**  
    Responsible for initializing WebDriver instances.  
    Supports multiple browsers and ensures thread safety for parallel execution.
    
-   🔃 **DriverUtils**  
    Wraps reusable browser actions like clicking, typing, waits, scrolling.  
    Reduces code duplication inside step definitions.
    
-   📄 **ReportManager**  
    Handles reporting setup and integration.  
    Generates readable reports (HTML/JSON) for executed test runs.
    

----------

### 📦 `runners/`

#### 🔹 `TestRun.java`

-   Main runner that:
    
    -   Executes scenarios
        
    -   Applies tags (`@Smoke`, `@Regression`)
        
    -   Generates HTML/JSON reports
        
    -   Captures failed scenarios in `target/rerun.txt`
        

#### 🔹 `FailedTestRun.java`

-   Reads from `target/rerun.txt`
    
-   Reruns only failed scenarios
    
-   Skips execution if no failed tests are present
    

----------

## 🧪 2. Test Execution & Reporting

### ▶️ Run Tests

```bash
mvn clean test

```

### 📊 Reports

-   **HTML Report:** `reports/cucumber-report.html`
    
-   **JSON Report:** `reports/cucumber-report.json`
    

### 🔄 Rerun Failed Tests

```bash
mvn test -Dtest=runners.FailedTestRun

```
---

## 📚 3. Utilities

-   **ConfigReader** → Loads configs (URL, credentials, browser)
    
-   **Driver Utils (optional)** → Browser actions, waits (can be extended later)
    
-   **Hooks** → Ensure consistent setup/teardown across scenarios
    

----------

## 🎮 CI/CD Integration

-   Works with **GitHub + Jenkins**
    
-   Add build step:
    

```bash
mvn clean test

```

-   Post-build: archive `reports/*.html` for access
    

----------

## 🧠 Future Enhancements

-   Add `DriverFactory` for multiple browser support
    
-   Integrate Allure reports
    
-   Support parallel execution
    
-   Extend utilities for advanced Selenium actions
    

----------

## 🙌 Author

**Naveen Kumar**  
SDET II | QA Automation Engineer  
[GitHub](https://github.com/naveenkumarqa) | [LinkedIn](https://linkedin.com/in/naveenkumarqa) | [Portfolio](https://bento.me/naveenkumarqa)

----------
