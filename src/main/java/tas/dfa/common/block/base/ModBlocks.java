package tas.dfa.common.block.base;

import tas.dfa.common.block.BlockAncientAnvil;
import tas.dfa.common.block.BlockPool;
import tas.dfa.common.block.testBlock;

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
    }
}
