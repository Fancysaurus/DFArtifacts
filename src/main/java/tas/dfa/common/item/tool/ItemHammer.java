package tas.dfa.common.item.tool;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.lib.LibItems;

/**
 * Created by fancysaurus on 8/8/16.
 */
public class ItemHammer extends BaseItem {
    public ItemHammer()
    {
        super(LibItems.hammerArtifact);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        return super.onEntitySwing(entityLiving, stack);
    }


}
