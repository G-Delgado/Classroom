package ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	
	private Classroom classroom;
	
	// Main pane
	
	@FXML
	private Pane mainPane;
	
	// Login controls
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private TextField passwordField;
	
	@FXML
	private Label warningLabel;
	
	// Register controls
	
	@FXML
	private TextField createUsernameText;
	
	@FXML
	private TextField createPasswordText;
	
	@FXML
	private Button browseBtn;
	
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
	
	@FXML
	private ComboBox<String> favBrowser;
	
	// List controls
	
	@FXML
	private Label usernameLabel;
	
	@FXML
	private ImageView userImage;
	
	@FXML
	private TableView<UserAccount> accountsTable;
	
	@FXML
	private TableColumn<UserAccount, String> tcUsername;
	
	@FXML
	private TableColumn<UserAccount, String> tcGender;
	
	@FXML
	private TableColumn<UserAccount, String> tcCareer;
	
	@FXML
	private TableColumn<UserAccount, String> tcBirthday;
	
	@FXML
	private TableColumn<UserAccount, String> tcBrowser;
	
	// Constructor
	
	public ClassroomGUI(Classroom cr) {
		classroom = cr;
	}
	
	@FXML
	public void loadLogin() throws IOException { // CAMBIAR EL TAMAÑO DEL WINDOW AL VOLVER A RECARGAR ESTA STAGE
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlLoader.setController(this);
		Parent login = fxmlLoader.load();
		
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(login);
		
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setHeight(400);
		stage.setWidth(544);
	}
	
	@FXML
	public void login(ActionEvent event) throws IOException { // Validate the alert when it is wrong
		String username = usernameField.getText();
		String password = passwordField.getText();
		String photoPath = "";
		boolean found = false;
		
		ArrayList<UserAccount> studentList = classroom.getAccounts(); // This probably should not go in here, or not the type we need
		
		if (studentList.size() == 0) {
			System.out.println("Mano, cho tonto, nisiquiera hay cuentas creadas");
		} else {
			for (int i = 0; i < studentList.size() && !found; i++) {
				if (username.equals(studentList.get(i).getUsername()) && password.equals(studentList.get(i).getPassword())) {
					System.out.println("LLEGUE AQUI LOCO!!");
					photoPath = studentList.get(i).getProfilePhotoUrl();
					found = true;
				}
			}
			
			if (found) {
				loadList(username, photoPath);
			} else {
				warningLabel.setText("Datos incorrectos");
			}
		}
	
	}
	
	public void createAccount(ActionEvent event) throws IOException {
		if (createUsernameText.getText().equals("") || createPasswordText.getText().equals("") || profileUrl.getText().equals("") || datePicker.getValue() == null || (!softCheck.isSelected() && !indCheck.isSelected() && !telCheck.isSelected()) || (!maleBtn.isSelected() && !femaleBtn.isSelected() && otherBtn.isSelected()) || favBrowser.getValue() == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Validation Error");
			alert.setContentText("You must fill each field in the form");
			alert.showAndWait();
		} else {
			String usernameText = createUsernameText.getText();
			String passwordText = createPasswordText.getText();
			String profilePath = profileUrl.getText();
			System.out.println(datePicker.getValue());
			String date = datePicker.getValue() + "";
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
			String gender = "";
			if (maleBtn.isSelected()) {
				gender = "Male";
			} else if (femaleBtn.isSelected()) {
				gender = "Female";
			} else if (otherBtn.isSelected()) {
				gender = "Other";
			}
			gender = gender.replaceAll(" ", "").toUpperCase();
			String favoriteBrowser = favBrowser.getValue();
			favoriteBrowser = favoriteBrowser.replaceAll(" ", "").toUpperCase();
			
			classroom.addStudent(usernameText, passwordText, profilePath, gender, careers, date, favoriteBrowser);
			loadList(usernameText, profilePath);
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Account created");
			alert.setContentText("The new account has been created");
			alert.showAndWait();
		}

		
	
	}
	
	public void searchFile(ActionEvent event) {
		String path = "";
		FileChooser fileChooser = new FileChooser();
		Stage stage = (Stage) mainPane.getScene().getWindow();
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			path = file.getPath();
			//openFile(file);
		}
		profileUrl.setText(path);
		System.out.println(path);
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
		final ToggleGroup group = new ToggleGroup();
		maleBtn.setToggleGroup(group);
		femaleBtn.setToggleGroup(group);
		otherBtn.setToggleGroup(group);
		ObservableList<String> options = FXCollections.observableArrayList(
				"Firefox",
				"Chrome",
				"Edge",
				"Safari",
				"Opera",
				"Tor",
				"Brave"
				);
		favBrowser.setItems(options);

	}
	
	@FXML
	public void initializeTableView() {
		ObservableList<UserAccount> accounts;
		accounts = FXCollections.observableArrayList(classroom.getAccounts());
		
		accountsTable.setItems(accounts);
		tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("Username")); // The tableView searches for a method called getName
		tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("gender")); // The tableView searches for a method called getName
		tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("careers"));
		tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("birthday"));
		tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("browser"));
	}
	
	public void loadList(String username, String photoPath) throws IOException { // CAMBIAR EL TAMAÑO DEL WINDOW AL VOLVER A RECARGAR ESTE STAGE
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
		fxmlLoader.setController(this);
		Parent register = fxmlLoader.load();
		mainPane.getChildren().setAll(register);
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setHeight(500);
		stage.setWidth(625);
		
		File file = new File(photoPath);
		usernameLabel.setText(username);
		userImage.setImage(new Image(file.toURI().toString()));
		initializeTableView();
		
	}
	
}
