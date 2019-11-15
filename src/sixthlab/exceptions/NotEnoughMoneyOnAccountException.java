package sixthlab.exceptions;

public class NotEnoughMoneyOnAccountException extends RuntimeException {
  public NotEnoughMoneyOnAccountException() {
  }

  public NotEnoughMoneyOnAccountException(String message) {
    super(message);
  }

  public NotEnoughMoneyOnAccountException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotEnoughMoneyOnAccountException(Throwable cause) {
    super(cause);
  }

  public NotEnoughMoneyOnAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
