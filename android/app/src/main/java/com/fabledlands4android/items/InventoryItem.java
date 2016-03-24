package com.fabledlands4android.items;

public abstract class InventoryItem {
    String name;

    InventoryItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
