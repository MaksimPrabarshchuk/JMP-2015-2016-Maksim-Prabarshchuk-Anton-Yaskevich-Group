package by.epam.mentoring;

class Main {
	public static void main(String[] args) {

	  AbstractFactory factory = new RedSquareFactory();
	  Shape square = factory.getShape();
	  Color red = factory.getColor();
	  square.draw();
	  red.fill();

 	  factory = new GreenCircleFactory();
	  Shape circle = factory.getShape();
	  Color green = factory.getColor();
	  circle.draw();
	  green.fill();
   }
}