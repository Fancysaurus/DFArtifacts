package tas.dfa.common.block.base;

import net.minecraft.block.properties.IProperty;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import tas.dfa.common.item.base.IVariantHolder;

/**
 * Created by fancysaurus on 8/4/16.
 */
public interface IDFABlock extends IVariantHolder, IVariantEnumHolder
{
    String getBareName();
    IProperty[] getIgnoredProperties();

    EnumRarity getBlockRarity(ItemStack stack);

}
