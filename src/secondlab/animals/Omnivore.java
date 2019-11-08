package secondlab.animals;

import java.util.Random;

public class Omnivore extends Animal {
  public Omnivore(String id, String name, Food food) {
    super(id, name, food);
  }

  public Omnivore(String name, Food food) {
    super(name, food);
  }

  public Omnivore(String id, String name) {
    super(id, name);
  }

  public Omnivore(String name) {
    super(name);
  }

  @Override
  public Food calcCountOfFood() {
    return new Food("Usually set of food for omnivore", new Random().nextInt() % 250);
  }
}
