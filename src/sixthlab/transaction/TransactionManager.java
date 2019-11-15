package sixthlab.transaction;

import sixthlab.Account;
import sixthlab.exceptions.NotEnoughMoneyOnAccountException;
import sixthlab.parser.TransactionParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
  private List<Transaction> list;
  private List<Account> accounts;

  public TransactionManager(List<Transaction> list, List<Account> accounts) {
    this.list = list;
    this.accounts = accounts;
  }

  public TransactionManager(String filename, List<Account> accounts) {
    this.accounts = accounts;
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String nextTransaction;
      list = new ArrayList<>();
      while ((nextTransaction = br.readLine()) != null) {
        list.add(TransactionParser.parse(nextTransaction, accounts));
      }
    } catch (FileNotFoundException ex) {
      System.err.println("The file sent to the construct wasn't found!");
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void makeTransaction(Object lock) {
    for (int i = 0; i < list.size(); ++i) {
      final int toThread = i;
      try {
        new Thread(() -> {
          list.get(toThread).makeTransaction(lock);
        }).start();
      } catch (NotEnoughMoneyOnAccountException ex) {
        System.err.println("not enough money!");
      }
    }
  }

  public void print() {
    for (Account nextAccount : accounts) {
      System.out.println(nextAccount);
    }
  }

}
