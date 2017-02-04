import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * Created by alacambra on 04/02/2017.
 */
public class SimpleSampleTest {

    protected Client client;
    protected WebTarget webTarget;
    String endpoint = "http://192.168.99.100:7050/";


    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
        webTarget = client.target(endpoint).path("chaincode");
    }

    @After
    public void tearDown() {
        client.close();
    }

    @Test
    public void deploySimpleSample() {
        SimpleSample simpleSampleDeploy = new SimpleSample(TransactionType.deploy, "deploy", "a", "100", "b", "200");
        printJson(simpleSampleDeploy.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(simpleSampleDeploy.toJsonBuilder().build().toString()));
        printJson(response);
    }

    @Test
    public void transferSimpleSample() {
        SimpleSample simpleSampleQuery = new SimpleSample(TransactionType.invoke, "transfer", "a", "b", "100");
        printJson(simpleSampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(simpleSampleQuery.toJsonBuilder().build().toString()));
        printJson(response);
    }

    @Test
    public void putSimpleSample() {
        for (int i = 0; i < 1000; i++) {
            SimpleSample simpleSampleQuery = new SimpleSample(TransactionType.invoke, "put", "b", "1000" + i);
            printJson(simpleSampleQuery.toJsonBuilder());
            System.out.println(".......");
            Response response = webTarget.request().post(Entity.json(simpleSampleQuery.toJsonBuilder().build().toString()));
            printJson(response);
        }
    }

    @Test
    public void querySimpleSample() {
        SimpleSample simpleSampleQuery = new SimpleSample(TransactionType.query, "query", "a", "b", "c");
        printJson(simpleSampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(simpleSampleQuery.toJsonBuilder().build().toString()));
        printJson(response);
    }

    void printJson(String json) {
        System.out.println(JsonFormatter.format(json));
    }

    void printJson(JsonObjectBuilder json) {
        printJson(json.build().toString());
    }

    void printJson(Response response) {
        printJson(response.readEntity(String.class));
    }

}
