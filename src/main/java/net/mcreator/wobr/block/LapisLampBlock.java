
package net.mcreator.wobr.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.WobrModElements;

import java.util.List;
import java.util.Collections;

@WobrModElements.ModElement.Tag
public class LapisLampBlock extends WobrModElements.ModElement {
	@ObjectHolder("wobr:lapis_lamp")
	public static final Block block = null;
	public LapisLampBlock(WobrModElements instance) {
		super(instance, 202);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(WoBCreativeTabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(3f, 6f).lightValue(15).harvestLevel(2)
					.harvestTool(ToolType.PICKAXE));
			setRegistryName("lapis_lamp");
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
