package tas.dfa.common.block.base;

import net.minecraftforge.fml.common.registry.GameRegistry;
import tas.dfa.common.block.BlockAncientAnvil;
import tas.dfa.common.block.BlockPool;
import tas.dfa.common.block.testBlock;
import tas.dfa.common.block.tile.TileAncientAnvil;
import tas.dfa.common.block.tile.TilePool;
import tas.dfa.common.lib.LibBlocks;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class ModBlocks
{

    public static BaseBlock testBlock;
    public static BaseBlock ancientAnvil;
    public static BaseBlock pool;

    public static void init()
    {
        //testBlock = new testBlock();
        ancientAnvil = new BlockAncientAnvil();
        pool = new BlockPool();

        initTileEntities();
    }

    private static void initTileEntities()
    {
        GameRegistry.registerTileEntity(TileAncientAnvil.class, LibBlocks.ancientAnvil);
        GameRegistry.registerTileEntity(TilePool.class,LibBlocks.pool);
    }
}
