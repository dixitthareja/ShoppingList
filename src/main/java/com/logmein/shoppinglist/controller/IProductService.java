/*
 * Interface for the insert, update, delete and list all operations.
 */

package com.logmein.shoppinglist.controller;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product addProduct(String name);

    String deleteProduct(String id);

    String updateProduct(String id, String name);

}
