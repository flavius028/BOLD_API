import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class APITestSample {

    //Executado ANTES de cada TESTE (Não Obrigatório!)
    //@BeforeTest

    //Executado DEPOIS de cada TESTE (Não Obrigatório!)
    //@AfterTest

    //Tests Scripts
    @Test (priority = 0)
    void test_GET_SAMPLE_PASS(){
        Response response = given()
                .queryParam("CUSTOMER_ID","68195") //queryParam Não  é Obrigatorio !
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1") //****
                .when()
                .get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1")
                .then()
                .log().body()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().asString().contains("TRANSACTION_ID") /*Expected value*/, true /*Actual Value*/);

    }

    @Test(priority = 1)
    void test2_GET_SAMPLE_FAIL(){
        Response response = given()
                .queryParam("CUSTOMER_ID","68195") //queryParam Não  é Obrigatorio !
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1") //****
                .when()
                .get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1")
                .then()
                .log().body()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.getBody().asString().contains("TRANSACTION_ID") /*Expected value*/, true /*Actual Value*/);

        
    }
}
