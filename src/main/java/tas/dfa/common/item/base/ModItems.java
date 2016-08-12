package tas.dfa.common.item.base;

import tas.dfa.common.item.ItemBlank;
import tas.dfa.common.item.ItemSmithHammer;
import tas.dfa.common.item.ItemSoulWater;
import tas.dfa.common.item.ItemTongs;
import tas.dfa.common.item.tool.ItemAncientHammer;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class ModItems
{
    //public static BaseItem testOrb;
    public static BaseItem ancientHammer;
    public static BaseItem soulWater;
    public static BaseItem tongs;
    public static BaseItem smithHammer;
    public static BaseItem blank;


    public static void init()
    {
        //testOrb = new ItemTestingOrb();
        ancientHammer = new ItemAncientHammer();
        soulWater = new ItemSoulWater();
        tongs = new ItemTongs();
        blank = new ItemBlank();
        smithHammer = new ItemSmithHammer();
    }
}
