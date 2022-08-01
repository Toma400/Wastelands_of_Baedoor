package toma400.wobr;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import static toma400.wobr.Wobr.MOD_ID;

@Mod(modid = MOD_ID, name = Wobr.NAME, version = Wobr.VERSION)
public class Wobr
{
    public static final String NAME = "Wastelands of Baedoor";
    public static final String VERSION = "12v0a8";
    public static final String MOD_ID = "wobr";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}
