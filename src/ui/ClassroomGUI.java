package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	
	private Classroom classroom;
	
	// Main pane fxml
	
	@FXML
	private Pane mainPane;
	
	// Login controls
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private TextField passwordField;
	
	// Register controls
	
	@FXML
	private TextField createUsernameText;
	
	@FXML
	private TextField createPasswordText;
	
	@FXML
	private TextField profileUrl;
	
	@FXML
	private RadioButton maleBtn;
	
	@FXML
	private RadioButton femaleBtn;
	
	@FXML
	private RadioButton otherBtn;
	
	@FXML
	private CheckBox softCheck;
	
	@FXML
	private CheckBox telCheck;
	
	@FXML
	private CheckBox indCheck;
	
	@FXML
	private DatePicker datePicker;
	
	// List controls
	
	public ClassroomGUI(Classroom cr) {
		classroom = cr;
	}
	
	@FXML
	public void loadLogin() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlLoader.setController(this);
		Parent login = fxmlLoader.load();
		
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(login);
	}
	
	@FXML
	public void login(ActionEvent event) throws IOException {
		String userName = usernameField.getText();
		String password = passwordField.getText();
		boolean found = false;
		
		ArrayList<UserAccount> studentList = classroom.getClassroom(); // This probably shouldnt go in here, or not the type we need
		
		if (studentList.size() == 0) {
			System.out.println("Mano, cho tonto, nisiquiera hay cuentas creadas");
		} else {
			for (int i = 0; i < studentList.size() && !found; i++) {
				if (userName.equals(studentList.get(i).getUserName()) && password.equals(studentList.get(i).getPassword())) {
					System.out.println("LLEGUE AQUI LOCO!!");
					found = true;
				}
			}
			
			if (found) {
				loadList();
			} else {
				// Validar datos erroneos
				// Validar Strings vacios
				System.out.println("No tienes los datos que son, cagón!");
				// Tambien validar si no hay estudiantes aun
			}
		}
	
	}
	
	public void createAccount(ActionEvent event) throws IOException {
		// Nada de lo que está abajo debería hacerse al momento de darle al botón, si no al cargar el fxml
		String usernameText = createUsernameText.getText();
		String passwordText = createPasswordText.getText();
		System.out.println(datePicker.getValue()); // Hay que validar cuando sea NULL ------- asasas sasasa/////////
		String date = datePicker.getValue() + "";
		System.out.println("La fecha es: " + date);
		final ToggleGroup group = new ToggleGroup();
		maleBtn.setToggleGroup(group);
		femaleBtn.setToggleGroup(group);
		otherBtn.setToggleGroup(group);
		ArrayList<String> careers = new ArrayList<String>();
		if (softCheck.isSelected()) {
			careers.add("Software Engineering");
		}
		if (telCheck.isSelected()) {
			careers.add("Telematic Engineering");
		}
		if (indCheck.isSelected()) {
			careers.add("Industrial Engineering");
		}
		System.out.println(careers);
		System.out.println("Cuenta creada exitosamente!");
		loadList();
	
	}
	
	@FXML
	public void loadRegister(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
		fxmlLoader.setController(this);
		Parent register = fxmlLoader.load();
		mainPane.getChildren().setAll(register);
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setHeight(500);
		stage.setWidth(582);
		// ----------------------- Preparing Stuff -----------------------

	}
	
	@FXML
	public void loadList() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
		fxmlLoader.setController(this);
		Parent register = fxmlLoader.load();
		mainPane.getChildren().setAll(register);
	}
}
