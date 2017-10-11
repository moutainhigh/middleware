package com.emotibot.middleware.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class MongoUtils
{
    
    private static final Logger logger = Logger.getLogger(MongoUtils.class);
    
    public static MongoClient createMongoClient(String mongoHost, int mongoPort)
    {
        return new MongoClient(mongoHost, mongoPort);
    }
    
    public static void closeMongoClient(MongoClient client)
    {
        client.close();
    }
    
    public static MongoDatabase getOrCreateMongoDatabase(MongoClient client, String databaseName)
    {
        if (client == null)
        {
            return null;
        }
        return client.getDatabase(databaseName);
    }
    
    public static void dropMongoDatabase(MongoClient client, String databaseName)
    {
        if (client == null)
        {
            return;
        }
        client.dropDatabase(databaseName);
    }
    
    public static MongoCollection<Document> getOrCreateCollection(MongoDatabase database, String collectionName)
    {
        if (database == null)
        {
            return null;
        }
        return database.getCollection(collectionName);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<JsonObject> getJsonObject(MongoCollection collection, String fieldName, Object value)
    {
        if (collection == null)
        {
            return null;
        }
        FindIterable<Document> findIterable = collection.find(Filters.eq(fieldName, value));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<JsonObject> ret = new ArrayList<JsonObject>();
        while(mongoCursor.hasNext())
        {  
            Document doc = mongoCursor.next();
            JsonObject obj = (JsonObject) JsonUtils.getObject(doc.toJson(), JsonObject.class);
            ret.add(obj);
        }
        return ret;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<JsonObject> getAllJsonObject(MongoCollection collection)
    {
        if (collection == null)
        {
            return null;
        }
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<JsonObject> ret = new ArrayList<JsonObject>();
        while(mongoCursor.hasNext())
        {  
            Document doc = mongoCursor.next();
            JsonObject obj = (JsonObject) JsonUtils.getObject(doc.toJson(), JsonObject.class);
            ret.add(obj);
        }
        return ret;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static boolean insertJsonObject(MongoCollection collection, JsonObject obj)
    {
        if (collection == null)
        {
            return false;
        }
        Document doc = Document.parse(obj.toString());
        collection.insertOne(doc);
        return true;
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean updateJsonObject(MongoCollection collection, String keyFieldName, Object keyValue, JsonObject obj)
    {
        if (collection == null)
        {
            return false;
        }
        Document doc = Document.parse(obj.toString());
        UpdateResult ret = collection.updateMany(Filters.eq(keyFieldName, keyValue), new Document("$set", doc));
        logger.debug("update count is: " + ret.getModifiedCount());
        return true;
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean deleteJsonObject(MongoCollection collection, String keyFieldName, Object keyValue)
    {
        if (collection == null)
        {
            return false;
        }
        DeleteResult ret = collection.deleteMany(Filters.eq(keyFieldName, keyValue));
        logger.debug("delete count is: " + ret.getDeletedCount());
        return true;
    }
}
