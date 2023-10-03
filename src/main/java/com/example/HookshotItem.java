package com.example;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.client.MinecraftClient;



public class HookshotItem extends Item {

    public HookshotItem(Settings settings) {
        super(settings);
    }

    // 右クリックされたときのアクションを定義します
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            System.out.println("HookshotItem used");
            // フックショットのアクションを実装
            HookshotAction(world, player, hand);
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }

    // フックショットのアクションを定義
    private void HookshotAction(World world, PlayerEntity player, Hand hand) {
        // プレイヤーが向いている方向にレイを飛ばして、フックが何かに当たるか確認
        MinecraftClient client = MinecraftClient.getInstance();
        HitResult hitResult = client.crosshairTarget;
        System.out.println(hitResult.getType());

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = ((BlockHitResult) hitResult).getBlockPos();

            // ここでHookshotEntityを生成してフックを飛ばす
            System.out.println("HookshotEntity created");
            HookshotEntity hookshotEntity = new HookshotEntity(world, player, blockPos);
            hookshotEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 2.0F,
                    1.0F);
            System.out.println("HookshotEntity spawned");
            world.spawnEntity(hookshotEntity);
        }
    }
}
