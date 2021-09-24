package net.mcreator.wobr.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.CardSpadeAceWinfletonItem;
import net.mcreator.wobr.item.CardHeartAceWinfletonItem;
import net.mcreator.wobr.item.CardDiamondAceWinfletonItem;
import net.mcreator.wobr.item.CardClubAceWinfletonItem;
import net.mcreator.wobr.block.VulcanicVeilBlock;
import net.mcreator.wobr.block.SandyVeilBlock;
import net.mcreator.wobr.WobrModElements;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

@WobrModElements.ModElement.Tag
public class MerchantGUIBuyProcedure extends WobrModElements.ModElement {
	public MerchantGUIBuyProcedure(WobrModElements instance) {
		super(instance, 1398);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MerchantGUIBuy!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure MerchantGUIBuy!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		double card_draw = 0;
		double eme_eme = 0;
		eme_eme = (double) 0;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if ((new ItemStack(Items.EMERALD, (int) (1)).getItem() == (itemstackiterator).getItem())) {
						eme_eme = (double) ((eme_eme) + (((itemstackiterator)).getCount()));
					}
				}
			}
		}
		if (((entity.getPersistentData().getDouble("merchant_gui")) == 1)) {
			if (((eme_eme) >= 20)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Items.EMERALD, (int) (1));
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 20);
				}
				card_draw = (double) (Math.random() * 100);
				if (((card_draw) <= 25)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(CardDiamondAceWinfletonItem.block, (int) (1));
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				} else if (((card_draw) <= 50)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(CardHeartAceWinfletonItem.block, (int) (1));
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				} else if (((card_draw) <= 75)) {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(CardClubAceWinfletonItem.block, (int) (1));
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				} else {
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(CardSpadeAceWinfletonItem.block, (int) (1));
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
				}
			}
		} else if (((entity.getPersistentData().getDouble("merchant_gui")) == 2)) {
			if (((eme_eme) >= 2)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Items.EMERALD, (int) (1));
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 2);
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(SandyVeilBlock.block, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
		} else if (((entity.getPersistentData().getDouble("merchant_gui")) == 3)) {
			if (((eme_eme) >= 2)) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Items.EMERALD, (int) (1));
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 2);
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(VulcanicVeilBlock.block, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
		}
	}
}
