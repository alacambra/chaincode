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
public class RangeExampleTest {

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
    public void deployRangeExample() {
        RangeExample RangeExampleDeploy = new RangeExample(TransactionType.deploy, "init");
        printJson(RangeExampleDeploy.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(RangeExampleDeploy.toJsonBuilder().build().toString()));
        printJson(response);
    }

    @Test
    public void putRangeExample() {
        RangeExample RangeExampleQuery = new RangeExample(TransactionType.invoke, "put", "b", "1000", "b1", "10w00w");
        printJson(RangeExampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(RangeExampleQuery.toJsonBuilder().build().toString()));
        printJson(response);
    }

    public void delRangeExample() {
        RangeExample RangeExampleQuery = new RangeExample(TransactionType.invoke, "del", "b", "1000");
        printJson(RangeExampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(RangeExampleQuery.toJsonBuilder().build().toString()));
        printJson(response);
    }

    @Test
    public void queryRangeExample() {
        RangeExample RangeExampleQuery = new RangeExample(TransactionType.query, "keys", "a", "z");
        printJson(RangeExampleQuery.toJsonBuilder());
        System.out.println(".......");
        Response response = webTarget.request().post(Entity.json(RangeExampleQuery.toJsonBuilder().build().toString()));
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
