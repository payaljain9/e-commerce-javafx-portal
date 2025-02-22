package org.jain.login;

import java.io.IOException;
import java.sql.SQLException;

import org.jain.common.RestUtil;
import org.jain.home.HomeScreen;
import org.jain.user.dto.LoginRequest;
import org.jain.user.dto.LoginResponse;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreenController {
	@FXML
	TextField usernametxt;
	
	@FXML
	PasswordField passwordtxt;
	
	@FXML
	Button loginbtn;
	
	@FXML
	Button resetbtn;
	
	
	public void resetbtnClick()
	{
		usernametxt.setText("");
		passwordtxt.setText("");
	}
	
	public void loginbtnClick() throws SQLException, IOException, InterruptedException
	{
		LoginRequest request = new LoginRequest();
		String url="http://localhost:8080/api/user/auth";
		request.setName(usernametxt.getText());
		request.setPassword(passwordtxt.getText());
		
		LoginResponse response = RestUtil.postRequest(url, request, LoginResponse.class);
	
		if(response.getResponseCode().equals("0000"))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Operation Successful");
			alert.setHeaderText(null); 
			alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
			alert.showAndWait();
			 
			new HomeScreen().show();
			
		}
		else
		{
			//displaying error msg
			 Alert alert = new Alert(Alert.AlertType.WARNING);
			 alert.setTitle("Invalid Username/Password");
			 //header text setting as null
			 alert.setHeaderText(null); 
			 alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
			 alert.showAndWait();
	
			usernametxt.setText("");
			passwordtxt.setText("");
		}
	}
}
