package com.week1.cashregisterpart1;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private static ProductList instance;
    private List<Product> productList;

    private ProductList() {
        productList = new ArrayList<>();
        initializeProducts();
    }

    public static ProductList getInstance() {
        if (instance == null) {
            instance = new ProductList();
        }
        return instance;
    }

    private void initializeProducts() {
        productList.add(new Product("Product 1", 5, 9.99));
        productList.add(new Product("Product 2", 3, 12.99));
        productList.add(new Product("Product 3", 8, 19.99));
        productList.add(new Product("Product 4", 5, 9.99));
        productList.add(new Product("Product 5", 3, 12.99));
        productList.add(new Product("Product 6", 8, 19.99));
        productList.add(new Product("Product 7", 5, 9.99));
        productList.add(new Product("Product 8", 3, 12.99));
        productList.add(new Product("Product 9", 8, 19.99));
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void updateProductQuantity(String productName, int newQuantity) {
        for (Product product : productList) {
            if (product.getName().equals(productName)) {
                product.setQuantity(newQuantity);
                break;
            }
        }
    }
}
