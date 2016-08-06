package tas.dfa.common.item.base;

import tas.dfa.common.item.ItemSoulWater;
import tas.dfa.common.item.ItemTestingOrb;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class ModItems
{
    public static BaseItem testOrb;
    public static BaseItem soulWater;

    public static void init()
    {
        testOrb = new ItemTestingOrb();
        soulWater = new ItemSoulWater();
    }
}
