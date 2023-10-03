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
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;


public class ModItemGroups {
    public static void registerModItemGroups() {
        ModConfig.LOGGER.info("Registering Mod Item Groups for " + ModConfig.MOD_ID);
        ItemGroup MILK_CHOICE = Registry.register(Registries.ITEM_GROUP,
                new Identifier(ModConfig.MOD_ID, ModConfig.MOD_ID),
                FabricItemGroup.builder()
                        .displayName(Text.translatable("itemgroup." + ModConfig.MOD_ID))
                        .icon(() -> new ItemStack(ItemRegistry.items.get(0)))
                        .entries((displayContext, entries) -> {

                            for (Block block : BlockRegistry.blocks) {
                                entries.add(block);
                                System.out.println(block.getName().getString());
                            }

                            for (Item item : ItemRegistry.items) {
                                entries.add(item);
                                System.out.println(item.getName().getString());
                            }
                            entries.add(Items.MILK_BUCKET);
                            entries.add(Items.BUCKET);
                            entries.add(Blocks.IRON_BLOCK);
                        }).build());
    }
}
