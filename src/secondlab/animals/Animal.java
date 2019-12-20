package secondlab.animals;

import java.io.Serializable;
import java.util.Objects;
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
    this.name = name;
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

  public String getFoodType() {
    return food.getType();
  }

  public Integer getAmount() {
    return food.getAmount();
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

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Animal animal = (Animal) obj;
    return Objects.equals(ID, animal.ID)
            && Objects.equals(food.amount, animal.food.amount)
            && Objects.equals(name, animal.name)
            && Objects.equals(food.foodType, animal.food.foodType);
  }
}
