package com.mycompany.e.commerce;

import java.time.LocalDate;

public class ECommerce {
    public static void main(String[] args) {
        // Successful case
        Product cheese = new Product("Cheese", 100, 5
                , LocalDate.now().plusDays(10), 200.0);
        
        Product biscuits = new Product("Biscuits", 150,
                3, LocalDate.now().plusDays(5), 700.0);
        
        
        Customer customer = new Customer("Menna", 1000);
        
        
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        new Checkout().checkout(customer, cart);

        
        // Case 1: Empty cart
        /*
        try {
            Cart emptyCart = new Cart();
        
            new Checkout().checkout(customer, emptyCart);
        
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        */

        //  Case 2: Insufficient balance
        /*
        try {
            Cart cart2 = new Cart();
            Product tv = new Product("TV", 1000, 2, null, 5000.0);
            cart2.add(tv, 1);
            
            Customer Customer2 = new Customer("C2", 100);
            new Checkout().checkout(Customer2, cart2);
            
            
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
        */

        
        
        // Case 3: Out of stock item
        /*
        try {
            Cart cart3 = new Cart();
            cart3.add(cheese, 10); 
            
            new Checkout().checkout(customer, cart3);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }*/
        

    }
}