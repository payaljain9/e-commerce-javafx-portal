package org.jain.product.search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.jain.common.RestUtil;
import org.jain.product.ProductScreen;
import org.jain.product.dto.SearchProductResponse;
import org.jain.user.dto.SearchUserResponse;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SearchProductScreenController {
	@FXML
	TextField pidtxt;
	
	@FXML
	TextField pnametxt;
	
	@FXML
	TextField ppricetxt;
	
	@FXML
	TextField pquantitytxt;
	
	@FXML
	Button searchbtn;
	
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
		ppricetxt.setText("");
		pquantitytxt.setText("");
	}
	
	public void searchbtnClick() throws Exception
	{
		SearchProductResponse searchProductResponse=null;
		
		if(pidtxt.getText()!=null)
		{
			int pid=Integer.parseInt(pidtxt.getText());
			String url="http://localhost:8080/api/product/search/"+pid;
			
			searchProductResponse = RestUtil.getRequest(url, SearchProductResponse.class);
			
			if(searchProductResponse.getResponseCode().equals("0000"))
			{
			
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Operation Successful");
				alert.setHeaderText(null); 
				alert.setContentText(searchProductResponse.getResponseMessage()+"with code= "+searchProductResponse.getResponseCode());
				alert.showAndWait();
				 
				pnametxt.setText(searchProductResponse.getProduct().getName());
				pidtxt.setText(String.valueOf(searchProductResponse.getProduct().getProductId()));
				ppricetxt.setText(String.valueOf(searchProductResponse.getProduct().getPrice()));
				pquantitytxt.setText(String.valueOf(searchProductResponse.getProduct().getQuantity()));
			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.WARNING);
			    alert.setTitle("Product Not Available");
			    alert.setHeaderText(null); 
			    alert.setContentText(searchProductResponse.getResponseMessage()+" code "+searchProductResponse.getResponseCode());
			    alert.showAndWait();
				
				pidtxt.setText("");
			}
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
		    alert.setTitle("Missing Details");
		    alert.setHeaderText(null); // No header text
		    alert.setContentText(searchProductResponse.getResponseMessage()+" code= "+searchProductResponse.getResponseCode());
		    alert.showAndWait();
		}
	}
}
