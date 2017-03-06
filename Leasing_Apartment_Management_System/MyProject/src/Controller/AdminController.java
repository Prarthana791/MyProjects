package Controller;

import java.io.IOException;

import DAO.AdminApartmentDAO;
import Model.Apartment;
import Model.Rent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AdminController {
	@FXML
	private ComboBox cb;
	@FXML
	private TextField bldgno;
	@FXML
	private TextField aptno;
	@FXML
	private TextField rent;
	@FXML
	private TextField charge1;
	@FXML
	private TextField charge2;
	@FXML
	private TextField charge3;
	@FXML
	private RadioButton bhk1;
	@FXML
	private RadioButton bhk2;
	@FXML
	private RadioButton bhk3;
	@FXML
	private CheckBox month6;
	@FXML
	private CheckBox month12;
	@FXML
	private CheckBox month18;
	@FXML
	private ToggleGroup tg;
	String cb1, cb2, cb3, room;
	Double rate1, rate2, rate3;
	int i;
	int flag = 0;

	public RadioButton rb;
	private Stage primaryStage = new Stage();

	public void setDialogStage(Stage dialogStage) {
		this.primaryStage = dialogStage;
	}

	ObservableList<String> items = FXCollections.observableArrayList("1 BHK", "2 BHK", "3 BHK");

	public void addApt() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/AddApartment.fxml"));
		AdminController a = loader.<AdminController> getController();
		try {

			AnchorPane root = (AnchorPane) loader.load();
			System.out.println(items);

			UserController.abpane.setCenter(root);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void Submit() {

		String bldg = this.bldgno.getText();
		String apt = this.aptno.getText();
		AdminApartmentDAO apd = new AdminApartmentDAO();
		if (month6.isSelected()) {
			cb1 = month6.getText();
			rate1 = Double.parseDouble(charge1.getText());

		}
		if (month12.isSelected()) {
			cb2 = month12.getText();
			rate2 = Double.parseDouble(charge2.getText());

		}
		if (month18.isSelected()) {
			cb3 = month18.getText();
			rate3 = Double.parseDouble(charge3.getText());

		}
		if (tg.getSelectedToggle() == bhk1) {
			room = bhk1.getText();
		}
		if (tg.getSelectedToggle() == bhk2) {
			room = bhk2.getText();
		}
		if (tg.getSelectedToggle() == bhk3) {
			room = bhk3.getText();
		}
		// room = rb.getText();
		System.out.println("room is" + room);
		String rent1 = this.rent.getText();
		if (rent1 == null || rent1.trim().equals("") || room == null || room.trim().equals("") || bldg == null
				|| bldg.trim().equals("") || apt == null || apt.trim().equals("")) {
			flag = 1;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please enter/select all required details: " + "\n" + "Apartment No." + "\n"
					+ "Building No." + "\n" + "Rooms" + "\n" + "Rent");
			alert.showAndWait();
		}
		if (flag == 0) {
			Apartment apa = new Apartment();
			apa.setBuilding_no(bldg);
			apa.setApt_no(apt);
			apa.setRooms(room);
			apa.setBase_rent(rent1);

			apd.create(apa);
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Apartment ID:");
			a.setContentText("Apartment is created. Its Id is " + apa.getApt_id().toString());
			a.showAndWait();
			Rent r = new Rent();
			// for(i=0;i<3;i++)
			// {
			if (month6.isSelected()) {
				System.out.println("checkbox 1 is" + cb1);
				r.setAptid(apa.getApt_id());
				r.setLeaseperiod(cb1);
				r.setUtilities(rate1);
				r.setTotal(apa.calculateRent(Double.parseDouble(apa.getBase_rent()), rate1));

				apd.Rent(r);
			}
			if (month12.isSelected()) {

				r.setAptid(apa.getApt_id());
				r.setLeaseperiod(cb2);
				r.setUtilities(rate2);
				r.setTotal(apa.calculateRent(Double.parseDouble(apa.getBase_rent()), rate2));
				apd.Rent(r);
			}
			if (month18.isSelected()) {
				r.setAptid(apa.getApt_id());
				r.setLeaseperiod(cb3);
				r.setUtilities(rate3);
				r.setTotal(apa.calculateRent(Double.parseDouble(apa.getBase_rent()), rate3));
				apd.Rent(r);
			}
		}
	}

	// }

	public void cb1check() {

		charge1.setDisable(false);
		charge1.setEditable(true);
	}

	public void cb2check() {
		charge2.setDisable(false);
		charge2.setEditable(true);
	}

	public void cb3check() {
		charge3.setDisable(false);
		charge3.setEditable(true);
	}

	public void logOutAdmin() {

		close();
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/Login.fxml"));

		try {
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			setDialogStage(primaryStage);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void viewProfile() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/AdminProfile.fxml"));
		try {

			AnchorPane root = (AnchorPane) loader.load();
			AdminProfileController adm = loader.<AdminProfileController> getController();
			adm.show();

			UserController.abpane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void viewApt() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/UpdateApartment.fxml"));
		try {

			AnchorPane root = (AnchorPane) loader.load();
			ApartmentUpdate adm = loader.<ApartmentUpdate> getController();
			adm.initialView();

			UserController.abpane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void close() {
		primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

}
