package com.pepito.pepitotech.datagen;

import com.pepito.pepitotech.Pepitotech;
import com.pepito.pepitotech.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public class ModModelProvider extends ModelProvider {
    public ModModelProvider(PackOutput output) {
        super(output, Pepitotech.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockmodels, ItemModelGenerators itemmodels) {
        itemmodels.generateFlatItem(ModItems.GOLD_COIN.get(), ModelTemplates.FLAT_ITEM);
        itemmodels.generateFlatItem(ModItems.IRON_COIN.get(), ModelTemplates.FLAT_ITEM);
    }
}
