package net.mcreator.wobr.procedures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.HandmadeRevolverItem;
import net.mcreator.wobr.item.AmmoBoxLavaItem;
import net.mcreator.wobr.item.AmmoBoxIronItem;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class TypeofAmmoCheckProcedure extends WobrModElements.ModElement {
	public TypeofAmmoCheckProcedure(WobrModElements instance) {
		super(instance, 754);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure TypeofAmmoCheck!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				WobrMod.LOGGER.warn("Failed to load dependency itemstack for procedure TypeofAmmoCheck!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:wobn_firearms").toLowerCase(java.util.Locale.ENGLISH)))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))) {
			if ((((ItemTags.getCollection().getOrCreate(new ResourceLocation(("wobr:wobr_firearm_revolver").toLowerCase(java.util.Locale.ENGLISH)))
					.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))
					&& (!(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == HandmadeRevolverItem.block)))
					|| (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == AmmoBoxIronItem.block))) {
				(itemstack).getOrCreateTag().putString("Ammo_Using", "Small_Bullet");
			} else if (((ItemTags.getCollection().getOrCreate(new ResourceLocation(("wobr:wobr_firearm_rifle").toLowerCase(java.util.Locale.ENGLISH)))
					.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))
					|| (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == AmmoBoxLavaItem.block))) {
				(itemstack).getOrCreateTag().putString("Ammo_Using", "Large_Bullet");
			} else if ((ItemTags.getCollection()
					.getOrCreate(new ResourceLocation(("wobr:wobr_firearm_shotgun").toLowerCase(java.util.Locale.ENGLISH)))
					.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))) {
				(itemstack).getOrCreateTag().putString("Ammo_Using", "Slug");
			} else if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == HandmadeRevolverItem.block)) {
				(itemstack).getOrCreateTag().putString("Ammo_Using", "Gunpowder");
			}
		}
	}
}
