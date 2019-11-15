package sixthlab.transaction;

import sixthlab.Account;
import sixthlab.exceptions.NotEnoughMoneyOnAccountException;

public class Transaction {
  private final int id;
  private final int amountOfMoney;
  private final Account sender;
  private final Account reciever;
  private boolean isCompleted;

  public Transaction(int id, int amountOfMoney, Account sender, Account reciever) {
    if (amountOfMoney < 0) {
      throw new IllegalArgumentException("The amount of money which was sent by transaction is less than 0. That's wrong!");
    }

    if (sender == null || reciever == null)
      throw new IllegalArgumentException("One of members of transaction isn't mentioned!");


    this.id = id;
    this.amountOfMoney = amountOfMoney;
    this.sender = sender;
    this.reciever = reciever;
    this.isCompleted = false;
  }

  public void makeTransaction(Object lock) {
    if (sender.isEnoughMoneyForTransaction(amountOfMoney)) {
      synchronized (lock) {
        try {
          sender.makeTransacion(-amountOfMoney);
          reciever.makeTransacion(amountOfMoney);
          System.out.println(String.format("The transaction %d from: %d to: %d is completed", amountOfMoney, sender.getId(), reciever.getId()));
        } catch (NotEnoughMoneyOnAccountException ex) {
          System.err.println(String.format("The transaction %d from: %d to: %d isn't completed. Not enough money!", amountOfMoney, sender.getId(), reciever.getId()));
        }
      }
    }

    isCompleted = true;
  }

  public boolean isCompleted()
  {
    return isCompleted;
  }
}
