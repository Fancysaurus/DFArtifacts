package tas.dfa.common.block.base;

import net.minecraftforge.fml.common.registry.GameRegistry;
import tas.dfa.common.block.*;
import tas.dfa.common.block.tile.TileAncientAnvil;
import tas.dfa.common.block.tile.TileCoalForge;
import tas.dfa.common.block.tile.TileMetal;
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
    public static BaseBlock barrel;
    public static BaseBlock coalForge;
    public static BaseBlock Metal;

    public static void init()
    {
        //testBlock = new testBlock();
        ancientAnvil = new BlockAncientAnvil();
        pool = new BlockPool();
        //barrel = new BlockBarrel();
        coalForge = new BlockCoalForge();
        Metal = new BlockMetal();

        initTileEntities();
    }

    private static void initTileEntities()
    {
        GameRegistry.registerTileEntity(TileAncientAnvil.class, LibBlocks.ancientAnvil);
        GameRegistry.registerTileEntity(TilePool.class,LibBlocks.pool);
        GameRegistry.registerTileEntity(TileCoalForge.class,LibBlocks.coalForge);
        GameRegistry.registerTileEntity(TileMetal.class,LibBlocks.metalBlank);
    }
}
