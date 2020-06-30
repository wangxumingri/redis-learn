package com.wxss.springbootredis;

import com.wxss.springbootredis.entity.User;
import com.wxss.springbootredis.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringbootRedisApplicationTests {

	@Autowired
	private RedisService redisService;

	@Test
	void testString() {
		redisService.set("hello","你好");
		System.out.println(redisService.get("hello"));

		redisService.set("world","世界",20L);
		System.out.println(redisService.getExpire("world"));
	}

	@Test
	void testObject(){
		User user = new User();
		user.setId(1L);
		user.setName("测试");
		user.setAge(9);
		user.setBirthday(new Date());

		redisService.set("user:id:1",user);
		Object obj = redisService.get("user:id:1");

		System.out.println(obj);
		if (obj instanceof User){
			User u = (User) obj;
			System.out.println(u);
		}
	}

}
