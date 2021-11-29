package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class StructureSwitchProcedure extends WobrModElements.ModElement {
	public StructureSwitchProcedure(WobrModElements instance) {
		super(instance, 1354);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure StructureSwitch!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure StructureSwitch!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((WobrModVariables.MapVariables.get(world).Structure_Generation == (false))) {
			WobrModVariables.MapVariables.get(world).Structure_Generation = (boolean) (true);
			WobrModVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Additional structure generation enabled"), (false));
			}
		} else if ((WobrModVariables.MapVariables.get(world).Structure_Generation == (true))) {
			WobrModVariables.MapVariables.get(world).Structure_Generation = (boolean) (false);
			WobrModVariables.MapVariables.get(world).syncData(world);
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Additional structure generation disabled"), (false));
			}
		}
	}
}
