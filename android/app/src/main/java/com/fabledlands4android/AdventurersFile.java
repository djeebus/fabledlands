package com.fabledlands4android;

import com.fabledlands4android.items.InventoryItem;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.ArrayList;
import java.util.HashMap;

public class AdventurersFile {
    HashMap<String, Profession> professions;
    int initialStamina, initialRank, initialGold;
    ArrayList<InventoryItem> items;
    ArrayList<Adventurer> prebuiltAdventurers;

    public AdventurersFile(
            HashMap<String, Profession> professions,
            int initialStamina,
            int initialRank,
            int initialGold,
            ArrayList<InventoryItem> items,
            ArrayList<Adventurer> prebuiltAdventurers
    ) {
        this.professions = professions;
        this.initialGold = initialGold;
        this.initialRank = initialRank;
        this.initialStamina = initialStamina;
        this.items = items;
        this.prebuiltAdventurers = prebuiltAdventurers;
    }

    WritableNativeMap toWritable() {
        WritableNativeMap map = new WritableNativeMap();

        map.putInt("initialStamina", this.initialStamina);
        map.putInt("initialRank", this.initialRank);
        map.putInt("initialGold", this.initialGold);

        return map;
    }

    public int getInitialGold() {
        return this.initialGold;
    }

    public int getInitialRank() {
        return this.initialRank;
    }

    public int getInitialStamina() {
        return this.initialStamina;
    }

    public Profession getProfession(String professionName) {
        return this.professions.get(professionName);
    }

    public ArrayList<Adventurer> getPrebuiltAdventurers() {
        return this.prebuiltAdventurers;
    }
}
