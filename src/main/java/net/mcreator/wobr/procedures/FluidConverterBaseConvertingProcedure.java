package net.mcreator.wobr.procedures;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.tags.BlockTags;
import net.minecraft.state.IProperty;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.wobr.block.BlackSandBlock;
import net.mcreator.wobr.block.BaedoorFuntBlockBlock;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class FluidConverterBaseConvertingProcedure extends WobrModElements.ModElement {
	public FluidConverterBaseConvertingProcedure(WobrModElements instance) {
		super(instance, 1805);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				WobrMod.LOGGER.warn("Failed to load dependency x for procedure FluidConverterBaseConverting!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				WobrMod.LOGGER.warn("Failed to load dependency y for procedure FluidConverterBaseConverting!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				WobrMod.LOGGER.warn("Failed to load dependency z for procedure FluidConverterBaseConverting!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				WobrMod.LOGGER.warn("Failed to load dependency world for procedure FluidConverterBaseConverting!");
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
				if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("forge:stone").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) (x + sx), (int) (y - 2), (int) (z + sz)))).getBlock()))) {
					{
						BlockPos _bp = new BlockPos((int) (x + sx), (int) (y - 2), (int) (z + sz));
						BlockState _bs = Blocks.NETHERRACK.getDefaultState();
						BlockState _bso = world.getBlockState(_bp);
						for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
							IProperty _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
							if (_property != null && _bs.has(_property))
								try {
									_bs = _bs.with(_property, (Comparable) entry.getValue());
								} catch (Exception e) {
								}
						}
						world.setBlockState(_bp, _bs, 3);
					}
				} else if (((world.getBlockState(new BlockPos((int) (x + sx), (int) (y - 2), (int) (z + sz))))
						.getMaterial() == net.minecraft.block.material.Material.ORGANIC)) {
					{
						BlockPos _bp = new BlockPos((int) (x + sx), (int) (y - 2), (int) (z + sz));
						BlockState _bs = BlackSandBlock.block.getDefaultState();
						BlockState _bso = world.getBlockState(_bp);
						for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
							IProperty _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
							if (_property != null && _bs.has(_property))
								try {
									_bs = _bs.with(_property, (Comparable) entry.getValue());
								} catch (Exception e) {
								}
						}
						world.setBlockState(_bp, _bs, 3);
					}
				} else if (((world.getBlockState(new BlockPos((int) (x + sx), (int) (y - 2), (int) (z + sz)))).getBlock() == Blocks.BONE_BLOCK)) {
					if ((net.minecraftforge.fml.ModList.get().isLoaded("cavesandcliffs"))) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("wobr", "addon_cc_calcite"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) x, (int) y, (int) z), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					}
				} else if ((BlockTags.getCollection().getOrCreate(new ResourceLocation(("wobr:hard_stone").toLowerCase(java.util.Locale.ENGLISH)))
						.contains((world.getBlockState(new BlockPos((int) (x + sx), (int) (y - 2), (int) (z + sz)))).getBlock()))) {
					if ((net.minecraftforge.fml.ModList.get().isLoaded("cavesandcliffs"))) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("wobr", "addon_cc_deepslate"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) x, (int) y, (int) z), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					}
				} else if (((world.getBlockState(new BlockPos((int) (x + sx), (int) (y - 2), (int) (z + sz))))
						.getBlock() == BaedoorFuntBlockBlock.block)) {
					if ((net.minecraftforge.fml.ModList.get().isLoaded("create"))) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("wobr", "addon_create_brass_block"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) x, (int) y, (int) z), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					}
				} else if (((world.getBlockState(new BlockPos((int) (x + sx), (int) (y - 2), (int) (z + sz)))).getBlock() == Blocks.END_STONE)) {
					if ((net.minecraftforge.fml.ModList.get().isLoaded("betterendforge"))) {
						if (!world.getWorld().isRemote) {
							Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
									.getTemplateDefaulted(new ResourceLocation("wobr", "addon_be_endstone_dust"));
							if (template != null) {
								template.addBlocksToWorld(world, new BlockPos((int) x, (int) y, (int) z), new PlacementSettings()
										.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
							}
						}
					}
				}
				sz = (double) (sz + 1);
			}
			sx = (double) (sx + 1);
		}
	}
}
