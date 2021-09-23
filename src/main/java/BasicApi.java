import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class BasicApi {
    public static void main(String [] args){
        String APITOKEN = "ssXEelWebuh8ihcEBit4ILhV7ERCuPy5dNAHjNjYaazOsoWJrGeMHz8T54WWlI/enB4EUAvJ3tZ0Yw61tdxU+2Adc+Q6iQviHxJ1QhJ0y1bppeFfml2EVSKeE/nv7rkRV3H95y2uaT/fiP6hFo3V6A==";
        RestAssured.baseURI ="https://apihub.document360.io";

        //
        String jsondata = given().header("api_token",APITOKEN).when().get("/v1/ProjectVersions")
        .then().log().all().assertThat().statusCode(200).body("success",equalTo(true)).extract().response().asString();

        //System.out.println(jsondata);
        JsonPath js = new JsonPath(jsondata);
        String version1Id = js.getString("data[0].id");
        int versionCount = js.getInt("data.size()");
        //System.out.println(version1Id);
        int langCount;
        System.out.println(versionCount);
        for (int i =0; i<=versionCount;i++){
             langCount = js.getInt("data["+i+"].language_versions.size()");
             System.out.println(langCount);
            System.out.println(js.getString("data["+i+"].version_code_name")+ "has these languages ");
            for (int j=0; j<=langCount;j++){

                System.out.println(js.getString("data["+i+"].language_versions["+j+"].name"));
            }
        }
        System.out.println("-------------------------------------");

/*
        //add category
given().header("api_token",APITOKEN).header("Content-Type","application/json").body("{\n" +
        "  \"name\": \"category from API\",\n" +
        "  \"project_version_id\": \"6f0ec09d-c1d5-471e-9b46-472b99c6167c\",\n" +
        "  \"order\": 1,\n" +
        "  \"parent_category_id\": \"59a58dd4-5b03-4141-a5fd-7aedfb461e53\",\n" +
        "  \"content\": \"testing from postman\",\n" +
        "  \"category_type\": 1,\n" +
        "  \"user_id\": \"1fc2be7f-95d3-4862-b5b1-a180f7524199\"\n" +
        "}").when().post("/v1/Categories").then().log().all().assertThat().statusCode(200);


//add article
        given().header("api_token",APITOKEN).header("Content-Type","application/json").body("{\n" +
                "  \"title\": \"article creation from postman\",\n" +
                "  \"content\": \"sample\",\n" +
                "  \"category_id\": \"a760e1eb-ef2b-4387-8a95-b4f2e79f6e14\",\n" +
                "  \"project_version_id\": \"6f0ec09d-c1d5-471e-9b46-472b99c6167c\",\n" +
                "  \"order\": 0,\n" +
                "  \"user_id\": \"1fc2be7f-95d3-4862-b5b1-a180f7524199\"\n" +
                "}").when().post("/v1/Articles").then().log().all().assertThat().statusCode(200);
    */

    }

}
