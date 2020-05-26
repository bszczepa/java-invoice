package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private int invoiceId;

    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public Invoice() {
        this.invoiceId = 1;
    }

    public int getInvoiceId() {
        return this.invoiceId;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }


    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public String productsToString() {
        String result;
        result = invoiceId + "\n";
        int productCount = 0;
        for (Product p: products.keySet()) {
            productCount++;
            result = result + String.format("Produkt %d: %s, Ilość: %d, Cena: %s\n",
                                                    productCount,
                                                    p.getName(),
                                                    products.get(p),
                                                    p.getPrice().toString());
        }
        result = result + products.size();
        return result;
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }




}
