package by.epam.mentoring;

class HardDrive {
    public String read(String pointer, int size) {
    	System.out.println("HDD is reading data at sector " + pointer+ " with size "+ size);
    	return "data";
    }
}