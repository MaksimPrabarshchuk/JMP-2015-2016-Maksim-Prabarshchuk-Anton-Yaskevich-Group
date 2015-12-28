package by.epam.mentoring;

class Main {
  public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
		
		int [] characterCodes = {1,2};
		for (int nextCode : characterCodes){
			EnglishCharacter character = factory.getCharacter(nextCode);
			character.printCharacter();
		}
    }
}