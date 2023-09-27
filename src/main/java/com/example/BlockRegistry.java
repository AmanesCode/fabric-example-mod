package com.example;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;

public class BlockRegistry {

    // ブロックを登録するためのリスト
    public static final ArrayList<Block> blocks = new ArrayList<Block>();

    static {
        Block TES_BLOCK =
                registerBlock("tes_block", new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
        blocks.add(TES_BLOCK);
        ItemRegistry.items.add(registerBlockItem("tes_block", TES_BLOCK));
    }

    // ブロックを登録するメソッド
    public static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(ModConfig.MOD_ID, name), block);
    }

    // ブロックアイテムを登録するメソッド
    public static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(ModConfig.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }
}


