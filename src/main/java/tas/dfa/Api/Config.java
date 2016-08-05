package tas.dfa.Api;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by David on 8/4/2016.
 */
public class Config
{
    // TODO: Post-Jam support - rework this into a config file so end users can modify its contents

    public boolean isValidDrawItem(ItemStack stack)
    {
        Item item = stack.getItem();

        if(item == Items.GOLD_INGOT) return true;
        if(item == Items.GHAST_TEAR) return true;
        if(item == Items.BLAZE_POWDER) return true;
        if(item == Items.ENDER_PEARL) return true;
        if(item == Items.REDSTONE)
        if(item == Items.DYE)
        {
            // Check that we're talking about a piece of Lapis Lazuli
            if(stack.getItemDamage() == 4) return true;
        }

        // Fall through = no-go
        return false;
    }
}
