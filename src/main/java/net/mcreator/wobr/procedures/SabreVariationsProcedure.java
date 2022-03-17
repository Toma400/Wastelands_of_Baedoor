package net.mcreator.wobr.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.item.WhiteSabreItem;
import net.mcreator.wobr.item.PrismarineSabreItem;
import net.mcreator.wobr.item.IronSabreItem;
import net.mcreator.wobr.item.HardenedIronSabreItem;
import net.mcreator.wobr.item.GoldenScimitarItem;
import net.mcreator.wobr.item.BrotherhoodSabreItem;
import net.mcreator.wobr.item.BlackSabreItem;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class SabreVariationsProcedure extends WobrModElements.ModElement {
	public SabreVariationsProcedure(WobrModElements instance) {
		super(instance, 1078);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				WobrMod.LOGGER.warn("Failed to load dependency entity for procedure SabreVariations!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				WobrMod.LOGGER.warn("Failed to load dependency itemstack for procedure SabreVariations!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getDouble("Sabre_Checker")) < 255)) {
			(itemstack).getOrCreateTag().putDouble("Sabre_Defence", 10);
			(itemstack).getOrCreateTag().putDouble("Sabre_Harm", 1);
			if (((((itemstack).getItem() == BrotherhoodSabreItem.block) || ((itemstack).getItem() == BlackSabreItem.block))
					|| ((itemstack).getItem() == GoldenScimitarItem.block))) {
				(itemstack).getOrCreateTag().putDouble("Sabre_Cooldown", 15);
				(itemstack).getOrCreateTag().putDouble("Sabre_Checker", 255);
			} else if (((((itemstack).getItem() == PrismarineSabreItem.block) || ((itemstack).getItem() == WhiteSabreItem.block))
					|| (((itemstack).getItem() == HardenedIronSabreItem.block) || ((itemstack).getItem() == IronSabreItem.block)))) {
				(itemstack).getOrCreateTag().putDouble("Sabre_Cooldown", 20);
				(itemstack).getOrCreateTag().putDouble("Sabre_Checker", 255);
			}
		}
		if (((itemstack).getItem() == PrismarineSabreItem.block)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.CONDUIT_POWER, (int) 20, (int) 1, (false), (false)));
		} else if (((itemstack).getItem() == WhiteSabreItem.block)) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 20, (int) 1));
		} else if (((itemstack).getItem() == BlackSabreItem.block)) {
			if ((!(new Object() {
				boolean check(Entity _entity) {
					if (_entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) _entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.WITHER)
								return true;
						}
					}
					return false;
				}
			}.check(entity)))) {
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.WITHER, (int) 40, (int) 1, (false), (false)));
			}
		}
	}
}
