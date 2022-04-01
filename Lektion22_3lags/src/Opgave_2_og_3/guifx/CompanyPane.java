package Opgave_2_og_3.guifx;

import Opgave_2_og_3.application.controller.Controller;
import Opgave_2_og_3.application.model.Company;
import Opgave_2_og_3.application.model.Customer;
import Opgave_2_og_3.application.model.Employee;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Optional;

public class CompanyPane extends GridPane {
	private TextField txfName, txfHours;
	private TextArea txaEmps, txaCustomers;
	private ListView<Company> lvwCompanies;
	private ListView<Customer> lvwCustomers = new ListView<>(); //lvwCompanies har de sat 'new' til længere nede.

	public CompanyPane() {
		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(10);
		this.setGridLinesVisible(false);

		Label lblComp = new Label("Companies");
		this.add(lblComp, 0, 0);


		//nedenstående har jeg ikke lavet, men det er bare for at give en listview ude i venstre side af skærmen.
		lvwCompanies = new ListView<>();
		this.add(lvwCompanies, 0, 1, 1, 3);
		lvwCompanies.setPrefWidth(200);
		lvwCompanies.setPrefHeight(200);
		lvwCompanies.getItems().setAll(Controller.getCompanies());

		ChangeListener<Company> listener = (ov, oldCompny, newCompany) -> this.selectedCompanyChanged();
		lvwCompanies.getSelectionModel().selectedItemProperty().addListener(listener);

		Label lblName = new Label("Name:");
		this.add(lblName, 1, 1);

		txfName = new TextField();
		this.add(txfName, 2, 1);
		txfName.setEditable(false);

		Label lblHours = new Label("Weekly Hours:");
		this.add(lblHours, 1, 2);

		txfHours = new TextField();
		this.add(txfHours, 2, 2);
		txfHours.setEditable(false);

		Label lblEmps = new Label("Employees:");
		this.add(lblEmps, 1, 3);
		GridPane.setValignment(lblEmps, VPos.BASELINE);
		lblEmps.setPadding(new Insets(4, 0, 4, 0));

		txaEmps = new TextArea();
		this.add(txaEmps, 2, 3);
		txaEmps.setPrefWidth(200);
		txaEmps.setPrefHeight(100);
		txaEmps.setEditable(false);

		Label lblCustomers = new Label("Customers:");
		this.add(lblCustomers,1,4);
		GridPane.setValignment(lblCustomers,VPos.BASELINE);


		txaCustomers = new TextArea();
		this.add(txaCustomers, 2,4);
		txaCustomers.setPrefWidth(200);
		txaCustomers.setPrefHeight(100);
		txaCustomers.setEditable(false);


		HBox hbxButtons = new HBox(40);
		this.add(hbxButtons, 0, 5, 3, 1);
		hbxButtons.setPadding(new Insets(10, 0, 0, 0));
		hbxButtons.setAlignment(Pos.BASELINE_CENTER);

		Button btnCreateCompany = new Button("Create Company");
		hbxButtons.getChildren().add(btnCreateCompany);
		btnCreateCompany.setOnAction(event -> this.createAction());

		Button btnUpdate = new Button("Update");
		hbxButtons.getChildren().add(btnUpdate);
		btnUpdate.setOnAction(event -> this.updateAction());

		Button btnDelete = new Button("Delete");
		hbxButtons.getChildren().add(btnDelete);
		btnDelete.setOnAction(event -> this.deleteAction());

		Button btnCreateCustomer = new Button("Create Customer"); // laver en ny knap
		hbxButtons.getChildren().add(btnCreateCustomer);
		btnCreateCustomer.setOnAction(event -> this.createCustomerAction());

		if (lvwCompanies.getItems().size() > 0) {
			lvwCompanies.getSelectionModel().select(0);
		}
	}




// -----------------------laver en createCustomerAction hvor jeg bare kopierer fra CreateAction endenfor
	private void createCustomerAction() {
		Company company = lvwCompanies.getSelectionModel().getSelectedItem(); // det her er dog kopieret fra UpdateAction, hvor den lægger mærke til, hvilken company jeg har selected, idet jeg trykker Create Customer.
		if (company != null) {

			CustomerWindow dia = new CustomerWindow("Create Customer", company);
			dia.showAndWait();
			// venter på dialogboksen lukker

			lvwCompanies.getItems().setAll(Controller.getCompanies());
			int index = lvwCompanies.getItems().size() - 1;
			lvwCompanies.getSelectionModel().select(index); // det her shit gør bare, at når man har indtastet noget, så klikker den væk, fordi så opdaterer den når man klikker der igen
		}
	}
	// -------------------------------------------------------------------------

	private void createAction() {
		CompanyWindow dia = new CompanyWindow("Create Company");
		dia.showAndWait();

		// Wait for the modal dialog to close

		lvwCompanies.getItems().setAll(Controller.getCompanies());
		int index = lvwCompanies.getItems().size() - 1;
		lvwCompanies.getSelectionModel().select(index);
	}

	private void updateAction() {
		Company company = lvwCompanies.getSelectionModel().getSelectedItem();
		if (company != null) {

			CompanyWindow dia = new CompanyWindow("Update Company",company);
			dia.showAndWait();

			// Wait for the modal dialog to close

			int selectIndex = lvwCompanies.getSelectionModel().getSelectedIndex();
			lvwCompanies.getItems().setAll(Controller.getCompanies());
			lvwCompanies.getSelectionModel().select(selectIndex);
		}
	}

	private void deleteAction() {
		Company company = lvwCompanies.getSelectionModel().getSelectedItem();
		if (company != null) {

			if (company.employeesCount() == 0) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Delete Company");
				// alert.setContentText("Are you sure?");
				alert.setHeaderText("Are you sure?");
				Optional<ButtonType> result = alert.showAndWait();
				if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
					Controller.deleteCompany(company);
					lvwCompanies.getItems().setAll(Controller.getCompanies());
					this.updateControls();
				}

			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Delete Company");
				alert.setHeaderText("Can't delete a company that has emlpoyees");
				// wait for the modal dialog to close
				alert.show();
			}
		}
	}

	// -------------------------------------------------------------------------

	private void selectedCompanyChanged() {
		this.updateControls();
	}

	public void updateControls() {
		Company company = lvwCompanies.getSelectionModel().getSelectedItem();
		Customer customer = lvwCustomers.getSelectionModel().getSelectedItem();
		if (company != null) {
			txfName.setText(company.getName());
			txfHours.setText("" + company.getHours());

			StringBuilder sb = new StringBuilder();
			for (Employee emp : company.getEmployees()) {
				sb.append(emp + "\n");
			}

			// kender ikker Stringbuilder, men gør det samme som de har gjort med Employees -------------------
			StringBuilder sb2 = new StringBuilder();
			for (Customer cust : company.getCustomers()){
				sb2.append(cust + "\n");
			}
			txaEmps.setText(sb.toString());
			txaCustomers.setText(sb2.toString());
		} else {
			txfName.clear();
			txfHours.clear();
			txaEmps.clear();
			txaCustomers.clear();
		}
	}

}
