package fr.theorozier.webstreamer;

import fr.theorozier.webstreamer.display.DisplayBlock;
import fr.theorozier.webstreamer.display.DisplayBlockEntity;
import fr.theorozier.webstreamer.display.DisplayBlockItem;
import fr.theorozier.webstreamer.display.DisplayNetworking;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebStreamerMod implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("WebStreamer");

    public static Block DISPLAY_BLOCK;
    public static DisplayBlockItem DISPLAY_ITEM;
    public static BlockEntityType<DisplayBlockEntity> DISPLAY_BLOCK_ENTITY;

    @Override
    public void onInitialize() {

        DISPLAY_BLOCK = new DisplayBlock();
        DISPLAY_ITEM = new DisplayBlockItem(DISPLAY_BLOCK, new FabricItemSettings());

        ItemGroup tabNES = new FabricItemGroupBuilderImpl(new Identifier("webstreamer", "group"))
                .icon(() -> new ItemStack(Items.REDSTONE))
                .entries((context, entries) -> {
                    entries.add(DISPLAY_ITEM);
                })
                .build();

        Registry.register(Registries.BLOCK, "webstreamer:display", DISPLAY_BLOCK);
        Registry.register(Registries.ITEM, "webstreamer:display", DISPLAY_ITEM);
        DISPLAY_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, "webstreamer:display", FabricBlockEntityTypeBuilder.create(DisplayBlockEntity::new, DISPLAY_BLOCK).build());
        
        DisplayNetworking.registerDisplayUpdateReceiver();
        
        LOGGER.info("WebStreamer started.");

    }

}
