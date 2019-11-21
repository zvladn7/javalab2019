package fourthlab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) {
    FileSystemManager fsm = new FileSystemManager();
    System.out.println("To show commands write: help");
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
