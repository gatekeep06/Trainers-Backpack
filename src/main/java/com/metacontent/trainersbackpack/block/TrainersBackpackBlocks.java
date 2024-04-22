package com.metacontent.trainersbackpack.block;

import com.metacontent.trainersbackpack.TrainersBackpack;
import com.tiviacz.travelersbackpack.blocks.TravelersBackpackBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TrainersBackpackBlocks {
    public static final TravelersBackpackBlock HEALING_BACKPACK_BLOCK = registerBackpackBlock("healing_backpack", FabricBlockSettings.copyOf(Blocks.WHITE_WOOL));

    private static TravelersBackpackBlock registerBackpackBlock(String name, FabricBlockSettings settings) {
        return Registry.register(Registries.BLOCK, new Identifier(TrainersBackpack.ID, name),
                new TravelersBackpackBlock(settings));
    }

    public static void registerBlocks() {
        TrainersBackpack.LOGGER.info("Registering blocks for " + TrainersBackpack.ID);
    }
}
