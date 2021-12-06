import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class APITestSample {

    //Executado ANTES da classe (Não Obrigatório!)
    @BeforeClass
    public static void setup() {
        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "https://jsonplaceholder.typicode.com";
        }
        RestAssured.baseURI = baseHost;

        //Name the CSV File to Create
        String FileName = "TestGET";
        BuilderCSV builderCSV = new BuilderCSV();
        builderCSV.setFileName(FileName);
    }

    //Test Scripts
    @Test(priority = 0)
    void GET_SAMPLE_PASS() throws IOException {
        //Define Endpoint
        String ENDPOINT = "/posts/1/comments";

        Response response = given()
                .queryParam("CUSTOMER_ID","68195") //queryParam Não é Obrigatorio !
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1") //****
                .when()
                .get(ENDPOINT)
                .then()
                .log().all()
                .extract().response();

        //Write to CSV
        String TestId = "01";
        String Summary = "test_GET_SAMPLE_PASS";
        String Description = "ESTE TESTE É UM GET QUE VAI PASSAR COM STATUS CODE 200";
        String Action = "Test GET methot trough endpoint "+ENDPOINT;
        String ExpectedResult = "Status code: HTTP/1.1."+"200";
        String Data = "Validate if API ENDPOINT is getting the right status code";

        BuilderCSV builderCSV = new BuilderCSV();
        builderCSV.setTestId(TestId);
        builderCSV.setSummary(Summary);
        builderCSV.setDescription(Description);
        builderCSV.setAction(Action);
        builderCSV.setExpectedResult(ExpectedResult);
        builderCSV.setData(Data);

        BuilderCSV.main();
        //****

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("odio adipisci rerum aut animi"));

    }

    @Test(priority = 1)
    void GET_SAMPLE_FAIL() throws IOException {
        //Define Endpoint
        String ENDPOINT = "/posts/1/comments";

        Response response = given()
                .queryParam("CUSTOMER_ID","68195") //queryParam Não  é Obrigatorio !
                .queryParam("PASSWORD","1234!")
                .queryParam("Account_No","1") //****
                .when()
                .get(ENDPOINT)
                .then()
                .log().body()
                .extract().response();

        //Write to CSV
        String TestId = "02";
        String Summary = "test_GET_SAMPLE_FAIL";
        String Description = "02";
        String Action = "Test GET methot trough endpoint"+ENDPOINT;
        String ExpectedResult = "Status code: HTTP/1.1."+"200";
        String Data = "02";

        BuilderCSV builderCSV = new BuilderCSV();
        builderCSV.setTestId(TestId);
        builderCSV.setSummary(Summary);
        builderCSV.setDescription(Description);
        builderCSV.setAction(Action);
        builderCSV.setExpectedResult(ExpectedResult);
        builderCSV.setData(Data);

        BuilderCSV.main();
        //****

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.getBody().asString().contains("architecto"));
    }

    @Test(priority = 2)
    public void POST_SAMPLE_PASS() throws IOException, ParseException {
        //Define Endpoint
        String ENDPOINT = "/posts";

        //CALL DEFINED JSON
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/test/java/JSONS_REQUESTS/test_POST_SAMPLE_PASS.json"));

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(obj)
                .when()
                .post(ENDPOINT)
                .then()
                .log().body()
                .extract().response();

        Assert.assertEquals(response.getStatusCode(), 201);

    }

}
