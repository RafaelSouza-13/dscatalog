package edu.rafael.dscatalog.tests;

import edu.rafael.dscatalog.dto.ProductDTO;
import edu.rafael.dscatalog.entities.Category;
import edu.rafael.dscatalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product createProduct(){
        Product product = new Product("PS5", "The new generation PS5 video game", 600.0, "", Instant.now());
        product.getCategories().add(new Category(1L, "Eletrônicos"));
        return product;
    }

    public static ProductDTO createProductDTO(){
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }
}
