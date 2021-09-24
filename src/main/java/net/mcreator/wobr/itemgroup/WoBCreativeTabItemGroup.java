
package net.mcreator.wobr.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.wobr.item.AyerShotgunItem;
import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class WoBCreativeTabItemGroup extends WobrModElements.ModElement {
	public WoBCreativeTabItemGroup(WobrModElements instance) {
		super(instance, 660);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabwo_b_creative_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(AyerShotgunItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
