package tas.dfa.common.core.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tas.dfa.common.block.base.ModBlocks;
import tas.dfa.common.item.base.ModItems;
import tas.dfa.common.potion.ModPotions;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        ModItems.init();
        ModBlocks.init();
        ModPotions.Init();
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
