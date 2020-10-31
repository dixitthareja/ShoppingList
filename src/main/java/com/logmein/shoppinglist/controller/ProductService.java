/*
 * This class ProductService has been used for the handling of operations i.e. insert, update, delete, list all
 * on shopping list. The products will not sustain if the service is restarted.
 */


package com.logmein.shoppinglist.controller;

import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ProductService implements IProductService {
    final static List<Product> products = new CopyOnWriteArrayList<>();

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product addProduct(String name) {
        //Random product id generator
        String productID = UUID.randomUUID().toString();
        Product product = new Product();
        // same product can be added again as each product have unique UUID(maybe same product exists in different categories).
        product.setId(productID);
        product.setName(name);
        products.add(product);
        // return the added product
        return product;
    }

    @Override
    public String deleteProduct(String id) {
        boolean deleted = false;
        Iterator<Product> iter = products.iterator();
        while (iter.hasNext()) {
            Product product = iter.next();
            if (product.getId().equals(id)) {
                products.remove(product);
                deleted = true;
            }

        }
        return deleted ? "Product deleted from shopping list" : "Product not found";

    }

    @Override
    public String updateProduct(String id, String name) {

        boolean updated = false;
        Iterator<Product> iter = products.iterator();
        while (iter.hasNext()) {
            Product product = iter.next();
            if (product.getId().equals(id)) {
                product.setName(name);
                updated = true;
            }
        }
        return updated ? "Shopping list updated successfully" : "Product not found";

    }
}
