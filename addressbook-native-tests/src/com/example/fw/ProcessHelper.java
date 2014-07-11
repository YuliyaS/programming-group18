package com.example.fw;

import java.io.IOException;

public class ProcessHelper extends HelpersBase {

	private Process exec;

	public ProcessHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void startAppUnderTest() throws IOException {
		String command = manager.getProperty("app.path");
		exec = Runtime.getRuntime().exec(command);
	}
	
	public void stopAppUnderTest() {
		exec.destroy();
	}

}
