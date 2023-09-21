package toma400.wobr;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import toma400.wobr.core.WobrItems;
import toma400.wobr.core.WobrUtils;

@Mod(Wobr.MOD_ID)
public class Wobr
{
    public  static final String MOD_ID = "wobr";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Wobr() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        WobrUtils.registerTabs(eventBus);
        WobrItems.register(eventBus);
        eventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }
}
