package com.mycompany.restaurant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantGUI extends JFrame {
    private Menu menu;
    private Order order;
    private JTextArea orderArea;
    private JLabel totalLabel;
    private JTextField customerField;  // Text field for customer name
    private Customer customer;
    private Waiter waiter;
    private Table table;
    private Bill bill;

    public RestaurantGUI() {
        menu = new Menu();
        order = new Order();

        // Create a sample waiter and table
        waiter = new Waiter("Jane Smith");
        table = new Table(1);  // Assign table number 1 to the customer

        // Add items to the menu
        menu.addItem(new Item("Burger", 8.99));
        menu.addItem(new Item("Pizza", 12.99));
        menu.addItem(new Item("Pasta", 10.99));
        menu.addItem(new Item("Salad", 6.99));

        setTitle("Restaurant Ordering System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Customer name input
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new FlowLayout());
        customerPanel.add(new JLabel("Customer Name:"));
        customerField = new JTextField(15);  // Input field for customer name
        customerPanel.add(customerField);

        // Menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(menu.getItems().size(), 1));
        for (Item item : menu.getItems()) {
            JButton itemButton = new JButton(item.toString());
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    order.addItem(item);
                    updateOrderDisplay();
                }
            });
            menuPanel.add(itemButton);
        }

        // Order display
        orderArea = new JTextArea();
        orderArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderArea);

        // Total display
        totalLabel = new JLabel("Total: $0.00");

        // Bill button
        JButton billButton = new JButton("Generate Bill");
        billButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });

        // Reset button
        JButton resetButton = new JButton("New Order");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetOrder();
            }
        });

        // Add panels to frame
        add(customerPanel, BorderLayout.NORTH);  // Add customer input panel
        add(menuPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(totalLabel, BorderLayout.SOUTH);

        // Panel for bill and reset buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(billButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateOrderDisplay() {
        StringBuilder orderText = new StringBuilder();
        for (Item item : order.getItems()) {
            orderText.append(item.toString()).append("\n");
        }
        orderArea.setText(orderText.toString());
        totalLabel.setText("Total: $" + String.format("%.2f", order.calculateTotal()));
    }

    private void generateBill() {
        String customerName = customerField.getText();  // Get the customer name from the text field
        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a customer name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        customer = new Customer(customerName);  // Create a new customer with the entered name
        table.seatCustomer(order);
        waiter.takeOrder(order, customer);

        bill = new Bill(order, 0.10, 0.15, customerName);  // Pass customer name to Bill
        JOptionPane.showMessageDialog(this, bill.toString(), "Bill", JOptionPane.INFORMATION_MESSAGE);
    }

    private void resetOrder() {
        order = new Order();  // Reset the order
        orderArea.setText("");  // Clear the order display
        totalLabel.setText("Total: $0.00");  // Reset the total label
        customerField.setText("");  // Clear the customer input
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RestaurantGUI();
            }
        });
    }
}
