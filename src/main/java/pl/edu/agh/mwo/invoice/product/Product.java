package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Product {
    private final String name;

    private final BigDecimal price;

    private final BigDecimal taxPercent;
    private static BigDecimal EXCISE = new BigDecimal(5.56);

    protected Product(String name, BigDecimal price, BigDecimal tax) {
        if (name == null
                || name.equals("")
                || price == null || tax == null || tax.compareTo(new BigDecimal(0)) < 0
                || price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.taxPercent = tax;
        this.price = price;

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
        BigDecimal priceWithTax = price.multiply(taxPercent).add(price);

        if (Product.this.getClass().isAssignableFrom(FuelCanister.class)
                || Product.this.getClass().isAssignableFrom(BottleOfWine.class)) {
            priceWithTax = priceWithTax.add(EXCISE);
        }

        return priceWithTax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(taxPercent, product.taxPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, taxPercent);
    }
}
