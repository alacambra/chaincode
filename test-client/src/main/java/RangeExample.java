/**
 * Created by alacambra on 04/02/2017.
 */
public class RangeExample extends BaseMessage {

    String id = "RangeExample";

    public RangeExample(TransactionType txType, String... params) {
        super(txType);
        MessageParams messageParams = new MessageParams(id, params);
        setMessageParams(messageParams);
    }
}
