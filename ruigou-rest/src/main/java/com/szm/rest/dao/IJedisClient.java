package com.szm.rest.dao;

public interface IJedisClient {

    //这是redis操作最常用的存储数据和读取数据的方法
    String get(String key);
    String set(String key,String val);

    //除了上面2个方法，我们还有什么方法，比如还有hget,hset方法
    String hget(String hkey,String key);
    long hset(String hkey,String key,String val);

    //redis还有自增长方法incr
    long incr(String key);

    //redis还有设置数据过期时间方法
    long expire(String key,int second);

    //还有ttl查看有效的剩余时间,查看是否已经过期
    long ttl(String key);

    //设置删除redis的key
    long del(String key);

    //删除hash中的key
    long hdel(String hkey,String key);
}
