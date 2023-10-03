// エフェクトを追加する

package com.example;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.registry.Registries;
import net.minecraft.entity.attribute.AttributeContainer;

import net.minecraft.world.explosion.Explosion;

public class EffectRegistry {
    public static final TestEffect TEST_EFFECT = registerEffect("test_effect", new TestEffect());
    public static final Explosive EXPLOSIVE = registerEffect("explosive", new Explosive());

    // 実際の効果
    public static class TestEffect extends StatusEffect {
        // コンストラクタ
        public TestEffect() {
            super(StatusEffectCategory.BENEFICIAL, 0x98D982);
        }

        @Override
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            return true;
        }

        // DamageSource.MAGICと同じ効果を持つDamageSourceを作成する

        // 効果
        // かかった相手は効果持続中はHP1以下にならない
        @Override
        public void applyUpdateEffect(net.minecraft.entity.LivingEntity entity, int amplifier) {
            // レベルに応じて最低HPを変更
            int minHealth = 1 + amplifier;

            // ダメージを受ける前にHPが最低HP以下にならないようにする
            if (entity.getHealth() <= minHealth && entity.getHealth() > 0) {
                entity.setHealth(minHealth);

            }
            if (entity.getHealth() <= 0) {
                entity.removeStatusEffect(this);
            }
        }
    }

    // 効果終了時に地形を破壊しない大爆発を起こし更にプレイヤーが死亡するエフェクト
    public static class Explosive extends StatusEffect {
        public Explosive() {
            super(StatusEffectCategory.HARMFUL, 0x98D982);
        }

        @Override
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            return false;
        }

        // 効果終了時に地形を破壊しない大爆発を起こし更にプレイヤーが死亡するエフェクト

        @Override
        public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
            // msgを表示
            entity.sendMessage(net.minecraft.text.Text.Serializer
                    .fromJson("{\"text\":\"" + entity.getName().getString() + "は爆発した！\"}"));
            // 爆発を作成
            Explosion explosion = new Explosion(entity.getEntityWorld(), entity, entity.getX(),
                    entity.getY(), entity.getZ(), amplifier, false, Explosion.DestructionType.KEEP);
            // 爆発を起こす
            explosion.collectBlocksAndDamageEntities();
            // 爆発サウンドのカスタマイズ
            entity.getEntityWorld().playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                    SoundEvents.ENTITY_GENERIC_EXPLODE, // カスタムサウンドを指定
                    SoundCategory.BLOCKS, 1.0F, 1.0F);
        }



    }

    // エフェクトを登録するメソッド
    public static <T extends StatusEffect> T registerEffect(String name, T effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(ModConfig.MOD_ID, name),
                effect);
    }

    public static void registerModStatusEffects() {
        String templateString = "Registering Mod Effects for " + ModConfig.MOD_NAME;
        System.out.println(templateString);
        ModConfig.LOGGER.info(templateString);
    }

}
