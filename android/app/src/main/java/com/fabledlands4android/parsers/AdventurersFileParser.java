package com.fabledlands4android.parsers;

import com.fabledlands4android.*;
import com.fabledlands4android.items.InventoryItem;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AdventurersFileParser implements AbstractFileParser {
    public Integer getAmount(XmlPullParser parser) {
        String value = parser.getAttributeValue(null, "amount");
        return Integer.parseInt(value);
    }

    public HashMap<String, Profession> parseProfessions(XmlPullParser parser, HashMap<String, Profession> professions)
            throws XmlPullParserException, IOException {
        AbilityType[] abilityOrder = null;

        while (true) {
            switch (parser.next()) {
                case XmlPullParser.START_TAG:
                    switch (parser.getName()) {
                        case "header":
                            String headers = parser.nextText();
                            String[] parts = headers.split(" ");
                            abilityOrder = new AbilityType[parts.length];
                            for (int x = 0; x < parts.length; x++) {
                                abilityOrder[x] = AbilityType.valueOf(parts[x]);
                            }
                            break;

                        case "profession":
                            String professionName = parser.getAttributeValue(null, "name");
                            String abilityText = parser.nextText();
                            String[] abilityParts = abilityText.split(" ");

                            HashMap<AbilityType, Integer> currentAbilities = new HashMap<>(abilityParts.length);
                            for (int x = 0; x < abilityParts.length; x++) {
                                currentAbilities.put(
                                        abilityOrder[x],
                                        Integer.parseInt(abilityParts[x])
                                );
                            }

                            professions.put(
                                    professionName,
                                    new Profession(
                                            professionName,
                                            currentAbilities
                                    )
                            );
                            break;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    switch (parser.getName()) {
                        case "abilities":
                            return professions;
                    }

                    break;
            }
        }
    }

    public ArrayList<InventoryItem> parseItems(XmlPullParser parser, HashMap<String, Profession> professions)
            throws IOException, XmlPullParserException
    {
        ArrayList<InventoryItem> items = new ArrayList<>();
        while (true) {
            switch (parser.next()) {
                case XmlPullParser.START_TAG:
                    items.add(ItemParser.parse(parser));
                    break;

                case XmlPullParser.END_TAG:
                    switch (parser.getName()) {
                        case "items":
                            return items;
                    }
            }
        }
    }

    private ArrayList<Adventurer> parsePrebuildAdventurers(
            XmlPullParser parser, HashMap<String, Profession> professions,
            Integer initialStamina, Integer initialRank, Integer initialGold
    )
            throws IOException, XmlPullParserException
    {
        ArrayList<Adventurer> adventurers = new ArrayList<>();

        while (true) {
            switch (parser.next()) {
                case XmlPullParser.START_TAG:
                    switch (parser.getName()) {
                        case "adventurer":
                            String name = parser.getAttributeValue(null, "name");
                            String professionName = parser.getAttributeValue(null, "profession");
                            String genderName = parser.getAttributeValue(null, "gender");

                            Profession profession = professions.get(professionName);
                            if (profession == null) {
                                throw new IOException("No profession: " + professionName);
                            }

                            adventurers.add(
                                    new Adventurer(
                                            name, initialRank, initialStamina,
                                            initialGold, profession, genderName.equalsIgnoreCase("m")
                                    )
                            );
                            break;
                    }

                case XmlPullParser.END_TAG:
                    switch (parser.getName()) {
                        case "adventurer":
                            return adventurers;
                    }
            }
        }
    }

    @Override
    public Object parse(XmlPullParser parser)
            throws XmlPullParserException, IOException {
        ArrayList<Adventurer> prebuiltAdventurers = new ArrayList<>();
        HashMap<String, Profession> professions = new HashMap<>();
        Integer initialStamina = 0,
                initialRank = 0,
                initialGold = 0;
        ArrayList<InventoryItem> items = new ArrayList<>();

        int eventType;
        while ((eventType = parser.getEventType()) != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    switch (parser.getName()) {
                        case "abilities":
                            this.parseProfessions(parser, professions);
                            break;

                        case "items":
                            items = this.parseItems(parser, professions);
                            break;

                        case "starting":
                            prebuiltAdventurers = this.parsePrebuildAdventurers(
                                    parser, professions,
                                    initialStamina, initialRank, initialGold
                            );
                            break;

                        case "stamina":
                            initialStamina = this.getAmount(parser);
                            break;

                        case "rank":
                            initialRank = this.getAmount(parser);
                            break;

                        case "gold":
                            initialRank = this.getAmount(parser);
                            break;
                    }
                    break;
            }
        }

        return new AdventurersFile(
                professions,
                initialStamina,
                initialRank,
                initialGold,
                items,
                prebuiltAdventurers
        );
    }
}