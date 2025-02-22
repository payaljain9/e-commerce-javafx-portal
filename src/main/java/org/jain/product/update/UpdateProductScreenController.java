package org.jain.product.update;

import java.io.IOException;

import org.jain.common.RestUtil;
import org.jain.product.ProductScreen;
import org.jain.product.dto.SearchProductResponse;
import org.jain.product.dto.UpdateProductRequest;
import org.jain.product.dto.UpdateProductResponse;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateProductScreenController {
	@FXML
	TextField pnametxt;
	
	@FXML
	TextField pidtxt;
	
	@FXML
	TextField ppricetxt;
	
	@FXML
	TextField pquantitytxt;
	
	@FXML
	Button updatebtn;
	
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
		pidtxt.setDisable(false);
	}
	
	
	public void pidtxtClick() throws IOException, InterruptedException
	{
		int id=Integer.parseInt(pidtxt.getText());
		
		String url="http://localhost:8080/api/product/search/"+id;
		
		SearchProductResponse searchProductResponse = RestUtil.getRequest(url, SearchProductResponse.class);
		
		if(pidtxt.getText()!=null)
		{
			if(searchProductResponse.getResponseCode().equals("0000"))
			{
				pnametxt.setText(searchProductResponse.getProduct().getName());
				pidtxt.setText(String.valueOf(searchProductResponse.getProduct().getProductId()));
				ppricetxt.setText(String.valueOf(searchProductResponse.getProduct().getPrice()));
				pquantitytxt.setText(String.valueOf(searchProductResponse.getProduct().getQuantity()));
				pidtxt.setDisable(true);
			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.WARNING);
			    alert.setTitle("Product Not Available");
			    alert.setHeaderText(null); // No header text
			    alert.setContentText(searchProductResponse.getResponseMessage()+" code= "+searchProductResponse.getResponseCode());
			    alert.showAndWait();
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
	
	
	
	public void updatebtnClick() throws Exception
	{
		UpdateProductResponse response=null;
		
		if(pnametxt.getText()!=null && pidtxt.getText()!=null && ppricetxt.getText()!=null && pquantitytxt.getText()!=null)
		{
			
			int pid=Integer.parseInt(pidtxt.getText());
		
			String url="http://localhost:8080/api/product/update";
			
			UpdateProductRequest request = new UpdateProductRequest();
			
			request.setName(pnametxt.getText());
			request.setPrice(Integer.parseInt(ppricetxt.getText()));
			request.setQuantity(Integer.parseInt(pquantitytxt.getText()));
			request.setProductId(pid);
			
			response = RestUtil.postRequest(url, request, UpdateProductResponse.class);
			
			if(response.getResponseCode().equals("0000"))
			{
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
			    alert.setTitle("Operation Successful");
			    alert.setHeaderText(null); 
			    alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
			    alert.showAndWait();
			    
			    pnametxt.setText(response.getRequest().getName());
			    pidtxt.setText(String.valueOf(response.getRequest().getProductId()));
			    ppricetxt.setText(String.valueOf(response.getRequest().getPrice()));
			    pquantitytxt.setText(String.valueOf(response.getRequest().getQuantity()));
			}
			else
			{
				Alert alert = new Alert(Alert.AlertType.WARNING);
			    alert.setTitle("Product Not Available");
			    alert.setHeaderText(null); // No header text
			    alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
			    alert.showAndWait();
				pidtxt.setText("");
			}
		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.WARNING);
		    alert.setTitle("Missing Details");
		    alert.setHeaderText(null); // No header text
		    alert.setContentText(response.getResponseMessage()+" with code "+response.getResponseCode());
		    alert.showAndWait();
		}
	}
}
