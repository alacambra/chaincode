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
public class LinkExampleTest {

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
    public void deployLinkExample() {
        LinkExample LinkExampleDeploy = new LinkExample(TransactionType.deploy, "init", "albert", "1");
        printJson(LinkExampleDeploy.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(LinkExampleDeploy.toJsonBuilder().build().toString()));
        printJson(response);
    }

    @Test
    public void putLinkExample() {
        LinkExample LinkExampleQuery = new LinkExample(TransactionType.invoke, "put", "a", "1000", "b1", "lalalala");
        printJson(LinkExampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(LinkExampleQuery.toJsonBuilder().build().toString()));
        printJson(response);
    }

    public void delLinkExample() {
        LinkExample LinkExampleQuery = new LinkExample(TransactionType.invoke, "del", "b", "1000");
        printJson(LinkExampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(LinkExampleQuery.toJsonBuilder().build().toString()));
        printJson(response);
    }

    @Test
    public void queryLinkExample() {
        LinkExample LinkExampleQuery = new LinkExample(TransactionType.query, "query", "b1");
        printJson(LinkExampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(LinkExampleQuery.toJsonBuilder().build().toString()));
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
