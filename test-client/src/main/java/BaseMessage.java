import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 * Created by alacambra on 04/02/2017.
 */
public class BaseMessage {

    static int idCount = 0;
    String jsonrpc = "2.0";
    MessageParams messageParams;
    private TransactionType method;

    public BaseMessage(TransactionType method) {
        this.method = method;
    }

    public JsonObjectBuilder toJsonBuilder() {
        return Json.createObjectBuilder()
                .add("jsonrpc", jsonrpc)
                .add("method", method.name())
                .add("id", idCount++)
                .add("params", messageParams.toJsonBuilder());
    }

    public BaseMessage setMessageParams(MessageParams messageParams) {
        this.messageParams = messageParams;
        return this;
    }
}
