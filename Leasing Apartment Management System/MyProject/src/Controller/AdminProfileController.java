package Controller;

import DAO.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AdminProfileController {
	@FXML
	private TextField showid;
	@FXML
	private TextField showname;
	@FXML
	private TextField showemail;
	@FXML
	private TextField showph;

	@FXML
	private Button Save;
	@FXML
	private Button Update;

	public void show() {
		System.out.println(UserDAO.admin.getName());
		showname.setText(UserDAO.admin.getName());

		showid.setText(Integer.toString(UserDAO.admin.getId()));

		showemail.setText(UserDAO.admin.getEmail());
		showph.setText(Double.toString(UserDAO.admin.getContact()));
	}

	public void doUpdate() {
		showname.setEditable(true);
		System.out.println("in update");
		showemail.setEditable(true);
		showph.setEditable(true);
		Save.setDisable(false);
		Update.setDisable(true);

	}

	public void doSave() {
		int flag=0;
		UserDAO.admin.setName(this.showname.getText());

		UserDAO.admin.setEmail(this.showemail.getText());
		if(this.showname.getText()==null||this.showname.getText().trim().equals("")||
				this.showemail.getText()==null||this.showemail.getText().trim().equals(""))
		{
		flag=1;
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setContentText("Please enter details in all required fields i.e" + "\n" + "1.Name" + "\n"
				+ "2.Email id");
		alert.showAndWait();
		}
		try{
		UserDAO.admin.setContact(Double.parseDouble(this.showph.getText()));
		}
		catch(NumberFormatException n)
		{
			flag=1;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please enter a valid contact number");
			alert.showAndWait();
			
		}

		Update.setDisable(false);
if(flag==0)
{	UserDAO u = new UserDAO();
		u.UpdateAdmin(UserDAO.admin);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setContentText("Record Updated");
		alert.showAndWait();

	}

}}
