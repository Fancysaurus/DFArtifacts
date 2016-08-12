package tas.dfa.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.lib.LibItems;

/**
 * Created by fancysaurus on 8/10/16.
 */
public class ItemBlank extends BaseItem {
    public ItemBlank()
    {
        super(LibItems.blank);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        if(entityIn.isImmuneToFire() || entityIn instanceof EntityPlayer && ((EntityPlayer)entityIn).capabilities.isCreativeMode)
            return;

        entityIn.setFire(2);

        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
    }
}
