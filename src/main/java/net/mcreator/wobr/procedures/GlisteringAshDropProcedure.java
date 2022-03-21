package net.mcreator.wobr.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.block.Blocks;

import net.mcreator.wobr.item.GlisteringAshItem;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class GlisteringAshDropProcedure extends WobrModElements.ModElement {
	public GlisteringAshDropProcedure(WobrModElements instance) {
		super(instance, 1149);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure GlisteringAshDrop!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure GlisteringAshDrop!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure GlisteringAshDrop!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure GlisteringAshDrop!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure GlisteringAshDrop!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((WobrModVariables.MapVariables.get(world).KF_Drop_Glister == (true))) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.END_STONE)
					&& ((world.getDimension().getType().getId()) == (1)))) {
				if (((EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,
						((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)) != 0))) {
					if ((((WobrModVariables.MapVariables.get(world).KF_Drop_Glister_A / 100)
							+ (((EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE,
									((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))) * 5)
									/ 100)) >= Math.random())) {
						if (!world.getWorld().isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(GlisteringAshItem.block));
							entityToSpawn.setPickupDelay((int) 10);
							world.addEntity(entityToSpawn);
						}
					}
				} else {
					if (((WobrModVariables.MapVariables.get(world).KF_Drop_Glister_A / 100) >= Math.random())) {
						if (!world.getWorld().isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), x, y, z, new ItemStack(GlisteringAshItem.block));
							entityToSpawn.setPickupDelay((int) 10);
							world.addEntity(entityToSpawn);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event) {
		Entity entity = event.getPlayer();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("xpAmount", event.getExpToDrop());
		dependencies.put("x", event.getPos().getX());
		dependencies.put("y", event.getPos().getY());
		dependencies.put("z", event.getPos().getZ());
		dependencies.put("px", entity.getPosX());
		dependencies.put("py", entity.getPosY());
		dependencies.put("pz", entity.getPosZ());
		dependencies.put("world", event.getWorld().getWorld());
		dependencies.put("entity", entity);
		dependencies.put("blockstate", event.getState());
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
