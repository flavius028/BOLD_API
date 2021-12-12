import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class APITestSampleGET {

    //SETUP API URL AND CSV FILE NAME
    @BeforeClass()
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

    //Test Scripts
    @Test(priority = 0)
    public void GET_SAMPLE_PASS() throws IOException {
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
        BuilderCSV builderCSV = new BuilderCSV();
        builderCSV.setTestId("01");
        builderCSV.setSummary("test_GET_SAMPLE_PASS");
        builderCSV.setDescription("ESTE TESTE É UM GET QUE VAI PASSAR COM STATUS CODE 200");
        builderCSV.setAction("Test GET methot trough endpoint: "+ENDPOINT);
        builderCSV.setExpectedResult("Status code: HTTP/1.1."+"200");
        builderCSV.setData("Validate if API ENDPOINT is getting the right status code");

        BuilderCSV.main();
        //****

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("odio adipisci rerum aut animi"));

    }

    @Test(priority = 1)
    public void GET_SAMPLE_FAIL() throws IOException {
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
        BuilderCSV builderCSV = new BuilderCSV();
        builderCSV.setTestId("02");
        builderCSV.setSummary("test_GET_SAMPLE_FAIL");
        builderCSV.setDescription("ESTE TESTE É UM GET QUE VAI FALHAR COM STATUS CODE 400");
        builderCSV.setAction("Test GET methot trough endpoint: "+ENDPOINT);
        builderCSV.setExpectedResult("Status code: HTTP/1.1."+"400");
        builderCSV.setData("Validate if API ENDPOINT is getting the right status code");

        BuilderCSV.main();
        //****

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(response.getBody().asString().contains("architecto"));
    }

}
