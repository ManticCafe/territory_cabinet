package com.ManticCafe.Territory_cabinet.common.multiblock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.ManticCafe.Territory_cabinet.main;

public class MultiblockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, main.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, main.MODID);

    //register

    // 注册多方块主方块
    public static final RegistryObject<Block> territory_cabinet = BLOCKS.register("territory_cabinet",
            () -> new MultiBlock(BlockBehaviour.Properties.of().strength(4.0f).sound(SoundType.METAL)));

    // 注册多方块部分方块（不可见部分）
    public static final RegistryObject<Block> territory_cabinet_part = BLOCKS.register("territory_cabinet_part",
            () -> new MultiBlockPart(BlockBehaviour.Properties.of().noOcclusion().noCollission()));

    // 注册多方块主方块实体
    public static final RegistryObject<BlockEntityType<MultiBlockEntity>> MULTI_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("territory_cabinet_entity",
                    () -> BlockEntityType.Builder.of(MultiBlockEntity::new, territory_cabinet.get()).build(null));

    // 注册多方块部分方块实体
    public static final RegistryObject<BlockEntityType<MultiBlockPartEntity>> MULTI_BLOCK_PART_ENTITY =
            BLOCK_ENTITIES.register("territory_cabinet_part_entity",
                    () -> BlockEntityType.Builder.of(MultiBlockPartEntity::new, territory_cabinet_part.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
        System.out.println("Multiblock blocks and entities registered successfully");
    }
}