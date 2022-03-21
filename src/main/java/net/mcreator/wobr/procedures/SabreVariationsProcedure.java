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

import java.util.Map;
import java.util.Collection;

@WobrModElements.ModElement.Tag
public class SabreVariationsProcedure extends WobrModElements.ModElement {
	public SabreVariationsProcedure(WobrModElements instance) {
		super(instance, 937);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SabreVariations!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure SabreVariations!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getDouble("Sabre_Checker")) < 255)) {
			(itemstack).getOrCreateTag().putDouble("Sabre_Defence", 10);
			(itemstack).getOrCreateTag().putDouble("Sabre_Harm", 1);
			if (((((itemstack).getItem() == new ItemStack(BrotherhoodSabreItem.block, (int) (1)).getItem())
					|| ((itemstack).getItem() == new ItemStack(BlackSabreItem.block, (int) (1)).getItem()))
					|| ((itemstack).getItem() == new ItemStack(GoldenScimitarItem.block, (int) (1)).getItem()))) {
				(itemstack).getOrCreateTag().putDouble("Sabre_Cooldown", 15);
				(itemstack).getOrCreateTag().putDouble("Sabre_Checker", 255);
			} else if (((((itemstack).getItem() == new ItemStack(PrismarineSabreItem.block, (int) (1)).getItem())
					|| ((itemstack).getItem() == new ItemStack(WhiteSabreItem.block, (int) (1)).getItem()))
					|| (((itemstack).getItem() == new ItemStack(HardenedIronSabreItem.block, (int) (1)).getItem())
							|| ((itemstack).getItem() == new ItemStack(IronSabreItem.block, (int) (1)).getItem())))) {
				(itemstack).getOrCreateTag().putDouble("Sabre_Cooldown", 20);
				(itemstack).getOrCreateTag().putDouble("Sabre_Checker", 255);
			}
		}
		if (((itemstack).getItem() == new ItemStack(PrismarineSabreItem.block, (int) (1)).getItem())) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.CONDUIT_POWER, (int) 20, (int) 1));
		} else if (((itemstack).getItem() == new ItemStack(WhiteSabreItem.block, (int) (1)).getItem())) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 20, (int) 1));
		} else if (((itemstack).getItem() == new ItemStack(BlackSabreItem.block, (int) (1)).getItem())) {
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
