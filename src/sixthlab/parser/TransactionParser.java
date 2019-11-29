package sixthlab.parser;

import sixthlab.account.Account;
import sixthlab.transaction.Transaction;
import sixthlab.transaction.TransactionSynchronized;
import sixthlab.transaction.TransactionConcurrent;

import java.util.List;

public class TransactionParser {
  private static int id = 0;

  public static Transaction parse(String transaction, List<Account> accounts, boolean isSynchronized) {
    if (transaction.contains("from:") && transaction.contains("to:") && transaction.contains("amount:")) {
      int from = Integer.parseInt(transaction.substring(transaction.indexOf(' ') + 1, transaction.indexOf("to:") - 1));
      int to = Integer.parseInt(transaction.substring(transaction.indexOf(":", transaction.indexOf("to:")) + 2, transaction.indexOf("amount:") - 1));
      int amount = Integer.parseInt(transaction.substring(transaction.lastIndexOf(' ') + 1));
      if (isSynchronized)
        return new TransactionSynchronized(id++, amount, accounts.get(from - 1), accounts.get(to - 1));
      else
        return new TransactionConcurrent(id++, amount, accounts.get(from - 1), accounts.get(to - 1));
    } else {
      throw new IllegalArgumentException("You've sent invalid format of transaction to parser method!");
    }
  }
}
