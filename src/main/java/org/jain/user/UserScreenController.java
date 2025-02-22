package org.jain.user;

import org.jain.home.HomeScreen;
import org.jain.user.add.AddUserScreen;
import org.jain.user.delete.DeleteUserScreen;
import org.jain.user.search.SearchUserScreen;
import org.jain.user.update.UpdateUserScreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserScreenController {
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
	
	
	public void addbtnClick()
	{
		new AddUserScreen().show();
	}
	
	public void updatebtnClick()
	{
		new UpdateUserScreen().show();
	}
	
	public void deletebtnClick()
	{
		new DeleteUserScreen().show();
	}
	
	public void searchbtnClick()
	{
		new SearchUserScreen().show();
	}
	public void backbtnClick()
	{
		new HomeScreen().show();
	}
}
