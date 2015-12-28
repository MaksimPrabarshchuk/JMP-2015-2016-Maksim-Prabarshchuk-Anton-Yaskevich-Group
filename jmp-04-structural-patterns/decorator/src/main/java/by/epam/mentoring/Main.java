package by.epam.mentoring;

class Main {
  public static void main(String[] args) {
        Animal animal = new LivingAnimal();
        animal.describe();
        
        animal = new WingDecorator(animal);
        animal.describe();
    }
}