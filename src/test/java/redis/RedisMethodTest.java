package redis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.entity.User;

/**
 * redis 特性学习
 * 
 * @author zhangmin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config-redis.xml" })
public class RedisMethodTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	RedisTemplate<String, User> redisTemplate;

	/**
	 * ValueOperations Redis String/Value 操作 
	 */
	@Resource(name = "redisTemplate")
	ValueOperations<String, User> valueOperations;
	/**
	 * ListOperations Redis List 操作
	 */
	@Resource(name = "redisTemplate")
	ListOperations<String, String> listOperations;
	/**
	 * SetOperations Redis Set 操作
	 */
	@Resource(name = "redisTemplate")
	SetOperations<String, String> setOperations;
	/**
	 * HashOperations Redis Hash 操作
	 */
	@Resource(name = "redisTemplate")
	private HashOperations<String, String, Object> hashOps;
	/**
	 * ZSetOperations Redis Sort Set 操作
	 */
	@Resource(name = "redisTemplate")
	private ZSetOperations<String, String> ZSetOps;

	/**
	 * 简单的key—value操作
	 */
	@Test
	public void testValueOperations() {
		User a = new User(null, 0);
		a.setAge(1);
		a.setCreateDate(new Date());
		a.setName("haha");
		valueOperations.set("test", a, 30, TimeUnit.SECONDS);
	}

	/**
	 * list队列操作
	 */
	@Test
	public void testListOperations() {
		redisTemplate.delete("listTest"); // 删除这个List
		// 添加操作
		listOperations.rightPush("listTest", "1");
		listOperations.rightPush("listTest", "2");
		listOperations.leftPush("listTest", "3");
		listOperations.leftPush("listTest", "4");

		// 查看listTest这个List的值
		System.out.println(listOperations.range("listTest", 0, -1)); // [4, 3,
																		// 1, 2]

		// 取左边第一个值
		System.out.println(listOperations.leftPop("listTest")); // 4
		// 取左边第一个值
		System.out.println(listOperations.rightPop("listTest")); // 2

		// 查看listTest这个List的值
		System.out.println(listOperations.range("listTest", 0, -1)); // [3, 1,
																		// 2]

		// 修改操作 将下标为1的值修改为 "set_1"，(下标从0开始)
		listOperations.set("listTest", 1, "set_1");
		System.out.println(listOperations.range("listTest", 0, -1)); // [3,
																		// set_1,
																		// 2]

		// 删除操作 将下标为1，值为 set_1的元素删除
		listOperations.remove("listTest", 1, "set_1");
		System.out.println(listOperations.range("listTest", 0, -1)); // [3, 2]
	}

	/**
	 * set集合操作
	 */
	@Test
	public void testSetOps() {
		// 添加
		setOperations.add("setTest1", "1", "2", "3", "4");
		setOperations.add("setTest2", "a", "b", "1", "2");

		// 查询 set集合是无序的
		System.out.println(setOperations.members("setTest1"));// [2, 1, 3, 4]

		// 判断是否有这个值
		System.out.println(setOperations.isMember("setTest1", "4"));// true

		// 删除 可以删除多个值
		setOperations.remove("setTest1", "1", "4");
		System.out.println(setOperations.members("setTest1"));// [3, 2]

		// 交集
		System.out.println(setOperations.intersect("setTest1", "setTest2"));// [2]

		// 并集
		System.out.println(setOperations.union("setTest1", "setTest2"));// [d,
																		// 2, c,
																		// 1, a,
																		// 3, b]
		// 差集
		System.out.println(setOperations.difference("setTest1", "setTest2"));// [3]
	}
	@Test
	// hash表操作 和Map差不多
	public void testHashOps() {
		// 添加
		hashOps.put("hashTest", "key1", "aa");
		Map<String, String> map = new HashMap<String, String>();
		map.put("key2", "bb");
		map.put("key3", "cc");
		map.put("key4", "dd");
		hashOps.putAll("hashTest", map);

		// 查询所有的key
		System.out.println(hashOps.keys("hashTest")); // [key3, key4, key2,
														// key1]

		// 查询 所有的value
		System.out.println(hashOps.values("hashTest"));// [aa, bb, cc, dd]

		// 删除 key
		hashOps.delete("hashTest", "key1");
		System.out.println(hashOps.keys("hashTest")); // [key2, key4, key3]
	}
	@Test
	// ZSetOps操作  
    public void testZSetOps(){  
        //添加  
        ZSetOps.add("ZSetTest", "google.com", 10);  
        ZSetOps.add("ZSetTest", "baidu.com", 8);  
        ZSetOps.add("ZSetTest", "soso.com", 5);  
        ZSetOps.add("ZSetTest", "360.com", 2);  
          
        //默认按照分数的降序排列  
        System.out.println(ZSetOps.range("ZSetTest", 0, -1));  
        //[360.com, soso.com, baidu.com, google.com]  
          
        //按照分数范围查询  
        System.out.println(ZSetOps.rangeByScore("ZSetTest", 5, 10));  
        //[soso.com, baidu.com, google.com]  
          
        //查看排名  
        System.out.println(ZSetOps.rank("ZSetTest", "baidu.com"));// 2  说明第一名是0  
          
        // 按分数从大到小排列  
        System.out.println(ZSetOps.reverseRange("ZSetTest", 0, -1));  
        //[google.com, baidu.com, soso.com, 360.com]  
          
        //看这个对象的 socre  
        System.out.println(ZSetOps.score("ZSetTest", "google.com")); //10.0  
          
        // 删除,按照对象  
        ZSetOps.remove("ZSetTest", "360.com");  
        // 删除，根据下标  
        ZSetOps.removeRange("ZSetTest", 0, 1);  
        // 删除，根据score  
        ZSetOps.removeRangeByScore("ZSetTest", 2, 5);  
    }  
}
