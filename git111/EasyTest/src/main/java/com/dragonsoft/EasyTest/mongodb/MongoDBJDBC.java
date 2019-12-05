package com.dragonsoft.EasyTest.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoDBJDBC{
    public MongoCollection<Document> getCollection(String collect){
        MongoDatabase mongoDatabase =new MongoDBUtil().getConnect();
        //创建test集合   创建过了
//            mongoDatabase.createCollection("test");
        MongoCollection<Document> collection=mongoDatabase.getCollection(collect);
        return collection;
    }
    public void insert(){
        try{
            MongoCollection<Document> collection=getCollection("test");
            List<Document> documents = new ArrayList<Document>();
            for(int i=0;i<10;i++){
                Document document = new Document("title", "MongoDB"+i).
                        append("description", "database").
                        append("likes", 100+i).
                        append("by", "Fly");
                documents.add(document);
            }
            collection.insertMany(documents);
            System.out.println("文档插入成功");
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    public void query(){
        try{
            MongoCollection<Document> collection=getCollection("test");
            //检索所有文档
            /**
             * 1. 获取迭代器FindIterable<Document>
             * 2. 获取游标MongoCursor<Document>
             * 3. 通过游标遍历检索出的文档集合
             * */
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                System.out.println("数据："+mongoCursor.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void delete(){
        MongoCollection<Document> collection=getCollection("test");
        //删除符合条件的第一个文档
        collection.deleteOne(Filters.eq("likes", 200));
        //删除所有符合条件的文档
        collection.deleteMany (Filters.eq("likes", 200));
        query();
    }
    public void update(){
        MongoCollection<Document> collection=getCollection("test");
        //将likes为100的对象中的数据修改为200
        collection.updateMany(Filters.eq("likes", 100), new Document("$set",new Document("likes",200)));
        query();
    }
    public static void main( String args[] ){
        new MongoDBJDBC().query();
    }
}
