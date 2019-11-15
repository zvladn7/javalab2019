package sixthlab;

import sixthlab.exceptions.NotEnoughMoneyOnAccountException;

public class Account {
  private final int id;
  private int amountOfMoney;

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

  public boolean isEnoughMoneyForTransaction(int moneyForTransaction) {
    return amountOfMoney + moneyForTransaction >= 0;
  }

  public synchronized void makeTransacion(int moneyForTransaction) throws NotEnoughMoneyOnAccountException {
    if (isEnoughMoneyForTransaction(moneyForTransaction)) {
      this.amountOfMoney += moneyForTransaction;
    } else {
      throw new NotEnoughMoneyOnAccountException("There's not enough money to make transaction!");
    }
  }

  @Override
  public String toString() {
    return String.format("id: %d, amount: %d", id, amountOfMoney);
  }
}
