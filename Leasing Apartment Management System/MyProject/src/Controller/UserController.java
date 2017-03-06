package Controller;

import java.io.IOException;

import Model.CreateUser;
import Model.Tenant;
import DAO.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class UserController {
	public Stage primaryStage2 = new Stage();
	public Stage primaryStage1 = new Stage();
	public Stage s=new Stage();
	Scene scene;
	private GridPane gp;
	String user_id;
	String passport;
	String status;
	static BorderPane bpane;
	public static BorderPane abpane;
	// This is the Text box element in the view for name of user
	@FXML
	private TextField name;

	@FXML
	private PasswordField password;
	@FXML
	private TextField emailid;
	@FXML
	private TextField contact;
	@FXML
	private TextField joining_dt;
	@FXML
	private TextField userID;
	public int userid, i;
	public String id;
	public Double contact1;

	// Method to set the parent stage of the current view
	public void setDialogStage(Stage dialogStage) {
		this.primaryStage2 = dialogStage;

	}

	public void setDialogStage1(Stage dialogStage) {
		this.primaryStage1 = dialogStage;
	}

	public Stage getDialogStage() {
		return this.primaryStage2;

	}

	// Method to submit the form to database
	public void submit() {
		// Extract the data from the view elements
int flag=0;
		String name = this.name.getText();
		String password = this.password.getText();
		String email = this.emailid.getText();

		// Validate the data
		if (name == null || name.trim().equals("") || password == null || password.trim().equals("") || email == null
				|| email.trim().equals("")) {
			flag=1;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please enter details in all fields");
			alert.showAndWait();
		}
if(flag==0)
		{try {
			contact1 = Double.parseDouble(this.contact.getText());

			// Create the model object

			Tenant user = new Tenant();

			// Set the values from the input form
			user.setName(name);
			user.setPassword(password);
			user.setEmail(email);
			user.setContact(contact1);

			// Create a DAO instance of the model
			UserDAO u = new UserDAO();
			// Use the DAO to persist the model to database
			u.create(user);
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Your User ID:");
			a.setContentText("Your User ID is " + user.getId().toString() + " Please use this ID for login");
			a.showAndWait();
			close(primaryStage2);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
		} catch (NumberFormatException n) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please enter an integer value for Contact Number");
			alert.showAndWait();
		} catch (NullPointerException n) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Your User ID:");
			a.setContentText("One or many fields are blank. Sorry can't create your account.");
			a.showAndWait();

		}
		}
	}

	public void user() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CreateTenant.fxml"));
		try {
			System.out.println(loader.getLocation());
			AnchorPane root = (AnchorPane) loader.load();
			// Set window title

			primaryStage2.setTitle("Create User");
			// Create a scene with the inflated view
			Scene scene = new Scene(root);
			// Set the scene to the stage
			primaryStage2.setScene(scene);
			//
			primaryStage2.show();
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
			e.printStackTrace();
		}

	}

	public void dologin() {
int flag=0;
		try {
			id = this.userID.getText();
			userid = Integer.parseInt(id);
		
	/*	catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please enter an integer value for User Id.");
			alert.showAndWait();
		}*/
		String password = this.password.getText();
		System.out.println(userid);
		System.out.println(password);

		if (id == null || id.trim().equals("")||password == null || password.trim().equals("")) {

			flag=1;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please enter user ID and password both");
			alert.showAndWait();

		}
	if(flag==0)	
	{
		CreateUser tenant = new CreateUser();
		tenant.setId(userid);
		tenant.setPassword(password);
		UserDAO tenant_dao = new UserDAO();

		i = tenant_dao.login(tenant);
		System.out.println("controller i is" + i);

		if (i == 1) {
			close(primaryStage2);

			System.out.println("hi");
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/TenantPage_New.fxml"));
			try {

				bpane = (BorderPane) loader.load();
				//primaryStage2 = new Stage();
				// Set window title
				s.setTitle("Welcome User");

				// Create a scene with the inflated view
				scene = new Scene(bpane);
				// Set the scene to the stage
				s.setScene(scene);
				setDialogStage(s);
				//System.out.println("stage is" + primaryStage2);
				//
				s.show();
			} catch (Exception e) {
				System.out.println("Error occured while inflating view: " + e);
				e.printStackTrace();
			}
		}

		// close();
		else if (i == 2) {
			close(primaryStage2);
			System.out.println("hi");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AdminPage.fxml"));
			try {
				// System.out.println(loader.getLocation());
				abpane = (BorderPane) loader.load();
				// Set window title
				primaryStage1 = new Stage();
				primaryStage1.setTitle("Welcome Admin");
				// setDialogStage(primaryStage2);
				// primaryStage1.setTitle("Create User");
				// Create a scene with the inflated view
				Scene scene = new Scene(abpane);
				// Set the scene to the stage
				primaryStage1.setScene(scene);
				//
				primaryStage1.show();
			} catch (Exception e) {
				System.out.println("Error occured while inflating view: " + e);
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Wromg Credentials entered.Please try again.");
			alert.showAndWait();

		}}}
		catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please enter an integer value for User Id.");
			alert.showAndWait();}

	}

	public void viewProfile() {
		System.out.println("after stage is" + getDialogStage());
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TenantProfile.fxml"));

		try {
			AnchorPane root = (AnchorPane) loader.load();

			TenantPageController tpc = loader.<TenantPageController> getController();

			tpc.initial1();

			bpane.setCenter(root);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void viewApt() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ApartmentView.fxml"));
		try {
			AnchorPane root = (AnchorPane) loader.load();

			ApartmentBook tpc = loader.<ApartmentBook> getController();

			tpc.initialView();
			bpane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void logOut() {
		close(s);
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/Login.fxml"));
		try {
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			Stage loginstage = new Stage();
			loginstage.setScene(scene);
			loginstage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateProfile() {

	}

	public void bookApt() {
	}

	private void close(Stage s) {
		System.out.println("in close");
		s.fireEvent(new WindowEvent(s, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

}
