package net.mcreator.wobr.procedures;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.BaedoorFuntItem;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

@WobrModElements.ModElement.Tag
public class CoinIteratorProcedure extends WobrModElements.ModElement {
	public CoinIteratorProcedure(WobrModElements instance) {
		super(instance, 1494);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure CoinIterator!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure CoinIterator!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		double coins = 0;
		coins = (double) 0;
		{
			AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
			entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
			if (_iitemhandlerref.get() != null) {
				for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
					ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
					if ((BaedoorFuntItem.block == (itemstackiterator).getItem())) {
						coins = (double) (coins + (((itemstackiterator)).getCount()));
					}
				}
			}
		}
		{
			double _setval = (double) coins;
			entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.Coins = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
