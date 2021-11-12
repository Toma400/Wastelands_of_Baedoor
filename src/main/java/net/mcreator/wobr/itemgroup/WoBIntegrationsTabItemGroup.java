
package net.mcreator.wobr.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.wobr.item.CirtainIngotItem;
import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class WoBIntegrationsTabItemGroup extends WobrModElements.ModElement {
	public WoBIntegrationsTabItemGroup(WobrModElements instance) {
		super(instance, 1608);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabwo_b_integrations_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CirtainIngotItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
