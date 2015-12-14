package by.epam.mentoring;

class Main {
  public static void main(String[] args) {
    Director director = new Director();
    ComputerBuilder cheapComputerBuilder = new CheapComputerBuilder();

    director.setComputerBuilder(cheapComputerBuilder);
    director.constructComputer();

    Computer computer = director.getComputer();
    System.out.println(computer.toString());
  }
}