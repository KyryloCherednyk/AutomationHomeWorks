package HomeWorks;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HW8 {

    @Test
    public void testEmojisContainArticulatedLorry() throws Exception {

        HttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("https://api.github.com/emojis");

        HttpResponse response = httpClient.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode, 200, "Status code is not 200");

        String jsonResponse = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = new JSONObject(jsonResponse);

        boolean containsArticulatedLorry = jsonObject.has("articulated_lorry");
        Assert.assertTrue(containsArticulatedLorry, "JSON does not contain 'articulated_lorry' key");
    }

    @Test
    public void testEmoji() {

        RestAssured.baseURI = "https://api.github.com";

        Response response = get("/emojis");

        response.then().statusCode(200);

        response.then().body("articulated_lorry", notNullValue());
    }

}
