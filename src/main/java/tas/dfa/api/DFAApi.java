package tas.dfa.api;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by fancysaurus on 8/5/16.
 */
public class DFAApi
{
    private static List<Object> validDrawInputs = new ArrayList<Object>();

    public static void registerDrawInput(String input)
    {
        if(OreDictionary.doesOreNameExist(input))
        {
            validDrawInputs.add(input);
        }
        else
            System.out.println("Unable to find item:"+input+" in OreDictionary!");
    }

    public static void registerDrawInput(Item input)
    {
        validDrawInputs.add(input);
    }

    public static boolean checkValidDrawInput(ItemStack stackIn)
    {
        for(Object o : validDrawInputs)
        {
            if(o instanceof String)
            {
                List<ItemStack> ores = OreDictionary.getOres((String) o, true);
                for(ItemStack ore : ores)
                {
                    if(ore.getItem() == stackIn.getItem() && ore.getItemDamage() == stackIn.getItemDamage())
                        return true;
                }
            }
            else if(o instanceof Item)
            {
                if(stackIn.getItem() == (Item) o)
                    return true;
            }
        }

        return false;
    }

    static
    {
        registerDrawInput(Items.REDSTONE);
        registerDrawInput(Items.GOLD_INGOT);
        registerDrawInput(Items.CHORUS_FRUIT);
        registerDrawInput(Items.NETHER_STAR);
        registerDrawInput(Items.PRISMARINE_SHARD);
        registerDrawInput(Items.DIAMOND);
        registerDrawInput(Items.EMERALD);
        registerDrawInput("gemLapis");
    }
}
