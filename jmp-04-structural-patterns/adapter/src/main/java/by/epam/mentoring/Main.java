package by.epam.mentoring;

class Main {
  public static void main(String[] args) {
        ItemAdapter itemAdapter = new ItemAdapter(100.0);
        itemAdapter.move(55.4);
        itemAdapter.getPosition();
        itemAdapter.move(-232.1);
        itemAdapter.getPosition();
    }
}