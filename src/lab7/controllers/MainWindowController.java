package lab7.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainWindowController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button lab1_button;

  @FXML
  private Button lab4_button;

  @FXML
  private Button lab2_button;

  @FXML
  private Button lab5_button;

  @FXML
  private Button lab3_button;

  @FXML
  private Button lab6_button;

  @FXML
  void initialize() {
    lab1_button.setOnAction(actionEvent -> {
      lab1_button.getScene().getWindow().hide();

      FXMLLoader loader = new FXMLLoader();
      try {
        loader.setLocation(new File("src/lab7/fxmlfiles/FirstLab.fxml").toURL());
      } catch (MalformedURLException e) {
        e.printStackTrace();
      }

      try {
        loader.load();
      } catch (IOException e) {
        e.printStackTrace();
      }

      Parent root = loader.getRoot();
      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.showAndWait();
    });
  }
}
