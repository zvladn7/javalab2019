package sixthlab.account;

import sixthlab.exceptions.NotEnoughMoneyOnAccountException;

public class AccountSynchronized extends Account{

  public AccountSynchronized(int id, int amountOfMoney) {
    super(id, amountOfMoney);
  }

  public int getId() {
    return id;
  }

  public int getAmountOfMoney() {
    return amountOfMoney;
  }

  @Override
  public boolean isEnoughMoneyForTransaction(int moneyForTransaction) {
    return amountOfMoney + moneyForTransaction >= 0;
  }

  @Override
  public void makeTransacion(int moneyForTransaction) throws NotEnoughMoneyOnAccountException {
    if (isEnoughMoneyForTransaction(moneyForTransaction)) {
      this.amountOfMoney += moneyForTransaction;
    } else {
      throw new NotEnoughMoneyOnAccountException("There's not enough money to make transaction!");
    }
  }
}
