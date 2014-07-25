package com.example.tests;

import static org.testng.Assert.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.fw.User;

public class SignupTest extends TestBase {

	public User user = new User().setLogin("testuser20").setPassword("123456")
			.setEmail("testuser20@localhost.localdomain");

	@BeforeClass
	public void createMailUser() {
		if (! app.getJamesHelper().doesUserExist(user.login)) {
			app.getJamesHelper().createUser(user.login, user.password);
		}
	}
	
	@AfterClass
	public void deleteMailUser() {
		if (app.getJamesHelper().doesUserExist(user.login)) {
			app.getJamesHelper().deleteUser(user.login);
		}
	}


	@Test
	public void newUserShouldSingUp() {

		app.getAccountHelper().signup(user);
		assertTrue(app.getAccountHelper().isLogged(user));
	}


}