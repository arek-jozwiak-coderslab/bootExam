package pl.coderslab.cart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class CartTest {

    Cart c;
    Product p1, p2;

    @Before
    public void setUp() {
        c = new Cart();
        p1 = new Product(new BigDecimal(100), "name1");
        p2 = new Product(new BigDecimal(11), "name2");
        c.add(p1, 2);
        c.add(p2, 1);
    }

    @Test
    public void test_get_total_quantity() {
        assertEquals(c.getTotalQuantity(), 3);
    }

    @Test
    public void test_get_total_price() {
        assertEquals(c.getTotalPrice().doubleValue(), 211, 1);
    }

    @Test
    public void test_add() {
        assertNotNull((c.getCartItemMap().get(p1)));
    }

    @Test
    public void test_update() {
        c.update(p1, 3);
        assertTrue(c.getCartItemMap().get(p1)==3);
    }
    
    @Test(expected=ProductNotFoundException.class)
    public void test_update_product_not_found_exception() {
       c.update(null, 200);
    }
    
}
