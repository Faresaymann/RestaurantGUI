package com.mycompany.restaurant;

public class Bill {
    private Order order;
    private double taxRate;
    private double tipRate;
    private String customerName;

    public Bill(Order order, double taxRate, double tipRate, String customerName) {
        this.order = order;
        this.taxRate = taxRate;
        this.tipRate = tipRate;
        this.customerName = customerName;
    }

    public double calculateSubtotal() {
        return order.calculateTotal();
    }

    public double calculateTax() {
        return calculateSubtotal() * taxRate;
    }

    public double calculateTip() {
        return calculateSubtotal() * tipRate;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax() + calculateTip();
    }

    @Override
    public String toString() {
        return "Customer: " + customerName + "\n" +
               "Subtotal: $" + String.format("%.2f", calculateSubtotal()) + "\n" +
               "Tax (10%): $" + String.format("%.2f", calculateTax()) + "\n" +
               "Tip (15%): $" + String.format("%.2f", calculateTip()) + "\n" +
               "Total: $" + String.format("%.2f", calculateTotal());
    }
}
