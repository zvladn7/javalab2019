package sixthlab.transaction;

import sixthlab.account.Account;
import sixthlab.exceptions.NotEnoughMoneyOnAccountException;

import java.io.PrintWriter;

public class TransactionConcurrent extends Transaction {

  public TransactionConcurrent(int id, int amountOfMoney, Account sender, Account reciever, PrintWriter out) {
    super(id, amountOfMoney, sender, reciever, out);
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
          transaction();
        } catch (NotEnoughMoneyOnAccountException ex) {
          out.println(String.format("The transaction %d from: %d to: %d isn't completed. Not enough money!", amountOfMoney, sender.getId(), reciever.getId()));
        } finally {
          sender.getLock().unlock();
          reciever.getLock().unlock();
        }
      }

      isCompleted = true;
  }
}
