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
		products.put(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be positive");
		} else {
			products.put(product, quantity);
		}
	}	

	public BigDecimal getNetPrice() {
			
		BigDecimal subtotal = BigDecimal.ZERO;
		
		for (Product p: products.keySet()) {
			subtotal = subtotal.add(p.getPrice().multiply(BigDecimal.valueOf(products.get(p))));
		}
		return subtotal;
	}

	public BigDecimal getTax() {
		
		BigDecimal totalTaxAmount = BigDecimal.ZERO;
		BigDecimal taxAmount = BigDecimal.ZERO;
		
		for (Product p: products.keySet()) {
			taxAmount = p.getPrice().multiply(p.getTaxPercent());
			totalTaxAmount = totalTaxAmount.add(taxAmount.multiply(BigDecimal.valueOf(products.get(p))));
		}
		return totalTaxAmount;
	}

	public BigDecimal getTotal() {
		
		BigDecimal total = BigDecimal.ZERO;
		for (Product p: products.keySet()) {
			total = total.add(p.getPriceWithTax().multiply(BigDecimal.valueOf(products.get(p))));
		}
		return total;
	}
}
