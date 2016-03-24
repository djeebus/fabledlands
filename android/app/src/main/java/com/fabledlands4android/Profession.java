package com.fabledlands4android;

import com.fabledlands4android.items.InventoryItem;

import java.util.ArrayList;
import java.util.HashMap;

public class Profession {
    private String name;
    private HashMap<AbilityType, Integer> stats = new HashMap<>();
    private ArrayList<InventoryItem> initialItems;

    public Profession(
            String name,
            HashMap<AbilityType, Integer> stats
    ) {
        this.name = name;
        this.stats = stats;
        this.initialItems = new ArrayList<>();
    }

    public Integer getStat(AbilityType attribute) {
        return this.stats.get(attribute);
    }
    public String getName() {
        return this.name;
    }
    public ArrayList<InventoryItem> getInitialItems() {
        return this.initialItems;
    }
    public void addInitialItem(InventoryItem item) {
        this.initialItems.add(item);
    }
}
