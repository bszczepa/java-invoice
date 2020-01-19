package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private Map<Product, Integer> products;
	
	public Invoice() {
		products = new HashMap<Product, Integer>();
	}

	public void addProduct(Product product) {
		// TODO: implement
	}

	public void addProduct(Product product, Integer quantity) {
		// TODO: implement
	}

	public BigDecimal getSubtotal() {
		
		BigDecimal subtotal = BigDecimal.ZERO;
		for (Product p: products.keySet()) {
			subtotal = subtotal.add(p.getPrice());
		}
		return subtotal;
	}

	public BigDecimal getTax() {
		return null;
	}

	public BigDecimal getTotal() {
		return null;
	}
}
