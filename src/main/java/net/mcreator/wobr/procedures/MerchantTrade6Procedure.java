package net.mcreator.wobr.procedures;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec2f;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.ICommandSource;
import net.minecraft.command.CommandSource;
import net.minecraft.block.Blocks;

import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

@WobrModElements.ModElement.Tag
public class MerchantTrade6Procedure extends WobrModElements.ModElement {
	public MerchantTrade6Procedure(WobrModElements instance) {
		super(instance, 1557);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure MerchantTrade6!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure MerchantTrade6!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure MerchantTrade6!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure MerchantTrade6!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure MerchantTrade6!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double money = 0;
		money = (double) 0;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if ((Blocks.DIAMOND_BLOCK.asItem() == (itemstackiterator).getItem())) {
						money = (double) (money + (((itemstackiterator)).getCount()));
					}
				}
			}
		}
		if ((money >= 1)) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(Blocks.DIAMOND_BLOCK);
				((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 1);
			}
			if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
				world.getWorld().getServer().getCommandManager().handleCommand(
						new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
								new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
						"give @a[distance=..1] wobr:glowing_obsidian 1");
			}
		} else {
			money = (double) 0;
			{
				AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
				if (_iitemhandlerref.get() != null) {
					for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
						ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
						if ((Items.DIAMOND == (itemstackiterator).getItem())) {
							money = (double) (money + (((itemstackiterator)).getCount()));
						}
					}
				}
			}
			if ((money >= 9)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Items.DIAMOND);
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 9);
				}
				if (!world.getWorld().isRemote && world.getWorld().getServer() != null) {
					world.getWorld().getServer().getCommandManager().handleCommand(
							new CommandSource(ICommandSource.DUMMY, new Vec3d(x, y, z), Vec2f.ZERO, (ServerWorld) world, 4, "",
									new StringTextComponent(""), world.getWorld().getServer(), null).withFeedbackDisabled(),
							"give @a[distance=..1] wobr:glowing_obsidian 1");
				}
			}
		}
	}
}
