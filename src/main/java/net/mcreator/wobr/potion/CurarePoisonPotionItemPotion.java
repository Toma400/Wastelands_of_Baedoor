
package net.mcreator.wobr.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.potion.Potion;
import net.minecraft.potion.EffectInstance;

import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class CurarePoisonPotionItemPotion extends WobrModElements.ModElement {
	@ObjectHolder("wobr:curare_poison")
	public static final Potion potionType = null;
	public CurarePoisonPotionItemPotion(WobrModElements instance) {
		super(instance, 1213);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPotion(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(new PotionCustom());
	}
	public static class PotionCustom extends Potion {
		public PotionCustom() {
			super(new EffectInstance(CurarePoisonPotionEffect.potion, 20, 0, false, true));
			setRegistryName("curare_poison");
		}
	}
}
