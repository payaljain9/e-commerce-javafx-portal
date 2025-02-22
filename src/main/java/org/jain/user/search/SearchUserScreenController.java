package org.jain.user.search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jain.common.RestUtil;
import org.jain.user.UserScreen;
import org.jain.user.dto.SearchUserResponse;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchUserScreenController {
	@FXML
	TextField useridtxt;
	
	@FXML
	TextField passwordtxt;
	
	@FXML
	TextField usernametxt;
	
	@FXML
	TextField emailtxt;
	
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
		passwordtxt.setText("");
		usernametxt.setText("");
		emailtxt.setText("");
	}
	
	public void searchbtnClick() throws Exception
	{
		int id=Integer.parseInt(useridtxt.getText());
		
		String url="http://localhost:8080/api/user/search/"+id;
		
		SearchUserResponse searchUserResponse = RestUtil.getRequest(url, SearchUserResponse.class);
		
		if(useridtxt.getText()!=null)
		{
			if(searchUserResponse.getResponseCode().equals("0000"))
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("User Available");
			    alert.setHeaderText(null); // No header text
			    alert.setContentText(searchUserResponse.getResponseMessage()+"with code "+searchUserResponse.getResponseCode());
			    alert.showAndWait();
				
				useridtxt.setText(String.valueOf(searchUserResponse.getUser().getUserId()));
				usernametxt.setText(searchUserResponse.getUser().getName());
				emailtxt.setText(searchUserResponse.getUser().getEmail());
				passwordtxt.setText(searchUserResponse.getUser().getPassword());
			}
			else if(searchUserResponse.getResponseCode().equals("911"))
			{
				Alert alert = new Alert(Alert.AlertType.WARNING);
			    alert.setTitle("User Not Available");
			    alert.setHeaderText(null); // No header text
			    alert.setContentText(searchUserResponse.getResponseMessage()+" code= "+searchUserResponse.getResponseCode());
			    alert.showAndWait();
			    useridtxt.setText("");
			}
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
}
