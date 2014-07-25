package com.example.fw;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

public class AccountHelper extends HelperWithWebDriverBase {

	public AccountHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}
	
	public void signup(User user) {
		openUrl("");
		click(By.cssSelector("span.bracket-link"));
		type(By.name("username"),user.login);
		type(By.name("email"),user.email);
		click(By.cssSelector("input.button"));
		
		pause(10000);		
		String msg = manager.getMailHelper().getNewMail(user.login, user.password);
		String confirmationLink = getConfirmationLink(msg);
		openAbsoluteUrl(confirmationLink);
		
		type(By.name("password"),user.password);
		type(By.name("password_confirm"),user.password);
		click(By.cssSelector("input.button"));
		
	}


	public String getConfirmationLink(String text) {
		Pattern regex = Pattern.compile("http\\S*");
		Matcher matcher = regex.matcher(text);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		}
	}

	public boolean isLogged(User user) {
		return false;
	}

}
