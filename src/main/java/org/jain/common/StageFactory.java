package org.jain.common;

import javafx.stage.Stage;

public class StageFactory {
	private static Stage stage;
	
	public static void setStage(Stage tempStage)
	{
		stage=tempStage;
	}
	
	public static Stage getStage()
	{
		if(stage==null)
		{
			System.out.println("Stage not created..!\n Closing Application..!");
			System.exit(0);
		}
		return stage;
	}
}
