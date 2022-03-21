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

import net.mcreator.wobr.item.CardSpadeAceWinfletonItem;
import net.mcreator.wobr.item.CardHeartAceWinfletonItem;
import net.mcreator.wobr.item.CardDiamondAceWinfletonItem;
import net.mcreator.wobr.item.CardClubAceWinfletonItem;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

@WobrModElements.ModElement.Tag
public class MerchantTrade9Procedure extends WobrModElements.ModElement {
	public MerchantTrade9Procedure(WobrModElements instance) {
		super(instance, 1560);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure MerchantTrade9!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure MerchantTrade9!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		double money = 0;
		double randomness = 0;
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
			randomness = (double) (Math.random() * 100);
			if ((randomness <= 25)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(CardDiamondAceWinfletonItem.block);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			} else if ((randomness <= 50)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(CardHeartAceWinfletonItem.block);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			} else if ((randomness <= 75)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(CardClubAceWinfletonItem.block);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			} else {
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(CardSpadeAceWinfletonItem.block);
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
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
				randomness = (double) (Math.random() * 100);
				if ((randomness <= 25)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(CardDiamondAceWinfletonItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				} else if ((randomness <= 50)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(CardHeartAceWinfletonItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				} else if ((randomness <= 75)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(CardClubAceWinfletonItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				} else {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(CardSpadeAceWinfletonItem.block);
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				}
			}
		}
	}
}
