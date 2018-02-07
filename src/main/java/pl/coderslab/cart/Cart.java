package pl.coderslab.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable {

    private static final long serialVersionUID = 42L;

    private Map<Saleable, Integer> cartItemMap = new LinkedHashMap<>();
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private int totalQuantity = 0;

    public Map<Saleable, Integer> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Saleable, Integer> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public void add(Saleable sellable, int quantity) {

        if (cartItemMap.containsKey(sellable)) {
            cartItemMap.put(sellable, cartItemMap.get(sellable) + quantity);
        } else {
            cartItemMap.put(sellable, quantity);
        }

        totalPrice = totalPrice.add(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity += quantity;
    }

    public void update(Saleable sellable, int quantity) {
        if (!cartItemMap.containsKey(sellable))
            throw new ProductNotFoundException();
        if (quantity < 0)
            throw new QuantityOutOfRangeException();

        int productQuantity = cartItemMap.get(sellable);
        BigDecimal productPrice = sellable.getPrice().multiply(BigDecimal.valueOf(productQuantity));

        cartItemMap.put(sellable, quantity);

        totalQuantity = totalQuantity - productQuantity + quantity;
        totalPrice = totalPrice.subtract(productPrice).add(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
    }

    public void remove(Saleable sellable, int quantity) {
        if (!cartItemMap.containsKey(sellable))
            throw new ProductNotFoundException();

        int productQuantity = cartItemMap.get(sellable);

        if (quantity < 0 || quantity > productQuantity)
            throw new QuantityOutOfRangeException();

        if (productQuantity == quantity) {
            cartItemMap.remove(sellable);
        } else {
            cartItemMap.put(sellable, productQuantity - quantity);
        }

        totalPrice = totalPrice.subtract(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity -= quantity;
    }

    public void remove(Saleable sellable) {
        if (!cartItemMap.containsKey(sellable))
            throw new ProductNotFoundException();

        int quantity = cartItemMap.get(sellable);
        cartItemMap.remove(sellable);
        totalPrice = totalPrice.subtract(sellable.getPrice().multiply(BigDecimal.valueOf(quantity)));
        totalQuantity -= quantity;
    }

    public void clear() {
        cartItemMap.clear();
        totalPrice = BigDecimal.ZERO;
        totalQuantity = 0;
    }

    public int getQuantity(Saleable sellable)  {
        if (!cartItemMap.containsKey(sellable))
            throw new ProductNotFoundException();
        return cartItemMap.get(sellable);
    }

    public BigDecimal getCost(Saleable sellable) {
        if (!cartItemMap.containsKey(sellable))
            throw new ProductNotFoundException();
        return sellable.getPrice().multiply(BigDecimal.valueOf(cartItemMap.get(sellable)));
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

}
