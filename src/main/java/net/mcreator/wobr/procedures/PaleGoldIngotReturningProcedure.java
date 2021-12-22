package net.mcreator.wobr.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class PaleGoldIngotReturningProcedure extends WobrModElements.ModElement {
	public PaleGoldIngotReturningProcedure(WobrModElements instance) {
		super(instance, 1500);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure PaleGoldIngotReturning!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				WobrMod.LOGGER.warn("Failed to load dependency itemstack for procedure PaleGoldIngotReturning!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double time_to_convert = 0;
		if ((time_to_convert < 1200)) {
			time_to_convert = (double) (time_to_convert + 1);
		} else {
			((itemstack)).shrink((int) 1);
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(Items.GOLD_INGOT);
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
			time_to_convert = (double) 0;
		}
	}
}
