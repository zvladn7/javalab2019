package fifthlab;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
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


    PropertiesReader propertiesReader = new PropertiesReader("test.properties");
    System.out.println("by map:");
    Map<String, String> map = propertiesReader.asMap();
    map.forEach((key, value) -> System.out.println("key: " + key + " value: " + value));

    System.out.println("by function:");
    try (PrintWriter out = new PrintWriter(System.out)){
      propertiesReader.printList(out);

      out.println("get value by key:");
      out.println("address: " + propertiesReader.getValue("address"));
    }
  }
}
