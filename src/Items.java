
public class Items {

	private String Name;
	private Price[] Prices;

	public Items(String name, Price[] prices) {
		super();
		Name = name;
		Prices = prices;
	}

	public Items(String name) {
		super();

		Prices = new Price[3];
		Prices[0] = new Price("Small", 5);
		Prices[1] = new Price("Medium", 10);
		Prices[2] = new Price("Large", 15);

		Name = name;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Price[] getPrice() {
		return Prices;
	}

	public void setPrice(Price[] prices) {
		Prices = prices;
	}

	@Override
	public String toString() {
		return Name;
	}

}
