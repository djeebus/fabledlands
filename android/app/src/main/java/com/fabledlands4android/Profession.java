package com.fabledlands4android;

import com.fabledlands4android.items.InventoryItem;

import java.util.ArrayList;
import java.util.HashMap;

public class Profession {
    private String name;
    private HashMap<AbilityType, Integer> stats = new HashMap<>();
    private ArrayList<InventoryItem> initialItems = new ArrayList<>();

    public Profession(
            String name,
            HashMap<AbilityType, Integer> stats
    ) {
        this.name = name;
        this.stats = stats;
    }

    public Integer getStat(AbilityType attribute) {
        return this.stats.get(attribute);
    }
}
