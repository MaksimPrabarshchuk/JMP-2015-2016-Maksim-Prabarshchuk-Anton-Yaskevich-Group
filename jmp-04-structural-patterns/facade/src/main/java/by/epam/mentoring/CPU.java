package by.epam.mentoring;

public class CPU {

	public void freeze() {
		System.out.println("CPU is freezed.");
	}
    public void jump(String position) {
    	System.out.println("CPU jumped to position " + position);
    }
    public void execute() {
    	System.out.println("CPU is processing.");
    }

}