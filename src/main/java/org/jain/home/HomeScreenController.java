package org.jain.home;

import org.jain.login.LoginScreen;
import org.jain.product.ProductScreen;
import org.jain.user.UserScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeScreenController {
	//binding
	@FXML
	Button userbtn;
	
	@FXML
	Button backbtn;
	
	@FXML
	Button productbtn;
	
	public void userbtnClick()
	{
		//calling show() of UserScreen(Usermanagment) which contains code of stage setting for UI of add/update/delete/search for user
		new UserScreen().show();
	}
	
	public void backbtnClick()
	{
		new LoginScreen().show();
	}
	
	
	public void productbtnClick()
	{
		//calling showProductScreen() of ProductScreen(Productmanagment) which contains code of stage setting for UI of 
		//add/update/delete/search for product
		new ProductScreen().show();
	}
	
}
