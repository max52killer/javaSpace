package com.dragonsoft.EasyTest;

import com.dragonsoft.EasyTest.mongodb.MongoDBJDBC;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyTestApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMongoDb(){
		MongoDBJDBC dbjdbc=new MongoDBJDBC();
		dbjdbc.query();
	}

}
