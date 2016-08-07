package tas.dfa.common.block.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
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
                if (!Config.instance.isValidBaseItem(stack)) {
                    playerIn.addChatComponentMessage(new TextComponentString("It all starts with iron or diamond..."));
                    return false;
                }
                stack.stackSize--;
                isWaitingForBaseItem = false;
            } else {
                if (!Config.instance.isValidDrawItem(stack)) {
                    playerIn.addChatComponentMessage(new TextComponentString("It takes magic to continue!"));
                    return false;
                }
                stack.stackSize--;
                doDraw(playerIn);
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

    private void doDraw(EntityPlayer playerIn) {
        Random rng = new Random();

        int value = rng.nextInt(3) + 1;

        currentDrawValue += value;
        if(currentDrawValue > 21) {
            playerIn.addChatMessage(new TextComponentString("You have gone too far!"));

            PotionEffect effect = Config.instance.generateFailureDebuff();
            if(effect != null) playerIn.addPotionEffect(effect);
            reset();
        }
        else if(currentDrawValue == 21) {
            playerIn.addChatComponentMessage(new TextComponentString("Perfect! Take your prize!"));
        }
        else {
            markDirty();
        }
    }

    private void finish(EntityPlayer playerIn) {
        if(isWaitingForBaseItem) return;

        if(currentDrawValue > 21) {
            playerIn.addChatMessage(new TextComponentString("You have gone too far!"));

            PotionEffect effect = Config.instance.generateFailureDebuff();
            if(effect != null) playerIn.addPotionEffect(effect);
        }
        else {
            ItemStack stack = Config.instance.generateArtifactItem(currentDrawValue);

            if(stack != null) {
                playerIn.addChatComponentMessage(
                        new TextComponentString(
                                "You have crafted the " + stack.getDisplayName()));
                playerIn.inventory.addItemStackToInventory(stack);
            }
        }

        reset();
    }
}
