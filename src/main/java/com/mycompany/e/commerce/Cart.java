
package com.mycompany.e.commerce;


import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        
        if (quantity <= 0) {
            throw new RuntimeException("Invalid quantity");
        }
        
        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Low stock: " + product.getName());
        }
        
        if (product.isExpired()) {
            throw new RuntimeException(product.getName() + " is expired");
        }
        items.add(new CartItem(product, quantity));
    }

    
    
    
    
    
    
    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}