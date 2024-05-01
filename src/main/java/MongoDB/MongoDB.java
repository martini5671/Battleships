package MongoDB;

import com.mongodb.client.*;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class MongoDB {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    MongoDB(String connection_string, String database_name, String collection_name)
    {
        this.mongoClient = MongoClients.create(connection_string);
        this.database = mongoClient.getDatabase(database_name);
        this.collection = database.getCollection(collection_name);
    }
    public void insertStats(String winner, String looser, int total_gameplay_time,
                             int n_of_rounds_before_end, int n_of_destroyed_segments_by_looser,
                             HashMap<String, Double> average_time_of_turn)
    {
        // Get the current date
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);


        Document document = new Document();

        //create document:
        document.append("Winner", winner);
        document.append("Looser", looser);
        document.append("Total gameplay time", total_gameplay_time);
        document.append("Number of rounds before end", n_of_rounds_before_end);
        document.append("Number of destroyed ship segments by looser:", n_of_destroyed_segments_by_looser);
        document.append("Average time of turn:", new Document()
                .append("Winner",average_time_of_turn.get("Winner"))
                .append("Looser", average_time_of_turn.get("Looser")));
        document.append("Date time of game end", formattedDate);

        collection.insertOne(document);
    }
    public void printAllDocuments()
    {
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }
    public void deleteCollection()
    {
        collection.drop();
    }

    public static void main(String[] args) {
        //getmongoExample();
        MongoDB mongoDB = new MongoDB("mongodb://localhost:27017/", "battleships_scores", "test" );
        Document document = new Document();

        //make insert
        mongoDB.insertStats("Player1", "Player2", 1200, 10, 5, new HashMap<String, Double>(){ { put("Winner", 5.0); put("Looser", 3.5); } });

        //make an insert
        mongoDB.printAllDocuments();
    }
}