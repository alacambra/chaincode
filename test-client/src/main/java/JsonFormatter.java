import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import javax.json.JsonValue;

/**
 * Created by alacambra on 04/02/2017.
 */
public class JsonFormatter {

    public static String format(JsonValue jsonValue) {
        try {
            return new JSONObject(jsonValue.toString()).toString(2);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static String format(String jsonValue) {
        try {
            return new JSONObject(jsonValue).toString(2);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}