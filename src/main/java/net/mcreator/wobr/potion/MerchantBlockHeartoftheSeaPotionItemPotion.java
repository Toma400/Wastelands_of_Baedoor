
package net.mcreator.wobr.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.potion.Potion;
import net.minecraft.potion.EffectInstance;

import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class MerchantBlockHeartoftheSeaPotionItemPotion extends WobrModElements.ModElement {
	@ObjectHolder("wobr:merchant_block_heartofthe_sea")
	public static final Potion potionType = null;
	public MerchantBlockHeartoftheSeaPotionItemPotion(WobrModElements instance) {
		super(instance, 1912);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerPotion(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(new PotionCustom());
	}
	public static class PotionCustom extends Potion {
		public PotionCustom() {
			super(new EffectInstance(MerchantBlockHeartoftheSeaPotionEffect.potion, 3600, 0, false, true));
			setRegistryName("merchant_block_heartofthe_sea");
		}
	}
}
