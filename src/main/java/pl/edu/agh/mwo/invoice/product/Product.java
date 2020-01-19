package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax) {
		if (name == null || price == null || tax == null) {
			throw new IllegalArgumentException("Name, price or tax is null");
		} else if (name.length() == 0) {
			throw new IllegalArgumentException("Empty name");
		} else if (tax.signum() == -1 || price.signum() == -1) {
			throw new IllegalArgumentException("Price or tax negative");
		} else {
			this.name = name;
			this.price = price;
			this.taxPercent = tax;
		}
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public BigDecimal getTaxPercent() {
		return taxPercent;
	}

	public BigDecimal getPriceWithTax() {
		return price.add(price.multiply(taxPercent));
	}
}
