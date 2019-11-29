package sixthlab;

import sixthlab.account.Account;
import sixthlab.account.AccountConcurrent;
import sixthlab.account.AccountSynchronized;
import sixthlab.transactionGenerator.TransactionGenerator;
import sixthlab.transactionManager.TransactionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Main {
  public static void main(String[] args) {
    List<Account> list = new ArrayList<>();
    int selector = 0;
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      System.out.println("Choose the impl, write 1 or 2:\n 1 - Synchronized\n 2 - Concurrent");
      selector = Integer.parseInt(br.readLine());
      System.out.println("Please write amount of account:");
      int amountOfAccount = Integer.parseInt(br.readLine());
      new TransactionGenerator(amountOfAccount).generate("./src/sixthlab/transactions");
      for (int i = 0; i < amountOfAccount; ++i) {
        System.out.println(String.format("Write %d money's amount: ", i + 1));
        if (selector == 1) {
          list.add(new AccountSynchronized(i + 1, Integer.parseInt(br.readLine())));
        } else if (selector == 2) {
          list.add(new AccountConcurrent(i + 1, Integer.parseInt(br.readLine())));
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    if (selector == 1) {
      startWorkingProcess(list, true);
    } else if (selector == 2) {
      startWorkingProcess(list, false);
    } else {
      System.err.println("You've just written invalid selector!");
    }
  }

  private static void startWorkingProcess(List<Account> list, boolean isSynchronized) {
    TransactionManager tm = new TransactionManager("./src/sixthlab/transactions", list, isSynchronized);
    tm.print();
    tm.makeTransaction();
    tm.print();
  }
}
