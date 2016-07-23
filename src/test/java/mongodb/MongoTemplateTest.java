package mongodb;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.snail.architecture.entity.Page;
import com.snail.architecture.service.IBaseMongoDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config-mongodb.xml")
public class MongoTemplateTest{
	@Autowired
	private IBaseMongoDao<Custmoer> baseMongoDaoImpl;

	@Test
	public void save() {
		Custmoer a = new Custmoer();
		a.setUserid("31238");
		a.setUsername("zhangmin1");
		
		Custmoer b = new Custmoer();
		b.setUserid("31238");
		b.setUsername("zhangmin2");
		
		Custmoer c = new Custmoer();
		c.setUserid("31238");
		c.setUsername("zhangmin3");
		
		Custmoer d = new Custmoer();
		d.setUserid("31238");
		d.setUsername("zhangmin4");
		baseMongoDaoImpl.save(a);
		baseMongoDaoImpl.save(b);
		baseMongoDaoImpl.save(c);
		baseMongoDaoImpl.save(d);
	}
	@Test
	public void delete(){
		Query q = new Query(Criteria.where("userid").is("31238"));
		baseMongoDaoImpl.remove(q);
	}
	
	@Test
	public void find(){
		Query q = new Query(Criteria.where("userid").is("31238"));
		List<Custmoer> data = baseMongoDaoImpl.find(q);
		System.out.println(data);
	}
	
	@Test
	public void findAll(){
		Query q = new Query(Criteria.where("userid").is("31238"));
		Custmoer data = baseMongoDaoImpl.findOne(q);
		System.out.println(data);
	}
	@Test
	public void findPage(){
		Page<Custmoer> p = new Page<Custmoer>();
		p.setPageNumber(2);
		p.setPageSize(2);
		Query q = new Query(Criteria.where("userid").is("31238"));
		Page<Custmoer> data=baseMongoDaoImpl.findPage(p, q);
		System.out.println(data);
	}
}
