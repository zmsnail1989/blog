package redis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-redis.xml" })
public class ExampleTest extends AbstractJUnit4SpringContextTests {
	/**
		ValueOperations	Redis String/Value 操作
		ListOperations	Redis List 操作
		SetOperations	Redis Set 操作
		ZSetOperations	Redis Sort Set 操作
		HashOperations	Redis Hash 操作
	 */
	@Autowired
	RedisTemplate<String, User> redisTemplate;
	@Resource(name = "redisTemplate")
	ValueOperations<String, User> valueOperations;
	@Resource(name = "redisTemplate")
	ListOperations<String, User> listOperations;
	@Resource(name = "redisTemplate")
	SetOperations<String, User> setOperations;

	@Before
	public void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testValueOperations() {
		User a = new User(null, 0);
		a.setAge(1);
		a.setCreateDate(new Date());
		a.setName("haha");
		valueOperations.set("test", a, 30,TimeUnit.SECONDS);
	}
	@Test
	public void testListOperations(){
		List<User> test  = new ArrayList<User>();
		
		User a = new User(null, 0);
		a.setAge(1);
		a.setCreateDate(new Date());
		a.setName("test");
		
		User b = new User(null, 0);
		b.setAge(1);
		b.setCreateDate(new Date());
		b.setName("xxxx");
		
		test.add(a);
		test.add(b);
		
		listOperations.rightPushAll("list-values", test);
		
		List<User> rtn=  listOperations.range("list-values", 2, 3);
		
		System.out.println(rtn.toString());
	}
}
