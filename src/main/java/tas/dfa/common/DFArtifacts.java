package tas.dfa.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tas.dfa.common.core.proxy.CommonProxy;
import tas.dfa.common.lib.LibMisc;

/**
 * Created by fancysaurus on 8/4/16.
 */

@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.VERSION)
public class DFArtifacts
{
    @Mod.Instance(LibMisc.MOD_ID)
    public static DFArtifacts instance;

    @SidedProxy(serverSide = LibMisc.COMMON_PROXY,clientSide = LibMisc.CLIENT_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

}
