package lab7.controllers;

import fourthlab.FileSystemManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;

public class FourthLabController {

  @FXML
  private TextArea consoleField;

  private FileSystemManager fileSystemManager;

  @FXML
  void VK_ENTER(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) {
      String text = consoleField.getText().substring(0, consoleField.getText().length() - 1);
      String command = text.substring(text.lastIndexOf(':') + 1);
      fileSystemManager.apply(command);
      String result = "\n";
      try {
        FileReader fileReader = new FileReader("out4.txt");
        BufferedReader in = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String nextLine = "";
        while ((nextLine = in.readLine()) != null) {
          sb.append("     ").append(nextLine).append("\n");
        }
        fileReader.close();
        in.close();
        result = sb.toString();
        consoleField.appendText(result);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      try {
        System.out.println(fileSystemManager.getPath());
        consoleField.appendText(fileSystemManager.getPath() + "$:");
        new PrintWriter("out4.txt").close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      consoleField.appendText("");
//      try {
//        consoleField.appendText(fileSystemManager.getPath() + "$:");
//        //out.flush();
//        while (true) {
//          String text = consoleField.getText().substring(0, consoleField.getText().length() - 1);
//          String command = text.substring(text.indexOf(':') + 1);
//          fileSystemManager.apply(command);
//          consoleField.appendText(fileSystemManager.getPath() + "$:");
//          BufferedReader in = new BufferedReader(new FileReader("out4.txt"));
//          String next;
//          while ((next = in.readLine()) != null) {
//            consoleField.appendText(next);
//          }
//          in.close();
//          // out.flush();
//        }
//      } catch (IOException ex) {
//        System.err.println("Smth's going wrong. Let's try to rerun app!");
//      }
    }
  }

  @FXML
  void initialize() {
    try {
      fileSystemManager = new FileSystemManager(new PrintWriter(new FileWriter("out4.txt")), true, "out4.txt");
      consoleField.setText(fileSystemManager.getPath() + "$:");
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      Runtime.getRuntime().exec("java src/fourthLab/Main.java");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
