package lab7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainWindow extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Parent root = null;
    try {
      root = FXMLLoader.load(new File("src/lab7/fxmlfiles/MainWindow.fxml").toURL());
      primaryStage.setTitle("MainWindow");
      primaryStage.setScene(new Scene(root, 700, 400));
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


