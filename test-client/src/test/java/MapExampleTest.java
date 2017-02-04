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
public class MapExampleTest {

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
    public void deployMapExample() {
        MapExample MapExampleDeploy = new MapExample(TransactionType.deploy, "init");
        printJson(MapExampleDeploy.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(MapExampleDeploy.toJsonBuilder().build().toString()));
        printJson(response);
    }

    @Test
    public void putMapExample() {
        MapExample MapExampleQuery = new MapExample(TransactionType.invoke, "put", "a", "1000", "b1", "hola");
        printJson(MapExampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(MapExampleQuery.toJsonBuilder().build().toString()));
        printJson(response);
    }

    public void delMapExample() {
        MapExample MapExampleQuery = new MapExample(TransactionType.invoke, "del", "b", "1000");
        printJson(MapExampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(MapExampleQuery.toJsonBuilder().build().toString()));
        printJson(response);
    }

    @Test
    public void queryMapExample() {
        MapExample MapExampleQuery = new MapExample(TransactionType.query, "query", "b1");
        printJson(MapExampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(MapExampleQuery.toJsonBuilder().build().toString()));
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
