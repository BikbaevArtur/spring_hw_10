package ru.bikbaev.spring_hw_7_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.bikbaev.spring_hw_7_1.data.ProductRepository;
import ru.bikbaev.spring_hw_7_1.model.Product;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setBalance_in_stock(10);
        product1.setPurchase_price(100);
        product1.setMinimum_quantity(5);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setBalance_in_stock(20);
        product2.setPurchase_price(200);
        product2.setMinimum_quantity(10);

        productRepository.save(product1);
        productRepository.save(product2);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testFindAll() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }
}
