package org.jain.product.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jain.common.RestUtil;
import org.jain.product.ProductScreen;
import org.jain.product.dto.DeleteProductResponse;
import org.jain.user.dto.DeleteUserResponse;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DeleteProductScreenController {
	@FXML
	TextField pidtxt;
	
	@FXML
	Button deletebtn;
	
	@FXML
	Button resetbtn;
	
	@FXML
	Button backbtn;
	
	public void backbtnClick()
	{
		new ProductScreen().show();
	}
	
	public void resetbtnClick()
	{
		pidtxt.setText("");
	}
	
	public void deletebtnClick() throws Exception
	{
		DeleteProductResponse response=null;
		
		if(pidtxt.getText()!=null)
		{
			int pid=Integer.parseInt(pidtxt.getText());
			
			String url="http://localhost:8080/api/product/delete/"+pid;
			
			response = RestUtil.deleteRequest(url, DeleteProductResponse.class);
			
			if(response.getResponseCode().equals("0000"))
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("Operation Successful");
			    alert.setHeaderText(null); 
			    alert.setContentText(response.getResponseMessage()+"with code "+response.getResponseCode()+"\nDeleted Pid="+response.getProductId());
			    alert.showAndWait(); 
			    pidtxt.setText("");
			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.WARNING);
			    alert.setTitle("Product Not Available");
			    alert.setHeaderText(null); 
			    alert.setContentText(response.getResponseMessage()+" code= "+response.getResponseCode());
			    alert.showAndWait();
			    pidtxt.setText("");
			}
			pidtxt.setText("");
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
		    alert.setTitle("Missing Details");
		    alert.setHeaderText(null); // No header text
		    alert.setContentText(response.getResponseMessage()+" code= "+response.getResponseCode());
		    alert.showAndWait();
		}
	} 
}
