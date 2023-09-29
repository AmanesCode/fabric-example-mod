package com.example;

import net.fabricmc.api.ModInitializer;

public class ExampleMod implements ModInitializer {
    @Override
    public void onInitialize() {
        BlockRegistry.registerModBlocks();
        ItemRegistry.registerModItems();

        ModItemGroups.registerModItemGroups();

    }
}
