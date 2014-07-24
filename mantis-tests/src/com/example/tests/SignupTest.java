package com.example.tests;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

import com.example.fw.User;

public class SignupTest extends TestBase {
	
	@Test
	public void newUserShouldSingUp() {
		User user = new User().setLogin("testuser1").setPassword("123456").setEmail("testuser1@localhost");
		app.getAccountHelper().signup(user);
		assertTrue(app.getAccountHelper.isLogged(user));
	}

}
