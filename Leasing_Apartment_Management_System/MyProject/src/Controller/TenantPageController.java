package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.xml.soap.Node;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import DAO.TenantPageDAO;
import DAO.UserDAO;
import Model.CreateUser;
import Model.Tenant;

public class TenantPageController {
	public Stage primaryStage;
	public Stage dialogStage = new Stage();

	TenantPageController c;
	@FXML
	private TextField showname;
	@FXML
	private TextField showid;
	@FXML
	private TextField showemail;
	@FXML
	private TextField showph;
	@FXML
	private TextField showpassport;
	@FXML
	private Button Update;
	@FXML
	private Button Save;
	@FXML
	private Menu menudata;

	UserController uc;

	int i;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void doUpdate() throws IOException {

		showname.setEditable(true);
		System.out.println("in update");
		showemail.setEditable(true);
		showph.setEditable(true);
		showpassport.setEditable(true);
		Save.setDisable(false);
		Update.setDisable(true);

	}

	public void doSave() {
		int flag = 0;
		if (this.showname.getText() == null || this.showname.getText().trim().equals("")
				|| this.showemail.getText() == null || this.showemail.getText().trim().equals("")) {
			flag = 1;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please enter details in all required fields i.e" + "\n" + "1.Name" + "\n"
					+ "2.Email id");
			alert.showAndWait();
		}
		if (flag == 0) {
			try {
				Double contact = Double.parseDouble(this.showph.getText());
				UserDAO.profile.setName(this.showname.getText());

				UserDAO.profile.setEmail(this.showemail.getText());
				UserDAO.profile.setContact(Double.parseDouble(this.showph.getText()));
				UserDAO.profile.setPassport(this.showpassport.getText());
				Update.setDisable(false);

				UserDAO u = new UserDAO();
				u.Update(UserDAO.profile);
			} catch (NumberFormatException n) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Enter a valid contact number");
				alert.showAndWait();

			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("Information updated");
			alert.showAndWait();
		}
		

	}

	public void initial1() {
		System.out.println(UserDAO.profile.getName());
		showname.setText(UserDAO.profile.getName());

		showid.setText(Integer.toString(UserDAO.profile.getId()));

		showemail.setText(UserDAO.profile.getEmail());
		showph.setText(Double.toString(UserDAO.profile.getContact()));
		showpassport.setText(UserDAO.profile.getPassport());
		System.out.println("hi");

	}

	private void close() {
		dialogStage.fireEvent(new WindowEvent(dialogStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

	// String showname = this.showname.getText();

}
