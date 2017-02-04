/**
 * Created by alacambra on 04/02/2017.
 */
public class MapExample extends BaseMessage{

    String id = "map";

    public MapExample(TransactionType txType, String... params) {
        super(txType);
        MessageParams messageParams = new MessageParams(id, params);
        setMessageParams(messageParams);
    }
}
