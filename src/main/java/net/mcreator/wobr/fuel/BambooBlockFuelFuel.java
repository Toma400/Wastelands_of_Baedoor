
package net.mcreator.wobr.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.mcreator.wobr.block.BambooBlockBlock;
import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class BambooBlockFuelFuel extends WobrModElements.ModElement {
	public BambooBlockFuelFuel(WobrModElements instance) {
		super(instance, 774);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == BambooBlockBlock.block.asItem())
			event.setBurnTime(400);
	}
}
