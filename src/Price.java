public class Price {

	private String Name;
	private double Cost;

	public Price(String name, double cost) {
		super();
		Name = name;
		Cost = cost;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public double getCost() {
		return Cost;
	}

	public void setCost(double cost) {
		Cost = cost;
	}

	@Override
	public String toString() {
		return Name + " $" + Cost;
	}

}
