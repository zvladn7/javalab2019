package fifthlab;

import java.io.*;
import java.util.Properties;

public class Main {
  public static void main(String[] args) {
    try (PrintWriter out = new PrintWriter(new FileWriter("test.properties"))) {
      Properties propertiesForStore = new Properties();
      propertiesForStore.setProperty("email", "mymail@gmail.ru");
      propertiesForStore.setProperty("address", "SPB");
      propertiesForStore.setProperty("job", "programmer");
      propertiesForStore.setProperty("station", "Polytechnicheskay");
      propertiesForStore.store(out, null);
    } catch(IOException ex) {
      ex.printStackTrace();
    }


    try (BufferedReader in = new BufferedReader(new FileReader("test.properties"))) {
      Properties gettingProperties = new Properties();
      gettingProperties.load(in);
      gettingProperties.list(System.out);
    } catch(IOException ex) {
      ex.printStackTrace();
    }
  }
}
