package fourthlab;

import java.io.*;

public class Main {
  public static void main(String[] args) {
    PrintWriter out = null;
    try {
      out = new PrintWriter(new FileWriter("out4.txt"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    FileSystemManager fsm = new FileSystemManager(out, false, "");
    out.println("To show commands write: help");
    out.flush();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String command;
      System.out.print(fsm.getPath() + "$:");
      while (!("exit".equals(command = br.readLine()))) {
        fsm.apply(command);
        System.out.print(fsm.getPath() + "$:");
      }
    } catch (IOException ex) {
      System.err.println("Smth's going wrong. Let's try to rerun app!");
    }
  }
}
