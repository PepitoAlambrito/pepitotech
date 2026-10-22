package com.pepito.pepitotech.item;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;

public class HammerEvents {
    @SubscribeEvent
    public void onBlockDrops(BlockDropsEvent event) {
        if (!(event.getBreaker() instanceof Player)
                || !(event.getTool().getItem() instanceof HammerItem hammer)
                || !hammer.canProcessOre(event.getState())) {
            return;
        }

        List<ItemEntity> drops = event.getDrops();
        int dropCount = drops.stream().mapToInt(drop -> drop.getItem().getCount()).sum();
        if (dropCount == 0) {
            return;
        }

        Item gravel = hammer.getOreGravel(event.getState());
        BlockPos pos = event.getPos();
        drops.clear();
        while (dropCount > 0) {
            int stackSize = Math.min(dropCount, gravel.getDefaultMaxStackSize());
            drops.add(new ItemEntity(event.getLevel(), pos.getX() + 0.5, pos.getY() + 0.5,
                    pos.getZ() + 0.5, new ItemStack(gravel, stackSize)));
            dropCount -= stackSize;
        }
    }
}
