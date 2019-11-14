package fifthlab;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
  private HashMap<String, String> propertiesMap = new HashMap<>();

  public PropertiesReader(String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      Properties properties = new Properties();
      properties.load(br);
      properties.forEach((key, value) -> propertiesMap.put(key.toString(), value.toString()));
    } catch (FileNotFoundException ex) {
      System.err.println("The file wasn't found!\n");
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void set(String key, String value) {
    propertiesMap.put(key, value);
  }

  public String getValue(String key) {
    return propertiesMap.get(key);
  }

  public void printList(PrintWriter out) {
    propertiesMap.forEach((key, value) -> out.println(key + "=" + value));
  }

  public void printList(PrintStream out) {
    propertiesMap.forEach((key, value) -> out.println(key + "=" + value));
  }

  public Map<String, String> asMap() {
    return propertiesMap;
  }
}
