package backend.db;

import backend.db.controller.Controller;
import backend.db.service.CalcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DbApplicationTests {

	@Autowired
	Controller controller;
	@Autowired
	CalcService service;

	@Test
	void contextLoads() {
	}

	@Test
	void calcTestServ() {
		assertEquals(2.3, service.calculate("1.3+3-2"));
	}

	@Test
	void calcTesCont() {
		assertEquals("2.3", controller.calculate("1.3+3-2").getBody());
	}
}
