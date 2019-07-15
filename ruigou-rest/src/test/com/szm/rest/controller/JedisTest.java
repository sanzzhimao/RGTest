package com.szm.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisMovedDataException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class JedisTest {

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-redis.xml");
    }

    //redis集群
    @Test
    public void testJedisCluster() {
        JedisCluster jedisCluster = (JedisCluster) applicationContext
                .getBean("jedisCluster");

        jedisCluster.set("name", "zhangsan");
        String value = jedisCluster.get("name");
        System.out.println(value);
    }

    // 连接redis集群
    @Test
    public void testJedisCluster1() {

        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(30);
        // 最大连接空闲数
        config.setMaxIdle(2);

        //集群结点
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7001));
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7002));
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7003));
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7004));
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7005));
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7006));
        JedisCluster jc = new JedisCluster(jedisClusterNode, config);

        JedisCluster jcd = new JedisCluster(jedisClusterNode);
        jcd.set("name", "zhangsan");
        String value = jcd.get("name");
        System.out.println(value);
    }

    @Test
    public void deleteAllKeys() {
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(30);
        // 最大连接空闲数
        config.setMaxIdle(2);

        //集群结点
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7001));
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7002));
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7003));
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7004));
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7005));
        jedisClusterNode.add(new HostAndPort("192.168.15.128", 7006));
        JedisCluster jc = new JedisCluster(jedisClusterNode, config);



        try {
            // 获取集群中所有的节点
            Collection<JedisPool> jedisPools = jc.getClusterNodes().values();
            boolean isSuccess = false;
            // 遍历所有的节点，获取每个节点上对应数据匹配的结果，并删除
            for (JedisPool pool : jedisPools) {
                Jedis jedis = null;
                try {
                    jedis = pool.getResource();
                    Set<String> keys = jedis.keys("*");
                    Iterator<String> it = keys.iterator();
                    while (it.hasNext()) {
                        String key = it.next();
                        jedis.del(key);
                    }
                    pool.returnResource(jedis);
                    isSuccess = true;
                } catch (JedisMovedDataException ignore) {
                    isSuccess = false;
                    pool.returnBrokenResource(jedis);
                } catch (Exception e) {
                    pool.returnBrokenResource(jedis);
                    isSuccess = false;
                    System.out.println("Error message: " + e.getMessage());
                }
            }
            if (isSuccess) {
                System.out.println("Complete!");
            } else {
                System.out.println("Error!");
            }
            jc.close();
        } catch (Exception e) {
            jc.close();
            e.printStackTrace();
        }
    }


}