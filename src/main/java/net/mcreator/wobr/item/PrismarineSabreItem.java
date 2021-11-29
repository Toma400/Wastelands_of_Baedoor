
package net.mcreator.wobr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.entity.Entity;
import net.minecraft.client.util.ITooltipFlag;

import net.mcreator.wobr.procedures.SabreVariationsProcedure;
import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class PrismarineSabreItem extends WobrModElements.ModElement {
	@ObjectHolder("wobr:prismarine_sabre")
	public static final Item block = null;
	public PrismarineSabreItem(WobrModElements instance) {
		super(instance, 4);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 166;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 1;
			}

			public int getEnchantability() {
				return 20;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.PRISMARINE_CRYSTALS, (int) (1)));
			}
		}, 3, -1.8f, new Item.Properties().group(WoBCreativeTabItemGroup.tab)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent(
						"Prismarine sabre seems to be taken from depths of the sea, so be aware, as it can be hiding some mysterious powers..."));
			}

			@Override
			public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
				super.inventoryTick(itemstack, world, entity, slot, selected);
				double x = entity.posX;
				double y = entity.posY;
				double z = entity.posZ;
				if (selected) {
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("itemstack", itemstack);
					SabreVariationsProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("prismarine_sabre"));
	}
}
