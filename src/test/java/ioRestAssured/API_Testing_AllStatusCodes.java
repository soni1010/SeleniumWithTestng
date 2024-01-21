package ioRestAssured;

import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class API_Testing_AllStatusCodes {



    public static void main(String[] args) {

        String URI="https://dummy.restapiexample.com/api/v1";
                //"https://cdnjs.cloudflare.com/ajax/libs/prism/1.21.0/themes/prism-tomorrow.min.css";
                //"https://jsonplaceholder.typicode.com"
        // 1XX - response codes 100, 101, 102, 103, 104, 199
        RestAssured.baseURI = URI; // Example API base URL

        // Example: Test for 100 Continue
        Response response100 = given().when().get("/employees");
        //Assert.assertEquals(response100.getStatusCode(),100);
        System.out.println("Response Code 100 Continue: "+response100.getStatusCode()+"\n"+ response100.asString());

        // Example: Test for 101 Switching Protocols
        Response response101 = given().when().get("/employees");
       // Assert.assertEquals(response101.getStatusCode(),101);
        System.out.println("Response Code 101 Switching Protocols: "+response101.getStatusCode()+"\n"+response101.asString());

        // Example: Test for 102 Processing
        Response response102 = given().when().get("/employees");
       // Assert.assertEquals(response102.getStatusCode(),102);
        System.out.println("Response Code 102 Processing: "+response102.getStatusCode()+"\n"+response102.asString());



    }

}
