package tas.dfa.client.core.handler;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import tas.dfa.common.block.base.IDFABlock;
import tas.dfa.common.item.base.BaseItem;
import tas.dfa.common.item.base.IVariantHolder;
import tas.dfa.common.lib.LibMisc;
import tas.dfa.common.lib.LibResources;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class ModelHandler
{
    public static void preInit()
    {

        OBJLoader.INSTANCE.addDomain(LibMisc.MOD_ID);

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

        if(item instanceof ItemBlock && ((ItemBlock)item).getBlock() instanceof IDFABlock)
        {
            IDFABlock block = (IDFABlock) ((ItemBlock)item).getBlock();
            Class clazz = block.getVariantEnum();

            IProperty[] ignored = block.getIgnoredProperties();
            if(ignored != null  && ignored.length > 0)
            {
                StateMap.Builder builder = new StateMap.Builder();
                for(IProperty p : ignored)
                    builder.ignore(p);

                ModelLoader.setCustomStateMapper((Block)block,builder.build());
            }

            if(clazz != null)
            {
                //TODO:register the variants
                return;
            }



        }

        for(int i = 0; i < variants.length; i++)
        {
            String name = LibResources.PREFIX+variants[i];
            ModelResourceLocation loc = new ModelResourceLocation(name,"inventory");
            System.out.println(loc);
            ModelLoader.setCustomModelResourceLocation(item,i,loc);
        }
    }
}
