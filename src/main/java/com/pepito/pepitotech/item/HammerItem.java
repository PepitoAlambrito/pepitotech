package com.pepito.pepitotech.item;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.Nullable;

public class HammerItem extends Item {
    private final ToolMaterial material;

    public HammerItem(ToolMaterial material, int durability, Item.Properties properties) {
        super(createProperties(material, durability, properties));
        this.material = material;
    }

    private static Item.Properties createProperties(ToolMaterial material, int durability, Item.Properties properties) {
        Item.Properties toolProperties = material.applyToolProperties(
                properties, BlockTags.MINEABLE_WITH_PICKAXE, 1.0F, -2.8F, 0.0F);
        toolProperties.durability(durability);

        if (material == ToolMaterial.STONE) {
            HolderGetter<Block> blockLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
            List<Tool.Rule> rules = new ArrayList<>(List.of(
                    Tool.Rule.deniesDrops(HolderSet.direct(
                            blockLookup.getOrThrow(ResourceKey.create(Registries.BLOCK, Identifier.withDefaultNamespace("iron_ore"))),
                            blockLookup.getOrThrow(ResourceKey.create(Registries.BLOCK, Identifier.withDefaultNamespace("deepslate_iron_ore"))))),
                    Tool.Rule.deniesDrops(blockLookup.getOrThrow(material.incorrectBlocksForDrops())),
                    Tool.Rule.minesAndDrops(blockLookup.getOrThrow(BlockTags.MINEABLE_WITH_PICKAXE), material.speed())));
            toolProperties.component(DataComponents.TOOL, new Tool(rules, 1.0F, 1, true));
        }

        return toolProperties;
    }

    public boolean canProcessOre(BlockState state) {
        if (state.is(Blocks.COPPER_ORE) || state.is(Blocks.DEEPSLATE_COPPER_ORE)) {
            return true;
        }

        return material == ToolMaterial.IRON
                && (state.is(Blocks.IRON_ORE) || state.is(Blocks.DEEPSLATE_IRON_ORE));
    }

    public Item getOreGravel(BlockState state) {
        if (state.is(Blocks.COPPER_ORE) || state.is(Blocks.DEEPSLATE_COPPER_ORE)) {
            return ModItems.COPPER_GRAVEL.get();
        }
        if (state.is(Blocks.IRON_ORE) || state.is(Blocks.DEEPSLATE_IRON_ORE)) {
            return ModItems.IRON_GRAVEL.get();
        }
        throw new IllegalArgumentException("Unsupported ore block state: " + state);
    }

    @Override
    public @Nullable ItemStackTemplate getCraftingRemainder(ItemInstance instance) {
        int damage = instance.getOrDefault(DataComponents.DAMAGE, 0);
        int maxDamage = instance.getOrDefault(DataComponents.MAX_DAMAGE, 0);
        int nextDamage = damage + 1;
        if (maxDamage <= 0 || nextDamage >= maxDamage) {
            return null;
        }

        if (instance instanceof ItemStack stack) {
            ItemStack remainder = stack.copy();
            remainder.setDamageValue(nextDamage);
            return ItemStackTemplate.fromNonEmptyStack(remainder);
        }

        DataComponentPatch patch = DataComponentPatch.builder()
                .set(DataComponents.DAMAGE, nextDamage)
                .build();
        return new ItemStackTemplate(instance.typeHolder(), 1, patch);
    }
}
