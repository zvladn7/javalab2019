package sixthlab.account;

import sixthlab.exceptions.NotEnoughMoneyOnAccountException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountConcurrent extends Account {
  private ReentrantLock lock = new ReentrantLock();

  public AccountConcurrent(int id, int amountOfMoney) {
    super(id, amountOfMoney);
  }

  @Override
  public boolean isEnoughMoneyForTransaction(int moneyForTransaction) {
    lock.lock();
    try {
      return amountOfMoney + moneyForTransaction >= 0;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void makeTransacion(int moneyForTransaction) throws NotEnoughMoneyOnAccountException {
    lock.lock();
    try {
      if (isEnoughMoneyForTransaction(moneyForTransaction)) {
        this.amountOfMoney += moneyForTransaction;
      } else {
        throw new NotEnoughMoneyOnAccountException("There's not enough money to make transaction!");
      }
    } finally {
      lock.unlock();
    }
  }

  public Lock getLock() {
    return lock;
  }
}
