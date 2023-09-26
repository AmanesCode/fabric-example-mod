package com.example;

import java.util.ArrayList;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {

    // アイテムを登録するためのリスト
    public static ArrayList<Item> items = new ArrayList<Item>();

    static {
        // アイテムを登録
        items.add(registerItem("tes_item", new Item(new FabricItemSettings())));
    }

    public static void addItemsToIngredientItemGroupe(FabricItemGroupEntries entries) {
        for (Item item : items) {
            entries.add(item);
        }
    }

    // アイテムを登録するメソッド
    public static Item registerItem(String name, Item item) {
        // itemDataからアイテムを登録
        return Registry.register(Registries.ITEM, new Identifier(ModConfig.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Hello Fabric world!");
        ModConfig.LOGGER.info("Registering Mod Items for " + ModConfig.MOD_NAME);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register(ItemRegistry::addItemsToIngredientItemGroupe);
    }
}
