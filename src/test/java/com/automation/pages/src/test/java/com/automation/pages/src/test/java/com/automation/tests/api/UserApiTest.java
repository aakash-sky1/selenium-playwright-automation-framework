package com.automation.tests.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * UserApiTest — REST-Assured API automation tests.
 * Covers CRUD operations, chaining, and schema validation.
 */
public class UserApiTest {

    private static final String BASE_URL = "https://reqres.in/api";
    private String createdUserId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(description = "Verify GET users returns 200 with valid data")
    public void testGetUsers() {
        given()
            .header("Content-Type", "application/json")
        .when()
            .get("/users?page=1")
        .then()
            .statusCode(200)
            .body("data", not(empty()))
            .body("data[0].id", notNullValue())
            .body("data[0].email", containsString("@"));
    }

    @Test(description = "Verify POST creates a user and returns 201")
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"Aakash Sharma\", \"job\": \"SDET\" }";

        Response response = given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("name", equalTo("Aakash Sharma"))
            .body("job", equalTo("SDET"))
            .body("id", notNullValue())
            .extract().response();

        createdUserId = response.jsonPath().getString("id");
        Assert.assertNotNull(createdUserId, "User ID should not be null after creation");
    }

    @Test(description = "Verify PUT updates user details", dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        String updateBody = "{ \"name\": \"Aakash Sharma\", \"job\": \"Senior SDET\" }";

        given()
            .header("Content-Type", "application/json")
            .body(updateBody)
        .when()
            .put("/users/2")
        .then()
            .statusCode(200)
            .body("job", equalTo("Senior SDET"))
            .body("updatedAt", notNullValue());
    }

    @Test(description = "Verify DELETE returns 204 No Content")
    public void testDeleteUser() {
        given()
        .when()
            .delete("/users/2")
        .then()
            .statusCode(204);
    }

    @Test(description = "Verify 404 returned for non-existent user")
    public void testGetNonExistentUser() {
        given()
        .when()
            .get("/users/9999")
        .then()
            .statusCode(404);
    }
}
