package com.jjkj.main;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mysql.jdbc.Connection;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemCenterApplicationTests {
	private static final Logger log = LoggerFactory.getLogger(SystemCenterApplicationTests.class);

	@Autowired
	DataSource dataSource;
	@Autowired
	JedisPool jedisPool;

	@Test
	public void contextLoads() throws SQLException, InterruptedException {
//		System.out.println(dataSource);
//		System.out.println(dataSource.getClass());
//		System.out.println(dataSource);
//		log.info("aaa");
//		log.warn("aaa");
//		log.error("aaa");
		
		for (int i = 0; i < 5; i++) {
			System.out.println(jedisPool);
			Jedis jedis = jedisPool.getResource();
//			Thread.sleep(15000);
//			System.out.println(jedis.auth("so123"));
			System.out.println(jedis.get("a"));
			jedis.set("ok"+i, "ok");
			System.out.println(jedis);
//			jedis.close();
		}
		
	}

}
