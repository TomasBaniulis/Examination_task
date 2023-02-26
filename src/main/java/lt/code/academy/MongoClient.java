package lt.code.academy;

public class MongoClient {

    private static MongoClient client;

    public MongoClient() {}

    public static MongoClient getClient (){
        if (client == null){
            client = new MongoClient();
        }
        return client;
    }
}
