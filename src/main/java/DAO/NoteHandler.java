package DAO;

import DTO.NoteObject;
import java.util.*;
import java.text.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import org.bson.Document;
import org.bson.types.ObjectId;

public class NoteHandler {
    private static NoteHandler dataBase;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    BufferedReader br;
    MongoClient mongoClient = null;
    MongoDatabase db = null;
    MongoCollection<Document> myNotes = null;

    // Fetch the singleton database object
    public static NoteHandler getDataBase(){
        // Lazy load initialize
        if (dataBase == null){
            dataBase = new NoteHandler();
        }

        return dataBase;
    }

    // Initializes the user data from json file into an object array
    private NoteHandler(){
        try {
            // Initializes the Mongodb
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase db = mongoClient.getDatabase("MyDatabase");
            myNotes = db.getCollection("MyNotes");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Creates a new note and stores it into myNotes
    public NoteObject storeNote(String body){
        Date createdDate = new Date();
        Document doc = new Document("name", "Note")
                .append("date", createdDate)
                .append("data", body);
        myNotes.insertOne(doc);

        // Return a note object for response check
        ObjectId id = (ObjectId)doc.get( "_id" );
        NoteObject note = new NoteObject(doc.getString("data"),
                doc.getObjectId("_id").toString(),
                doc.getDate("date"));
        return note;
    }

    // Replaces an existing note with new data text
    public NoteObject updateNote(String _id, String body){
        myNotes.updateOne(eq("_id", new ObjectId(_id)),
                new Document("$set", new Document("data", body)));
        return getNote(_id);
    }

    public NoteObject getNote(String _id){
        Document search = myNotes.find(eq("_id", new ObjectId(_id) )).first();
        NoteObject foundNote = new NoteObject(search.getString("data"),
                search.getObjectId("_id").toString(),
                search.getDate("date"));
        return foundNote;
    }

    public NoteObject deleteNote(String _id){
        NoteObject foundNote = getNote(_id);
        myNotes.deleteOne(eq("_id", _id));
        return foundNote;
    }

    public ArrayList<NoteObject> listNotes(){
        MongoCursor<Document> cursor = myNotes.find().iterator();
        ArrayList<NoteObject> noteList = new ArrayList<NoteObject>();
        JsonParser jsonParser = new JsonParser();
        try {
            while (cursor.hasNext()) {
                Document current = cursor.next();
                //String noteJson = cursor.next().toJson();
                //NoteObject note = gson.fromJson(noteJson, NoteObject.class);
                NoteObject note = new NoteObject(
                        current.getString("data"),
                        current.getObjectId("_id").toString(),
                        current.getDate("date"));
                noteList.add(note);
            }
        } finally {
            cursor.close();
        }
        return noteList;
    }
}
