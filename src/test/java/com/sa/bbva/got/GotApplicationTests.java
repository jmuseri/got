package com.sa.bbva.got;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
// @SpringBootTest(classes = GotApplication.class)
@DataJpaTest
@WebAppConfiguration
public class GotApplicationTests {

	@Test
	public void contextLoads() {
	}

}
