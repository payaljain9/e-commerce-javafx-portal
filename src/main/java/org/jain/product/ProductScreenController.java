package org.jain.product;

import org.jain.home.HomeScreen;
import org.jain.product.add.AddProductScreen;
import org.jain.product.delete.DeleteProductScreen;
import org.jain.product.search.SearchProductScreen;
import org.jain.product.update.UpdateProductScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ProductScreenController {
	    //binding
		@FXML
		Button addbtn;
		
		@FXML
		Button updatebtn;
		
		@FXML
		Button deletebtn;
		
		@FXML
		Button searchbtn;
		
		@FXML
		Button backbtn;
		
		public void backbtnClick()
		{
			new HomeScreen().show();
		}
		
		public void addbtnClick()
		{
			new AddProductScreen().show();
		}
		
		public void updatebtnClick()
		{
			new UpdateProductScreen().show();
		}
		
		public void deletebtnClick()
		{
			new DeleteProductScreen().show();
		}
		
		public void searchbtnClick()
		{
			new SearchProductScreen().show();
		}
}
