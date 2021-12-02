
package net.mcreator.wobr.potion;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.util.ResourceLocation;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effect;

import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class MerchantBlockHeartoftheSeaPotionEffect extends WobrModElements.ModElement {
	@ObjectHolder("wobr:merchant_block_heartofthe_sea")
	public static final Effect potion = null;
	public MerchantBlockHeartoftheSeaPotionEffect(WobrModElements instance) {
		super(instance, 2130);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerEffect(RegistryEvent.Register<Effect> event) {
		event.getRegistry().register(new EffectCustom());
	}
	public static class EffectCustom extends Effect {
		private final ResourceLocation potionIcon;
		public EffectCustom() {
			super(EffectType.NEUTRAL, -13421773);
			setRegistryName("merchant_block_heartofthe_sea");
			potionIcon = new ResourceLocation("wobr:textures/heart_of_the_sea.png");
		}

		@Override
		public String getName() {
			return "effect.merchant_block_heartofthe_sea";
		}

		@Override
		public boolean isBeneficial() {
			return false;
		}

		@Override
		public boolean isInstant() {
			return false;
		}

		@Override
		public boolean shouldRenderInvText(EffectInstance effect) {
			return false;
		}

		@Override
		public boolean shouldRender(EffectInstance effect) {
			return false;
		}

		@Override
		public boolean shouldRenderHUD(EffectInstance effect) {
			return false;
		}

		@Override
		public boolean isReady(int duration, int amplifier) {
			return true;
		}
	}
}
