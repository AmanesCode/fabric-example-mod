package com.example;

import java.util.ArrayList;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemRegistry {

        // アイテムを登録するためのリスト
        public static final ArrayList<Item> items = new ArrayList<Item>();

        static {
                // アイテムを登録
                items.add(registerItem("tes_item", new Item(new FabricItemSettings())));
                items.add(registerItem("tes_item2", new Item(new FabricItemSettings())));
                items.add(registerItem("tes_item3",
                                new ItemGetLastDeathLocation(new FabricItemSettings())));
                items.add(registerItem("tes_item4", new GiveEffectItem(new FabricItemSettings(),
                                new StatusEffectInstance((StatusEffect) Registries.STATUS_EFFECT
                                                .get(new Identifier("minecraft", "poison")), 100,
                                                20))));
                items.add(registerItem("tes_item5", new GiveEffectItem(new FabricItemSettings(),
                                new StatusEffectInstance(
                                                (StatusEffect) Registries.STATUS_EFFECT.get(
                                                                new Identifier(ModConfig.MOD_ID,
                                                                                "test_effect")),
                                                100, 4))));
                items.add(registerItem("tes_item6", new GiveEffectItem(new FabricItemSettings(),
                                new StatusEffectInstance((StatusEffect) Registries.STATUS_EFFECT
                                                .get(new Identifier(ModConfig.MOD_ID, "explosive")),
                                                20, 4))));
                items.add(registerItem("hookshot", new HookshotItem(new FabricItemSettings())));
        }

        public static void addItemsToIngredientItemGroupe(FabricItemGroupEntries entries) {
                for (Item item : items) {
                        entries.add(item);
                }
        }

        // アイテムを登録するメソッド
        public static Item registerItem(String name, Item item) {
                return Registry.register(Registries.ITEM, new Identifier(ModConfig.MOD_ID, name),
                                item);
        }

        public static void registerModItems() {
                String templateString = "Registering Mod Items for " + ModConfig.MOD_NAME;
                System.out.println(templateString);
                ModConfig.LOGGER.info(templateString);

                ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                                .register(ItemRegistry::addItemsToIngredientItemGroupe);
        }
}
