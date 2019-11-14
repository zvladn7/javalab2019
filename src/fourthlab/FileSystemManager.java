package fourthlab;

import java.util.HashMap;
import java.util.function.Consumer;

public class FileSystemManager {
  private FileSystemViewer fsv = new FileSystemViewer("/");
  private HashMap<String, Consumer> commandMap = new HashMap<>();

  public FileSystemManager() {
    commandMap.put("cd", x -> fsv.changeDir((String) x));
    commandMap.put("ls", x -> fsv.showFiles());
    commandMap.put("touch", x -> fsv.createFile((String) x));
    commandMap.put("mkdir", x -> fsv.createDir((String) x));
    commandMap.put("rm", x -> fsv.delete((String) x));
    commandMap.put("cat", x -> fsv.showFile((String) x));
    commandMap.put("addfile", x -> fsv.addLinesToFile((String) x));
    commandMap.put("help", x -> helpCommand());
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

  public String getPath() {
    return fsv.getPath();
  }

  private String[] parseCommand(String command) {
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

  private void helpCommand() {
    System.out.println("cd \"dir\" - change directory");
    System.out.println("ls - show files in current directory");
    System.out.println("touch \"file\" - create file");
    System.out.println("mkdir \"dir\" - create directory");
    System.out.println("rm \"file\"/\"dir\" - remove file or directory");
    System.out.println("cat \"file\" - show file");
    System.out.println("addfile \"file\" - append to file");
  }
}
