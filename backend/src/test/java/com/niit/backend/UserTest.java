package com.niit.backend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.dao.UserDAO;
import com.niit.backend.model.User;

public class UserTest {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.backend");
		context.refresh();
		userDAO = (UserDAO) context.getBean("userDAO");
	}

	@Test
	public void saveTest() {
		user=userDAO.get("admin@gmail.com");
		/*user.setUsername("admin");
		user.setPassword("12345678");
		user.setEmailid("admin@gmail.com");
		user.setCpassword("12345678");
		user.setAddress("Hyderabad");
		user.setPhno("123456789");
		user.setRole("ROLE_ADMIN");
		user.setEnabled("True");*/
		
		user.setPhno("1234567890");
		assertEquals("success",true,userDAO.saveorUpdate(user));
	}

}
