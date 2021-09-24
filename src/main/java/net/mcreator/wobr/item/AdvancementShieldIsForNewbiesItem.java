
package net.mcreator.wobr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class AdvancementShieldIsForNewbiesItem extends WobrModElements.ModElement {
	@ObjectHolder("wobr:advancement_shield_is_for_newbies")
	public static final Item block = null;
	public AdvancementShieldIsForNewbiesItem(WobrModElements instance) {
		super(instance, 1184);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(null).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("advancement_shield_is_for_newbies");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
