package tas.dfa.common.block.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import tas.dfa.Api.Config;
import tas.dfa.common.block.tile.base.BaseTile;

import java.util.List;
import java.util.Random;

/**
 * Created by David on 8/4/2016.
 */
public class TileAncientAnvil extends BaseTile implements ITickable {

    @Override
    public void update() {
        // Only handle item consumption on the server!
        if(worldObj.isRemote) return;

        List<EntityItem> items = worldObj.getEntitiesWithinAABB(
                EntityItem.class,
                new AxisAlignedBB(
                        pos,
                        pos.add(1, 2, 1)));

        for(EntityItem item : items) {
            ItemStack stack = item.getEntityItem();

            if(getIsWaitingForBaseItems()){
                if(!Config.instance.isValidBaseItem(stack))
                    continue;
                consumeItem(item);
                setIsWaitingForBaseItems(false);
            }
            else {
                if(!Config.instance.isValidDrawItem(stack))
                    continue;
                consumeItem(item);
                doDraw();
            }
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        // TODO: ?
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        // TODO: ?
    }

    public boolean getIsWaitingForBaseItems() {
        // TODO: Check NBT data
        return false;
    }

    private void consumeItem(EntityItem entity) {
        ItemStack stack = entity.getEntityItem();
        stack.stackSize--;
        if(stack.stackSize == 0)
            entity.setDead();
        else
            entity.setEntityItemStack(stack);
    }

    private void setIsWaitingForBaseItems(boolean value) {
        // TODO: Set NBT data
    }

    private void doDraw() {
        Random rng = new Random();

        // TODO: Add random value to score
        // TODO: Check for overflow of score
    }

    public void activate(EntityPlayer playerIn) {
        // TODO: Check current data and either punish the player or give them an artifact
    }
}
