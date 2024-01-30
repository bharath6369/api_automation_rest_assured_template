package token;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.hamcrest.Matchers.notNullValue;
import static token.Constant.BASE_URL;
import static token.Constant.END_POINT_TOKEN_GENERATE;

public class TokenGenerate {

    private String userName;
    private String password;
    private String grantType;
    private String basicAuthorization;

    public static String createToken(String basicAuthorization, String userName,String password, String grantType){
        RestAssured.useRelaxedHTTPSValidation();
        String   accessToken =  given()
                .header("Authorization","Basic "+basicAuthorization)
                .contentType(ContentType.URLENC)
                .formParam("username",userName)
                .formParam("password",password)
                .formParam("grant_type",grantType)
                .when()
                .post(BASE_URL+END_POINT_TOKEN_GENERATE)
                .then()
                .log().all()
                .extract()
                .jsonPath()
                .getString("access_token");

        return accessToken;




        }



    }
