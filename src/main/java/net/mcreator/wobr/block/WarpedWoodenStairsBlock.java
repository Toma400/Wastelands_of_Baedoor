
package net.mcreator.wobr.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.WobrModElements;

import java.util.List;
import java.util.Collections;

@WobrModElements.ModElement.Tag
public class WarpedWoodenStairsBlock extends WobrModElements.ModElement {
	@ObjectHolder("wobr:warped_wooden_stairs")
	public static final Block block = null;
	public WarpedWoodenStairsBlock(WobrModElements instance) {
		super(instance, 150);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(WoBCreativeTabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends StairsBlock {
		public CustomBlock() {
			super(() -> new Block(Block.Properties.create(Material.WOOD).sound(SoundType.STEM).hardnessAndResistance(2f, 3f).lightValue(0)
					.harvestLevel(1).harvestTool(ToolType.AXE)).getDefaultState(),
					Block.Properties.create(Material.WOOD).sound(SoundType.STEM).hardnessAndResistance(2f, 3f).lightValue(0).harvestLevel(1)
							.harvestTool(ToolType.AXE));
			setRegistryName("warped_wooden_stairs");
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
