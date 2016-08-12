package tas.dfa.common.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ITickable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.items.ItemStackHandler;
import tas.dfa.api.network.VanillaPacketDispatcher;
import tas.dfa.common.block.tile.base.TileSimpleInventory;
import tas.dfa.common.item.base.ModItems;

/**
 * Created by fancysaurus on 8/10/16.
 */
public class TileCoalForge extends TileSimpleInventory implements ITickable
{
    public int[] metalHeat = new int[getSizeInventory()];
    public final int MAX_METAL_HEAT = 100;

    public boolean onBlockActivated(EntityPlayer player, ItemStack stackIn)
    {
        if(stackIn == null || stackIn.getItem() == null)
            return false;

        boolean did = false;

        if(stackIn.getItem() == Items.IRON_INGOT)
        {

            //Find the first avalible slot//
            for (int i = 0; i < getSizeInventory(); i++)
            {
                if(itemHandler.getStackInSlot(i) == null)
                {
                    did = true;
                    ItemStack stackToAdd = stackIn.copy();
                    stackToAdd.stackSize = 1;
                    itemHandler.setStackInSlot(i,stackToAdd);

                    if(player == null || !player.capabilities.isCreativeMode)
                    {
                        stackIn.stackSize--;
                        if(stackIn.stackSize == 0 && player != null)
                            player.inventory.setInventorySlotContents(player.inventory.currentItem,null);
                    }
                    break;
                }

            }
        }

        if(stackIn.getItem() == ModItems.tongs && stackIn.getItemDamage() == 0)
        {
            for(int i = 0; i< getSizeInventory(); i++)
            {
                if (itemHandler.getStackInSlot(i)!= null && itemHandler.getStackInSlot(i).getItem() == ModItems.blank)
                {
                    did = true;
                    itemHandler.setStackInSlot(i,null);
                    stackIn.setItemDamage(1);
                }
            }
        }

        if(did)
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);

        return false;
    }

    @Override
    public ItemStackHandler createItemHandler() {
        return new SimpleItemStackHandler(this,false)
        {
            @Override
            protected int getStackLimit(int slot, ItemStack stack) {
                return 1;
            }

            @Override
            public ItemStack getStackInSlot(int slot)
            {
                metalHeat[slot] = -1;
                return super.getStackInSlot(slot);
            }

            @Override
            public void setStackInSlot(int slot, ItemStack stack) {
                metalHeat[slot] = 0;
                super.setStackInSlot(slot, stack);
            }
        };
    }

    @Override
    public int getSizeInventory() {
        return 8;
    }


    @Override
    public void writeCustomNBT(NBTTagCompound compound)
    {
        NBTTagList tagList = new NBTTagList();
        for(int i = 0; i < getSizeInventory(); i++)
        {
            NBTTagCompound metalData = new NBTTagCompound();
            metalData.setInteger("HEAT",metalHeat[i]);
            metalData.setInteger("SLOT",i);
            tagList.appendTag(metalData);
        }

        compound.setTag("METAL_DATA_LIST", tagList);
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound)
    {
        metalHeat = new int[getSizeInventory()];
        NBTTagList metalDataList = compound.getTagList("METAL_DATA_LIST",10);
        for(int i = 0;i< metalDataList.tagCount(); i++)
        {
            NBTTagCompound metalData = metalDataList.getCompoundTagAt(i);
            metalHeat[metalData.getInteger("SLOT")] = metalData.getInteger("HEAT");
        }
    }

    @Override
    public void update()
    {
        boolean shouldUpdate = false;
        for(int i = 0; i < getSizeInventory(); i++)
        {
            if(metalHeat[i] <= -1 || itemHandler.getStackInSlot(i) == null)
                continue;

            if(metalHeat[i] == MAX_METAL_HEAT)
            {
                //set the stack to Heated metal
                itemHandler.setStackInSlot(i,new ItemStack(ModItems.blank,1));
                worldObj.playSound(null,pos.getX(),pos.getY(),pos.getZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS,1F,1F);
                metalHeat[i] = -2;
            }

            metalHeat[i]++;
            shouldUpdate = true;
        }

        if(shouldUpdate)
            this.markDirty();
    }
}
