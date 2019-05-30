package com.sa.bbva.got.service;

import com.sa.bbva.got.model.Product;
import com.sa.bbva.got.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.never;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplSpyTest {
    @Spy
    private ProductServiceImpl prodServiceSpy;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private Product product;

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerException_whenGetProductByIdIsCalledWithoutContext() throws Exception {
        // Act
        Product retrievedProduct = prodServiceSpy.getProductById(5);
        // Assert
        assertThat(retrievedProduct, is(equalTo(product)));
    }

    public void shouldThrowNullPointerException_whenSaveProductIsCalledWithoutContext() throws Exception {
        // Arrange
        Mockito.doReturn(product).when(productRepository).save(product);
        // Act
        Product savedProduct = prodServiceSpy.saveProduct(product);
        // Assert
        assertThat(savedProduct, is(equalTo(product)));
    }

    @Test
    public void shouldVerifyThatGetProductByIdIsCalled() throws Exception {
        // Arrange
        Mockito.doReturn(product).when(prodServiceSpy).getProductById(5);
        // Act
        // Product retrievedProduct = prodServiceSpy.getProductById(5);
        // Assert
        Mockito.verify(prodServiceSpy).getProductById(5);
    }

    @Test
    public void shouldVerifyThatSaveProductIsNotCalled() throws Exception {
        // Arrange
        Mockito.doReturn(product).when(prodServiceSpy).getProductById(5);
        // Act
        // Product retrievedProduct = prodServiceSpy.getProductById(5);
        // Assert
        Mockito.verify(prodServiceSpy, never()).saveProduct(product);
    }
}