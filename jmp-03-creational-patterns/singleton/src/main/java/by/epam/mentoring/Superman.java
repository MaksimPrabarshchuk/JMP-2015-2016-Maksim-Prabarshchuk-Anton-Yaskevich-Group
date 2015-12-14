package by.epam.mentoring;
import java.io.*;

class Superman {

	private static Superman instance;

	private Superman(){}

	private static class SingletonHolder {
		private final static Superman instance = new Superman();
	}

	public static Superman getInstance() {
		return SingletonHolder.instance;
	}

	public void salutation() {
		System.out.println("Hello World! It's Superman!");
	}
}