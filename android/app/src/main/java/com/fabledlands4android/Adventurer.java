package com.fabledlands4android;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import java.util.HashMap;

public class Adventurer {
    private HashMap<AbilityType, Integer> _statModifiers = new HashMap<>();
    private Profession profession;

    private String name;
    private Integer stamina, rank, gold;
    private Boolean isMale;

    public Adventurer(
            String name,
            Integer rank,
            Integer stamina,
            Integer gold,

            Profession profession,
            Boolean isMale
    ) {
        this.name = name;
        this.profession = profession;
        this.isMale = isMale;

        this.rank = rank;
        this.stamina = stamina;
        this.gold = gold;
    }

    public String getName() {
        return this.name;
    }

    public Integer getStamina() {
        return this.stamina;
    }

    public Integer getRank() {
        return this.rank;
    }

    public Integer getGold() {
        return this.gold;
    }

    public Integer getStat(AbilityType attribute) {
        Integer baseStat = this.profession.getStat(attribute);
        Integer statMod = this._statModifiers.get(attribute);
        return baseStat + statMod;
    }

    public void modifyStat(AbilityType attribute, Integer modifier) {
        Integer currentMod = this._statModifiers.get(attribute);
        Integer newMod = currentMod + modifier;
        this._statModifiers.put(attribute, newMod);
    }

    public boolean isMale() {
        return isMale;
    }

    public Profession getProfession() {
        return this.profession;
    }

    public WritableMap toWritable() {
        WritableNativeMap a = new WritableNativeMap();
        a.putString("name", this.getName());
        a.putMap("profession", this.getProfession().toWritable());
        return a;
    }
}
