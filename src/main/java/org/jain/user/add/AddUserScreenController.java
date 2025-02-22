package org.jain.user.add;


import org.jain.common.RestUtil;
import org.jain.user.UserScreen;
import org.jain.user.dto.AddUserRequest;
import org.jain.user.dto.AddUserResponse;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddUserScreenController {
	@FXML
	TextField usernametxt;
	
	@FXML
	TextField useridtxt;
	
	@FXML
	TextField passwordtxt;
	
	@FXML
	TextField emailtxt;
	
	@FXML
	Button addbtn;
	
	@FXML
	Button resetbtn;
	
	@FXML
	Button backtbtn;
	
	public void resetbtnClick()
	{
		usernametxt.setText("");
		passwordtxt.setText("");
		useridtxt.setText("");
		emailtxt.setText("");
	}
	
	public void backbtnClick()
	{
		new UserScreen().show();
	}
	
	public void addbtnClick() throws Exception
	{
		AddUserRequest request = new AddUserRequest();
		
		String url="http://localhost:8080/api/user/add";
		
		request.setName(usernametxt.getText());
		request.setPassword(passwordtxt.getText());
		request.setEmail(emailtxt.getText());
		
		AddUserResponse response = RestUtil.postRequest(url, request, AddUserResponse.class);
			
		//int userid=Integer.parseInt(useridtxt.getText());
			
		if(usernametxt.getText()!=null && passwordtxt.getText()!=null && emailtxt.getText()!=null)
		{
			if(response.getResponseCode().equals("0000")) 
			{
				//displaying successful msg 
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("Operation Successful");
			    alert.setHeaderText(null); 
			    alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
			    alert.showAndWait(); 
			    
			    usernametxt.setText(response.getRequest().getName());;
				passwordtxt.setText(response.getRequest().getPassword());
				useridtxt.setText(String.valueOf(response.getUserId()));
				emailtxt.setText(response.getRequest().getEmail());
			}
			else
			{
				 //displaying error msg 
				 Alert alert = new Alert(Alert.AlertType.WARNING);
				 alert.setTitle("Unexpected error..!");
				 //header text setting as null
				 alert.setHeaderText(null); 
				 alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
				 alert.showAndWait();
				 
				 usernametxt.setText("");
				 passwordtxt.setText("");
				 useridtxt.setText("");
				 emailtxt.setText("");
			}
		}
		else
		{
			 //displaying error msg 
			 Alert alert = new Alert(Alert.AlertType.WARNING);
			 alert.setTitle("Missing Details");
			 //header text setting as null
			 alert.setHeaderText(null); 
			 alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
			 alert.showAndWait();
			 
			 usernametxt.setText("");
			 passwordtxt.setText("");
			 useridtxt.setText("");
			 emailtxt.setText("");
		}
	}
}
