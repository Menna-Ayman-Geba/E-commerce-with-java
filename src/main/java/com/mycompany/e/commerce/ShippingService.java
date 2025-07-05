
package com.mycompany.e.commerce;

import java.util.List;

public class ShippingService {
    
    
    public void ship(List<Shipping> items) {
        
        double totalWeight = 0;
        System.out.println("** Shipment notice **");
        
        for (Shipping item : items) {
            System.out.println(item.getName() + " " + item.getWeight() + "g");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight " + totalWeight/1000 + "kg");
    }
    
    
    
}