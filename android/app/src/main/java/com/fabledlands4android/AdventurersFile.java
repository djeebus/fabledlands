package com.fabledlands4android;

import com.fabledlands4android.items.InventoryItem;

import java.util.ArrayList;
import java.util.HashMap;

public class AdventurersFile {
    HashMap<String, Profession> professions;
    Integer initialStamina, initialRank, initialGold;
    ArrayList<InventoryItem> items;
    ArrayList<Adventurer> prebuildAdventurers;

    public AdventurersFile(
            HashMap<String, Profession> professions,
            Integer initialStamina,
            Integer initialRank,
            Integer initialGold,
            ArrayList<InventoryItem> items,
            ArrayList<Adventurer> prebuiltAdventurers
    ) {
        this.professions = professions;
        this.initialGold = initialGold;
        this.initialRank = initialRank;
        this.initialStamina = initialStamina;
        this.items = items;
        this.prebuildAdventurers = prebuiltAdventurers;
    }
}
