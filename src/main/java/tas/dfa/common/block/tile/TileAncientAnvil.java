package tas.dfa.common.block.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
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

    private int currentDrawValue = 0;
    private boolean isWaitingForBaseItem = true;

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

            if(isWaitingForBaseItem){
                if(!Config.instance.isValidBaseItem(stack))
                    continue;
                consumeItem(item);
                isWaitingForBaseItem = false;
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
            isWaitingForBaseItem = tag.hasKey("isWaitingForBaseItem")
                    ? tag.getBoolean("isWaitingForBaseItem")
                    : true;

            currentDrawValue = tag.hasKey("currentDrawValue")
                    ? tag.getInteger("currentDrawValue")
                    : 0;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        tag.setBoolean("isWaitingForBaseItem", isWaitingForBaseItem);
        tag.setInteger("currentDrawValue", currentDrawValue);
    }

    public void activate(EntityPlayer playerIn) {
        if(currentDrawValue > 21)
            ; // TODO: Punish the greedy player!
        else
            ; // TODO: Reward player with an artifact!

        reset();
    }

    public void reset() {
        isWaitingForBaseItem = false;
        currentDrawValue = 0;
        markDirty();
    }

    private void consumeItem(EntityItem entity) {
        ItemStack stack = entity.getEntityItem();
        stack.stackSize--;
        if(stack.stackSize == 0)
            entity.setDead();
        else
            entity.setEntityItemStack(stack);
    }

    private void doDraw() {
        Random rng = new Random();

        int value = rng.nextInt(13) + 1;
        if(value > 10) value = 10;

        currentDrawValue += value;
    }
}
