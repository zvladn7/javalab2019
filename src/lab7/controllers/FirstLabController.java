package lab7.controllers;

import firstlab.Catalog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class FirstLabController {

  @FXML
  private TableView<Catalog.Row> booksTable;

  @FXML
  private TableColumn<Catalog.Row, String> columnID;

  @FXML
  private TableColumn<Catalog.Row, String> columnName;

  @FXML
  private TableColumn<Catalog.Row, String> columnAuthor;

  @FXML
  private TableColumn<Catalog.Row, String> columnYear;

  @FXML
  private TextField textFieldID;

  @FXML
  private TextField textFieldName;

  @FXML
  private TextField textFieldAuthor;

  @FXML
  private TextField textFieldYear;

  private Catalog catalog = new Catalog();

  @FXML
  void initialize() {
    columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
    columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
  }

  private boolean isCorrectID(String ID) {
    return ID.matches("[0-9]{1,8}");
  }

  private boolean isCorrectName(String name) {
    return name.matches("^[A-Z]([ 0-9A-Za-z]*)");
  }

  private boolean isCorrectAuthor(String author) {
    return author.matches("^[A-Z]([A-Za-z]*)");
  }

  private boolean isCorrectYear(String year) {
    return year.matches("[0-9]{1,4}") && Integer.parseInt(year) <= 2019;

  }

  public void onAddButtonCliked(ActionEvent actionEvent) {
    Catalog.Row newRow = getByCheck();
    if (newRow == null) {
      return;
    }
    catalog.add(newRow);
    booksTable.getItems().add(newRow);
    clearFields();
  }

  public void onRemoveButtonCliked(ActionEvent actionEvent) {
    Catalog.Row forRemoveRow = getByCheck();
    if (forRemoveRow == null) {
      return;
    }

    if (catalog.delete(forRemoveRow)) {
      booksTable.getItems().remove(forRemoveRow);
      clearFields();
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Remove Error");
      alert.setContentText("Sent: " + forRemoveRow.toString());
      alert.showAndWait();
    }
  }

  private void clearFields() {
    textFieldYear.clear();
    textFieldAuthor.clear();
    textFieldName.clear();
    textFieldID.clear();
  }


  private Catalog.Row getByCheck() {
    String ID = textFieldID.getText();
    if (!isCorrectID(ID)) {
      textFieldID.clear();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Book ID is invalid");
      alert.setContentText("Sent: " + ID);
      alert.showAndWait();
      return null;
    }

    String name = textFieldName.getText();
    if (!isCorrectName(name)) {
      textFieldName.clear();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Book name is invalid");
      alert.setContentText("Sent: " + name);
      alert.showAndWait();
      return null;
    }

    String author = textFieldAuthor.getText();
    if (!isCorrectAuthor(author)) {
      textFieldAuthor.clear();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Book author is invalid");
      alert.setContentText("Sent: " + author);
      alert.showAndWait();
      return null;
    }

    String year = textFieldYear.getText();
    if (!isCorrectYear(year)) {
      textFieldYear.clear();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Book year is invalid");
      alert.setContentText("Sent: " + year);
      alert.showAndWait();
      return null;
    }
    return new Catalog.Row(ID ,author, name, year);
  }
}
