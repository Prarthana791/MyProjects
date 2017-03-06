package Controller;

import DAO.AdminApartmentDAO;
import DAO.ApartmentDAO;
import DAO.UpdateApartmentDAO;
import DAO.UserDAO;
import Model.Apartment;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ApartmentUpdate {
	@FXML
	TableView<Apartment> table;
	@FXML
	TableColumn<Apartment, Integer> bldg;
	@FXML
	TableColumn<Apartment, Integer> apt;
	@FXML
	TableColumn<Apartment, String> room;
	@FXML
	TableColumn<Apartment, Double> rent;
	@FXML
	TableColumn<Apartment, String> no;
	@FXML
	TableColumn<Apartment, String> status;
	@FXML
	private TextField aptno;
	@FXML
	private TextField bldgno;
	@FXML
	private TextField roomt;
	@FXML
	private TextField rentt;
	@FXML
	private TextField statust;
	@FXML
	private Button save;
	@FXML
	private Button update;
	@FXML
	private Button delete;

	@FXML
	private void initialize() {
		table.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showApartmentDetails(newValue));
	}

	public void initialView() {
		// TODO Auto-generated method stub
		UpdateApartmentDAO ad = new UpdateApartmentDAO();
		ObservableList<Apartment> apt = ad.viewApt();
		table.setItems(apt);

	}

	public void showApartmentDetails(Apartment a) {
		aptno.setText(a.getApt_no());
		bldgno.setText(a.getBuilding_no());
		roomt.setText(a.getRooms());
		rentt.setText(a.getBase_rent());
		statust.setText(a.getAvailability_status());
	}

	public void Delete() {
		Apartment ap = table.getSelectionModel().getSelectedItem();
		AdminApartmentDAO aad = new AdminApartmentDAO();
		aad.Delete(ap);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText("Apartment Deleted");
		alert.showAndWait();

	}

	public void Update() {
		aptno.setEditable(true);
		bldgno.setEditable(true);
		roomt.setEditable(true);
		rentt.setEditable(true);
		statust.setEditable(true);
		save.setDisable(false);
		update.setDisable(true);

	}

	public void Save() {
		int flag=0;
		String apt = this.aptno.getText();
		String bldg = this.bldgno.getText();
		String rm = this.roomt.getText();
		String rent = this.rentt.getText();
		String status = this.statust.getText();
		if(apt == null || apt.trim().equals("")||bldg == null || bldg.trim().equals("")||rm == null || rm.trim().equals("")
			||rent == null || rent.trim().equals("")||status == null || status.trim().equals(""))
			{
				flag=1;
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setContentText("Please enter details in all fields");
				alert.showAndWait();
			}
		if(flag==0)
		{
		Apartment ap = table.getSelectionModel().getSelectedItem();
		System.out.println("selected apt id is" + ap.getApt_id());
		ap.setApt_no(apt);
		ap.setBuilding_no(bldg);
		ap.setRooms(rm);
		ap.setBase_rent(rent);
		ap.setAvailability_status(status);
		System.out.println("updated room is" + ap.getRooms());
		AdminApartmentDAO aad = new AdminApartmentDAO();
		aad.Update(ap);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setContentText("Apartment updated");
		alert.showAndWait();

	}}
}
