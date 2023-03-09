package toma400.wobr.core;

import toma400.wobr.Wobr;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class WobrItems {

    static final Item NETHER_SOUL_ESSENCE = new Item(new FabricItemSettings());

    static final Map<String, Item> WobrItemsMap = new HashMap<>();
    static {
        WobrItemsMap.put("nether_soul_essence", NETHER_SOUL_ESSENCE);
    }

    public static void registerItems() {

        //Registry.register(Registries.ITEM, new Identifier(Wobr.MOD_ID, "nether_soul_essence"), NETHER_SOUL_ESSENCE);

        for (Map.Entry<String, Item> item : WobrItemsMap.entrySet()) {
            Registry.register(Registries.ITEM, new Identifier(Wobr.MOD_ID, item.getKey()), item.getValue());
        }
    }
}