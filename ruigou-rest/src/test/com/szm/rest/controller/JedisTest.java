package com.szm.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class JedisTest {

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext.xml");
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



}