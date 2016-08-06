package tas.dfa.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import tas.dfa.common.block.base.BaseBlock;
import tas.dfa.common.block.tile.TileAncientAnvil;
import tas.dfa.common.lib.LibBlocks;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class BlockAncientAnvil extends BaseBlock implements ITileEntityProvider{
    public BlockAncientAnvil()
    {
        super(LibBlocks.ancientAnvil,Material.ANVIL);
        setSoundType(SoundType.ANVIL);
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

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAncientAnvil();
    }
}
