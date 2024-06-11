package ru.bikbaev.spring_hw_7_1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.bikbaev.spring_hw_7_1.data.ProductRepository;
import ru.bikbaev.spring_hw_7_1.model.Product;
import ru.bikbaev.spring_hw_7_1.service.serviceProduct.ProductService;
import ru.bikbaev.spring_hw_7_1.service.serviceProduct.PurchaseOrder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PurchaseOrder purchaseOrder;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Product1");
        productService.creatProduct(product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setId(1);
        when(productRepository.findId(1)).thenReturn(Optional.of(product));

        Product found = productService.findById(1);
        assertNotNull(found);
        assertEquals(1, found.getId());
    }

    @Test
    void testDeleteById() {
        Product product = new Product();
        product.setId(1);
        when(productRepository.findId(1)).thenReturn(Optional.of(product));

        productService.deleteById(1);

        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testSellProduct() {
        Product product = new Product();
        product.setId(1);
        product.setBalance_in_stock(100);
        when(productRepository.findId(1)).thenReturn(Optional.of(product));

        productService.sellProduct(1, 10);

        assertEquals(90, product.getBalance_in_stock());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testBuyProduct() {
        Product product = new Product();
        product.setId(1);
        product.setBalance_in_stock(100);
        when(productRepository.findId(1)).thenReturn(Optional.of(product));

        productService.buyProduct(1, 10);

        assertEquals(110, product.getBalance_in_stock());
        verify(productRepository, times(1)).save(product);
    }
}
