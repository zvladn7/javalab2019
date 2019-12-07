package secondlab.animals;

import java.io.Serializable;
import java.util.UUID;

abstract public class Animal implements Serializable {
  private String ID;
  private String name;
  private Food food;



  public static class Food implements Serializable {
    private String foodType;
    private int amount;

    public Food(final String foodType, final int amount) {
      this.foodType = foodType;
      this.amount = amount;
    }

    public String getType() {
      return foodType;
    }

    public int getAmount() {
      return amount;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("type: ").append(foodType).append(", amount: ").append(amount);
      return sb.toString();
    }
  }

  public Animal(String id, String name, Food food) {
    this.ID = id;
    this.name = id;
    this.food = food;
  }



  public Animal(String name, Food food) {
    this(UUID.randomUUID().toString(), name, food);
  }

  public Animal(String id, String name) {
    this(id, name, null);
  }

  public Animal(String name) {
    this(name, (Food) null);
  }

  public String getID() {
    return ID;
  }

  public String getName() {
    return name;
  }

  public Food getFood() {
    if (food == null) {
      food = calcCountOfFood();
    }
    return food;
  }


  abstract public Food calcCountOfFood();

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ID: ").append(ID).append(", name: ").append(name).append(", food: ").append(food.toString());
    return sb.toString();
  }
}
