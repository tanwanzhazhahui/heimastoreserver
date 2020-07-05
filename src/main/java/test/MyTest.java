package test;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import netscape.javascript.JSObject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import test.pojo.*;
import test.service.*;
import test.utils.jsonvo.JsonOrder;
import test.utils.jsonvo.JsonOrderList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserService userService = ctx.getBean("UserService", UserService.class);
        HashMap map = new HashMap();
        for (User user : userService.findUser(map)) {
            System.out.println(user.getWx_id());
        }
    }

    @Test
    public void testbs(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        CategoryService categoryService = ctx.getBean("CategoryService", CategoryService.class);
        HashMap map = new HashMap();
        map.put("bname","悬疑");
        Bcategory bcategory = categoryService.getBcategories(map).get(0);
        System.out.println(bcategory.getId());
        Scategory s1=new Scategory();
        s1.setSname("港台悬疑");
        s1.setBid(bcategory.getId());

        Scategory s2=new Scategory();
        s2.setSname("大陆悬疑");
        s2.setBid(bcategory.getId());

        Scategory s3=new Scategory();
        s3.setSname("欧美悬疑");
        s3.setBid(bcategory.getId());

        categoryService.addScategory(s1);
        categoryService.addScategory(s2);
        categoryService.addScategory(s3);

    }

    @Test
    public void test3(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        CategoryService categoryService = ctx.getBean("CategoryService", CategoryService.class);
        for (Bcategory bcategory : categoryService.getBcategoriesWithScategory(new HashMap())) {
            System.out.println(bcategory.getBname());
        }
    }

    @Test
    public void test4(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ImageService imageService = ctx.getBean("ImageService", ImageService.class);
        HashMap map = new HashMap();
        map.put("id",2);
        for (SwiperImage swiperImage : imageService.findSwiperImage(map)) {
            swiperImage.getUrl();
        }
    }

    @Test
    public void pageproducttest(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ProductService productService = ctx.getBean("ProductService", ProductService.class);
        PageHelper.startPage(2,5).countColumn("1");
        List<Product> productList = productService.findProduct(new HashMap());
        System.out.println(productList.size());
        PageInfo<Product> pageInfo=new PageInfo<Product>(productList);
        for (Product product : pageInfo.getList()) {
            System.out.println(product.getUrl());
        }
    }

    @Test
    public void finduserorder(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserService userService = ctx.getBean("UserService", UserService.class);
        User user = userService.findUserWithOrderByWx_id("oRvMp4_jxWvqlzZ_2durkNy_v5xY");
        System.out.println(user);
    }

    @Test
    public void Testredis1(){
        Jedis jedis = new Jedis("106.14.135.230",6379);
        System.out.println("清空数据:"+jedis.flushDB());
        System.out.println("判断某个键是否存在"+jedis.exists("username"));
        System.out.println("新增username,wdnmd键值对"+jedis.set("username","wdnmd"));
        System.out.println("新增password,password键值对"+jedis.set("password","password"));
        System.out.println("系统中所有的键值如下");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);

    }

    @Test
    public void Testredis2(){
        Jedis jedis = new Jedis("106.14.135.230",6379);
        System.out.println("删除键password"+jedis.del("password"));
        System.out.println("判断password是否存在"+jedis.exists("password"));
        System.out.println("查看username所存储的值的类型"+jedis.type("username"));
        System.out.println("随机返回key空间的一个"+jedis.randomKey());
        System.out.println("重命名key"+jedis.rename("username","name"));
        System.out.println("按索引查询"+jedis.select(0));

    }

    @Test
    public void Testredis3(){
        Jedis jedis = new Jedis("106.14.135.230",6379);
        System.out.println("删除当前选择数据库中所有key"+jedis.flushDB());
        System.out.println("返回当前数据库中key的数目"+jedis.dbSize());
        System.out.println("删除所有数据库中的所有key"+jedis.flushAll());

    }

    @Test
    public void Testredis4(){
        Jedis jedis = new Jedis("106.14.135.230",6379);
        jedis.flushDB();
        System.out.println("==========新增键值对防止覆盖原先值=========");
        System.out.println(jedis.setnx("key1","value1"));
        System.out.println(jedis.setnx("key2","value2"));
        System.out.println(jedis.setnx("key2","value2-new"));
        System.out.println(jedis.get("key1"));
        System.out.println(jedis.get("key2"));

        System.out.println("==========新键值对并设置有效时间============");
        System.out.println(jedis.setex("key3",2,"value3"));
        System.out.println(jedis.get("key3"));
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(jedis.get("key3"));
        System.out.println(jedis.getSet("key2","key2GetSet"));
        System.out.println(jedis.get("key2"));
        System.out.println("获得key2的值的字符串"+jedis.getrange("key2",2,4));

    }

    @Test
    public void TestredisSet() {
        Jedis jedis = new Jedis("106.14.135.230",6379);
        jedis.flushDB();
        System.out.println("========向集合中添加元素(不重复)===========");
        System.out.println(jedis.sadd("eleSet","e1","e2","e4","e3","e0","e8","e7","e5"));
        System.out.println(jedis.sadd("eleSet","e6"));
        System.out.println(jedis.sadd("eleSet","e6"));
        System.out.println("eleSet的所有元素为："+jedis.smembers("eleSet"));
        System.out.println("删除第一个元素e0"+jedis.srem("eleSet","e0"));
        System.out.println("eleSet的所有元素为："+jedis.smembers("eleSet"));
        System.out.println("删除两个元素e7和e6"+jedis.srem("eleSet","e7","e6"));
        System.out.println("eleSet的所有元素为"+jedis.smembers("eleSet"));
        System.out.println("随机的移除集合中的一个元素"+jedis.spop("eleSet"));
        System.out.println("随机的移除集合中的一个元素"+jedis.spop("eleSet"));
        System.out.println("eleSet中的所有元素"+jedis.smembers("eleSet"));
        System.out.println("eleSet中包含元素的个数"+jedis.scard("eleSet"));
        System.out.println("e3是否在eleSet中"+jedis.sismember("eleSet","e3"));
        System.out.println("e1是否在eleSet中"+jedis.sismember("eleSet","e1"));
        System.out.println("e5是否在eleSet中"+jedis.sismember("eleSet","e5"));
        System.out.println("======================================");
        System.out.println(jedis.sadd("eleSet1","e1","e2","e4","e3","e0","e8","e7","e5"));
        System.out.println(jedis.sadd("eleSet2","e1","e2","e4","e3","e0","e8"));
        System.out.println("将eleSet1中删除e1并存入eleSet3中"+jedis.smove("eleSet1","eleSet3","e1"));
        System.out.println("将eleSet1中删除e2并存入eleSet3中"+jedis.smove("eleSet1","eleSet3","e2"));
        System.out.println("eleSet1中的元素:"+jedis.smembers("eleSet1"));
        System.out.println("eleSet3中的元素:"+jedis.smembers("eleSet3"));
        System.out.println("=============集合运算========================");
        System.out.println("eleSet1中的元素:"+jedis.smembers("eleSet1"));
        System.out.println("eleSet2中的元素:"+jedis.smembers("eleSet2"));
        System.out.println("eleSet1和eleSet2的交集"+jedis.sinter("eleSet1","eleSet2"));
        System.out.println("eleSet1和eleSet2的并集"+jedis.sunion("eleSet1","eleSet2"));
        System.out.println("eleSet1和eleSet2的差集"+jedis.sdiff("eleSet1","eleSet2"));//eleSet1中有,eleSet2中没有
        jedis.sinterstore("eleSet4","eleSet1","eleSet2");//求交集并保存到dstkey中
        System.out.println("eleSet4中的元素"+jedis.smembers("eleSet4"));


    }

    @Test
    public void testTx(){
        Jedis jedis = new Jedis("106.14.135.230",6379);
        jedis.flushDB();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello","world");
        jsonObject.put("name","wdnmd");
        //开启事务
        Transaction multi = jedis.multi();
        String result = jsonObject.toJSONString();
        jedis.watch(result);
        try {
            multi.set("user1",result);
            multi.set("user2",result);
            int i=1/0;
            multi.exec();//执行事务
        } catch (Exception e) {
            multi.discard();//放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();//关闭连接
        }
    }
}
