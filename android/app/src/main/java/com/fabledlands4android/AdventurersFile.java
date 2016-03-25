package com.fabledlands4android;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.ArrayList;
import java.util.HashMap;

public class AdventurersFile {
    HashMap<String, Profession> professions;
    int initialStamina, initialRank, initialGold;
    ArrayList<Adventurer> prebuiltAdventurers;

    public AdventurersFile(
            HashMap<String, Profession> professions,
            int initialStamina,
            int initialRank,
            int initialGold,
            ArrayList<Adventurer> prebuiltAdventurers
    ) {
        this.professions = professions;
        this.initialGold = initialGold;
        this.initialRank = initialRank;
        this.initialStamina = initialStamina;
        this.prebuiltAdventurers = prebuiltAdventurers;
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

    public WritableNativeMap toWritable() {
        WritableNativeMap map = new WritableNativeMap();

        map.putInt("initialStamina", this.initialStamina);
        map.putInt("initialRank", this.initialRank);
        map.putInt("initialGold", this.initialGold);

        WritableArray chars = new WritableNativeArray();
        for (Adventurer adventurer : this.prebuiltAdventurers) {
            chars.pushMap(adventurer.toWritable());
        }
        map.putArray("characters", chars);

        WritableArray professions = new WritableNativeArray();
        for (Profession profession : this.professions.values()) {
            professions.pushMap(profession.toWritable());
        }
        map.putArray("professions", professions);

        return map;
    }
}
