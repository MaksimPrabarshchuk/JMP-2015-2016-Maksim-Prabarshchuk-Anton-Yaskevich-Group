package by.epam.mentoring;

public class RedSquareFactory implements AbstractFactory {

    public Shape getShape() {
        return new Square();
    }

    public Color getColor() {
        return new Red();
    }
}