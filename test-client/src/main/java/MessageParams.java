import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.stream.Stream;

/**
 * Created by alacambra on 04/02/2017.
 */
public class MessageParams {

    final int type = 4;
    String chaincodeId;
    String[] args;

    public MessageParams(String chaincodeId, String[] args) {
        this.chaincodeId = chaincodeId;
        this.args = args;
    }

    public JsonObjectBuilder toJsonBuilder() {
        return Json.createObjectBuilder()
                .add("type", type)
                .add("chaincodeID", Json.createObjectBuilder().add("name", chaincodeId))
                .add("ctorMsg", Json.createObjectBuilder()
                        .add("args", getArgsIntoJsonArray())
                );
    }

    private JsonArrayBuilder getArgsIntoJsonArray() {
        return Stream.of(args).collect(Json::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::add);
    }
}