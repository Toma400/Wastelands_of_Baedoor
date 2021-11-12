package net.mcreator.wobr.procedures;

import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import net.mcreator.wobr.potion.MerchantBlockElytraPotion;
import net.mcreator.wobr.WobrModElements;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class GunMerchantTrade6Procedure extends WobrModElements.ModElement {
	public GunMerchantTrade6Procedure(WobrModElements instance) {
		super(instance, 1925);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure GunMerchantTrade6!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure GunMerchantTrade6!");
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
		if (((money) >= 9)) {
			if ((!(new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == MerchantBlockElytraPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				if (entity instanceof PlayerEntity) {
					ItemStack _stktoremove = new ItemStack(Blocks.DIAMOND_BLOCK, (int) (1));
					((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 9);
				}
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(Items.ELYTRA, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
				if (entity instanceof LivingEntity)
					((LivingEntity) entity)
							.addPotionEffect(new EffectInstance(MerchantBlockElytraPotion.potion, (int) 24000, (int) 1, (false), (false)));
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
			if (((money) >= 81)) {
				if ((!(new Object() {
					boolean check(Entity _entity) {
						if (_entity instanceof LivingEntity) {
							Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
							for (EffectInstance effect : effects) {
								if (effect.getPotion() == MerchantBlockElytraPotion.potion)
									return true;
							}
						}
						return false;
					}
				}.check(entity)))) {
					if (entity instanceof PlayerEntity) {
						ItemStack _stktoremove = new ItemStack(Items.DIAMOND, (int) (1));
						((PlayerEntity) entity).inventory.clearMatchingItems(p -> _stktoremove.getItem() == p.getItem(), (int) 81);
					}
					if (entity instanceof PlayerEntity) {
						ItemStack _setstack = new ItemStack(Items.ELYTRA, (int) (1));
						_setstack.setCount((int) 1);
						ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
					}
					if (entity instanceof LivingEntity)
						((LivingEntity) entity)
								.addPotionEffect(new EffectInstance(MerchantBlockElytraPotion.potion, (int) 24000, (int) 1, (false), (false)));
				}
			}
		}
	}
}
