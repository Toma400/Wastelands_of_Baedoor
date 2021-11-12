package net.mcreator.wobr.procedures;

import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class ModeCheckProcedure extends WobrModElements.ModElement {
	public ModeCheckProcedure(WobrModElements instance) {
		super(instance, 938);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ModeCheck!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:wobn_firearms").toLowerCase(java.util.Locale.ENGLISH)))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))) {
			if ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("wobr:wobr_firearm_mode").toLowerCase(java.util.Locale.ENGLISH)))
					.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))) {
				if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getDouble("Shoot_Mode")) == 0)) {
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putDouble("Shoot_Mode", 1);
					entity.getPersistentData().putString("Message", "          Changed mode to alternative");
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						MessageManagerProcedure.executeProcedure($_dependencies);
					}
				} else if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getDouble("Shoot_Mode")) == 1)) {
					((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
							.putDouble("Shoot_Mode", 0);
					entity.getPersistentData().putString("Message", "           Changed mode to standard");
					{
						Map<String, Object> $_dependencies = new HashMap<>();
						$_dependencies.put("entity", entity);
						MessageManagerProcedure.executeProcedure($_dependencies);
					}
				}
			} else {
				entity.getPersistentData().putString("Message", "         This item doesn't have modes");
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					MessageManagerProcedure.executeProcedure($_dependencies);
				}
			}
		} else if ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:wobr_firearms").toLowerCase(java.util.Locale.ENGLISH)))
				.contains(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				GunAddOnModesProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
