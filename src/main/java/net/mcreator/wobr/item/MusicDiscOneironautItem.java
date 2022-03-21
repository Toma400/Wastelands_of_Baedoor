
package net.mcreator.wobr.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Item;

import net.mcreator.wobr.itemgroup.WoBCreativeTabItemGroup;
import net.mcreator.wobr.WobrModElements;

@WobrModElements.ModElement.Tag
public class MusicDiscOneironautItem extends WobrModElements.ModElement {
	@ObjectHolder("wobr:music_disc_oneironaut")
	public static final Item block = null;
	public MusicDiscOneironautItem(WobrModElements instance) {
		super(instance, 199);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new MusicDiscItemCustom());
	}
	public static class MusicDiscItemCustom extends MusicDiscItem {
		public MusicDiscItemCustom() {
			super(0, WobrModElements.sounds.get(new ResourceLocation("wobr:disc_oneironaut")),
					new Item.Properties().group(WoBCreativeTabItemGroup.tab).maxStackSize(1).rarity(Rarity.RARE));
			setRegistryName("music_disc_oneironaut");
		}
	}
}
