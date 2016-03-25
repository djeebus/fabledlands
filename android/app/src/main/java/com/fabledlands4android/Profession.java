package com.fabledlands4android;

import com.fabledlands4android.items.InventoryItem;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

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

    public WritableNativeMap toWritable() {
        WritableNativeMap m = new WritableNativeMap();
        m.putString("name", this.getName());
        for (AbilityType abilityType : this.stats.keySet()) {
            m.putInt(abilityType.toString(), this.stats.get(abilityType));
        }

        WritableNativeArray i = new WritableNativeArray();
        for (InventoryItem item : this.initialItems) {
            i.pushMap(item.toWritable());
        }
        return m;
    }
}
