package tas.dfa.common.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tas.dfa.common.block.base.ModBlocks;
import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.item.base.ModItems;
import tas.dfa.common.lib.LibItems;

import java.util.List;

/**
 * Created by fancysaurus on 8/11/16.
 */
public class ItemSmithHammer extends BaseItem
{
    public ItemSmithHammer() {
        super(LibItems.smithHammer);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.getBlockState(pos).getBlock() == ModBlocks.ancientAnvil)
        {
            worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1F, 1F);
            return EnumActionResult.SUCCESS;
        }

        if(worldIn.getBlockState(pos).getBlock() == Blocks.ANVIL)
        {
            AxisAlignedBB aABB = new AxisAlignedBB(pos.offset(EnumFacing.UP));
            List<EntityItem> items = worldIn.getEntitiesWithinAABB(EntityItem.class,aABB);
            for(EntityItem item : items)
            {
                if(item.getEntityItem() != null && item.getEntityItem().getItem() == ModItems.blank)
                {
                    item.setDead();
                    //TODO: NBT Reading from itemStack//
                    worldIn.setBlockState(pos.offset(EnumFacing.UP),ModBlocks.Metal.getDefaultState());
                    worldIn.playSound(null,pos.getX(),pos.getY(),pos.getZ(),SoundEvents.BLOCK_LAVA_EXTINGUISH,SoundCategory.BLOCKS,1F,1F);
                }
            }

        }

        return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
