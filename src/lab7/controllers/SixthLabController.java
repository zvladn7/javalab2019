package lab7.controllers;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import sixthlab.Main;
import sixthlab.account.Account;
import sixthlab.account.AccountConcurrent;
import sixthlab.account.AccountSynchronized;
import sixthlab.transactionGenerator.TransactionGenerator;

public class SixthLabController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private TextArea transactionsTextArea;

  @FXML
  private Button runSynchronizedButton;

  @FXML
  private Button runConcurrentButton;

  private List<Account> listSynchronized = new ArrayList<>();
  private List<Account> listConcurrent = new ArrayList<>();


  @FXML
  void onRunConcurrentClicked(ActionEvent event) {
    try (PrintWriter out = new PrintWriter("out6.txt")) {
      Main.startWorkingProcess(listConcurrent, false, out);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    printTransactions();
  }

  @FXML
  void onRunSynchronizedClicked(ActionEvent event) {
    try (PrintWriter out = new PrintWriter("out6.txt")) {
      Main.startWorkingProcess(listSynchronized, true, out);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    printTransactions();
  }

  void printTransactions() {
    try {
      new BufferedReader(new FileReader("out6.txt")).lines().forEach(line -> {
        transactionsTextArea.appendText(line);
        transactionsTextArea.appendText("\n");
      });
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void initialize() {
    Random random = new Random();
    int amount = random.nextInt(30);
    new TransactionGenerator(amount).generate("./src/sixthlab/transactions");
    for (int i = 0; i < amount; ++i) {
      int nextAmount = random.nextInt(5000);
      listSynchronized.add(new AccountSynchronized(i + 1, nextAmount));
      listConcurrent.add(new AccountConcurrent(i + 1, nextAmount));
    }
  }
}
