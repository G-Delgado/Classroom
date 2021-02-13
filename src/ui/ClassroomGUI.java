package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Classroom;

public class ClassroomGUI {
	
	private Classroom classroom;
	
	@FXML
	private Pane mainPane;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private TextField passwordField;
	
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
	public void login(ActionEvent event) {
		String userName = "";
	}
	
	@FXML
	public void loadRegister(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
		fxmlLoader.setController(this);
		Parent register = fxmlLoader.load();
		mainPane.getChildren().setAll(register);

	}
}
