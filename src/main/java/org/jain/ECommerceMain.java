package org.jain;

import org.jain.common.StageFactory;
import org.jain.login.LoginScreen;

import javafx.application.Application;
import javafx.stage.Stage;

public class ECommerceMain extends Application{
	public static void main(String args[])
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//setting primarystage to common stage(present in StageFactory class) that can be used by all classes of project 
		StageFactory.setStage(primaryStage);
		
		//calling login screen method(contains stage that shows login screen UI)
		new LoginScreen().show();
	}
}
