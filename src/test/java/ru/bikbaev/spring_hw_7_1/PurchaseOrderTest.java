package ru.bikbaev.spring_hw_7_1;

import org.junit.jupiter.api.Test;
import ru.bikbaev.spring_hw_7_1.model.Order;
import ru.bikbaev.spring_hw_7_1.model.Product;
import ru.bikbaev.spring_hw_7_1.service.serviceProduct.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseOrderTest {

    @Test
    void testCreatOrder() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Product1");
        product1.setBalance_in_stock(5);
        product1.setMinimum_quantity(10);
        product1.setPurchase_price(100);

        products.add(product1);

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        List<Order> orders = purchaseOrder.creatOrder(products);

        assertEquals(1, orders.size());
        assertEquals(5, orders.get(0).getQuantity());
    }
}
