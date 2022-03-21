
package net.mcreator.wobr.enchantment;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class DismantleEnchantment extends WobrModElements.ModElement {
	@ObjectHolder("wobr:dismantle")
	public static final Enchantment enchantment = null;
	public DismantleEnchantment(WobrModElements instance) {
		super(instance, 1327);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("dismantle"));
	}
	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.COMMON, EnchantmentType.WEAPON, slots);
		}

		@Override
		public int getMinLevel() {
			return 1;
		}

		@Override
		public int getMaxLevel() {
			return 5;
		}

		@Override
		protected boolean canApplyTogether(Enchantment ench) {
			if (ench == Enchantments.SMITE)
				return false;
			if (ench == Enchantments.SHARPNESS)
				return false;
			if (ench == Enchantments.BANE_OF_ARTHROPODS)
				return false;
			return true;
		}

		@Override
		public boolean isTreasureEnchantment() {
			return false;
		}

		@Override
		public boolean isCurse() {
			return false;
		}

		@Override
		public boolean isAllowedOnBooks() {
			return true;
		}
	}
}
