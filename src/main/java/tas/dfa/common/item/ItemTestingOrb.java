package tas.dfa.common.item;

import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.lib.LibItems;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class ItemTestingOrb extends BaseItem
{

    public ItemTestingOrb()
    {
        super(LibItems.testOrb);
        setNoRepair();
        setMaxStackSize(1);
    }
}
