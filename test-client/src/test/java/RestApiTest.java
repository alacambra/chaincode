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
public class RestApiTest {

    protected Client client;
    protected WebTarget webTarget;
    String endpoint = "http://192.168.99.100:7050/";


    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
        webTarget = client.target(endpoint);
    }

    @Test
    public void getBlock() {
        Response response = webTarget.path("chain").path("blocks").path("35").request().get();
        printJson(response);
    }

    @Test
    public void getChain() {
        Response response = webTarget.path("chain").request().get();
        printJson(response);
    }

    @Test
    public void getNetworkPeers() {
        Response response = webTarget.path("network/peers").request().get();
        printJson(response);
    }

    @Test
    public void getTransactions() {
        Response response = webTarget.path("transactions").path("ab1586e8-f88b-457a-8ac4-271b3ad3caa1").request().get();
        printJson(response);
    }

    @After
    public void tearDown() {
        client.close();
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
