package tas.dfa.common.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.lib.LibItems;

/**
 * Created by fancysaurus on 8/6/16.
 */
public class ItemSoulWater extends BaseItem
{
    public ItemSoulWater()
    {
        super(LibItems.soulWater);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.UNCOMMON;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
