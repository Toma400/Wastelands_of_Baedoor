package net.mcreator.wobr.procedures;

import net.minecraft.item.ItemStack;

import net.mcreator.wobr.item.StoneSpearItem;
import net.mcreator.wobr.item.IronSpearItem;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class SpearRegistryProcedure extends WobrModElements.ModElement {
	public SpearRegistryProcedure(WobrModElements instance) {
		super(instance, 1553);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure SpearRegistry!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getDouble("spear_reg")) < 15)) {
			if (((itemstack).getItem() == new ItemStack(StoneSpearItem.block, (int) (1)).getItem())) {
				(itemstack).getOrCreateTag().putDouble("spr_def_power", 2);
				(itemstack).getOrCreateTag().putDouble("spr_def_time", 80);
				(itemstack).getOrCreateTag().putDouble("spr_cooldown", 301);
				(itemstack).getOrCreateTag().putDouble("spr_rg_power", 1);
				(itemstack).getOrCreateTag().putDouble("spr_rg_factor", 2);
				(itemstack).getOrCreateTag().putDouble("spear_reg", 15);
			} else if (((itemstack).getItem() == new ItemStack(IronSpearItem.block, (int) (1)).getItem())) {
				(itemstack).getOrCreateTag().putDouble("spr_def_power", 2);
				(itemstack).getOrCreateTag().putDouble("spr_def_time", 140);
				(itemstack).getOrCreateTag().putDouble("spr_cooldown", 301);
				(itemstack).getOrCreateTag().putDouble("spr_rg_power", 1);
				(itemstack).getOrCreateTag().putDouble("spr_rg_factor", 2);
				(itemstack).getOrCreateTag().putDouble("spear_reg", 15);
			}
		}
	}
}
