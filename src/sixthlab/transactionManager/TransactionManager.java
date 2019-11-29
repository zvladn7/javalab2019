package sixthlab.transactionManager;

import sixthlab.account.Account;
import sixthlab.exceptions.NotEnoughMoneyOnAccountException;
import sixthlab.parser.TransactionParser;
import sixthlab.transaction.Transaction;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
  protected List<Transaction> list;
  protected List<Account> accounts;

  public TransactionManager(String filename, List<Account> accounts, boolean isSynchronized) {
    this.accounts = accounts;
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String nextTransaction;
      list = new ArrayList<>();
      while ((nextTransaction = br.readLine()) != null) {
        list.add(TransactionParser.parse(nextTransaction, accounts, isSynchronized));
      }
    } catch (FileNotFoundException ex) {
      System.err.println("The file sent to the construct wasn't found!");
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void makeTransaction() {
    for (int i = 0; i < list.size(); ++i) {
      Thread next;
      final int toThread = i;
      try {
        next = new Thread(() -> list.get(toThread).makeTransaction());
        next.start();
        try {
          next.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
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
