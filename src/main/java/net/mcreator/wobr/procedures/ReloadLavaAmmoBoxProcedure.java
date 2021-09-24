package net.mcreator.wobr.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.LargeBulletItem;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class ReloadLavaAmmoBoxProcedure extends WobrModElements.ModElement {
	public ReloadLavaAmmoBoxProcedure(WobrModElements instance) {
		super(instance, 813);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ReloadLavaAmmoBox!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
				.getDouble("Ammo")) < 120)) {
			for (int index0 = 0; index0 < (int) (10); index0++) {
				if (((entity instanceof PlayerEntity)
						? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(LargeBulletItem.block, (int) (1)))
						: false)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _stktoremove = new ItemStack(LargeBulletItem.block, (int) (1));
						((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
					}
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putDouble("Ammo", ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getOrCreateTag().getDouble("Ammo")) + 1));
				} else {
					entity.getPersistentData().putString("Message", "              No bullets in inventory!");
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						MessageManagerProcedure.executeProcedure($_dependencies);
					}
					break;
				}
			}
		} else if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
				.getDouble("Ammo")) >= 120)) {
			entity.getPersistentData().putString("Message", "                   Full ammo box!");
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				MessageManagerProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
