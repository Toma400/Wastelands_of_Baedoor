package net.mcreator.wobr.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.FunctionObject;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class FriendlyNetherAvoiderShieldExecutionProcedure extends WobrModElements.ModElement {
	public FriendlyNetherAvoiderShieldExecutionProcedure(WobrModElements instance) {
		super(instance, 973);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure FriendlyNetherAvoiderShieldExecution!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				WobrMod.LOGGER.warn("Failed to load dependency sourceentity for procedure FriendlyNetherAvoiderShieldExecution!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == Items.NAME_TAG)) {
			if (!sourceentity.world.isRemote && sourceentity.world.getServer() != null) {
				Optional<FunctionObject> _fopt = sourceentity.world.getServer().getFunctionManager()
						.get(new ResourceLocation("wobr:friendly_nether_avoider_shield"));
				if (_fopt.isPresent()) {
					FunctionObject _fobj = _fopt.get();
					sourceentity.world.getServer().getFunctionManager().execute(_fobj, sourceentity.getCommandSource());
				}
			}
			entity.getPersistentData().putBoolean("avoider_proof", (true));
		}
	}

	@SubscribeEvent
	public void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		Entity entity = event.getTarget();
		PlayerEntity sourceentity = event.getPlayer();
		if (event.getHand() != sourceentity.getActiveHand())
			return;
		int i = event.getPos().getX();
		int j = event.getPos().getY();
		int k = event.getPos().getZ();
		World world = event.getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("sourceentity", sourceentity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
