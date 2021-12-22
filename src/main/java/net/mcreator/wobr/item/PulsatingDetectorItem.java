
package net.mcreator.wobr.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.Minecraft;
import net.minecraft.block.BlockState;

import net.mcreator.wobr.procedures.PulsatingDetectorUseProcedure;
import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.WobrModElements;

import java.util.List;

import com.google.common.collect.ImmutableMap;

@WobrModElements.ModElement.Tag
public class PulsatingDetectorItem extends WobrModElements.ModElement {
	@ObjectHolder("wobr:pulsating_detector")
	public static final Item block = null;
	public PulsatingDetectorItem(WobrModElements instance) {
		super(instance, 237);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(WoBCreativeTabItemGroup.tab).maxStackSize(1).rarity(Rarity.UNCOMMON));
			setRegistryName("pulsating_detector");
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
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			PlayerEntity entity = Minecraft.getInstance().player;
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			if (!(PulsatingDetectorUseProcedure.executeProcedure(ImmutableMap.of("entity", entity)))) {
				return false;
			}
			return true;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("Detector that will glow if it recognises pulsating in the area"));
		}
	}
}
