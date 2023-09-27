package com.example;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;


public class ModItemGroups {
    public static void registerModItemGroups() {
        ModConfig.LOGGER.info("Registering Mod Item Groups for " + ModConfig.MOD_ID);
    }

    public static final ItemGroup MILK_CHOICE = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ModConfig.MOD_ID, ModConfig.MOD_ID),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup." + ModConfig.MOD_ID))
                    .icon(() -> new ItemStack(ItemRegistry.items.get(0)))
                    .entries((displayContext, entries) -> {
                        for (Item item : ItemRegistry.items) {
                            entries.add(item);
                        }
                        entries.add(Items.MILK_BUCKET);
                    }).build());
}
