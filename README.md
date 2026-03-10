[Java](https://img.shields.io/badge/Java-11+-orange?style=flat-square&logo=java)
![Selenium](https://img.shields.io/badge/Selenium-4.x-green?style=flat-square&logo=selenium)
![Playwright](https://img.shields.io/badge/Playwright-1.x-blue?style=flat-square)
![TestNG](https://img.shields.io/badge/TestNG-7.x-red?style=flat-square)
![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-yellow?style=flat-square&logo=jenkins)
![Maven](https://img.shields.io/badge/Maven-Build-purple?style=flat-square&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-lightgrey?style=flat-square)

A **production-grade hybrid automation framework** integrating **Selenium WebDriver** and **Playwright** for UI testing, **REST-Assured** for API automation, and **Cucumber BDD** for behaviour-driven test scenarios — with full **Jenkins CI/CD** integration.

---

## 📁 Project Structure

```
selenium-playwright-automation-framework/
├── src/
│   └── test/
│       ├── java/com/automation/
│       │   ├── config/          # Config reader, environment setup
│       │   ├── pages/           # Page Object Model classes
│       │   ├── tests/
│       │   │   ├── ui/          # Selenium & Playwright UI tests
│       │   │   └── api/         # REST-Assured API tests
│       │   └── utils/           # Driver factory, helpers, waits
│       └── resources/
│           └── features/        # Cucumber .feature files (BDD)
├── reports/                     # Allure / Extent report output
├── .github/
│   └── workflows/
│       └── ci.yml               # GitHub Actions CI pipeline
├── testng.xml                   # TestNG suite configuration
├── pom.xml                      # Maven dependencies
└── README.md
```

---

## ✨ Key Features

| Feature | Details |
|---|---|
| **Dual Engine** | Selenium 4 + Playwright running side by side |
| **BDD Support** | Cucumber + Gherkin for readable test scenarios |
| **API Automation** | REST-Assured with CRUD, chaining, schema validation |
| **Page Object Model** | Clean separation of page logic and test logic |
| **Parallel Execution** | TestNG XML-driven parallel browser execution |
| **Cross-Browser** | Chrome, Firefox, Edge — configurable via properties |
| **CI/CD Ready** | Jenkins pipeline + GitHub Actions workflow included |
| **Reporting** | Allure Reports with screenshots on failure |
| **Data-Driven** | Excel / JSON test data support via Apache POI |

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 11+ |
| UI Automation | Selenium WebDriver 4, Playwright |
| API Automation | REST-Assured, Postman |
| BDD | Cucumber, Gherkin |
| Test Runner | TestNG |
| Build Tool | Maven |
| CI/CD | Jenkins, GitHub Actions |
| Reporting | Allure Reports, Extent Reports |
| Mobile (extension) | Appium (Android & iOS) |

---

## 🚀 Quick Start

### Prerequisites
- Java 11+
- Maven 3.6+
- Chrome / Firefox installed

### Clone & Run

```bash
# Clone the repository
git clone https://github.com/aakash-sky1/selenium-playwright-automation-framework.git
cd selenium-playwright-automation-framework

# Run all tests
mvn clean test

# Run specific suite
mvn clean test -DsuiteFile=testng.xml

# Run with specific browser
mvn clean test -Dbrowser=firefox

# Generate Allure report
mvn allure:report
```

---

## 🥒 Sample BDD Feature File

```gherkin
Feature: User Login Functionality

  Background:
    Given the user is on the login page

  @smoke @regression
  Scenario: Successful login with valid credentials
    When the user enters valid username and password
    And clicks the login button
    Then the user should be redirected to the dashboard
    And the welcome message should be displayed

  @regression
  Scenario: Login fails with invalid credentials
    When the user enters invalid username "wronguser" and password "wrong123"
    And clicks the login button
    Then an error message "Invalid credentials" should be displayed
```

---

## 🔗 API Automation Sample

```java
@Test
public void verifyCreateUserAPI() {
    given()
        .baseUri("https://api.example.com")
        .header("Content-Type", "application/json")
        .body("{ \"name\": \"Aakash\", \"role\": \"SDET\" }")
    .when()
        .post("/users")
    .then()
        .statusCode(201)
        .body("name", equalTo("Aakash"))
        .body("role", equalTo("SDET"));
}
```

---

## 📊 Test Results

| Suite | Total | Passed | Failed | Skipped |
|---|---|---|---|---|
| Smoke | 25 | 25 | 0 | 0 |
| Regression UI | 80+ | 78 | 2 | 0 |
| API Suite | 40+ | 40 | 0 | 0 |
| **Total** | **145+** | **143** | **2** | **0** |

---

## ⚙️ Jenkins CI/CD Pipeline

```groovy
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps { git 'https://github.com/aakash-sky1/selenium-playwright-automation-framework' }
        }
        stage('Build & Test') {
            steps { sh 'mvn clean test' }
        }
        stage('Allure Report') {
            steps { allure includeProperties: false, results: [[path: 'target/allure-results']] }
        }
    }
    post {
        always { junit 'target/surefire-reports/*.xml' }
    }
}
```

---

## 👨‍💻 Author

**Aakash Sharma** — SDET | QA Automation Engineer
- 📧 aakashsharmamrt2002@gmail.com
- 💼 [LinkedIn](https://www.linkedin.com/in/aakash-sharma-3908a5223/)
- 🐙 [GitHub](https://github.com/aakash-sky1)

---

## 📄 License

This project is licensed under the MIT License.
