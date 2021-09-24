package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class AvoiderReaperSwitchProcedure extends WobrModElements.ModElement {
	public AvoiderReaperSwitchProcedure(WobrModElements instance) {
		super(instance, 1240);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure AvoiderReaperSwitch!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure AvoiderReaperSwitch!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((WobrModVariables.WorldVariables.get(world).Avoider_Reapering) == (false))) {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Avoider Reapering active"), (false));
			}
			WobrModVariables.WorldVariables.get(world).Avoider_Reapering = (boolean) (true);
			WobrModVariables.WorldVariables.get(world).syncData(world);
		} else {
			if (entity instanceof PlayerEntity && !entity.world.isRemote) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Avoider Reapering disabled"), (false));
			}
			WobrModVariables.WorldVariables.get(world).Avoider_Reapering = (boolean) (false);
			WobrModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}
