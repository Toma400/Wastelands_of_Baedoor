package net.mcreator.wobr.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.entity.OrmathWarriorEntity;
import net.mcreator.wobr.entity.OrmathVillagerEntity;
import net.mcreator.wobr.entity.OrmathShamanEntity;
import net.mcreator.wobr.entity.OrmathRangedWarriorEntity;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class OrmathFriendlyFireDefenceProcedure extends WobrModElements.ModElement {
	public OrmathFriendlyFireDefenceProcedure(WobrModElements instance) {
		super(instance, 1313);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure OrmathFriendlyFireDefence!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure OrmathFriendlyFireDefence!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if (((((sourceentity instanceof OrmathRangedWarriorEntity.CustomEntity) || (sourceentity instanceof OrmathWarriorEntity.CustomEntity))
				|| (sourceentity instanceof OrmathShamanEntity.CustomEntity))
				&& (((entity instanceof OrmathVillagerEntity.CustomEntity) || (entity instanceof OrmathWarriorEntity.CustomEntity))
						|| ((entity instanceof OrmathRangedWarriorEntity.CustomEntity) || (entity instanceof OrmathShamanEntity.CustomEntity))))) {
			if (dependencies.get("event") != null) {
				Object _obj = dependencies.get("event");
				if (_obj instanceof Event) {
					Event _evt = (Event) _obj;
					if (_evt.isCancelable())
						_evt.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttacked(LivingHurtEvent event) {
		if (event != null && event.getEntity() != null) {
			Entity entity = event.getEntity();
			Entity sourceentity = event.getSource().getTrueSource();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			double amount = event.getAmount();
			World world = entity.world;
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("amount", amount);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("sourceentity", sourceentity);
			dependencies.put("event", event);
			this.executeProcedure(dependencies);
		}
	}
}
