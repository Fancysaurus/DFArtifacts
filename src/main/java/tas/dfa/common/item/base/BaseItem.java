package tas.dfa.common.item.base;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tas.dfa.common.core.DFACreativeTab;
import tas.dfa.common.lib.LibResources;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fancysaurus on 8/4/16.
 */
public class BaseItem extends Item implements IVariantHolder
{

    public static final List<IVariantHolder> varaintHolders = new ArrayList<IVariantHolder>();

    private final String[] variants;
    private final String bareName;

    public BaseItem(String name, String... variants)
    {
        setUnlocalizedName(name);
        setCreativeTab(DFACreativeTab.INSTANCE);

        if(variants.length > 1)
            setHasSubtypes(true);

        if(variants.length == 0)
            variants = new String[]{name};

        bareName = name;
        this.variants = variants;

        varaintHolders.add(this);
    }

    @Override
    public Item setUnlocalizedName(String unlocalizedName) {
        super.setUnlocalizedName(unlocalizedName);
        GameRegistry.register(this,new ResourceLocation(LibResources.PREFIX + unlocalizedName));
        return this;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int dmg = stack.getItemDamage();
        String[] variants = getVariants();

        String name;

        if(dmg >= variants.length)
            name = bareName;
        else
            name = variants[dmg];

        return "item."+LibResources.PREFIX+name;
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for(int i =0; i < getVariants().length;i++)
            subItems.add(new ItemStack(itemIn,1,i));
    }

    @Override
    public String[] getVariants() {
        return variants;
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }
}
