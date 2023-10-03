package com.example;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.Entity;

import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.entity.SpawnGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;



public class HookshotEntity extends PersistentProjectileEntity {
    private static final double GRAVITY = 0.04;
    private static final double DRAG = 0.02;

    private final BlockPos hitPos;
    private boolean hooked = false;
    private Vec3d velocity = Vec3d.ZERO;

    public HookshotEntity(EntityType<? extends PersistentProjectileEntity> entityType,
            World world) {
        super(entityType, world);
        this.hitPos = null;
    }

    public HookshotEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world,
            BlockPos hitPos) {
        super(entityType, world);
        this.hitPos = hitPos;
    }

    public HookshotEntity(World world, LivingEntity owner, BlockPos hitPos) {
        super(HookshotEntityType, world);
        this.hitPos = hitPos;
    }

    public HookshotEntity(World world, LivingEntity owner) {
        super(HookshotEntityType, world);
        this.hitPos = null;
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        System.out.println("HookshotEntity onBlockHit");
        hooked = true;
        // ここでぶら下がる処理を実装
    }

    @Override
    public void tick() {
        super.tick();

        if (hooked) {
            System.out.println("HookshotEntity hooked");
            // 引力を適用して速度を減衰させる
            velocity = velocity.subtract(0, GRAVITY, 0).multiply(1.0 - DRAG);

            // プレイヤーを引っかかった位置に向かって加速度をかける
            Vec3d direction = new Vec3d(hitPos.getX() + 0.5 - getX(), hitPos.getY() + 0.5 - getY(),
                    hitPos.getZ() + 0.5 - getZ()).normalize();
            velocity = velocity.add(direction.multiply(0.1, 0.1, 0.1));

            // プレイヤーの位置を更新
            setPos(getX() + velocity.x, getY() + velocity.y, getZ() + velocity.z);

            // プレイヤーが引っかかった位置に十分近づいたらエンティティを削除
            if (getPos().distanceTo(new Vec3d(hitPos.getX() + 0.5, hitPos.getY() + 0.5,
                    hitPos.getZ() + 0.5)) < 0.5) {
                remove(RemovalReason.DISCARDED);
            }
        }
    }

    @Override
    public ItemStack asItemStack() {
        // ここで適切なItemStackを生成して返す
        // 例えば、ItemStackのコンストラクタにEntityTypeとWorldを渡して新しいItemStackを作成できます
        return new ItemStack(Registries.ITEM.get(new Identifier(ModConfig.MOD_ID, "hookshot")));
    }

    @Override
    public void initDataTracker() {
        // 何もしないでも良いです
    }

    public static final EntityType<HookshotEntity> HookshotEntityType =
            Registry.register(Registries.ENTITY_TYPE, new Identifier(ModConfig.MOD_ID, "hookshot"),
                    FabricEntityTypeBuilder
                            .<HookshotEntity>create(SpawnGroup.MISC, HookshotEntity::new)
                            .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static void registerHookshotEntity() {
        System.out.println("Registering Hookshot Entity for " + ModConfig.MOD_ID);
    }

}
