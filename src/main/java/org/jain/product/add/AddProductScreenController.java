package org.jain.product.add;

import java.io.IOException;
import java.sql.SQLException;

import org.jain.common.RestUtil;
import org.jain.product.ProductScreen;
import org.jain.product.dto.AddProductRequest;
import org.jain.product.dto.AddProductResponse;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddProductScreenController {
	@FXML
	TextField pnametxt;
	
	@FXML
	TextField pidtxt;
	
	@FXML
	TextField pquantitytxt;
	
	@FXML
	TextField ppricetxt;
	
	@FXML
	Button addbtn;
	
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
		pnametxt.setText("");
		pidtxt.setText("");
		pquantitytxt.setText("");
		ppricetxt.setText("");
	}
	
	public void addbtnClick() throws SQLException, IOException, InterruptedException
	{	
		AddProductResponse response=null;
		
		if(pnametxt.getText()!=null && pquantitytxt.getText()!=null && ppricetxt.getText()!=null)
		{
			//request obj as a body
			AddProductRequest request = new AddProductRequest();
			//api url
			String url="http://localhost:8080/api/product/add";
			
			//filling data in request obj
			request.setName(pnametxt.getText());
			request.setQuantity(Integer.parseInt(pquantitytxt.getText()));
			request.setPrice(Integer.parseInt(ppricetxt.getText())); 
			
			//response after submitting data
			response = RestUtil.postRequest(url, request, AddProductResponse.class);
			
			//if response returns 0000 means product added and displays the what added to user
			if(response.getResponseCode().equals("0000")) 
			{
				//displaying successful msg 
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("Operation Successful");
			    alert.setHeaderText(null); 
			    alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
			    alert.showAndWait(); 
			    
			    pnametxt.setText(response.getRequest().getName());
			    pidtxt.setText(String.valueOf(response.getProductId()));
			    pquantitytxt.setText(String.valueOf(response.getRequest().getQuantity()));
				ppricetxt.setText(String.valueOf(response.getRequest().getPrice()));
			    
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
				 
				 pnametxt.setText("");
				 pidtxt.setText("");
				 pquantitytxt.setText("");
				 ppricetxt.setText("");
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
		}
	}
}
