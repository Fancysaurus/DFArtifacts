package tas.dfa.common.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import tas.dfa.common.lib.LibMisc;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class DFACreativeTab extends CreativeTabs
{
    public static DFACreativeTab INSTANCE = new DFACreativeTab();

    public DFACreativeTab() {
        super(LibMisc.MOD_ID);
    }

    @Override
    public Item getTabIconItem()
    {
        return Items.BOOK;
    }
}
