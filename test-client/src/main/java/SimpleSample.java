/**
 * Created by alacambra on 04/02/2017.
 */
public class SimpleSample extends BaseMessage{

    String id = "SimpleSample";

    public SimpleSample(TransactionType txType, String... params) {
        super(txType);
        MessageParams messageParams = new MessageParams(id, params);
        setMessageParams(messageParams);
    }
}
