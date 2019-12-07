package lab7.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import thirdlab.UndoStringBuilder;

public class ThirdLabController {

  @FXML
  private TextField currentLine;

  @FXML
  private TextField appendingLine;

  @FXML
  private TextField startIndexForDelete;

  @FXML
  private TextField endIndexForDelete;

  @FXML
  private TextField startIndexForReplace;

  @FXML
  private TextField endIndexForReplace;

  @FXML
  private TextField lineForReplace;

  @FXML
  private TextField insertOffset;

  @FXML
  private TextField insertLine;

  private UndoStringBuilder usb;

  @FXML
  void onAppendButtonClicked(ActionEvent event) {
    usb.append(appendingLine.getText());
    currentLine.setText(usb.toString());
    clearAll();
  }

  @FXML
  void onClearButtonClicked(ActionEvent event) {
    usb = new UndoStringBuilder();
    currentLine.clear();
    clearAll();
  }

  @FXML
  void onDeleteButtonClicked(ActionEvent event) {
    try {
      usb.delete(Integer.parseInt(startIndexForDelete.getText()), Integer.parseInt(endIndexForDelete.getText()));
      currentLine.setText(usb.toString());
      clearAll();
    } catch (RuntimeException ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Delete Error");
      alert.setContentText("Indexes are invalid");
      alert.showAndWait();
    }
  }

  @FXML
  void onInsertButtonClicked(ActionEvent event) {
    try {
      usb.insert(Integer.parseInt(insertOffset.getText()), insertLine.getText());
      currentLine.setText(usb.toString());
      clearAll();
    } catch (RuntimeException ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Insert Error");
      alert.setContentText("Offset are invalid");
      alert.showAndWait();
    }
  }

  @FXML
  void onReplaceButtonClicked(ActionEvent event) {
    try {
      usb.replace(Integer.parseInt(startIndexForReplace.getText()), Integer.parseInt(endIndexForReplace.getText()),
              lineForReplace.getText());
      currentLine.setText(usb.toString());
      clearAll();
    } catch (RuntimeException ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Replace Error");
      alert.setContentText("Indexes are invalid");
      alert.showAndWait();
    }
  }

  @FXML
  void onReverseButtonClicked(ActionEvent event) {
    usb.reverse();
    currentLine.setText(usb.toString());
    clearAll();
  }

  @FXML
  void onUndoButtonClicked(ActionEvent event) {
    try {
      usb.undo();
      currentLine.setText(usb.toString());
      clearAll();
    } catch (RuntimeException ex) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Undo Error");
      alert.setContentText("There's no undo operations");
      alert.showAndWait();
    }
  }

  @FXML
  void initialize() {
     usb = new UndoStringBuilder();
  }

  void clearAll() {
    startIndexForReplace.clear();
    startIndexForDelete.clear();
    endIndexForReplace.clear();
    endIndexForDelete.clear();
    insertLine.clear();
    insertOffset.clear();
    lineForReplace.clear();
    appendingLine.clear();
  }
}

