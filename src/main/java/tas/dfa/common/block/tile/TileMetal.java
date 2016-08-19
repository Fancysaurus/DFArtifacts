package tas.dfa.common.block.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tas.dfa.common.block.tile.base.BaseTile;
import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.item.base.ModItems;

/**
 * Created by fancysaurus on 8/11/16.
 */
public class TileMetal extends BaseTile
{
    public int progress = 0;
    public int heat = 500;
    public int HitsTillDone = 5;
    public ItemStack blankStack;

    public TileMetal()
    {
        blankStack = new ItemStack(ModItems.blank,1);
    }

    public void Strike(World worldIn, EntityPlayer playerIn)
    {
        progress++;
        worldIn.playSound(null,pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS,1F,1F);
        worldIn.spawnParticle(EnumParticleTypes.LAVA,pos.getX()+0.5D,pos.getY()+1.5D,pos.getZ()+0.5D,0,1,0);
        if(progress == HitsTillDone)
        {
            EntityItem sword = new EntityItem(worldIn,pos.getX()+0.5D,pos.getY()+1.5D,pos.getZ()+0.5D,new ItemStack(Items.IRON_SWORD));
            worldIn.spawnEntityInWorld(sword);
            worldIn.setBlockToAir(pos);
        }
    }



    @Override
    public void writeCustomNBT(NBTTagCompound compound)
    {
        compound.setInteger("progress",progress);
        compound.setInteger("heat", heat);
        compound.setInteger("htd",HitsTillDone);
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound)
    {
        progress = compound.getInteger("progress");
        heat = compound.getInteger("heat");
        HitsTillDone = compound.getInteger("htd");
    }
}
