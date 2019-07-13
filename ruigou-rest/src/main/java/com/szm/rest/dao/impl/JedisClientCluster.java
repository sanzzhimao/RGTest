package com.szm.rest.dao.impl;

import com.szm.rest.dao.IJedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

public class JedisClientCluster implements IJedisClient {

    @Autowired
    private JedisCluster JedisCluster;

    @Override
    public String get(String key) {

        return JedisCluster.get(key);
    }

    @Override
    public String set(String key, String val) {

        return JedisCluster.set(key, val);
    }

    @Override
    public String hget(String hkey, String key) {

        return JedisCluster.hget(hkey, key);
    }

    @Override
    public long hset(String hkey, String key, String val) {

        return JedisCluster.hset(hkey, key, val);
    }

    @Override
    public long incr(String key) {

        return JedisCluster.incr(key);
    }

    @Override
    public long expire(String key, int seconds) {

        return JedisCluster.expire(key, seconds);
    }

    @Override
    public long ttl(String key) {

        return JedisCluster.ttl(key);
    }

    @Override
    public long del(String key) {
        return JedisCluster.del(key);
    }

    @Override
    public long hdel(String hkey, String key) {
        return JedisCluster.hdel(hkey, key);
    }
}
