package tas.dfa.client.core.proxy;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tas.dfa.client.core.handler.ModelHandler;
import tas.dfa.client.render.tile.RenderTileMetal;
import tas.dfa.client.render.tile.RenderTilePool;
import tas.dfa.common.block.tile.TileMetal;
import tas.dfa.common.block.tile.TilePool;
import tas.dfa.common.core.proxy.CommonProxy;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);

        ModelHandler.preInit();
        initRenderers();
    }

    public void initRenderers()
    {
        ClientRegistry.bindTileEntitySpecialRenderer(TileMetal.class, new RenderTileMetal());
        ClientRegistry.bindTileEntitySpecialRenderer(TilePool.class,new RenderTilePool());
    }
}
