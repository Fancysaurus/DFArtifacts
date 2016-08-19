package tas.dfa.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tas.dfa.common.block.base.BaseBlock;
import tas.dfa.common.block.tile.TileMetal;
import tas.dfa.common.item.base.ModItems;
import tas.dfa.common.lib.LibBlocks;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by fancysaurus on 8/11/16.
 */
public class BlockMetal extends BaseBlock implements ITileEntityProvider
{
    public final AxisAlignedBB Boundry = new AxisAlignedBB(0.20,0,0.20,0.8,0.35,0.8);

    public BlockMetal()
    {
        super(LibBlocks.metalBlank, Material.IRON);
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
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return Boundry;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
        if(worldIn.getBlockState(pos.offset(EnumFacing.DOWN)).getBlock() != Blocks.ANVIL)
        {
            worldIn.destroyBlock(pos,true);
        }
        super.neighborChanged(state, worldIn, pos, blockIn);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote)
            return true;

        if(heldItem != null && heldItem.getItem() == ModItems.smithHammer)
        {
            if(worldIn.getTileEntity(pos) instanceof TileMetal)
                ((TileMetal)worldIn.getTileEntity(pos)).Strike(worldIn,playerIn);
        }

        return false;
    }

    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModItems.blank;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileMetal();
    }
}
