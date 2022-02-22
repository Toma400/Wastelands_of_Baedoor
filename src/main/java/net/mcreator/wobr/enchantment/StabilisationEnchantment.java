
package net.mcreator.wobr.enchantment;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

import net.mcreator.wobr.item.WindsweeperItem;
import net.mcreator.wobr.item.ShortRevolverItem;
import net.mcreator.wobr.item.RustyReaperItem;
import net.mcreator.wobr.item.PepperBoxItem;
import net.mcreator.wobr.item.LongRevolverItem;
import net.mcreator.wobr.item.Lefs9Item;
import net.mcreator.wobr.item.JitadoShotgunItem;
import net.mcreator.wobr.item.HandmadeRevolverItem;
import net.mcreator.wobr.item.GoldenShotRevolverItem;
import net.mcreator.wobr.item.ElephantGunItem;
import net.mcreator.wobr.item.CrocodileShotgunItem;
import net.mcreator.wobr.item.AyerShotgunItem;
import net.mcreator.wobr.item.AyerSawedOffItem;
import net.mcreator.wobr.WobrModElements;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;


@WobrModElements.ModElement.Tag
public class StabilisationEnchantment extends WobrModElements.ModElement {
	@ObjectHolder("wobr:stabilisation")
	public static final Enchantment enchantment = null;
	public StabilisationEnchantment(WobrModElements instance) {
		super(instance, 1139);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("stabilisation"));
	}
	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.RARE, EnchantmentType.WEAPON, slots);
		}

		@Override
		public int getMinLevel() {
			return 1;
		}

		@Override
		public int getMaxLevel() {
			return 3;
		}

		@Override
		public boolean canApplyAtEnchantingTable(ItemStack stack) {
			if (ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:wobn_firearms").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((stack).getItem()) == true)
				return true;
			if (ItemTags.getCollection().getOrCreate(new ResourceLocation(("forge:wobr_firearms").toLowerCase(java.util.Locale.ENGLISH)))
				.contains((stack).getItem()) == true)
				return true;
			return false;
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
