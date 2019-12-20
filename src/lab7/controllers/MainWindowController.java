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
    setOnActionForButton(lab1_button, "src/lab7/fxmlfiles/FirstLab.fxml");
    setOnActionForButton(lab2_button, "src/lab7/fxmlfiles/SecondLab.fxml");
    setOnActionForButton(lab3_button, "src/lab7/fxmlfiles/ThirdLab.fxml");
    setOnActionForButton(lab4_button, "src/lab7/fxmlfiles/FourthLab.fxml");
    setOnActionForButton(lab5_button, "src/lab7/fxmlfiles/FifthLab.fxml");
    setOnActionForButton(lab6_button, "src/lab7/fxmlfiles/SixthLab.fxml");
  }

  void setOnActionForButton(Button button, String path) {
    button.setOnAction(actionEvent -> {

      FXMLLoader loader = new FXMLLoader();
      try {
        loader.setLocation(new File(path).toURL());
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
