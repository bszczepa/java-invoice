package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public abstract class Product {
	private final String name;

	private final BigDecimal price;

	private final BigDecimal taxPercent;

	protected Product(String name, BigDecimal price, BigDecimal tax) {
		if (name == null || price == null || tax == null) {
			throw new IllegalArgumentException("Name, price or tax is null");
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
