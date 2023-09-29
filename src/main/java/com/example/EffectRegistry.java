// エフェクトを追加する

package com.example;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class EffectRegistry {
    public static final TestEffect TEST_EFFECT = registerEffect("test_effect", new TestEffect());

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

        // 効果
        // かかった相手は効果持続中はHP1以下にならない
        @Override
        public void applyUpdateEffect(net.minecraft.entity.LivingEntity entity, int amplifier) {
            if (entity.getHealth() <= 1) {
                entity.setHealth(1);
            }
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
