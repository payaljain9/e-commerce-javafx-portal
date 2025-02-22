package org.jain.user.update;


import java.io.IOException;

import org.jain.common.RestUtil;
import org.jain.user.UserScreen;
import org.jain.user.dto.SearchUserResponse;
import org.jain.user.dto.UpdateUserRequest;
import org.jain.user.dto.UpdateUserResponse;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateUserScreenController {
	@FXML
	TextField useridtxt;
	
	@FXML
	TextField usernametxt;
	
	@FXML
	TextField emailtxt;
	
	@FXML
	TextField passwordtxt;
	
	@FXML
	Button savebtn;
	
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
		usernametxt.setText("");
		emailtxt.setText("");
		passwordtxt.setText("");
		useridtxt.setDisable(false);
		
	}
	
	public void useridtxtClick() throws IOException, InterruptedException
	{
		int id=Integer.parseInt(useridtxt.getText());
		
		String url="http://localhost:8080/api/user/search/"+id;
		
		SearchUserResponse searchUserResponse = RestUtil.getRequest(url, SearchUserResponse.class);
		
		if(searchUserResponse.getResponseCode().equals("0000"))
		{
			useridtxt.setText(String.valueOf(searchUserResponse.getUser().getUserId()));
			usernametxt.setText(searchUserResponse.getUser().getName());
			emailtxt.setText(searchUserResponse.getUser().getEmail());
			passwordtxt.setText(searchUserResponse.getUser().getPassword());
			useridtxt.setDisable(true);
		}
		else if(searchUserResponse.getResponseCode().equals("911"))
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
		    alert.setTitle("User Not Available");
		    alert.setHeaderText(null); // No header text
		    alert.setContentText(searchUserResponse.getResponseMessage()+" code= "+searchUserResponse.getResponseCode());
		    alert.showAndWait();
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
		    alert.setTitle("Missing Details");
		    alert.setHeaderText(null); // No header text
		    alert.setContentText(searchUserResponse.getResponseMessage()+" code= "+searchUserResponse.getResponseCode());
		    alert.showAndWait();
		}
	}
	
	public void savebtnClick() throws Exception
	{
		int id=Integer.parseInt(useridtxt.getText());
		
		String url="http://localhost:8080/api/user/update";
		
		UpdateUserRequest request = new UpdateUserRequest();
		
		request.setEmail(emailtxt.getText());
		request.setName(usernametxt.getText());
		request.setPassword(passwordtxt.getText());
		request.setUserId(Integer.parseInt(useridtxt.getText()));
		
		UpdateUserResponse response = RestUtil.postRequest(url, request, UpdateUserResponse.class);
		
		
		if(response.getResponseCode().equals("0000"))
		{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setTitle("Operation Successful");
		    alert.setHeaderText(null); 
		    alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
		    alert.showAndWait();
		    
		    useridtxt.setText(String.valueOf(response.getRequest().getUserId()));
			usernametxt.setText(response.getRequest().getName());
			emailtxt.setText(response.getRequest().getEmail());
			passwordtxt.setText(response.getRequest().getPassword());
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
		    alert.setTitle("User ID Missing");
		    alert.setHeaderText(null); // No header text
		    alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
		    alert.showAndWait();
		    
		    useridtxt.setText("");
			usernametxt.setText("");
			emailtxt.setText("");
			passwordtxt.setText("");
		}
	}
}
