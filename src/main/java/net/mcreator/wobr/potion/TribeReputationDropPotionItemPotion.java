
package net.mcreator.wobr.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.potion.Potion;
import net.minecraft.potion.EffectInstance;

import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class TribeReputationDropPotionItemPotion extends WobrModElements.ModElement {
	@ObjectHolder("wobr:tribe_reputation_drop")
	public static final Potion potionType = null;
	public TribeReputationDropPotionItemPotion(WobrModElements instance) {
		super(instance, 1425);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPotion(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(new PotionCustom());
	}
	public static class PotionCustom extends Potion {
		public PotionCustom() {
			super(new EffectInstance(TribeReputationDropPotionEffect.potion, 3600, 0, false, true));
			setRegistryName("tribe_reputation_drop");
		}
	}
}
