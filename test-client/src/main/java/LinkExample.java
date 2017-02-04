/**
 * Created by alacambra on 04/02/2017.
 */
public class LinkExample extends BaseMessage{

    String id = "link";

    public LinkExample(TransactionType txType, String... params) {
        super(txType);
        MessageParams messageParams = new MessageParams(id, params);
        setMessageParams(messageParams);
    }
}
