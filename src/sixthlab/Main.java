package sixthlab;

import sixthlab.exceptions.NotEnoughMoneyOnAccountException;
import sixthlab.transaction.TransactionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.List;


public class Main {
  public static void main(String[] args) {
    List<Account> list = new ArrayList<>();
    System.out.println("Please write amount of account:");
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int amountOfAccount = Integer.parseInt(br.readLine());
      for (int i = 0; i < amountOfAccount; ++i) {
        System.out.println(String.format("Write %d money's amount: ", i + 1));
        list.add(new Account(i + 1, Integer.parseInt(br.readLine())));
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    TransactionManager tm = new TransactionManager("./src/sixthlab/transactions", list);
    tm.print();
    tm.makeTransaction(new NotEnoughMoneyOnAccountException());

    try {
      Thread.sleep(2000);
      tm.print();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
