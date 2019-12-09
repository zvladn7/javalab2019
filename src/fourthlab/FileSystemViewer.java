package fourthlab;

import java.io.*;

import java.util.List;

public class FileSystemViewer {
  private File current;
  private PrintWriter out;

  public FileSystemViewer(String name, PrintWriter out) {
    this.out = out;
    current = new File(name);
  }

  public void setOut(PrintWriter out) {
    this.out = out;
  }

  public void changeDir(String dir) {
    if (current.isDirectory()) {
      File nextDir;
      if (current.getPath().equals("/") && dir.startsWith(".."))
      {
        out.println("You are in the root directory!");
        return;
      }
      if (!"..".equals(dir)) {
        nextDir = new File(current.getPath() + '/' + dir);
      } else {
        String path = current.getPath();
        path = path.substring(0, path.lastIndexOf("/"));
        if (path.isEmpty()) {
          path = "/";
        }
        nextDir = new File(path);
      }
      if (nextDir.isDirectory() || ("..".equals(dir))) {
        current = nextDir;
      } else {
        out.println("You've just sent the wrong name of directory!");
      }
    }
  }

  public void showFiles() {
    if (current.isDirectory()) {
      File[] dirFiles = current.listFiles();
      if (dirFiles != null)
        List.of(dirFiles).forEach(out::println);
    }
  }

  public void createFile(String file) {
    if (current.isDirectory()) {
      File newFile = new File(current.getPath() + '/' + file);
      if (!newFile.exists()){
        try {
          newFile.createNewFile();
          out.println("The file: " + file + " was successfully create!");
        } catch (IOException ex) {
          out.println("The error was happened while the file was created! Try again!");
        }
      } else {
        out.println("The file is already placed in this directory!");

      }
    }
  }

  public boolean createDir(String dir) {
    boolean returnedValue = false;
    if (current.isDirectory()) {
      File newFile = new File(current.getPath() + '/' + dir);
      if (!newFile.exists()){
        returnedValue = newFile.mkdir();
        out.println("The dir: " + dir + " was successfully create!");
      } else {
        out.println("The dir is already placed in this directory!");
      }
    }

    return returnedValue;
  }

  public boolean delete(String file) {
    boolean returnedValue = false;
    if (current.isDirectory()) {
      File fileToDelete = new File(current.getPath() + '/' + file);
      if (fileToDelete.exists()) {
        returnedValue = fileToDelete.delete();
        out.println("The file: " + file + " deleted successfully!");
      } else {
        out.println("You tried to deleted not existed file or directory!");
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
            out.println(nextLine);
          }
        }  catch (IOException ex) {
          out.println("The error was happened while file was reading!");
        }
      } else {
        out.println("The file isn't existed!");
      }
    }
  }

  public void addLinesToFile(String file) {
    if (current.isDirectory()) {
      File fileToAdd = new File(current.getPath() + '/' + file);
      if (fileToAdd.exists() && fileToAdd.isFile()) {
        String nextLine;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToAdd, true))) {
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          while (!"!squit".equals(nextLine = br.readLine())) {
            bw.write(nextLine);
            bw.write("\n");
          }
          out.println("Successful write to file!");
        } catch (IOException ex) {
          out.println("The error was happened while file was adding lines!");
        }
      }
    }
  }

  public String getPath()throws IOException {
    return current.getCanonicalPath();
  }
}


