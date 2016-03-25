package com.fabledlands4android.test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.test.InstrumentationRegistry;
import android.test.InstrumentationTestCase;

import com.fabledlands4android.AbilityType;
import com.fabledlands4android.Adventurer;
import com.fabledlands4android.AdventurersFile;
import com.fabledlands4android.Book;
import com.fabledlands4android.Profession;
import com.fabledlands4android.XmlFileService;
import com.fabledlands4android.items.ArmourItem;
import com.fabledlands4android.items.GenericItem;
import com.fabledlands4android.items.InventoryItem;
import com.fabledlands4android.items.WeaponItem;
import com.facebook.soloader.SoLoader;

import java.util.ArrayList;

public class AdventurersFileParserTest extends InstrumentationTestCase {
    AdventurersFile adventurersInfo = null;
    final int testBookId = 1;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        Instrumentation instrumentation = this.getInstrumentation();
        assertNotNull(instrumentation);

        Context appContext = instrumentation.getTargetContext();
        assertNotNull(appContext);

        SoLoader.init(appContext, false);

        AssetManager assets = appContext.getAssets();
        assertNotNull(assets);

        XmlFileService service = new XmlFileService(assets);

        Book book = new Book(this.testBookId, service);
        this.adventurersInfo = book.getAdventurersInfo();
    }

    public void testInfoExists() {
        assertNotNull(this.adventurersInfo);
    }

    public void testInitialGold() {
        assertEquals(16, this.adventurersInfo.getInitialGold());
    }

    public void testInitialRank() {
        assertEquals(1, this.adventurersInfo.getInitialRank());
    }

    public void testInitialStamina() {
        assertEquals(9, this.adventurersInfo.getInitialStamina());
    }

    public void testPriestProfession() {
        Profession priest = this.adventurersInfo.getProfession("Priest");

        assertEquals("Priest", priest.getName());
        assertEquals((Integer)4, priest.getStat(AbilityType.CHARISMA));
        assertEquals((Integer)2, priest.getStat(AbilityType.COMBAT));
        assertEquals((Integer)3, priest.getStat(AbilityType.MAGIC));
        assertEquals((Integer)6, priest.getStat(AbilityType.SANCTITY));
        assertEquals((Integer)4, priest.getStat(AbilityType.SCOUTING));
        assertEquals((Integer)2, priest.getStat(AbilityType.THIEVERY));

        ArrayList<InventoryItem> initialItems = priest.getInitialItems();
        assertEquals(3, initialItems.size());

        ArmourItem leatherJerkin = (ArmourItem)initialItems.get(0);
        assertEquals((Integer)1, leatherJerkin.getBonus());

        WeaponItem mace = (WeaponItem)initialItems.get(1);
        assertEquals("mace", mace.getName());

        GenericItem map = (GenericItem)initialItems.get(2);
        assertEquals("map", map.getName());
    }

    public void testWayfarer() {
        Profession wayfarer = this.adventurersInfo.getProfession("Wayfarer");

        assertEquals("Wayfarer", wayfarer.getName());
        assertEquals((Integer)2, wayfarer.getStat(AbilityType.CHARISMA));
        assertEquals((Integer)5, wayfarer.getStat(AbilityType.COMBAT));
        assertEquals((Integer)2, wayfarer.getStat(AbilityType.MAGIC));
        assertEquals((Integer)3, wayfarer.getStat(AbilityType.SANCTITY));
        assertEquals((Integer)6, wayfarer.getStat(AbilityType.SCOUTING));
        assertEquals((Integer)4, wayfarer.getStat(AbilityType.THIEVERY));

        ArrayList<InventoryItem> initialItems = wayfarer.getInitialItems();
        assertEquals(3, initialItems.size());

        ArmourItem leatherJerkin = (ArmourItem)initialItems.get(0);
        assertEquals((Integer)1, leatherJerkin.getBonus());

        WeaponItem spear = (WeaponItem)initialItems.get(1);
        assertEquals("spear", spear.getName());

        GenericItem map = (GenericItem)initialItems.get(2);
        assertEquals("map", map.getName());
    }

    public void testIgnatius() {
        ArrayList<Adventurer> adventuerers = this.adventurersInfo.getPrebuiltAdventurers();
        Adventurer ignatius = adventuerers.get(0);

        assertEquals("Ignatius the Devout", ignatius.getName());
        assertEquals(true, ignatius.isMale());
        assertEquals("Priest", ignatius.getProfession().getName());
    }

    public void testToWritable() {
        assertNotNull(this.adventurersInfo.toWritable());
    }
}
