package fourthlab;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.InputStreamReader;

import java.util.List;

public class FileSystemViewer {
  private File current;
  private String name;

  public FileSystemViewer(String name) {
    this.name = name;
    current = new File(name);
  }

  public void changeDir(String dir) {
    if (current.isDirectory()) {
      File nextDir = new File(current.getPath() + '/' + dir);
      if (List.of(current.listFiles()).contains(nextDir)) {
        current = nextDir;
      } else {
        System.err.println("You've just sent the wrong name of directory!\n");
      }
    }
  }

  public void showFiles() {
    if (current.isDirectory()) {
      File[] dirFiles = current.listFiles();
      if (dirFiles != null)
        List.of(dirFiles).forEach(System.out::println);
    }
  }

  public void createFile(String file) {
    if (current.isDirectory()) {
      File newFile = new File(current.getPath() + '/' + file);
      if (!newFile.exists()){
        try {
          newFile.createNewFile();
        } catch (IOException ex) {
          System.err.println("The error was happened while the file was created! Try again!\n");
        }
      }
    }
  }

  public boolean createDir(String dir) {
    boolean returnedValue = false;
    if (current.isDirectory()) {
      File newFile = new File(current.getPath() + '/' + dir);
      if (!newFile.exists()){
        returnedValue = newFile.mkdir();
      }
    }

    return returnedValue;
  }

  public boolean delete(String file) {
    boolean returnedValue = false;
    if (current.isDirectory()) {
      File fileToDelete = new File(current.getPath() + '/' + file);
      System.out.println(fileToDelete.getPath());
      if (fileToDelete.exists()) {
        returnedValue = fileToDelete.delete();
      }
    }

    return returnedValue;
  }

  public void showFile(String file) {
    if (current.isDirectory()) {
      File fileToShow = new File(current.getPath() + '/' + file);
      if (fileToShow.exists() && fileToShow.isFile()) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToShow))) {
          String nextLine;
          while ((nextLine = br.readLine()) != null) {
            System.out.println(nextLine);
          }
        }  catch (IOException ex) {
          System.err.println("The error was happened while file was reading!\n");
        }
      }
    }
  }

  public void addLinesToFile(String file) {
    if (current.isDirectory()) {
      File fileToAdd = new File(current.getPath() + '/' + file);
      if (fileToAdd.isFile()) {
        String nextLine;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToAdd, true))) {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          while (!"!squit".equals(nextLine = br.readLine())) {
            bw.write(nextLine);
            bw.write("\n");
          }
        } catch (IOException ex) {
          System.err.println("The error was happened while file was adding lines!\n");
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}


