package secondlab.animals;

import java.util.Random;

public class Predator extends Animal {
  public Predator(String id, String name, Food food) {
    super(id, name, food);
  }

  public Predator(String name, Food food) {
    super(name, food);
  }

  public Predator(String id, String name) {
    super(id, name);
  }

  public Predator(String name) {
    super(name);
  }

  @Override
  public Food calcCountOfFood() {
    return new Food("Usually set of food for predator", new Random().nextInt() % 350);
  }
}
