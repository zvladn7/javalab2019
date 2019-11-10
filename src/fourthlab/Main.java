package fourthlab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) {
    FileSystemManager fsv = new FileSystemManager();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String command;
      while (!("exit".equals(command = br.readLine()))) {
        System.out.println(command);
        fsv.apply(command);
      }
    } catch (IOException ex) {
      System.err.println("Smth's going wrong. Let's try to rerun app!\n");
    }
  }
}
