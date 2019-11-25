package sixthlab;

import sixthlab.account.AccountSynchronized;
import sixthlab.transactionManager.TransactionManagerSynchronized;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
  public static void main(String[] args) {
    List<AccountSynchronized> list = new ArrayList<>();
    System.out.println("Please write amount of account:");
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int amountOfAccount = Integer.parseInt(br.readLine());
      for (int i = 0; i < amountOfAccount; ++i) {
        System.out.println(String.format("Write %d money's amount: ", i + 1));
        list.add(new AccountSynchronized(i + 1, Integer.parseInt(br.readLine())));
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    TransactionManagerSynchronized tm = new TransactionManagerSynchronized("./src/sixthlab/transactions", list);
    tm.print();
    tm.makeTransaction();
    tm.print();
  }
}
