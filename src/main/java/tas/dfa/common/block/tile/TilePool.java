package tas.dfa.common.block.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import tas.dfa.common.block.tile.base.BaseTile;
import tas.dfa.common.item.base.ModItems;

/**
 * Created by fancysaurus on 8/6/16.
 */
public class TilePool extends BaseTile
{

    public void onActivated(World world, BlockPos pos, EntityPlayer player, ItemStack heldItem)
    {
        if(world.isRemote)
            return;

        if(heldItem.getItem() == Items.GLASS_BOTTLE)
        {
            if(player.experienceLevel >= 5)
            {
                EntityItem output = new EntityItem(world,pos.getX()+0.5,pos.getY()+1.5,pos.getZ()+0.5, new ItemStack(ModItems.soulWater,1));
                world.spawnEntityInWorld(output);
            }
            else
            {
                player.addChatComponentMessage(new TextComponentString("You are too inexperinced to do that!"));
            }
        }
        else
        {

        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {

    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {

    }
}
