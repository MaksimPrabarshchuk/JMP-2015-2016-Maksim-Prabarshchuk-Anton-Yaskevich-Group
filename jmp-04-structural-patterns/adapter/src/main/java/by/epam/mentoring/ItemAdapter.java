package by.epam.mentoring;

public class ItemAdapter {
    private Item item;
    
    private static final double cmInInches = 2.54;
    
    public ItemAdapter(double initialPosition) {
        item = new Item(convertCmToInches(initialPosition));
        System.out.printf("Now initial position is %.2f cm\n", initialPosition);
    }
    
    public void move(double cm) {
        item.move(convertCmToInches(cm));
        System.out.printf("Move item to %.2f cm\n", cm);
    }
    
    public double getPosition() {
        double position = convertInchesToCm(item.getPosition());
        System.out.printf("Current position is %.2f cm\n", position);
        return position;
    }
    
    private double convertCmToInches(double cm) {
        return cm/cmInInches;
    }

    private double convertInchesToCm(double inches) {
        return inches*cmInInches;
    }
}