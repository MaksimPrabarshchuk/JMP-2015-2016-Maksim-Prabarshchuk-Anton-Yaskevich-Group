package by.epam.mentoring;

public class Cookie implements Cloneable {
	private int weight;

	public Cookie(int weight) {
		this.weight = weight;
	}

	public int getWeight(){
		return this.weight;
	}

    @Override
    public Cookie clone() throws CloneNotSupportedException {
        return (Cookie) super.clone();
    }
}