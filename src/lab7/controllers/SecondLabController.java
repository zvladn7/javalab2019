package lab7.controllers;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import secondlab.Main;
import secondlab.animals.Animal;
import secondlab.animals.Herbivorous;
import secondlab.animals.Omnivore;
import secondlab.animals.Predator;

public class SecondLabController {

  @FXML
  private TableView<Animal> booksTable;

  @FXML
  private TableColumn<Animal, String> columnID;

  @FXML
  private TableColumn<Animal, String> columnName;

  @FXML
  private TableColumn<Animal, String> columnFoodType;

  @FXML
  private TableColumn<Animal, Integer> columnAmount;

  @FXML
  private TextField textFieldID;

  @FXML
  private TextField textFieldName;

  @FXML
  private TextField textFieldFoodType;

  @FXML
  private TextField textFieldAmount;

  @FXML
  private TextField textFieldInputFile;

  @FXML
  private TextField textFieldOutputFile;

  @FXML
  private TextArea resultsTextArea;

  @FXML
  private RadioButton radioHerbivorous;

  @FXML
  private RadioButton radioOmnovore;

  @FXML
  private RadioButton radioPredator;

  ArrayList<Animal> listOfAnimals = new ArrayList<>();

  @FXML
  void onAddButtonCliked(ActionEvent event) {
    Animal newRow = getByCheck();
    if (newRow == null) {
      return;
    }
    listOfAnimals.add(newRow);
    booksTable.getItems().add(newRow);
    clearFields();
  }

  @FXML
  void onRemoveButtonCliked(ActionEvent event) {
    Animal forRemoveAnimal = getByCheck();
    if (forRemoveAnimal == null) {
      return;
    }

    if (listOfAnimals.remove(forRemoveAnimal)) {
      booksTable.getItems().remove(forRemoveAnimal);
      clearFields();
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Remove Error");
      alert.setContentText("Sent: " + forRemoveAnimal.toString());
      alert.showAndWait();
    }
  }

  @FXML
  void onClearButtonCliked(ActionEvent event) {
    for (Animal animal : listOfAnimals) {
      booksTable.getItems().remove(animal);
    }
    listOfAnimals.clear();
    resultsTextArea.setText("");
  }

  @FXML
  void onFirstButtonCliked(ActionEvent event) {
    Main.sort(listOfAnimals);
    StringBuilder sb = new StringBuilder();
    listOfAnimals.stream().filter(x -> listOfAnimals.indexOf(x) < 5).forEach(x -> {
      sb.append(x).append("\n");
    });
    resultsTextArea.setText(sb.toString());
  }

  @FXML
  void onLastButtonCliked(ActionEvent event) {
    Main.sort(listOfAnimals);
    StringBuilder sb = new StringBuilder();
    listOfAnimals.stream().filter(x -> listOfAnimals.indexOf(x) > listOfAnimals.size() - 4).forEach(x -> {
      sb.append(x).append("\n");
    });
    resultsTextArea.setText(sb.toString());
  }

  @FXML
  void onReadButtonCliked(ActionEvent event) {
    String file = textFieldInputFile.getText();
    onClearButtonCliked(event);
    Main.read(listOfAnimals, file);
    booksTable.getItems().addAll(listOfAnimals);
  }

  @FXML
  void onWriteButtonCliked(ActionEvent event) {
    String file = textFieldOutputFile.getText();
    Main.write(listOfAnimals, file);
  }

  ToggleGroup group = new ToggleGroup();

  @FXML
  void initialize() {
    columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    columnFoodType.setCellValueFactory(new PropertyValueFactory<>("foodType"));
    columnAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    radioHerbivorous.setToggleGroup(group);
    radioHerbivorous.setSelected(true);
    radioOmnovore.setToggleGroup(group);
    radioPredator.setToggleGroup(group);
  }

  private boolean isCorrectID(String ID) {
    return ID.matches("[0-9]{1,8}");
  }

  private boolean isCorrectName(String name) {
    return name.matches("^[A-Z]([A-Za-z]*)");
  }

  private boolean isCorrectFoodType(String author) {
    return author.matches("([ A-Za-z]*)");
  }

  private boolean isCorrectAmount(String year) {
    return year.matches("[0-9]+");
  }

  private void clearFields() {
    textFieldFoodType.clear();
    textFieldAmount.clear();
    textFieldName.clear();
    textFieldID.clear();
  }


  private Animal getByCheck() {
    String ID = textFieldID.getText();
    if (!isCorrectID(ID)) {
      textFieldID.clear();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("ID of animal is invalid");
      alert.setContentText("Sent: " + ID);
      alert.showAndWait();
      return null;
    }

    String name = textFieldName.getText();
    if (!isCorrectName(name)) {
      textFieldName.clear();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Name of animal is invalid");
      alert.setContentText("Sent: " + name);
      alert.showAndWait();
      return null;
    }

    String foodType = textFieldFoodType.getText();
    if (!isCorrectFoodType(foodType)) {
      textFieldFoodType.clear();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Food type is invalid");
      alert.setContentText("Sent: " + foodType);
      alert.showAndWait();
      return null;
    }

    String amount = textFieldAmount.getText();
    if (!isCorrectAmount(amount)) {
      textFieldAmount.clear();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Amount of food is invalid");
      alert.setContentText("Sent: " + amount);
      alert.showAndWait();
      return null;
    }
    return getAnimal(getChosenAnimal(), ID, name, foodType, Integer.valueOf(amount));
  }

  private Animal getAnimal(String className, String ID, String name, String foodType, Integer amount) {
    if ("Herbivorous".equals(className)) {
      System.out.println(name);
      return new Herbivorous(ID, name, new Animal.Food(foodType, amount));
    }
    if ("Omnivore".equals(className)) {
      return new Omnivore(ID, name, new Animal.Food(foodType, amount));
    }
    if ("Predator".equals(className)) {
      return new Predator(ID, name, new Animal.Food(foodType, amount));
    }
    return null;
  }

  private String getChosenAnimal() {
    RadioButton radio = (RadioButton) group.getSelectedToggle();
    return radio.getText();
  }
}
