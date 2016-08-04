package tas.dfa.client.core.handler;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.item.base.IVariantHolder;
import tas.dfa.common.lib.LibResources;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class ModelHandler
{
    public static void preInit()
    {
        for(IVariantHolder holder : BaseItem.varaintHolders)
            registerModel(holder);
    }

    public static void registerModel(IVariantHolder holder)
    {
        ItemMeshDefinition def = holder.getCustomMeshDefinition();

        if(holder.getCustomMeshDefinition() != null)
            ModelLoader.setCustomMeshDefinition((Item)holder, def);
        else
            registerModel((Item)holder,holder.getVariants());

    }

    public static void registerModel(Item item,String[] variants)
    {
        for(int i = 0; i < variants.length; i++)
        {
            String name = LibResources.PREFIX+variants[i];
            ModelResourceLocation loc = new ModelResourceLocation(name,"inventory");
            System.out.println(loc);
            ModelLoader.setCustomModelResourceLocation(item,i,loc);
        }
    }
}
