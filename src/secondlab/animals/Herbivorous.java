package secondlab.animals;

import java.util.Random;

public class Herbivorous extends Animal {

  public Herbivorous(String id, String name, Food food) {
    super(id, name, food);
  }

  public Herbivorous(String name, Food food) {
    super(name, food);
  }

  public Herbivorous(String id, String name) {
    super(id, name);
  }

  public Herbivorous(String name) {
    super(name);
  }

  @Override
  public Food calcCountOfFood() {
    return new Food("Usually set of food for herbivorous", new Random().nextInt() % 300);
  }
}
