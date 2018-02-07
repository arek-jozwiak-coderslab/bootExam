package pl.coderslab.cart;

import java.math.BigDecimal;

public class Product implements Saleable {

    private BigDecimal price;

    private String name;

    public Product(BigDecimal price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
