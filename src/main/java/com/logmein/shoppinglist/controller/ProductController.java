
package com.logmein.shoppinglist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;


    @RequestMapping(path = "/products")
    ResponseEntity<Product> getProduct() {
        List<Product> products = productService.findAll();

        StringBuilder listProduct = new StringBuilder();
        String html = "<html> <head> <style> table { font-family: arial, sans-serif; border-collapse: collapse; width: 50%;} " +
                "td, th { border: 1px solid #dddddd; text-align: left; padding: 8px; } " +
                "tr:nth-child(even) { background-color: #dddddd; } </style> </head> <body> " + "<h2>Shopping List</h2>" +
                "<table> <tr> <th>ID</th>\n" +
                "<th>Product Name</th>";
        listProduct.append(html);
        for (Product product : products) {
            listProduct.append("<tr><td>" + product.getId() + "</td> <td> " + product.getName() + "</td></tr>");
        }
        listProduct.append("</table></body></html>");
        return new ResponseEntity(listProduct, HttpStatus.OK);
    }


    @RequestMapping(path = "/insert/{name}")
    ResponseEntity<String> insertProduct(@PathVariable String name) {
        Product product = productService.addProduct(name);
        return new ResponseEntity(product, HttpStatus.OK);
    }

    @RequestMapping(path = "/update/{id}/{name}")
    ResponseEntity<String> updateProduct(@PathVariable String id, @PathVariable String name) {
        String status = productService.updateProduct(id, name);
        return new ResponseEntity(status, HttpStatus.OK);
    }

    @RequestMapping(path = "/delete/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable String id) {
        String status = productService.deleteProduct(id);
        return new ResponseEntity(status, HttpStatus.OK);
    }

}
