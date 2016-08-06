package tas.dfa.common.block.tile.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;

/**
 * Created by fancysaurus on 8/5/16.
 */
public abstract class TileSimpleInventory extends BaseTile implements ISidedInventory
{
    protected ItemStack[] inventorySlots = new ItemStack[getSizeInventory()];

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return false;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return false;
    }

    @Override
    public abstract int getSizeInventory();

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index)
    {
        return inventorySlots[index];
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        if(inventorySlots[index] != null)
        {
            ItemStack stackAt;
            if(inventorySlots[index].stackSize <= count)
            {
                stackAt = inventorySlots[index];
                inventorySlots[index] = null;
                inventoryChanged(index);
                return stackAt;
            }
        }

        return null;
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {

    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound)
    {
        NBTTagList items = new NBTTagList();
        for(int i = 0; i < inventorySlots.length; i++)
        {
            if(inventorySlots[i] != null)
            {
                NBTTagCompound itemSlot = new NBTTagCompound();
                itemSlot.setByte("Slot",(byte)i);
                inventorySlots[i].writeToNBT(itemSlot);
                items.appendTag(itemSlot);
            }
        }
        compound.setTag("Items",items);
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound)
    {
        NBTTagList items = compound.getTagList("Items", 10);
        inventorySlots = new ItemStack[getSizeInventory()];
        for(int i = 0; i < items.tagCount(); i++)
        {
            NBTTagCompound itemSlot = items.getCompoundTagAt(i);
            byte slot = itemSlot.getByte("Slot");
            if(slot >= 0 && slot < inventorySlots.length)
                inventorySlots[slot] = ItemStack.loadItemStackFromNBT(itemSlot);
        }
    }

    public void inventoryChanged(int index)
    {
        //No-Op//
    }
}
