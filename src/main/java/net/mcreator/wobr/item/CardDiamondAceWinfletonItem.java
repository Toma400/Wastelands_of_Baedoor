
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
public class CardDiamondAceWinfletonItem extends WobrModElements.ModElement {
	@ObjectHolder("wobr:card_diamond_ace_winfleton")
	public static final Item block = null;
	public CardDiamondAceWinfletonItem(WobrModElements instance) {
		super(instance, 221);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(WoBCreativeTabItemGroup.tab).maxStackSize(64).rarity(Rarity.EPIC));
			setRegistryName("card_diamond_ace_winfleton");
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
			list.add(new StringTextComponent("Part of Winfleton's Card Deck"));
			list.add(new StringTextComponent("collector's dream painted himself by known philanthropist and artist"));
			list.add(new StringTextComponent("Heim Winfleton"));
		}
	}
}
