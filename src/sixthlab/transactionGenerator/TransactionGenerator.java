package sixthlab.transactionGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TransactionGenerator {
  private final int count;
  private Random random = new Random();

  public TransactionGenerator(int count) {
    this.count = count;
  }

  //generate lines like this: "from: 3 to: 4 amount: 50"
  public void generate(String filename) {
    try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
      StringBuilder sb = new StringBuilder();
      int countOfTrasaction = random.nextInt(30) + 1;
      for (int i = 0; i < countOfTrasaction; ++i) {
        int from = random.nextInt(count) + 1;
        int to = random.nextInt(count) + 1;
        if (to == from)
          if (to != count)
            ++to;
          else
            --to;
        int amount = (random.nextInt(200) + random.nextInt(300)) / 2 + 1;
        sb.append("from: ").append(from).append(" to: ").append(to).append(" amount: ").append(amount);
        out.println(sb.toString());
        sb = new StringBuilder();
      }
    } catch (IOException ex) {
      System.err.println("The error was happend while the transaction file was generating!");
      ex.printStackTrace();
    }
  }
}
