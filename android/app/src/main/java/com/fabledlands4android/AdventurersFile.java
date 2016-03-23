package com.fabledlands4android;

import android.util.Log;
import com.fabledlands4android.items.InventoryItem;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.ArrayList;
import java.util.HashMap;

public class AdventurersFile {
    static String TAG = "AdventurersFile";

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

    WritableNativeMap toWritable() {
        Log.i(TAG, "Creating writable version of AdventurersFile");
        WritableNativeMap map = new WritableNativeMap();

        map.putInt("initialStamina", this.initialStamina);
        map.putInt("initialRank", this.initialRank);
        map.putInt("initialGold", this.initialGold);

        return map;
    }
}
