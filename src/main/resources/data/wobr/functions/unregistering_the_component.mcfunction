import net.minecraftforge.fml.ModList;

if (net.minecraftforge.fml.ModList.get().isLoaded("byg"))
	elements.items.add(
		() -> new BlockItem(block, new Item.Properties().group(WoBIntegrationsTabItemGroup.tab)).setRegistryName(block.getRegistryName()));