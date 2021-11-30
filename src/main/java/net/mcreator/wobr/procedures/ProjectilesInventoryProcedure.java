package net.mcreator.wobr.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.ThrownDaggerItem;
import net.mcreator.wobr.item.StoneThrowingAxeItem;
import net.mcreator.wobr.item.StoneJavelinItem;
import net.mcreator.wobr.item.PoisonedThrownDaggerItem;
import net.mcreator.wobr.item.IronThrowingAxeItem;
import net.mcreator.wobr.item.BoneThrowingAxeItem;
import net.mcreator.wobr.item.BoneJavelinItem;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class ProjectilesInventoryProcedure extends WobrModElements.ModElement {
	public ProjectilesInventoryProcedure(WobrModElements instance) {
		super(instance, 2095);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure ProjectilesInventory!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				WobrMod.LOGGER.warn("Failed to load dependency itemstack for procedure ProjectilesInventory!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getItem() == ThrownDaggerItem.block) || ((itemstack).getItem() == PoisonedThrownDaggerItem.block))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown((itemstack).getItem(), (int) 20);
		} else if ((((itemstack).getItem() == BoneJavelinItem.block) || ((itemstack).getItem() == StoneJavelinItem.block))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown((itemstack).getItem(), (int) 60);
		} else if (((((itemstack).getItem() == BoneThrowingAxeItem.block) || ((itemstack).getItem() == StoneThrowingAxeItem.block))
				|| ((itemstack).getItem() == IronThrowingAxeItem.block))) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).getCooldownTracker().setCooldown((itemstack).getItem(), (int) 40);
		}
		((itemstack)).shrink((int) 1);
	}
}
