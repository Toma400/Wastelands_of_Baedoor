package net.mcreator.wobr.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import net.mcreator.wobr.block.LegholdTrapInactiveBlock;
import net.mcreator.wobr.block.LegholdTrapCatchBlock;
import net.mcreator.wobr.block.LegholdTrapActiveBlock;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;

@WobrModElements.ModElement.Tag
public class LegholdTrapActivationProcedure extends WobrModElements.ModElement {
	public LegholdTrapActivationProcedure(WobrModElements instance) {
		super(instance, 1235);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure LegholdTrapActivation!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure LegholdTrapActivation!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure LegholdTrapActivation!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure LegholdTrapActivation!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure LegholdTrapActivation!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Blocks.TRIPWIRE_HOOK, (int) (1)).getItem())) {
			if (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LegholdTrapInactiveBlock.block.getDefaultState()
					.getBlock())) {
				{
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					BlockState _bs = LegholdTrapActiveBlock.block.getDefaultState();
					world.setBlockState(_bp, _bs, 3);
				}
			} else if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LegholdTrapActiveBlock.block.getDefaultState()
					.getBlock())
					|| ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == LegholdTrapCatchBlock.block.getDefaultState()
							.getBlock()))) {
				{
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					BlockState _bs = LegholdTrapInactiveBlock.block.getDefaultState();
					world.setBlockState(_bp, _bs, 3);
				}
			}
		}
	}
}
