package org.jain.user.update;

import java.io.IOException;

import org.jain.common.StageFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class UpdateUserScreen {
	public void show()
	{
		//setting title to login screen by calling getStage() of StageFactory contains common stage object
		StageFactory.getStage().setTitle("Update User Screen");
		
		//
		Parent actorgroup=null;
		try 
		{
			actorgroup = FXMLLoader.load(getClass().getResource("/Update User2.fxml"));
		} 
		catch (IOException e) 
		{
			//displaying error msg
			 Alert alert = new Alert(Alert.AlertType.WARNING);
			 alert.setTitle("File Can't Open");
			 //header text setting as null
			 alert.setHeaderText(null); 
			 alert.setContentText("Can't Open Update User.fxml..!\nClosing Application..");
			 alert.showAndWait();
			 System.exit(0);
		}
		
		//creating scene
		Scene scene=new Scene(actorgroup,600,400);
		
		//setting login scene to stage
		StageFactory.getStage().setScene(scene);
		
		//showing stage
		StageFactory.getStage().show();
	}
}
