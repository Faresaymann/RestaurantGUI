package com.mycompany.restaurant;

public class Table {
    private int tableNumber;
    private boolean isOccupied;
    private Order currentOrder;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.isOccupied = false;
        this.currentOrder = null;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void seatCustomer(Order order) {
        if (!isOccupied) {
            this.isOccupied = true;
            this.currentOrder = order;
            System.out.println("Customer seated at table " + tableNumber);
        } else {
            System.out.println("Table " + tableNumber + " is already occupied.");
        }
    }

    public void freeTable() {
        this.isOccupied = false;
        this.currentOrder = null;
        System.out.println("Table " + tableNumber + " is now free.");
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    @Override
    public String toString() {
        return "Table " + tableNumber + (isOccupied ? " (Occupied)" : " (Available)");
    }
}
