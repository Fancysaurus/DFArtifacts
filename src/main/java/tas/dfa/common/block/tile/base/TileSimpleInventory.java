package tas.dfa.common.block.tile.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * Created by fancysaurus on 8/5/16.
 */
public abstract class TileSimpleInventory extends BaseTile
{
    protected ItemStackHandler itemHandler = createItemHandler();

    public abstract int getSizeInventory();

    public ItemStackHandler createItemHandler()
    {
        return new SimpleItemStackHandler(this,true);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.merge(itemHandler.serializeNBT());
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        itemHandler = createItemHandler();
        itemHandler.deserializeNBT(compound);
        super.readFromNBT(compound);
    }

    public IItemHandlerModifiable getItemHandler()
    {
        return itemHandler;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability,facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemHandler);

        return super.getCapability(capability, facing);
    }

    protected static class SimpleItemStackHandler extends ItemStackHandler
    {
        private final boolean allowWrite;
        private final TileEntity tileEntity;

        public SimpleItemStackHandler(TileSimpleInventory simpleInventory,boolean allowWrite)
        {
            super(simpleInventory.getSizeInventory());
            this.allowWrite = allowWrite;
            this.tileEntity = simpleInventory;
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if(allowWrite)
                return super.insertItem(slot, stack, simulate);
            else
                return stack;
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if(allowWrite)
                return super.extractItem(slot, amount, simulate);
            else
                return null;
        }

        @Override
        protected void onContentsChanged(int slot) {
            tileEntity.markDirty();
        }
    }
}
