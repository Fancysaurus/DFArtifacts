package tas.dfa.common.block.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import tas.dfa.Api.Config;
import tas.dfa.common.block.tile.base.BaseTile;

import java.util.List;
import java.util.Random;

/**
 * Created by David on 8/4/2016.
 */
public class TileAncientAnvil extends BaseTile {

    private int currentDrawValue = 0;
    private boolean isWaitingForBaseItem = true;

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

    public boolean activate(EntityPlayer playerIn) {
        ItemStack stack = playerIn.getHeldItemMainhand();

        if(stack == null) {
            finish(playerIn);
        }
        else {
            if (isWaitingForBaseItem) {
                if (!Config.instance.isValidBaseItem(stack)) return false;
                stack.stackSize--;
                isWaitingForBaseItem = false;
            } else {
                if (!Config.instance.isValidDrawItem(stack)) return false;
                stack.stackSize--;
                doDraw();
            }

            if (stack.stackSize == 0)
                playerIn.setHeldItem(EnumHand.MAIN_HAND, null);
        }

        return true;
    }

    private void reset() {
        isWaitingForBaseItem = true;
        currentDrawValue = 0;
        markDirty();
    }

    private void doDraw() {
        Random rng = new Random();

        int value = rng.nextInt(13) + 1;
        if(value > 10) value = 10;

        currentDrawValue += value;
        markDirty();
    }

    private void finish(EntityPlayer playerIn) {
        if(isWaitingForBaseItem) return;

        if(currentDrawValue > 21)
            ; // TODO: Punish the greedy player!
        else
            ; // TODO: Reward player with an artifact!

        reset();
    }
}
