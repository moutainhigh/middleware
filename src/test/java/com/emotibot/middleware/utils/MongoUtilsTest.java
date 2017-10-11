package com.emotibot.middleware.utils;


import java.util.List;

import org.bson.Document;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoUtilsTest
{
    
    public static final String MONGODB_HOST = "192.168.56.11";
    public static final int MONGODB_PORT = 27017;
    public static final String TEST_DB = "testdb";
    public static final String TEST_COLLECTION = "test";
    
    @Test
    public void test() throws InterruptedException
    {
        //获取连接
        MongoClient mongoClient = MongoUtils.createMongoClient(MONGODB_HOST, MONGODB_PORT);

        //新建db
        MongoDatabase mongoDatabase = MongoUtils.getOrCreateMongoDatabase(mongoClient, TEST_DB);
        
        //创建集合
        MongoCollection<Document> collection = MongoUtils.getOrCreateCollection(mongoDatabase, TEST_COLLECTION);
        
        //创建文档
        JsonObject obj = new JsonObject();
        obj.addProperty("name", "yanbinwang");
        obj.addProperty("age", 28);
        MongoUtils.insertJsonObject(collection, obj);
        MongoUtils.insertJsonObject(collection, obj);
  
        //搜索全部文档
        List<JsonObject> objList = MongoUtils.getAllJsonObject(collection);
        System.out.println(objList);
        
        //搜索特定文档
        objList = MongoUtils.getJsonObject(collection, "name", "yanbinwang");
        System.out.println(objList);
        
        //更新文档
        obj = new JsonObject();
        obj.addProperty("age", 30);
        obj.addProperty("sex", "man");
        MongoUtils.updateJsonObject(collection, "name", "yanbinwang", obj);
        objList = MongoUtils.getJsonObject(collection, "name", "yanbinwang");
        System.out.println(objList);
        
        //删除文档
        MongoUtils.deleteJsonObject(collection, "name", "yanbinwang");
        objList = MongoUtils.getJsonObject(collection, "name", "yanbinwang");
        System.out.println(objList);
        
        //删除数据库
        MongoUtils.dropMongoDatabase(mongoClient, TEST_DB);
        
        //断开连接
        MongoUtils.closeMongoClient(mongoClient);
    }
    
}
