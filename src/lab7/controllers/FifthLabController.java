package lab7.controllers;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FifthLabController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TextField inputFileField;

  @FXML
  private TextArea propertiesTextArea;

  @FXML
  private Button getPropertiesButton;

  @FXML
  void onGetPropertiesClicked(ActionEvent event) {
    File file = new File(inputFileField.getText());
    if (file.exists() && file.isFile()) {
      try (BufferedReader in = new BufferedReader(new FileReader(inputFileField.getText()))) {
        Properties gettingProperties = new Properties();
        gettingProperties.load(in);
        PrintWriter out = new PrintWriter(new FileWriter("properties.txt"));
        gettingProperties.list(out);
        out.close();
        BufferedReader inProp = new BufferedReader(new FileReader("properties.txt"));
        inProp.readLine();
        inProp.lines().forEach(line -> {
          propertiesTextArea.appendText(line);
          propertiesTextArea.appendText("\n");
        });
        inputFileField.clear();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("File not found");
      alert.setContentText("Sent: " + inputFileField.getText());
      alert.showAndWait();
    }
  }

  @FXML
  void initialize() {

  }
}
