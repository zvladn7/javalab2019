package sixthlab.transaction;

import sixthlab.account.AccountSynchronized;
import sixthlab.exceptions.NotEnoughMoneyOnAccountException;

public class TransactionSynchronized extends Transaction {
  public TransactionSynchronized(int id, int amountOfMoney, AccountSynchronized sender, AccountSynchronized reciever) {
    super(id, amountOfMoney, sender, reciever);
  }

  @Override
  public void makeTransaction() {
    if (sender.isEnoughMoneyForTransaction(amountOfMoney)) {
      synchronized (reciever) {
        synchronized (sender) {
          try {
            sender.makeTransacion(-amountOfMoney);
            reciever.makeTransacion(amountOfMoney);
            System.out.println(String.format("The transaction %d from: %d to: %d is completed", amountOfMoney, sender.getId(), reciever.getId()));
          } catch (NotEnoughMoneyOnAccountException ex) {
            System.err.println(String.format("The transaction %d from: %d to: %d isn't completed. Not enough money!", amountOfMoney, sender.getId(), reciever.getId()));
          }
        }
      }
    }

    isCompleted = true;
  }
}
