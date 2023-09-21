package toma400.wobr.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import toma400.wobr.Wobr;

public class WobrUtils {
    public static final DeferredRegister<CreativeModeTab> WOBR_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Wobr.MOD_ID);

    // fills the creative tab with items (in order defined in LibItems.ITEMS registry, I assume)
    private static void fillTab(CreativeModeTab.Output pOutput) {
        for (RegistryObject<Item> it : WobrItems.ITEMS.getEntries()) {
            pOutput.accept(it.get());
        }
    }

    public static final RegistryObject<CreativeModeTab> WOBR_TAB = WOBR_TABS.register("wobr_tab",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(WobrItems.FIREARM_TRIGGER.get()))
                    .title(Component.translatable("creative_tab." + Wobr.MOD_ID + ".wobr_tab"))
                    .displayItems(((pParameters, pOutput) -> {
                        fillTab(pOutput);
                    }))
                    .build());

    public static void registerTabs(IEventBus event) {
        WOBR_TABS.register(event);
    }
}
