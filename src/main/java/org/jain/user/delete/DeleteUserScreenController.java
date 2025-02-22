package org.jain.user.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jain.common.RestUtil;
import org.jain.user.UserScreen;
import org.jain.user.dto.DeleteUserResponse;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeleteUserScreenController {
	@FXML
	TextField useridtxt;
	
	@FXML
	Button deletebtn;
	
	@FXML
	Button resetbtn;
	
	@FXML
	Button backbtn;
	
	
	
	public void backbtnClick()
	{
		new UserScreen().show();
	}
	public void resetbtnClick()
	{
		useridtxt.setText("");
	}
	
	public void deletebtnClick() throws Exception
	{
		DeleteUserResponse response=null;
				
		if(useridtxt.getText()!=null)
		{
			int userid=Integer.parseInt(useridtxt.getText());
			
			String url="http://localhost:8080/api/user/delete/"+userid;
			
			response = RestUtil.deleteRequest(url, DeleteUserResponse.class);
		    
		    if(response.getResponseCode().equals("0000"))
		    {
		    	
		    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("Operation Successful");
			    alert.setHeaderText(null); 
			    alert.setContentText(response.getResponseMessage()+" code= "+response.getResponseCode());
			    alert.showAndWait(); 
		    	useridtxt.setText("");
		    }
		    else
		    {
		    	 //displaying error msg
				 Alert alert = new Alert(Alert.AlertType.WARNING);
				 alert.setTitle("User Not Available");
				 //header text setting as null
				 alert.setHeaderText(null); 
				 alert.setContentText(response.getResponseMessage()+" code= "+response.getResponseCode());
				 alert.showAndWait();
		         useridtxt.setText("");
		    }
		}
		else
		{
			//displaying error msg
			 Alert alert = new Alert(Alert.AlertType.WARNING);
			 alert.setTitle("Field Missing");
			 //header text setting as null
			 alert.setHeaderText(null); 
			 alert.setContentText(response.getResponseMessage()+" code= "+response.getResponseCode());
			 alert.showAndWait();
			 System.exit(0);
		}
	}
}
