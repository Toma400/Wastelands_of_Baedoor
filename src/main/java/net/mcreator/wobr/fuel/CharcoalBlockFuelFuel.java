
package net.mcreator.wobr.fuel;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.mcreator.wobr.block.CharcoalBlockBlock;
import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class CharcoalBlockFuelFuel extends WobrModElements.ModElement {
	public CharcoalBlockFuelFuel(WobrModElements instance) {
		super(instance, 773);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == CharcoalBlockBlock.block.asItem())
			event.setBurnTime(16000);
	}
}
