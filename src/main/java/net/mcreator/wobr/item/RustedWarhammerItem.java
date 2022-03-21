
package net.mcreator.wobr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.entity.Entity;
import net.minecraft.client.util.ITooltipFlag;

import net.mcreator.wobr.procedures.WarhammerRegistryProcedure;
import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class RustedWarhammerItem extends WobrModElements.ModElement {
	@ObjectHolder("wobr:rusted_warhammer")
	public static final Item block = null;
	public RustedWarhammerItem(WobrModElements instance) {
		super(instance, 1250);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 165;
			}

			public float getEfficiency() {
				return 0f;
			}

			public float getAttackDamage() {
				return 6f;
			}

			public int getHarvestLevel() {
				return 0;
			}

			public int getEnchantability() {
				return 6;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}
		}, 3, -3.7f, new Item.Properties().group(null)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("Warhammer from old days and old wars... rusted, yet still powerful."));
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
					WarhammerRegistryProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("rusted_warhammer"));
	}
}
