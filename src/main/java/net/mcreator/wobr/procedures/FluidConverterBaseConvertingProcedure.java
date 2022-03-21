package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.block.material.Material;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.wobr.block.BlackSandBlock;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class FluidConverterBaseConvertingProcedure extends WobrModElements.ModElement {
	public FluidConverterBaseConvertingProcedure(WobrModElements instance) {
		super(instance, 1368);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure FluidConverterBaseConverting!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure FluidConverterBaseConverting!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure FluidConverterBaseConverting!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure FluidConverterBaseConverting!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		double sx = 0;
		double sz = 0;
		sx = (double) (-1);
		for (int index0 = 0; index0 < (int) (3); index0++) {
			sz = (double) (-1);
			for (int index1 = 0; index1 < (int) (3); index1++) {
				if (((BlockTags.getCollection().getOrCreate(new ResourceLocation(("forge:stone").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y - 2), (int) (z + (sz))))).getBlock()))
						&& (!(BlockTags.getCollection()
								.getOrCreate(new ResourceLocation(("forge:invulnerable_stone").toLowerCase(java.util.Locale.ENGLISH)))
								.contains((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y - 2), (int) (z + (sz))))).getBlock()))))) {
					{
						BlockPos _bp = new BlockPos((int) (x + (sx)), (int) (y - 2), (int) (z + (sz)));
						BlockState _bs = Blocks.NETHERRACK.getDefaultState();
						world.setBlockState(_bp, _bs, 3);
					}
				} else if (((world.getBlockState(new BlockPos((int) (x + (sx)), (int) (y - 2), (int) (z + (sz)))))
						.getMaterial() == Material.ORGANIC)) {
					{
						BlockPos _bp = new BlockPos((int) (x + (sx)), (int) (y - 2), (int) (z + (sz)));
						BlockState _bs = BlackSandBlock.block.getDefaultState();
						world.setBlockState(_bp, _bs, 3);
					}
				}
				sz = (double) ((sz) + 1);
			}
			sx = (double) ((sx) + 1);
		}
	}
}
