package com.mycompany.restaurant;

import java.util.ArrayList;
import java.util.List;
import com.mycompany.restaurant.Item;

public class Menu {
    private List<Item> items;

    public Menu() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}
