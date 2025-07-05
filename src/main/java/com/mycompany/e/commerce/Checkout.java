package com.mycompany.e.commerce;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
    private final double SHIPPING_RATE = 30;

    
    
    
    public void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double subtotal = 0;
        
        List<Shipping> itemsToShip = new ArrayList<>();
        
        
        
        
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            if (product.getQuantity() < quantity) {
                throw new RuntimeException("Not enough stock in " + product.getName());
            }
            if (product.isExpired()) {
                throw new RuntimeException(product.getName() + " is expired");
            }

            subtotal += product.getPrice() * quantity;

            if (product.isShippable()) {
                itemsToShip.add(new ShippableItem(product, quantity));
            }
        }

        
        
        
        double totalWeight = calculateTotalWeight(itemsToShip);
        double totalAmount = subtotal + SHIPPING_RATE;

        if (customer.getBalance() < totalAmount) {
            throw new RuntimeException("Insufficient balance: " +
                    customer.getBalance() + " < " + totalAmount);
        }

        updateStock(cart);
        customer.decreaseBalance(totalAmount);

        if (!itemsToShip.isEmpty()) {
            new ShippingService().ship(itemsToShip);
        }

        printReceipt(cart, subtotal, SHIPPING_RATE, totalAmount, customer);
    }

    
    
    
    private class ShippableItem implements Shipping {
        private Product product;
        private int quantity;

        ShippableItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        @Override
        public String getName() {
            return quantity + "x " + product.getName();
        }

        @Override
        public double getWeight() {
            return product.getWeight() * quantity;
        }
    }

    private double calculateTotalWeight(List<Shipping> items) {
        double total = 0;
        for (Shipping item : items) {
            total += item.getWeight();
        }
        return total;
    }

    private void updateStock(Cart cart) {
        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
    }

    
    
    
    
    
    
    private void printReceipt(Cart cart, double subtotal, double shippingCost,
            double totalAmount, Customer customer) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            System.out.println(quantity + "x " + product.getName()
                    + " " + (product.getPrice() * quantity));
        }
System.out.println("----------------------");
System.out.println("Subtotal " + subtotal);
System.out.println("Shipping " + shippingCost);
System.out.println("Amount " + totalAmount);
System.out.println("Customer balance " + customer.getBalance());

    }
}