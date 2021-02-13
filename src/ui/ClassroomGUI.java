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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
	
	public ClassroomGUI(Classroom cr) {
		classroom = cr;
	}
	
	/*
	 * 
	 * 
	 * 	HACER LOS ALERT Y UNA VIEW DE UN "ABOUT" ;)
	 * ADEMÁS TENGO QUE HACER QUE LAS PERSONAS LLENEN TODOS LOS CAMPOSSSS
	 * 
	 * 
	 * */
	
	@FXML
	public void loadLogin() throws IOException { // CAMBIAR EL TAMAÑO DEL WINDOW AL VOLVER A RECARGAR ESTA STAGE
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlLoader.setController(this);
		Parent login = fxmlLoader.load();
		
		mainPane.getChildren().clear();
		mainPane.getChildren().setAll(login);
	}
	
	@FXML
	public void login(ActionEvent event) throws IOException { // Validar los alert cuando se equivoque
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
		System.out.println(usernameText);
		String passwordText = createPasswordText.getText();
		System.out.println(passwordText);
		String profilePath = profileUrl.getText();
		System.out.println("El path de la foto de perfil es: " + profilePath);
		System.out.println(datePicker.getValue()); // Hay que validar cuando sea NULL ------- asasas sasasa/////////
		String date = datePicker.getValue() + "";
		System.out.println("La fecha es: " + date);
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
		String gender = "";
		if (maleBtn.isSelected()) {
			gender = "Male";
		} else if (femaleBtn.isSelected()) {
			gender = "Female";
		} else if (otherBtn.isSelected()) {
			gender = "Other";
		}
		gender = gender.replaceAll(" ", "").toUpperCase();
		System.out.println("Gender: " + gender);
		System.out.println("Cuenta creada exitosamente!");
		String favoriteBrowser = favBrowser.getValue();
		favoriteBrowser = favoriteBrowser.replaceAll(" ", "").toUpperCase();
		System.out.println(favBrowser.getValue());
		classroom.addStudent(usernameText, passwordText, profilePath, gender, careers, date, favoriteBrowser);
		// NECESITO VALIDAR LO QUE PASA CUANDO FALTA ALGUN DATO
		loadList();
		// Al loadList podemos pasarle dos parametros que son el nombre usuario y la foto de perfil.
		
	
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
	
	/*public void openFile(File file) { // Unnecessary --------------
		final Desktop desktop = Desktop.getDesktop();
		try {
            desktop.open(file);
            System.out.println(file.getName());
            System.out.println(file.getPath());
        } catch (IOException ex) {
            Logger.getLogger(
                FileChooser.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
	}*/
	
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
	public void loadList() throws IOException { // CAMBIAR EL TAMAÑO DEL WINDOW AL VOLVER A RECARGAR ESTE STAGE
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
		fxmlLoader.setController(this);
		Parent register = fxmlLoader.load();
		mainPane.getChildren().setAll(register);
	}
}
