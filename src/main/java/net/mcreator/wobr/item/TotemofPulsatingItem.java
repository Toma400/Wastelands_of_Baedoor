
package net.mcreator.wobr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.WobrModElements;

import java.util.List;

@WobrModElements.ModElement.Tag
public class TotemofPulsatingItem extends WobrModElements.ModElement {
	@ObjectHolder("wobr:totem_of_pulsating")
	public static final Item block = null;
	public TotemofPulsatingItem(WobrModElements instance) {
		super(instance, 236);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(WoBCreativeTabItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("totem_of_pulsating");
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

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("Mysterious totem which feels like it either will help you or kill you... or both in the same time"));
		}
	}
}
