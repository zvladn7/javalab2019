package sixthlab.transaction;

import sixthlab.account.Account;

abstract public class Transaction {
  protected final int id;
  protected final int amountOfMoney;
  protected final Account sender;
  protected final Account reciever;
  protected boolean isCompleted;

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

  abstract public void makeTransaction();

  public boolean isCompleted()
  {
    return isCompleted;
  }

  protected void transaction() {
    sender.makeTransacion(-amountOfMoney);
    reciever.makeTransacion(amountOfMoney);
    System.out.println(String.format("The transaction %d from: %d to: %d is completed", amountOfMoney, sender.getId(), reciever.getId()));
  }
}
