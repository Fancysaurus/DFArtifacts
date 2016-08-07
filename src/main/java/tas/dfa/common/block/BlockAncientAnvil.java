package tas.dfa.common.block;

import com.sun.istack.internal.Nullable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
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
        //this.setDefaultState(this.blockState.getBaseState());
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

    @Override
    public boolean onBlockActivated(
            World worldIn,
            BlockPos pos,
            IBlockState state,
            EntityPlayer playerIn, EnumHand hand,
            @Nullable ItemStack heldItem,
            EnumFacing side,
            float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote) return false;

        TileEntity entity = worldIn.getTileEntity(pos);
        TileAncientAnvil anvil =
                entity != null
                    && entity instanceof TileAncientAnvil
                        ? (TileAncientAnvil)entity
                        : null;
        if(anvil == null) return false;

        anvil.activate(playerIn);
        return true;
    }
}
