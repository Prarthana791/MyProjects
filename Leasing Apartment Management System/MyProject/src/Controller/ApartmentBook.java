package Controller;

import java.time.LocalDate;

import DAO.ApartmentBookDAO;
import DAO.ApartmentDAO;
import Model.Apartment;
import Model.Lease;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApartmentBook {
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
	private TextField bookbldg;
	@FXML
	private TextField bookapt;
	@FXML
	private TextField bookpass;
	@FXML
	private RadioButton booklp1;
	@FXML
	private RadioButton booklp2;
	@FXML
	private RadioButton booklp3;
	@FXML
	private Button book;
	@FXML
	private DatePicker bookdt;
	@FXML
	private ToggleGroup booktg;
	String booklp;
	int flag = 0;
	int flag1 = 0;

	@FXML
	private void initialize() {
		table.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showApartmentDetails(newValue));
	}

	public void showApartmentDetails(Apartment a) {
		bookbldg.setText(a.getBuilding_no());
		bookapt.setText(a.getApt_no());
	}

	public void initialView() {
		// TODO Auto-generated method stub
		ApartmentDAO ad = new ApartmentDAO();
		ObservableList<Apartment> apt = ad.viewApt();

		table.setItems(apt);

	}

	public void Book() {
		String bldgno = this.bookbldg.getText();
		String aptno = this.bookapt.getText();
		LocalDate dt = this.bookdt.getValue();
		String pp = this.bookpass.getText();
		if (booktg.getSelectedToggle() == booklp1) {
			booklp = booklp1.getText();
		}
		if (booktg.getSelectedToggle() == booklp2) {
			booklp = booklp2.getText();
		}
		if (booktg.getSelectedToggle() == booklp3) {
			booklp = booklp3.getText();
		}
		if (pp == null || pp.trim().equals("") || dt == null || dt.toString().trim().equals("") || booklp == null
				|| booklp.trim().equals("")) {
			flag = 1;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please enter/select all details");
			alert.showAndWait();
		} else
			flag = 0;
		if (pp.length() > 10) {
			flag1 = 1;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please enter a valid 10 digit passport number");
			alert.showAndWait();
		} else
			flag1 = 0;
		if (flag == 0 && flag1 == 0) {
			Lease l = new Lease();
			l.setMoveInDate(dt);
			l.setLeasePeriod(booklp);
			l.setBldgNo(bldgno);
			l.setAptNo(aptno);
			l.setPassport(pp);
			System.out.println("booking bldg no is " + bldgno);
			System.out.println("booking apt no is " + aptno);
			System.out.println("booking date is " + dt);
			System.out.println("booking lp is " + booklp);
			ApartmentBookDAO a = new ApartmentBookDAO();
			a.bookApartment(l);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("Thank you for booking apartment. Your booking ID is " + l.getLeaseId());
			alert.showAndWait();

		}
	}

}
