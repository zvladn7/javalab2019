package fourthlab;

import java.util.HashMap;
import java.util.function.Consumer;

public class FileSystemManager {
  private FileSystemViewer fsv = new FileSystemViewer("/home/zvladn7");
  private HashMap<String, Consumer> commandMap = new HashMap<>();

  public FileSystemManager() {
    commandMap.put("cd", x -> fsv.changeDir((String) x));
    commandMap.put("ls", x -> fsv.showFiles());
    commandMap.put("touch", x -> fsv.createFile((String) x));
    commandMap.put("mkdir", x -> fsv.createDir((String) x));
    commandMap.put("rm", x -> fsv.delete((String) x));
    commandMap.put("cat", x -> fsv.showFile((String) x));
    commandMap.put("addfile", x -> fsv.addLinesToFile((String) x));
  }

  public void apply(String command) {
    String[] strings = parseCommand(command);
    String operation = strings[0];
    String parameter = strings[1];
    if (!commandMap.containsKey(operation)) {
      System.err.println("The wrong format of command: " + operation + " " + parameter);
    } else {
      commandMap.get(operation).accept(parameter);
    }
  }

  private String[] parseCommand(String command) throws IllegalArgumentException {
    String[] strings = new String[2];
    int start = 0;
    int spaceIndex = command.indexOf(" ");
    if (spaceIndex == -1) {
      spaceIndex = command.length();
    }
    for (int i = 0; i < 2; ++i) {
      strings[i] = command.substring(start, spaceIndex);
      start = spaceIndex + 1;
      if (start >= command.length()) {
        start = 0;
      }
      spaceIndex = command.length();
    }

    return strings;
  }
}
