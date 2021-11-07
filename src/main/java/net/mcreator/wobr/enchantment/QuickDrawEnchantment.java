
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

@WobrModElements.ModElement.Tag
public class QuickDrawEnchantment extends WobrModElements.ModElement {
	@ObjectHolder("wobr:quick_draw")
	public static final Enchantment enchantment = null;
	public QuickDrawEnchantment(WobrModElements instance) {
		super(instance, 1455);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("quick_draw"));
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
			return 5;
		}

		@Override
		public boolean canApplyAtEnchantingTable(ItemStack stack) {
			if (stack.getItem() == new ItemStack(HandmadeRevolverItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(PepperBoxItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(ShortRevolverItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(GoldenShotRevolverItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(LongRevolverItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(ElephantGunItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(JitadoShotgunItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(CrocodileShotgunItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(Lefs9Item.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(AyerShotgunItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(AyerSawedOffItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(WindsweeperItem.block, (int) (1)).getItem())
				return true;
			if (stack.getItem() == new ItemStack(RustyReaperItem.block, (int) (1)).getItem())
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
