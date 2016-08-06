package tas.dfa.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import tas.dfa.common.block.base.BaseBlock;
import tas.dfa.common.lib.LibBlocks;

/**
 * Created by fancysaurus on 8/6/16.
 */
public class BlockPool extends BaseBlock
{
    public BlockPool() {
        super(LibBlocks.pool,Material.ROCK);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
