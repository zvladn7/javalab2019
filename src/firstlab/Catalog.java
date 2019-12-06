package firstlab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Catalog {
  static public class Row {
    static private int nextID = 1;

    private String ID;
    private String author;
    private String name;
    private String year;

    public Row(String author, String name, String year) {
      this.ID = String.valueOf(nextID++);
      this.author = author;
      this.name = name;
      this.year = year;
    }

    public Row(String ID, String author, String name, String year) {
      this.ID = ID;
      this.author = author;
      this.name = name;
      this.year = year;
    }

    public String getID() {
      return ID;
    }

    public String getAuthor() {
      return author;
    }

    public String getName() {
      return name;
    }

    public String getYear() {
      return year;
    }

    public void print() {
      System.out.println(ID + ",  " + author + ",  " + name + ",  " + year);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null || getClass() != obj.getClass())
        return false;
      Row row = (Row) obj;
      return Objects.equals(ID, row.ID)
              && Objects.equals(author, row.author)
              && Objects.equals(name, row.name)
              && Objects.equals(year, row.year);
    }
  }

  private ArrayList<Row> rows = new ArrayList<>();

  //constructor which constructed object and add the first row to the list
  public Catalog(Row row) {
    rows.add(row);
  }

  //constructor to create a list without notes
  public Catalog() {
  }

  public void find(String string, boolean AuthororName) {
    for (Row row : rows) {
      if (row.author.equals(string) && AuthororName || row.name.equals(string) && !AuthororName) {
        row.print();
      }
    }
  }

  public void find(String year) {
    for (Row row : rows) {
      if (row.year.equals(year)) {
        row.print();
      }
    }
  }

  public void setAuthor(String id, String newAuthor, boolean changeAll) {
    for (Row row : rows) {
      if (row.ID.equals(id)) {
        row.author = newAuthor;
        if (!changeAll) {
          break;
        }
      }
    }
  }

  public void setAuthor(String id, String newAuthor) {
    setAuthor(id, newAuthor, false);
  }

  public void setName(String id, String name) {
    for (Row row : rows) {
      if (row.ID.equals(id)) {
        row.name = name;
        break;
      }
    }
  }

  public void setYear(String  id, String year) {
    for (Row row : rows) {
      if (row.ID.equals(id)) {
        row.year = year;
        break;
      }
    }
  }


  public void add(Row row) {
    rows.add(row);
  }

  public void delete(int index) {
    rows.remove(index - 1);
  }

  public boolean delete(Row row) {
    return rows.remove(row);
  }

  public void print() {
    for (Row row : rows) {
      row.print();
    }
  }

  private static Catalog catalog = new Catalog();
  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  public static Catalog.Row crud(String action) throws IOException {
    switch (action.toUpperCase()) {
      case "ADD": {
        System.out.println("Write the new row like \"author name year:\"");
        String[] strings = br.readLine().split(" ");
        catalog.add(new Catalog.Row(strings[0], strings[1], strings[2]));
        break;
      }
      case "FIND": {
        System.out.println("Write a field(author, name, year) and a value of field which you wan to find:");
        String[] str = br.readLine().split(" ");
        switch (str[0].toLowerCase()) {
          case "author": {
            catalog.find(str[1], true);
            break;
          }
          case "name": {
            catalog.find(str[1], false);
            break;
          }
          case "year": {
            catalog.find(str[1]);
          }
        }
        break;
      }
      case "SET": {
        System.out.println("Select the field(author, name, year) which you want to change and write an index and a value after \" \"");
        String[] str = br.readLine().split(" ");
        switch (str[0].toLowerCase()) {
          case "author": {
            catalog.setAuthor(str[1], str[2]);
            break;
          }
          case "name": {
            catalog.setName(str[1], str[2]);
            break;
          }
          case "year": {
            catalog.setYear(str[1], str[2]);
          }
        }
        break;
      }
      case "DELETE": {
        System.out.println("Write an index of row which you want to delete");
        catalog.delete(Integer.parseInt(br.readLine()));
        break;
      }
      case "PRINT": {
        catalog.print();
      }
    }
    return null;
  }

  public static void main(String[] args) {
    try {
      while (true) {
        System.out.println("Choose what you want to do:");
        System.out.println("1. ADD\n2. FIND\n3. SET\n4. DELETE\n5. PRINT\n6. EXIT");
        String action = br.readLine();
        if (action.toUpperCase().equals("EXIT")) {
          break;
        }
        crud(action);
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
