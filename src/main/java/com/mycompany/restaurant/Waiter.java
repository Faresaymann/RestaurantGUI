package com.mycompany.restaurant;
public class Waiter {
    private String name;

    public Waiter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void takeOrder(Order order, Customer customer) {
        System.out.println("Waiter " + name + " is taking order from " + customer.getName());
        // Additional logic for processing the order can be added here.
    }

    @Override
    public String toString() {
        return "Waiter: " + name;
    }
}
