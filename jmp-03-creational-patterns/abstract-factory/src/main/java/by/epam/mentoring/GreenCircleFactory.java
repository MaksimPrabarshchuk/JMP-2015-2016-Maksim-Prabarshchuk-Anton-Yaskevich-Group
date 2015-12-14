package by.epam.mentoring;

public class GreenCircleFactory implements AbstractFactory {

    public Shape getShape() {
        return new Circle();
    }

    public Color getColor() {
        return new Green();
    }
}