package tas.dfa.common.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tas.dfa.common.block.base.BaseBlock;
import tas.dfa.common.block.tile.TileCoalForge;
import tas.dfa.common.lib.LibBlocks;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by fancysaurus on 8/10/16.
 */
public class BlockCoalForge extends BaseBlock implements ITileEntityProvider
{
    public BlockCoalForge()
    {
        super(LibBlocks.coalForge, Material.ROCK);
        setTickRandomly(true);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        switch(rand.nextInt(5))
        {
            case 1:
                worldIn.spawnParticle(EnumParticleTypes.FLAME,pos.getX()+0.5F,pos.getY()+0.5F,pos.getZ()+0.5F,0,0.01D,0);
                break;

            case 2:
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,pos.getX()+0.5D,pos.getY(),pos.getZ()+0.5D,0,0.1D,0);
                break;

            default:
                break;
        }

        super.randomDisplayTick(stateIn, worldIn, pos, rand);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote)
            return true;

        TileEntity forge = worldIn.getTileEntity(pos);
        if(forge instanceof TileCoalForge)
            return ((TileCoalForge)forge).onBlockActivated(playerIn, heldItem);
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileCoalForge();
    }
}
