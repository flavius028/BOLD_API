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

public class APITestSamplePOST {

    //SETUP API URL AND CSV FILE NAME
    @BeforeClass
    public void setup() {
        //DEFINE BASE API URL
        String baseHost = "https://jsonplaceholder.typicode.com";

        if(baseHost==null || baseHost==""){
            System.out.println("\n***************************************");
            System.out.println("INFO STATUS: BaseHost URL MUST be declared !!");
            System.out.println("***************************************");
            System.exit(0);
        }else{
            RestAssured.baseURI = baseHost;
        }

        //Name the CSV File to Create
        String FileName = "Test_GET_POST";
        BuilderCSV builderCSV = new BuilderCSV();
        builderCSV.setFileName(FileName);
    }

    @Test(priority = 2)
    public void POST_SAMPLE_PASS() throws IOException, ParseException {
        //Define Endpoint
        String ENDPOINT = "/posts";

        //CALL PRE-DEFINED JSON
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

        //Write to CSV
        BuilderCSV builderCSV = new BuilderCSV();
        builderCSV.setTestId("03");
        builderCSV.setSummary("test_GET_SAMPLE_PASS");
        builderCSV.setDescription("ESTE TESTE É UM GET QUE VAI PASSAR COM STATUS CODE 201");
        builderCSV.setAction("Test GET methot trough endpoint: "+ENDPOINT);
        builderCSV.setExpectedResult("Status code: HTTP/1.1."+"201");
        builderCSV.setData("Validate if API ENDPOINT is getting the right status code");

        BuilderCSV.main();
        //****

        Assert.assertEquals(response.getStatusCode(), 201);
    }
}
