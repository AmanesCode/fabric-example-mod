package com.example;

import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.PersistentProjectileEntity;

public class HookshotEntityRenderer extends ProkectileEntityRenderer<HookshotEntity> {
    public HookshotEntityRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public Identifier getTexture(HookshotEntity entity) {
        // Return the texture identifier for your HookshotEntity
        return new Identifier("your_mod_id", "textures/entity/hookshot_entity.png");
    }
}
