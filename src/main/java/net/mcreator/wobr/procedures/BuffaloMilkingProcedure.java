package net.mcreator.wobr.procedures;

import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class BuffaloMilkingProcedure extends WobrModElements.ModElement {
	public BuffaloMilkingProcedure(WobrModElements instance) {
		super(instance, 1778);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				WobrMod.LOGGER.warn("Failed to load dependency sourceentity for procedure BuffaloMilking!");
			return;
		}
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == Items.BUCKET)) {
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _stktoremove = ((sourceentity instanceof LivingEntity)
						? ((LivingEntity) sourceentity).getHeldItemMainhand()
						: ItemStack.EMPTY);
				((PlayerEntity) sourceentity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
			}
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(Items.MILK_BUCKET);
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
		}
	}
}
