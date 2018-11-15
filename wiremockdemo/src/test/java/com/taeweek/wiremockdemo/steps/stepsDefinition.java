package com.taeweek.wiremockdemo.steps;

import com.github.tomakehurst.wiremock.WireMockServer;
import static org.junit.Assert.*;
import org.json.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class stepsDefinition {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    String alertsHost = "http://localhost:3000";
    String eventsPath = "/events";
    String tokensPath = "/tokens";
    String user = "0123456789";
    String password = "qwe123";
    String token;
    WireMockServer wireMockServer;


    @Given("that the alert server is on")
    public void that_the_alert_server_is_on() throws IOException {
        this.wireMockServer = new WireMockServer(8787);
        this.wireMockServer.start();
        this.wireMockServer.stubFor(delete(urlEqualTo("/aalert"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"status\": \"success\"}")));
    }

    @Given("a system is authenticated")
    public void a_system_is_authenticated() throws IOException, JSONException {
        Response response = authenticate(this.alertsHost, this.tokensPath, this.user, this.password);
        JSONObject obj = new JSONObject(response.body().string());
        this.token = obj.getString("id");
    }

    @When("a system sends an event")
    public void a_system_sends_an_event() throws IOException {
        String json = "{\"event\": \"Tranferencia realizada por $250.000\"}";
        Response response = postEvent(this.alertsHost, this.eventsPath, json);
        assertEquals("Error in sending the event, response code was: " + response.code(), 200, response.code());
    }

    @Then("alert server should receive a post request")
    public void alert_server_should_receive_a_post_request() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        wireMockServer.verify(1, postRequestedFor(urlEqualTo("/alert")));
    }

    private Response authenticate(String host, String path, String user, String pass) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, "{\"phone\": \"" + user + "\", \"password\": \"" + pass + "\"}");
        Request request = new Request.Builder()
                .url(String.format("%s%s", host, path))
                .post(body)
                .build();

        System.out.println(body.toString());
        return client.newCall(request).execute();
    }

    private Response postEvent(String host, String path, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(String.format("%s%s", host, path))
                .header("token", this.token)
                .post(body)
                .build();
        return client.newCall(request).execute();
    }

}
