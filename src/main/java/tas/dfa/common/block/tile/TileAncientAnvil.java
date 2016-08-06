package tas.dfa.common.block.tile;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
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
    public void update()
    {
        List<EntityItem> items = worldObj.getEntitiesWithinAABB(
                EntityItem.class,
                new AxisAlignedBB(
                        pos,
                        pos.add(1, 1, 1)));

        for(EntityItem item : items)
        {
            maybeConsumeItem(item);
        }
    }

    public boolean getIsWaitingForBaseItems()
    {
        // TODO: Check NBT data
        return false;
    }

    private void setIsWaitingForBaseItems(boolean value)
    {
        // TODO: Set NBT data
    }

    private void doDraw()
    {
        Random rng = new Random();

        // TODO: Add random value to score
        // TODO: Check for overflow of score
    }

    private boolean shouldConsumeItem(EntityItem item) {
        return false;
    }

    private void maybeConsumeItem(EntityItem item) {

        // Bail if we shouldn't eat this item
        if(!shouldConsumeItem(item)) return;

        // TODO: Consume the item
        // TODO: Adjust extended data appropriately
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {

    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {

    }
}
