package tas.dfa.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tas.dfa.common.block.base.BaseBlock;
import tas.dfa.common.block.tile.TilePool;
import tas.dfa.common.lib.LibBlocks;

import javax.annotation.Nullable;

/**
 * Created by fancysaurus on 8/6/16.
 */
public class BlockPool extends BaseBlock implements ITileEntityProvider
{
    public BlockPool() {
        super(LibBlocks.pool,Material.ROCK);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity pool = worldIn.getTileEntity(pos);
        if(pool instanceof TilePool)
        {
            ((TilePool)pool).onActivated(worldIn,pos,playerIn,heldItem);
            return true;
        }
        return false;
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
        return new TilePool();
    }
}
