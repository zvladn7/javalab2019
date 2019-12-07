package secondlab;

import secondlab.animals.*;

import java.io.*;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    ArrayList<Animal> listOfAnimals = new ArrayList<>();
    listOfAnimals.add(new Predator("Tiger"));
    listOfAnimals.add(new Herbivorous("1", "Cow", new Animal.Food("Grass",750)));
    listOfAnimals.add(new Herbivorous("Sheep", new Animal.Food("Grass",50)));
    listOfAnimals.add(new Predator("2", "Lion", new Animal.Food("Meat", 500)));
    listOfAnimals.add(new Omnivore("Mouse"));
    listOfAnimals.add(new Herbivorous("Elephant"));
    listOfAnimals.add(new Omnivore("Bear", new Animal.Food("Honey", 100)));


    //Task A
    System.out.println("----------------------Task A---------------------");
    sort(listOfAnimals);

    listOfAnimals.forEach(System.out::println);

    //Task B
    System.out.println("----------------------Task B---------------------");
    listOfAnimals.stream().filter(x -> listOfAnimals.indexOf(x) < 5).forEach(System.out::println);

    //Task C
    System.out.println("----------------------Task C---------------------");
    listOfAnimals.stream().filter(x -> listOfAnimals.indexOf(x) > listOfAnimals.size() - 4).forEach(System.out::println);

    //Tasks D and E
    System.out.println("----------------------Task D and E---------------------");
    write(listOfAnimals, "animalsObjects");

    ArrayList<Animal> listForReading = new ArrayList<>();
    read(listForReading, "animalsObjects");
    listForReading.forEach(System.out::println);
  }

  public static void read(ArrayList<Animal> list, String file) {
    try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {
        while (true) {
          try {
          list.add((Animal) ois.readObject());
          } catch (EOFException ex) {
            break;
          }
        }
    } catch (FileNotFoundException ex) {
      System.err.println("File isn't allowed to be opened!\n");
      ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
      System.err.println("Class for reading objects wasn't found!\n");
      ex.printStackTrace();
    } catch (IOException ex) {
      System.err.println("Error happened when the file was reading!\n");
      ex.printStackTrace();
    }
  }

  public static void write(ArrayList<Animal> list, String file) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))) {
      for (Animal animal: list) {
        oos.writeObject(animal);
      }
    } catch (FileNotFoundException ex) {
      System.err.println("File isn't allowed to be opened!\n");
      ex.printStackTrace();
    } catch (IOException ex) {
      System.err.println("Error happened when the object was writing!\n");
      ex.printStackTrace();
    }
  }

  public static void sort(ArrayList<Animal> list) {
    list.sort((animal1, animal2) -> (animal1.getFood() != animal2.getFood() ?
            animal1.getFood().getAmount() - animal2.getFood().getAmount() :
            animal1.getName().toUpperCase().compareTo(animal2.getName().toUpperCase())
    ));
  }
}
