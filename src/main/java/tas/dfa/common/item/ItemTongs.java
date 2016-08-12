package tas.dfa.common.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.item.base.ModItems;
import tas.dfa.common.lib.LibItems;

/**
 * Created by fancysaurus on 8/10/16.
 */
public class ItemTongs extends BaseItem
{

    public ItemTongs()
    {
        super(LibItems.tongs,LibItems.tongsEmpty,LibItems.tongsFull);
        setMaxStackSize(1);

    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote)
            return EnumActionResult.SUCCESS;

        if(playerIn.isSneaking() && stack.getItemDamage() == 1)
        {
            EntityItem blank = new EntityItem(worldIn,pos.getX()+0.5D,pos.offset(EnumFacing.UP).getY()+1,pos.getZ()+0.5D,new ItemStack(ModItems.blank,1));
            blank.setVelocity(0,0,0);
            blank.setPickupDelay(25);

            worldIn.spawnEntityInWorld(blank);
            stack.setItemDamage(0);
        }

        if(stack.getItemDamage() == 0)
        {
            if(worldIn.getBlockState(pos.offset(facing)).getBlock() == Blocks.LAVA)
            {
                //Check the players hotbar for Iron
                if(playerIn != null)
                    for(int i = 0; i < playerIn.inventory.getSizeInventory(); i++)
                    {
                        if(playerIn.inventory.getStackInSlot(i) != null && playerIn.inventory.getStackInSlot(i).getItem() == Items.IRON_INGOT)
                        {
                            ItemStack stackAt = playerIn.inventory.getStackInSlot(i);

                            if(!playerIn.capabilities.isCreativeMode)
                            {
                                stackAt.stackSize--;
                                if(stackAt.stackSize == 0)
                                    playerIn.inventory.setInventorySlotContents(i,null);
                            }

                            stack.setItemDamage(1);
                            worldIn.playSound(null,pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.PLAYERS,1F,1F);
                            return EnumActionResult.SUCCESS;
                        }
                    }
            }
        }


        return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
}
