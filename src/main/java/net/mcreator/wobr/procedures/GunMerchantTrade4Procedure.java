package net.mcreator.wobr.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.wobr.item.SandWandererSchemeItem;
import net.mcreator.wobr.WobrModElements;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

@WobrModElements.ModElement.Tag
public class GunMerchantTrade4Procedure extends WobrModElements.ModElement {
	public GunMerchantTrade4Procedure(WobrModElements instance) {
		super(instance, 1879);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure GunMerchantTrade4!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure GunMerchantTrade4!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		double money = 0;
		money = (double) 0;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if ((new ItemStack(Blocks.DIAMOND_BLOCK, (int) (1)).getItem() == (itemstackiterator).getItem())) {
						money = (double) ((money) + (((itemstackiterator)).getCount()));
					}
				}
			}
		}
		if (((money) >= 2)) {
			if (entity instanceof PlayerEntity) {
				ItemStack _stktoremove = new ItemStack(Blocks.DIAMOND_BLOCK, (int) (1));
				((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 2);
			}
			if (entity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(SandWandererSchemeItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
			}
		} else {
			money = (double) 0;
			{
				AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
				entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
				if (_iitemhandlerref.get() != null) {
					for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
						ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
						if ((new ItemStack(Items.DIAMOND, (int) (1)).getItem() == (itemstackiterator).getItem())) {
							money = (double) ((money) + (((itemstackiterator)).getCount()));
						}
					}
				}
			}
			if (((money) >= 18)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Items.DIAMOND, (int) (1));
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 18);
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(SandWandererSchemeItem.block, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
		}
	}
}
