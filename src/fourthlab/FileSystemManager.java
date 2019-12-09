package fourthlab;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.function.Consumer;

public class FileSystemManager {
  private FileSystemViewer fsv;
  private HashMap<String, Consumer> commandMap = new HashMap<>();
  private PrintWriter out;
  private boolean isFile;
  private String filename;

  public FileSystemManager(PrintWriter out, boolean isFile, String filename) {
    this.out = out;
    this.isFile = isFile;
    this.filename = filename;
    fsv = new FileSystemViewer("/", out);
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
    if (isFile) {
      try {
        out = new PrintWriter(new FileWriter(filename));
        fsv.setOut(out);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    String[] strings = parseCommand(command);
    String operation = strings[0];
    String parameter = strings[1];
    if (!commandMap.containsKey(operation)) {
      out.println("The wrong format of command: " + operation + " " + parameter);
    } else {
      commandMap.get(operation).accept(parameter);
      out.flush();
    }
    if (isFile) {
      out.close();
    }
  }

  public String getPath() throws IOException {
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
    out.println("cd \"dir\" - change directory");
    out.println("ls - show files in current directory");
    out.println("touch \"file\" - create file");
    out.println("mkdir \"dir\" - create directory");
    out.println("rm \"file\"/\"dir\" - remove file or directory");
    out.println("cat \"file\" - show file");
    out.println("addfile \"file\" - append to file");
  }
}
