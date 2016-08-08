package tas.dfa.common.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.asm.transformers.ItemStackTransformer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tas.dfa.common.block.base.ModBlocks;

/**
 * Created by fancysaurus on 8/8/16.
 */
public class ModRecipes
{
    public static void init()
    {
        addOreDictRecipe(new ItemStack(ModBlocks.pool),"SBS"," S ","SSS",'S',new ItemStack(Blocks.STONE).getItem(),'B', Items.WATER_BUCKET);
        
    }

    private static void addOreDictRecipe(ItemStack output,Object... recipe)
    {
        GameRegistry.addRecipe(output,recipe);
    }
}
