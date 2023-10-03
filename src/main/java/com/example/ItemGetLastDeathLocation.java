package com.example;

import java.util.regex.Matcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import java.util.regex.Pattern;

public class ItemGetLastDeathLocation extends Item {

    public ItemGetLastDeathLocation(Settings settings) {
        super(settings);
    }

    // 右クリック時の処理
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        // 特定の処理をここに追加
        if (!world.isClient) {
            // サーバーサイドの処理を追加
            // 正規表現で dimension と BlockPos の部分を抜き出す
            Pattern pattern = Pattern
                    .compile("\\[ResourceKey\\[([^/]+) \\/ ([^\\]]+)\\] BlockPos\\{(.*?)\\}");
            Matcher matcher = pattern.matcher(player.getLastDeathPos().toString());

            if (matcher.find()) {
                String dimension = matcher.group(2);
                String blockPos = matcher.group(3);

                player.sendMessage(net.minecraft.text.Text.Serializer
                        .fromJson("{\"text\":\"" + player.getName().getString() + "の最後の死亡地点は\"}"));
                player.sendMessage(net.minecraft.text.Text.Serializer
                        .fromJson("{\"text\":\"" + dimension + "の" + blockPos + "です\"}"));
            }
        }
        return new TypedActionResult<ItemStack>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }
}
