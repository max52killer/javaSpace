package com.dragonsoft.EasyTest.mongodb.base;

import com.alibaba.fastjson.JSON;
import com.dragonsoft.EasyTest.mongodb.anno.Collect;
import com.dragonsoft.EasyTest.mongodb.anno.Column;
import com.dragonsoft.EasyTest.mongodb.anno.GeneratedValue;
import com.dragonsoft.EasyTest.mongodb.anno.Id;
import com.dragonsoft.EasyTest.mongodb.enums.AutoGenerateType;
import com.dragonsoft.EasyTest.mongodb.enums.StatusCodeEnum;
import com.dragonsoft.EasyTest.util.StringUtils;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 传入指定的实体数据库对象 根据对象上的注解解析对象执行相应的查询
 * @param <T>
 */
public abstract class BaseDao<T>  extends DaoSupport implements IBaseDao<T>{
    @Autowired
    private MongoDbTemplate mongoDbTemplate;

    protected Class<T> clazz;
    @SuppressWarnings("uncheckd")
    public BaseDao() {
        @SuppressWarnings("rawtypes")
        Class clazz = getClass();
        //初始化获取当前泛型对象的class
        while (clazz != Object.class) {
            Type t = clazz.getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] args = ((ParameterizedType) t).getActualTypeArguments();
                if (args[0] instanceof Class) {
                    this.clazz = (Class<T>) args[0];
                    break;
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * 获取指定的文档
     * @param collect
     * @return
     */
    public MongoCollection<Document> getCollection(String collect){
        MongoDatabase mongoDatabase =mongoDbTemplate.mongoDatabase;

        MongoCollection<Document> collection=mongoDatabase.getCollection(collect);
        return collection;
    }
    @Override
    public T get(String var1) {
        //1、获取class
        Class c=this.clazz;
        //2、获取class上面的文档注解的实体名字
        boolean hasTable=c.isAnnotationPresent(Collect.class);
        if(!hasTable){
            return null;
        }
        Collect collect= (Collect) c.getAnnotation(Collect.class);
        String collectName= collect.name();
        MongoCollection<Document> collection=getCollection(collectName);
        FindIterable<Document> findIterable = collection.find(new Document("_id",new Document("$eq",var1)));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<T> lists=new ArrayList<>();
        while(mongoCursor.hasNext()){
            Document document=mongoCursor.next();
            T t=JSON.parseObject(document.toJson(),clazz);
            lists.add(t);
        }
        return lists.get(0);
    }

    @Override
    public T load(String var1) {
        return null;
    }

    @Override
    public T update(T var1) {
        //1、获取class
        Class c=var1.getClass();
        //2、获取class上面的文档注解的实体名字
        boolean hasTable=c.isAnnotationPresent(Collect.class);
        if(hasTable){
            Collect collect= (Collect) c.getAnnotation(Collect.class);
            String collectName= collect.name();
            MongoCollection<Document> collection=getCollection(collectName);
            String uuid="";
            String idName="";
            //遍历所有字段
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                boolean fExis = field.isAnnotationPresent(Column.class);
                if (!fExis) {
                    continue;
                }
                //获取该字段上的column注解
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name();
                String fieldName=field.getName();
                String getMethoudName="get"+fieldName.substring(0,1).toUpperCase()+
                        fieldName.substring(1);
                //为了让各种类型的返回值都能接收，定义Object类型
                Object fieldValue = null;
                try {
                    Method getMethod=c.getMethod(getMethoudName);
                    //调用f的对应字段的get方法
                    fieldValue=getMethod.invoke(var1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //判断字段上是否有id注解
                boolean idExits=field.isAnnotationPresent(Id.class);
                if(idExits){
                    uuid=String.valueOf(fieldValue) ;
                    idName=columnName;
                }
                if(!StringUtils.isBlank(uuid)){
                    if(columnName.equalsIgnoreCase(idName)){
                        continue;
                    }
                    collection.updateMany(Filters.eq(idName,uuid),new Document("$set",new Document(columnName,fieldValue)));
                }
            }
            return get(uuid);
        }else{
            throw new MongoException("没有标明document名称!");
        }
    }

    @Override
    public T save(T var1) {
        //1、获取class
        Class c=var1.getClass();
        //2、获取class上面的文档注解的实体名字
        boolean hasTable=c.isAnnotationPresent(Collect.class);
        if(hasTable){
            Collect collect= (Collect) c.getAnnotation(Collect.class);
            String collectName= collect.name();
            MongoCollection<Document> collection=getCollection(collectName);
            String uuid="";
            Document document = new Document();
            //遍历所有字段
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                boolean fExis = field.isAnnotationPresent(Column.class);
                if (!fExis) {
                    continue;
                }
                //获取该字段上的column注解
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name();
                String fieldName=field.getName();
                //判断字段上是否有generateValue注解  有则执行对应的set方法生成对应类型的数据
                boolean generateExist=field.isAnnotationPresent(GeneratedValue.class);
                if(generateExist){
                    GeneratedValue generatedValue=field.getAnnotation(GeneratedValue.class);
                    try {
                        //获得set的方法  get方法也建议这么干
                        PropertyDescriptor pd=new PropertyDescriptor(field.getName(),c);
                        Method method=pd.getWriteMethod();
                        //执行set方法
                        method.invoke(var1,autoGenerate(generatedValue.generator()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                String getMethoudName="get"+fieldName.substring(0,1).toUpperCase()+
                        fieldName.substring(1);
                //为了让各种类型的返回值都能接收，定义Object类型
                Object fieldValue = null;
                try {
                    Method getMethod=c.getMethod(getMethoudName);
                    //调用f的对应字段的get方法
                    fieldValue=getMethod.invoke(var1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                document.append(columnName,fieldValue);
                //判断字段上是否有id注解
                boolean idExits=field.isAnnotationPresent(Id.class);
                if(idExits){
                    uuid=String.valueOf(fieldValue) ;
                }
            }
            collection.insertOne(document);
            return get(uuid);
        }else{
            throw new MongoException("没有标明document名称!");
        }
    }

    @Override
    public Result deleteById(String var1) {
        //1、获取class
        Class c=this.clazz;
        //2、获取class上面的文档注解的实体名字
        boolean hasTable=c.isAnnotationPresent(Collect.class);
        if(hasTable){
            Collect collect= (Collect) c.getAnnotation(Collect.class);
            String collectName= collect.name();
            MongoCollection<Document> collection=getCollection(collectName);
            String idName="";
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                boolean fExis = field.isAnnotationPresent(Column.class);
                if (!fExis) {
                    continue;
                }
                //获取该字段上的column注解
                Column column = field.getAnnotation(Column.class);
                String columnName = column.name();
                //判断字段上是否有id注解  有则将id注解名称存起来
                boolean idExits=field.isAnnotationPresent(Id.class);
                if(idExits){
                    idName=columnName;
                }
            }
            collection.deleteOne(Filters.eq(idName,var1));
            Result result=new Result();
            result.setCode(StatusCodeEnum.SUCCESS.getCode());
            result.setMessage(StatusCodeEnum.SUCCESS.getDesc());
            return result;
        }else{
            throw new MongoException("没有标明document名称!");
        }
    }

    /**
     * 删除指定字段名字对应的文档内容  删除多个字段
     * @param name
     * @param val
     */
    @Override
    public void deleteByField(String name, String val) {
        //1、获取class
        Class c=this.clazz;
        //2、获取class上面的文档注解的实体名字
        boolean hasTable=c.isAnnotationPresent(Collect.class);
        if(hasTable){
            Collect collect= (Collect) c.getAnnotation(Collect.class);
            String collectName= collect.name();
            MongoCollection<Document> collection=getCollection(collectName);
            collection.deleteMany(Filters.eq(name,val));
        }else{
            throw new MongoException("没有标明document名称!");
        }
    }

    @Override
    public void saveOrUpdate(T var1) {

    }

    @Override
    public void delete(T var1) {

    }

    @Override
    public List<T> findAll() {
        //1、获取class
        Class c=this.clazz;
        //2、获取class上面的文档注解的实体名字
        boolean hasTable=c.isAnnotationPresent(Collect.class);
        if(!hasTable){
            return null;
        }
        Collect collect= (Collect) c.getAnnotation(Collect.class);
        String collectName= collect.name();
        MongoCollection<Document> collection=getCollection(collectName);
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<T> lists=new ArrayList<>();
        while(mongoCursor.hasNext()){
            Document document=mongoCursor.next();
            T t=JSON.parseObject(document.toJson(),clazz);
            lists.add(t);
        }
        return lists;
    }

    @Override
    public List find(String var1) {

        return null;
    }

    /**
     * 根据指定字段的值查询所有的对应的数据
     * @param var1
     * @param var2
     * @return
     */
    @Override
    public List<T> find(String var1, Object var2) {
        //1、获取class
        Class c=this.clazz;
        //2、获取class上面的文档注解的实体名字
        boolean hasTable=c.isAnnotationPresent(Collect.class);
        if(!hasTable){
            return null;
        }
        Collect collect= (Collect) c.getAnnotation(Collect.class);
        String collectName= collect.name();
        MongoCollection<Document> collection=getCollection(collectName);
        FindIterable<Document> findIterable = collection.find(Filters.eq(var1,var2));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<T> lists=new ArrayList<>();
        while(mongoCursor.hasNext()){
            Document document=mongoCursor.next();
            T t=JSON.parseObject(document.toJson(),clazz);
            lists.add(t);
        }
        return lists;
    }

    @Override
    public List find(String var1, Object[] var2) {
        return null;
    }

    /**
     * 多条件查询   只支持and拼接 以及eq的条件 其中查询条件字段全部写在params集合里面   vals填写对应的值  两者个数和位置一一对应
     * @param params 字段名称
     * @param vals  值
     * @return
     */
    @Override
    public List<T> find(List<String> params, List<String> vals) {
        //1、获取class
        Class c=this.clazz;
        //2、获取class上面的文档注解的实体名字
        boolean hasTable=c.isAnnotationPresent(Collect.class);
        if(!hasTable){
            return null;
        }
        Collect collect= (Collect) c.getAnnotation(Collect.class);
        String collectName= collect.name();
        MongoCollection<Document> collection=getCollection(collectName);
        List<Bson> bsons=new ArrayList<>();
        //构造查询条件
        for(int i=0;i<params.size();i++){
            bsons.add(Filters.eq(params.get(i),vals.get(i)));
        }
        Bson condition=Filters.and(bsons);
        FindIterable<Document> findIterable = collection.find(condition);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<T> lists=new ArrayList<>();
        while(mongoCursor.hasNext()){
            Document document=mongoCursor.next();
            T t=JSON.parseObject(document.toJson(),clazz);
            lists.add(t);
        }
        return lists;
    }

    @Override
    public void initialize(T var1) {

    }

    /**
     * 根据指定类型生成字符串
     * @param type
     * @return
     */
    private String autoGenerate(String type){
        String result="";
        if(AutoGenerateType.UUID.getCode().equalsIgnoreCase(type)){
            result= UUID.randomUUID().toString().replace("-","");
        }
        if(AutoGenerateType.RANDOM_NUM.getCode().equalsIgnoreCase(type)){
//            result=Math.random();
        }

        return result;
    }
}
