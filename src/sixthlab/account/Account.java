package sixthlab.account;

import sixthlab.exceptions.NotEnoughMoneyOnAccountException;

import java.util.concurrent.locks.Lock;

abstract public class Account {
  protected final int id;
  protected int amountOfMoney;

  public Account(int id, int amountOfMoney) {
    if (amountOfMoney < 0) {
      throw new IllegalArgumentException("");
    }
    this.id = id;
    this.amountOfMoney = amountOfMoney;
  }

  public int getId() {
    return id;
  }

  public int getAmountOfMoney() {
    return amountOfMoney;
  }

  abstract public boolean isEnoughMoneyForTransaction(int moneyForTransaction);

  abstract public void makeTransacion(int moneyForTransaction) throws NotEnoughMoneyOnAccountException;

  @Override
  public String toString() {
    return String.format("id: %d, amount: %d", id, amountOfMoney);
  }

  public Lock getLock() {
    throw new UnsupportedOperationException();
  }
}
