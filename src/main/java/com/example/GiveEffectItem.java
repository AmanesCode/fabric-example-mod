package com.example;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.ActionResult;

public class GiveEffectItem extends Item {
    private final StatusEffectInstance effect;

    public GiveEffectItem(Settings settings, StatusEffectInstance effect) {
        super(settings);
        this.effect = effect;
    }

    // アイテムを持ってい右クリックで効果を付与する
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        // 特定の処理をここに追加
        if (!world.isClient) {
            // サーバーサイドの処理を追加
            StatusEffectInstance copyEffect = new StatusEffectInstance(effect);
            player.addStatusEffect(copyEffect);
            System.out.println(copyEffect.getEffectType() + "effect added");
            System.out.println("duration:" + copyEffect.getDuration() + " amplifier:"
                    + copyEffect.getAmplifier());
        }
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }

}
