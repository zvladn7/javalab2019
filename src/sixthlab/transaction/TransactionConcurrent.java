package sixthlab.transaction;

import sixthlab.account.AccountConcurrent;
import sixthlab.account.AccountSynchronized;
import sixthlab.exceptions.NotEnoughMoneyOnAccountException;

public class TransactionConcurrent extends Transaction {

  public TransactionConcurrent(int id, int amountOfMoney, AccountConcurrent sender, AccountConcurrent reciever) {
    super(id, amountOfMoney, sender, reciever);
  }


    @Override
  public void makeTransaction() {
      if (sender.isEnoughMoneyForTransaction(amountOfMoney)) {
        while (true) {
          boolean lock1 = sender.getLock().tryLock();
          boolean lock2 = reciever.getLock().tryLock();

          if (lock1 && lock2) {
            break;
          }

          if (lock1) {
            sender.getLock().unlock();
          }

          if (lock2) {
            reciever.getLock().unlock();
          }
        }
        try {
          sender.makeTransacion(-amountOfMoney);
          reciever.makeTransacion(amountOfMoney);
          System.out.println(String.format("The transaction %d from: %d to: %d is completed", amountOfMoney, sender.getId(), reciever.getId()));
        } catch (NotEnoughMoneyOnAccountException ex) {
          System.err.println(String.format("The transaction %d from: %d to: %d isn't completed. Not enough money!", amountOfMoney, sender.getId(), reciever.getId()));
        }
      }

      isCompleted = true;
  }
}
